package org.platanios.tensorflow.examples

import org.platanios.tensorflow.api.learn.TRAINING
import org.platanios.tensorflow.data.text.DatasetLoader
import org.platanios.tensorflow.api.{Tensor, _}
import org.platanios.tensorflow.api.ops.Basic
import org.platanios.tensorflow.api.learn.layers.rnn.cell.{BasicLSTMCell, LSTMTuple, MultiCell, RNNCell}
import org.platanios.tensorflow.api.ops.rnn.cell.{LSTMState, Tuple}
import org.platanios.tensorflow.examples.RnnExample.{batch_size, truncated_backprop_length}

import scala.reflect.ClassTag

object LSTM extends App {

   val dataset = DatasetLoader.loadDataset("/home/nazar/interests/dataset3_exported.bin", 0, 200000).filter(1, 90).suffle
  //val dataset = DatasetLoader.loadDataset("dataset3_exported_part.bin", 0, 10000).filter(1, 50)
  val dataset_test = dataset.slice(0, 1000)

  // DatasetLoader.save("dataset3_exported_part.bin", data._1.take(10000), data._2.take(10000))

  println(dataset.size)

  def generateData(n: Int): (Tensor, Tensor, Tensor) = {
    val data_n = dataset.slice(1000, dataset.size).suffle.slice(0, n)
    (data_n.getTensorX, data_n.getTensorY, data_n.getTensorMask)
  }

  def generateDataTest(n: Int): (Tensor, Tensor, Tensor) = {
    val data_n = dataset_test.suffle.slice(0, n)
    (data_n.getTensorX, data_n.getTensorY, data_n.getTensorMask)
  }

  val batch_size = 64
  val max_sent_len = dataset.max_sent_len
  val w2v_dim =  dataset.w2v_dim

  val X_place = tf.placeholder(FLOAT32, Shape(batch_size, max_sent_len, w2v_dim), "X")
  val Y_place = tf.placeholder(INT32, Shape(batch_size), "Y")
  // val Len_place = tf.placeholder(INT32, Shape(batch_size), "Len")
  val Mask_place = tf.placeholder(BOOLEAN, Shape(batch_size, max_sent_len), "Mask_place")


  val state_size = 500
  val num_classes = 130

  // val cell: RNNCell[Output, Shape, LSTMState, (Shape, Shape)] = BasicLSTMCell((w2v_dim + state_size, state_size), "lstm0")

  val multiCell = MultiCell("multiCell", Seq(
    BasicLSTMCell("lstm1", state_size, FLOAT32),
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

  object RNNOutputLayer extends tf.learn.Layer[Tuple[Output, Seq[LSTMState]], Output]("RNNOutputLayer") {
    override val layerType: String = "RNNOutputLayer"
    override def _forward(input: Tuple[Output, Seq[LSTMState]])(implicit mode: tf.learn.Mode): Output = {
      tf.booleanMask(input.output, Mask_place)
    }
  }


  val outputLayer = // tf.learn.Dropout("Layer_3/Drop", 0.5f) >>
    tf.learn.Linear("Layer_3/Linear", 300) >> tf.learn.ReLU("Layer_3/ReLU", 0.1f) >>
      // tf.learn.Linear("Layer_4/Linear", 200) >> tf.learn.ReLU("Layer_4/ReLU", 0.1f) >>
    //tf.learn.Linear("Layer_5/Linear", 80) >> tf.learn.ReLU("Layer_5/ReLU", 0.1f) >>
    tf.learn.Linear("Layer_6/Linear", num_classes)

  val model = {
    val input = tf.learn.Input(FLOAT32, Shape(-1, -1, -1))
    val inputMask = tf.learn.Input(BOOLEAN, Shape(-1, -1))
    val trainInput = tf.learn.Input(INT32, Shape(-1))

    val layer = (rnn concatenate MaskLayer) >> RNNOutputLayer >> outputLayer
    val loss = tf.learn.SparseSoftmaxCrossEntropy("Loss/CrossEntropy") >>
      tf.learn.Mean("Loss/Mean") >> tf.learn.ScalarSummary("Loss/Summary", "Loss")
    val optimizer = tf.train.RMSProp(0.001)

    tf.learn.Model.supervised(input, layer, trainInput, loss, optimizer, tf.learn.ClipGradientsByGlobalNorm(5.0f))
  }


  val logits = (rnn >> RNNOutputLayer >> outputLayer).forward(X_place)(TRAINING)

  val predictions = tf.argmax(logits, -1)

  val true_proc = tf.countNonZero(predictions - Y_place) / batch_size.toDouble

  val losses = tf.sparseSoftmaxCrossEntropy(logits, Y_place)

  val total_loss: Output = tf.sum(losses) / batch_size

  val train_step = tf.train.RMSProp(0.001).minimize(total_loss)

  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

  val total_series_length: Int = 640

  for(epoch <- 0 to dataset.size / total_series_length) {
    println("epoch=" + epoch)

    val (x, y, m) = generateData(total_series_length)
    println("generated")

    for (i <- 0 until (total_series_length / batch_size)) {

      val batchX = x.apply(i * batch_size :: (i * batch_size + batch_size), ::, ::)
      val batchY = y(i * batch_size :: (i * batch_size + batch_size))
      val batchM = m(i * batch_size :: (i * batch_size + batch_size))

      if(i == 0) println("TEST")
      println(s"True value: ${batchY.summarize()}")

      val feeds = Map(
        X_place -> batchX,
        Y_place -> batchY,
        Mask_place -> batchM
      )
      val fetches: Seq[Output] = Seq(total_loss, predictions, true_proc)

      val trainLoss = if (i == 0) {
        session.run(feeds = feeds, fetches = fetches)
      } else {
        session.run(feeds = feeds, fetches = fetches, targets = train_step)
      }

      println(trainLoss.head.scalar)

      println(s"Predicted value: ${trainLoss(1).summarize()}")

      println(s"Proc: ${trainLoss(2).scalar}")

      session.deleteNativeReferences()

      batchX.close()
      batchY.close()
      batchM.close()
      trainLoss.foreach(_.close())


    }

    x.close()
    y.close()
    m.close()


  }


}
