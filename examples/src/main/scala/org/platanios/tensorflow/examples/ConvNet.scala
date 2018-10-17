package org.platanios.tensorflow.examples

import java.io._
import java.nio.{ByteBuffer, ByteOrder}

import org.platanios.tensorflow.data.image.ImageByUrlLoader
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.learn.TRAINING
import org.platanios.tensorflow.api.learn.estimators.IterativeEstimator
import org.platanios.tensorflow.api.learn.layers.{Identity, Input, Layer}
import org.platanios.tensorflow.api.learn.models.TrainableModel
import org.platanios.tensorflow.api.ops.{Basic, NN}
import org.platanios.tensorflow.api.ops.NN.{CNNDataFormat, NWCFormat, SameConvPadding}
import org.platanios.tensorflow.api.ops.io.data.IterableDataset
import org.tensorflow.framework.TensorProto
import org.platanios.tensorflow.data.serializer.JavaGenericSerializer

import scala.reflect.ClassTag





object ConvNet extends App with JavaGenericSerializer {

  ImageByUrlLoader.load("http://atmyplace.ru/images/product_images/popup_images/TU0256_3.jpg")

//  val url2label: Array[(String, Int)] = scala.io.Source.fromFile("/home/nazar/interests/image2cat.txt").getLines().toArray.map(line => {
//    val url = line.split(",")(0)
//    val label = line.split(",")(1).toInt
//    (url, label)
//  })
//
//  val img2label: Array[(Array[Float], Int)] = url2label.par.flatMap({case(s, l) => {
//    println(s)
//    try {
//      Some((ImageByUrlLoader.load(s), l))
//    } catch {
//      case e: Exception => None
//    }
//  }}).toArray

  // genericSave(img2label, "img2label.bin")
  val img2label: Array[(Array[Float], Int)] = genericLoad("img2label.bin")
  val shuffled: Array[(Array[Float], Int)] = suffle(img2label)
  val train_data = shuffled.drop(1000)
  val test_data = shuffled.take(1000)
  println("train " + train_data.length)

  val image_size = 80

  def suffle[T: ClassTag](arr: IndexedSeq[T]): Array[T] = new scala.util.Random().shuffle(arr).toArray


  def toTensor(values: Array[Int]): Tensor = {
    Tensor.fromArrayInt(values)
  }

  def generateTrainData(from: Int, to: Int) = {
    val data = train_data.slice(from, to)
    (Tensor.fromArrayFloat(data.flatMap(_._1)).reshape(Shape(data.length, image_size, image_size, 1)), Tensor(data.map(_._2)).reshape(Shape(data.length)))
  }

  def generateTestData(n: Int) = {
    val data = test_data.take(n)
    (Tensor.fromArrayFloat(data.flatMap(_._1)).reshape(Shape(data.length, image_size, image_size, 1)), Tensor(data.map(_._2)).reshape(Shape(data.length)))
  }

  val batch_size = 100


  val model = {

    val X_place = Input(FLOAT32, Shape(batch_size, image_size, image_size, 1), "X")
    val Y_place = Input(INT32, Shape(batch_size), "Y")

    val layer =
      tf.learn.Conv2D("Layer_0/Conv2D", Shape(4, 4, 1, 30), 2, 2, SameConvPadding) >>
      tf.learn.AddBias("Layer_0/Bias") >>
      tf.learn.ReLU("Layer_0/ReLU", 0.1f) >>
      tf.learn.MaxPool("Layer_0/MaxPool" , Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
      tf.learn.Conv2D("Layer_1/Conv2D" , Shape(2, 2, 30, 42), 1, 1, SameConvPadding) >>
      tf.learn.AddBias("Bias_1" ) >>
      tf.learn.ReLU("Layer_1/ReLU" , 0.1f) >>
      tf.learn.MaxPool("Layer_1/MaxPool" , Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
        tf.learn.Conv2D("Layer_2/Conv2D" , Shape(2, 2, 42, 42), 1, 1, SameConvPadding) >>
        tf.learn.AddBias("Bias_2" ) >>
        tf.learn.ReLU("Layer_2/ReLU" , 0.1f) >>
        tf.learn.MaxPool("Layer_2/MaxPool" , Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
      tf.learn.Flatten("Layer_3/Flatten" ) >>
      tf.learn.Dropout("Layer_3/Drop" , 0.5f) >>
      tf.learn.Linear("Layer_3/Linear" , 556) >> tf.learn.ReLU("Layer_3/ReLU" , 0.1f) >>
        tf.learn.Dropout("Layer_4/Drop" , 0.5f) >>
      tf.learn.Linear("Layer_4/Linear" , 228) >> tf.learn.ReLU("Layer_4/ReLU" , 0.1f) >>
      tf.learn.Linear("OutputLayer/Linear" , 130)

    val loss = tf.learn.SparseSoftmaxCrossEntropy("Loss/CrossEntropy") >>
      tf.learn.Mean("Loss/Mean") // >> tf.learn.ScalarSummary("Loss/Summary", "Loss")

    val optimizer = tf.train.RMSProp(0.003)

    new TrainableModel(X_place, layer, Y_place, Identity[Output]("Iden"), loss, optimizer, tf.learn.ClipGradientsByGlobalNorm(1.0f))
  }



  val accMetric = tf.metrics.MapMetric(
    (v: (Output, Output)) => (v._1.argmax(-1), v._2), tf.metrics.Accuracy())


  val estimator = new IterativeEstimator(model, Seq(accMetric), false)


  val total_series_length: Int = 640

  println("test data")

  val (xt, yt) = generateTestData(total_series_length / 2)
  val datasetTest = new IterableDataset(model, Seq(xt, yt), batch_size)


  for(epoch <- 0 to 5 * train_data.size / total_series_length) {
    println("epoch=" + epoch)

    val from = (total_series_length * epoch) % (train_data.size - total_series_length)
    val (x, y) = generateTrainData(from, from + total_series_length)

    val dataset_train = new IterableDataset(model, Seq(x, y), batch_size)
    estimator.train(dataset_train)

    dataset_train.close()

    println("Test")
    val eval = estimator.evaluate(datasetTest)
    println(eval.head.scalar)
    eval.foreach(_.close())


  }


}
