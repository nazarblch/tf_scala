package org.platanios.tensorflow.examples

import java.nio.file.Paths

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO
import org.platanios.tensorflow.api.core.Graph
import org.platanios.tensorflow.api.core.client.FeedMap
import org.platanios.tensorflow.api.learn.estimators.IterativeEstimator
import org.platanios.tensorflow.api.learn.layers.{Identity, Input, Layer}
import org.platanios.tensorflow.data.text.DatasetLoader
import org.platanios.tensorflow.api.{Tensor, _}
import org.platanios.tensorflow.api.ops.{Basic, Math, Op}
import org.platanios.tensorflow.api.learn.layers.rnn.cell.{BasicLSTMCell, LSTMTuple, MultiCell, RNNCell}
import org.platanios.tensorflow.api.learn.models.TrainableModel
import org.platanios.tensorflow.api.ops.io.data.{Dataset, IterableDataset}
import org.platanios.tensorflow.api.ops.metrics.Metric
import org.platanios.tensorflow.api.ops.rnn.cell.{LSTMState, Tuple}
import org.platanios.tensorflow.api.ops.training.optimizers.Optimizer
import org.platanios.tensorflow.api.types.FLOAT32
import org.platanios.tensorflow.examples.RnnExample.{batch_size, truncated_backprop_length}

import scala.reflect.ClassTag



object LSTM extends App {

  val dataset = DatasetLoader.loadDataset("/home/nazar/interests/dataset3_exported.bin", 0, 400000).filter(1, 90).suffle
  // val dataset = DatasetLoader.loadDataset("dataset3_exported_part.bin", 0, 10000).filter(1, 50)
  val data_train = dataset.slice(10000, dataset.size)
  val data_test = dataset.slice(0, 10000)

  // DatasetLoader.save("dataset3_exported_part.bin", data._1.take(10000), data._2.take(10000))

  println(dataset.size)

  val batch_size = 64
  val max_sent_len = dataset.max_sent_len
  val w2v_dim =  dataset.w2v_dim

  def generateData(from: Int, to: Int): (Tensor, Tensor, Tensor) = {
    val data_n = data_train.suffle.slice(from, to)
    (data_n.getTensorX, data_n.getTensorY, data_n.getTensorMask)
  }

  def generateDataTest(n: Int): (Tensor, Tensor, Tensor) = {
    val data_n = data_test.slice(0, n)
    (data_n.getTensorX, data_n.getTensorY, data_n.getTensorMask)
  }


  println("model")

  val state_size = 500
  val num_classes = 130

  // val cell: RNNCell[Output, Shape, LSTMState, (Shape, Shape)] = BasicLSTMCell((w2v_dim + state_size, state_size), "lstm0")

  val multiCell = MultiCell("multiCell", Seq(
    BasicLSTMCell("lstm1", 2 * state_size, FLOAT32),
    BasicLSTMCell("lstm2", 2 * state_size, FLOAT32),
    //BasicLSTMCell("lstm3", 2 * state_size, FLOAT32),
    //BasicLSTMCell((2 * state_size, state_size), "lstm4"),
    //BasicLSTMCell((2 * state_size, state_size), "lstm5")
  ))

  val rnn = tf.learn.RNN("RNN", multiCell)

  object MaskLayer extends tf.learn.Layer[Output, Output]("MaskLayer") {
    override val layerType: String = "MaskLayer"
    override def _forward(input: Output)(implicit mode: tf.learn.Mode): Output = {
      input
    }
  }

  object RNNOutputLayer extends tf.learn.Layer[(Tuple[Output, Seq[LSTMState]], Output), Output]("RNNOutputLayer") {
    override val layerType: String = "RNNOutputLayer"
    override def _forward(input: (Tuple[Output, Seq[LSTMState]], Output))(implicit mode: tf.learn.Mode): Output = {
      tf.booleanMask(input._1.output, input._2)
    }
  }



  val outputLayer = // tf.learn.Softsign("Sign") >>
    //tf.learn.Dropout("Layer_3/Drop", 0.5f) >>
    tf.learn.Linear("Layer_3/Linear", 300) >> tf.learn.ReLU("Layer_3/ReLU", 0.1f) >>
      // tf.learn.Linear("Layer_4/Linear", 200) >> tf.learn.ReLU("Layer_4/ReLU", 0.1f) >>
    //tf.learn.Linear("Layer_5/Linear", 80) >> tf.learn.ReLU("Layer_5/ReLU", 0.1f) >>
    tf.learn.Linear("Layer_6/Linear", num_classes)


  val model = {

    val X_place = Input(FLOAT32, Shape(-1, max_sent_len, w2v_dim), "X")
    val Y_place = Input(INT32, Shape(-1), "Y")
    // val Len_place = tf.placeholder(INT32, Shape(batch_size), "Len")
    val Mask_place = Input(BOOLEAN, Shape(-1, max_sent_len), "Mask_place")

    val layer = (rnn pair MaskLayer)  >> RNNOutputLayer >> outputLayer

    val loss = tf.learn.SparseSoftmaxCrossEntropy("Loss/CrossEntropy") >>
      tf.learn.Mean("Loss/Mean") // >> tf.learn.ScalarSummary("Loss/Summary", "Loss")
    val pen = tf.learn.SigmoidCrossEntropyPenalty("Loss/Pen") >>
      tf.learn.Mean("Loss/Pen/Mean") // >> tf.learn.ScalarSummary("Loss/Pen/Summary", "Loss")

    val pen_loss = (loss + (pen * 0.01)) >> tf.learn.ScalarSummary("Loss/Summary", "Loss")

    val optimizer = tf.train.RMSProp(0.001)

     new TrainableModel(X_place.zip(Mask_place), layer, Y_place, Identity[Output]("Iden"), pen_loss, optimizer, tf.learn.ClipGradientsByGlobalNorm(1.0f))
  }



  val accMetric = tf.metrics.MapMetric(
    (v: (Output, Output)) => (v._1.argmax(-1), v._2), tf.metrics.Accuracy())


  val estimator = new IterativeEstimator(model, Seq(accMetric), gpuAllowMemoryGrowth = true)


  val total_series_length: Int = 640

  println("test data")

  val (xt, yt, mt) = generateDataTest(total_series_length / 2)
  val datasetTest = new IterableDataset(model, Seq(xt, mt, yt), batch_size)


  for(epoch <- 0 to 2 * dataset.size / total_series_length) {
    println("epoch=" + epoch)

    val from = (total_series_length * epoch) % (dataset.size - total_series_length)
    val (x, y, m) = generateData(from, from + total_series_length)

    val dataset_train = new IterableDataset(model, Seq(x, m, y), batch_size)
    estimator.train(dataset_train)

    dataset_train.close()

    println("Test")
    val eval = estimator.evaluate(datasetTest)
    println(eval.head.scalar)
    eval.foreach(_.close())


  }


}
