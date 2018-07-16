package org.platanios.tensorflow.examples

import java.io._
import java.nio.{ByteBuffer, ByteOrder}

import org.platanios.tensorflow.data.image.ImageByUrlLoader
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.learn.TRAINING
import org.platanios.tensorflow.api.learn.layers.Layer
import org.platanios.tensorflow.api.ops.{Basic, NN}
import org.platanios.tensorflow.api.ops.NN.{CNNDataFormat, NWCFormat, SameConvPadding}
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
  println("saved")

  val image_size = 80

  def suffle[T: ClassTag](arr: IndexedSeq[T]): Array[T] = new scala.util.Random().shuffle(arr).toArray


  def toTensor(values: Array[Int]): Tensor = {
    Tensor.fromArrayInt(values)
  }

  def generateData(n: Int) = {
    val shuffled: Array[(Array[Float], Int)] = suffle(img2label)
    val x: Array[Int] = shuffled.take(n).flatMap(_._1).map(_.toInt)
//    val resx = Tensor.fromArrayInt(x).reshape(Shape(n, image_size, image_size, 1))
//    val im = ImageByUrlLoader.tensorToImage(resx(10, ::, ::, ::).reshape(Shape(1, image_size, image_size, 1)))
//    ImageByUrlLoader.displayImage(im)

    (Tensor.fromArrayInt(x).reshape(Shape(n, image_size, image_size, 1)), Tensor(shuffled.take(n).map(_._2)).reshape(Shape(n)))
  }

  val batch_size = 100


  val batchX_placeholder = tf.placeholder(INT32, Shape(batch_size, image_size, image_size, 1))
  val batchY_placeholder = tf.placeholder(INT32, Shape(batch_size))

  def creat_net(name: String, outSize: Int) = {
    tf.learn.Cast("Input/Cast" + name, FLOAT32) >>
    tf.learn.Conv2D("Layer_0/Conv2D"  + name, Shape(3, 3, 1, 20), 2, 2, SameConvPadding) >>
    tf.learn.AddBias("Layer_0/Bias"  + name) >>
    tf.learn.ReLU("Layer_0/ReLU"  + name, 0.1f) >>
    tf.learn.MaxPool("Layer_0/MaxPool"  + name, Seq(1, 3, 3, 1), 1, 1, SameConvPadding) >>
    tf.learn.Conv2D("Layer_1/Conv2D"  + name, Shape(2, 2, 20, 32), 1, 1, SameConvPadding) >>
    tf.learn.AddBias("Bias_1"  + name) >>
    tf.learn.ReLU("Layer_1/ReLU"  + name, 0.1f) >>
    tf.learn.MaxPool("Layer_1/MaxPool"  + name, Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
    tf.learn.Flatten("Layer_3/Flatten"  + name) >>
    tf.learn.Dropout("Layer_3/Drop"  + name, 0.5f) >>
    tf.learn.Linear("Layer_3/Linear"  + name, 256) >> tf.learn.ReLU("Layer_3/ReLU"  + name, 0.1f) >>
    tf.learn.Linear("Layer_4/Linear"  + name, 128) >> tf.learn.ReLU("Layer_4/ReLU"  + name, 0.1f) >>
    tf.learn.Linear("OutputLayer/Linear"  + name, outSize)  >> tf.learn.ReLU("Layer_5/ReLU"  + name, 0.1f)
  }

//
//  val weights = tf.variable("Weights1", FLOAT32, Shape(3, 3, 1, 5))
//  val conv1 = NN.conv2D(batchX_placeholder, weights, 1, 1,  SameConvPadding, NWCFormat, (1, 1, 1, 1), true)
//  val relu1 = NN.relu(conv1, 0.1f, "relu1")
//  val flatten = relu1.reshape(Shape(batch_size, -1))
//  val linear = NN.linear(flatten, tf.variable("W2", FLOAT32, Shape(flatten.shape(1), 130)))

  val layer1 = creat_net("_1", 10)
  val layer2 = creat_net("_2", 10)
  val layer3 = creat_net("_3", 10)
  val layer4 = creat_net("_4", 10)
  val layer5 = creat_net("_5", 10)
  val layer6 = creat_net("_6", 10)

  val out1234 = tf.concatenate(Seq(
    layer1.forward(batchX_placeholder)(TRAINING),
    layer2.forward(batchX_placeholder)(TRAINING),
    layer3.forward(batchX_placeholder)(TRAINING),
    layer4.forward(batchX_placeholder)(TRAINING),
    layer5.forward(batchX_placeholder)(TRAINING),
    layer6.forward(batchX_placeholder)(TRAINING)
  ), 1)

  val out1 = NN.linear(out1234, tf.variable("W1", FLOAT32, Shape(out1234.shape(1), 80)))
  val relu1 = NN.relu(out1, 0.1f, "relu1")
  val out = NN.linear(relu1, tf.variable("W2", FLOAT32, Shape(out1.shape(1), 130)))

  val predictions: Output = tf.argmax(out, -1)

  val losses = tf.sparseSoftmaxCrossEntropy(out, batchY_placeholder)
  val total_loss: Output = tf.sum(losses) / batch_size

  val train_step = tf.train.AdaGrad(0.1).minimize(total_loss)

  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

  val total_series_length: Int = 1000

  for(epoch <- 0 to 10000) {
    println("epoch=" + epoch)

    val (x,y) = generateData(total_series_length)
    println("generated")

    for (i <- 0 until (total_series_length /  batch_size)) {

      val batchX = x(i * batch_size :: (i * batch_size + batch_size))
      val batchY = y(i * batch_size :: (i * batch_size + batch_size))

      println(s"True value: ${batchY.summarize()}")

      val feeds = Map(
        batchX_placeholder -> batchX,
        batchY_placeholder -> batchY
      )
      val fetches: Seq[Output] = Seq(total_loss, predictions)
      val trainLoss = session.run(feeds = feeds, fetches = fetches, targets = train_step)

      println(trainLoss.head.scalar)

      println(s"Predicted value: ${trainLoss(1).summarize()}")

      session.deleteNativeReferences()


    }

    x.close()
    y.close()


  }


}
