package org.platanios.tensorflow.examples

import java.nio.file.Paths

import org.platanios.tensorflow.api.learn.{ClipGradientsByGlobalNorm, TRAINING}

import scala.util.Random
import org.platanios.tensorflow.api.{Tensor, _}
import org.platanios.tensorflow.api.ops.Basic
import org.platanios.tensorflow.api.learn.layers.rnn.cell.{BasicLSTMCell, LSTMTuple, MultiCell, RNNCell}
import org.platanios.tensorflow.api.ops.rnn.cell.{LSTMState, Tuple}
import org.platanios.tensorflow.examples.rnn.RNN

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

object RnnExample extends App {

  val echo_step = 4
  val batch_size = 50
  val total_series_length: Int = 5000000

  def circ[A: ClassTag]( L: Array[A], times: Int ): Array[A] = {
    if ( times == 0 || L.size < 2 ) L
    else circ(Array(L.last) ++ L.dropRight(1), times-1)
  }

  val truncated_backprop_length = 100

  def generateData(n : Int = total_series_length): (Tensor, Tensor) = {
    val r = new Random()
    val x: Array[Int] = Array.fill(total_series_length)(r.nextInt(2))
    val y  = circ(x, echo_step)

    (Tensor.fromArrayFloat(x.map(_.toFloat)).reshape(Shape(-1, truncated_backprop_length)), Tensor.fromArrayInt(y).reshape(Shape(-1, truncated_backprop_length)).slice(::, truncated_backprop_length - 1))
  }


  val batchX_placeholder = tf.placeholder(FLOAT32, Shape(batch_size, truncated_backprop_length))
  val batchY_placeholder = tf.placeholder(INT32, Shape(batch_size))

  val state_size = 30
  val num_classes = 2



  // val cell: RNNCell[Output, Shape, Output, Shape] = BasicRNNCell(W, b)
  // val cell: RNNCell[Output, Shape, LSTMState, (Shape, Shape)] = BasicLSTMCell((state_size + 1, state_size), "lstm0")
  val multiCell = MultiCell("multiCell", Seq(
    BasicLSTMCell("lstm1", state_size, FLOAT32),
    BasicLSTMCell("lstm2", 2 * state_size, FLOAT32),
    BasicLSTMCell("lstm3", 2 * state_size, FLOAT32),
    //BasicLSTMCell((2 * state_size, state_size), "lstm4"),
    //BasicLSTMCell((2 * state_size, state_size), "lstm5")
  ))

  // Forward pass


  val rnn = tf.learn.RNN("RNN", multiCell)
  //rnn.forward()
  // RNN[LSTMState, (Shape, Shape)](multiCell, state0, 3).forward(inputs_series).outputs


  object RNNOutputLayer extends tf.learn.Layer[Tuple[Output, Seq[LSTMState]], Output]("RNNOutputLayer") {
    override val layerType: String = "RNNOutputLayer"

    override def _forward(input: Tuple[Output, Seq[LSTMState]])(implicit mode: tf.learn.Mode): Output = {
      val W2 = tf.variable("W2", FLOAT32, Shape(2 * state_size, num_classes))
      val b2 = tf.variable("b2", FLOAT32, Shape(1, num_classes))
      println(input.output.shape)
      val res = tf.matmul(input.output.reshape(Shape(batch_size, 2 * state_size)), W2) + b2
      // We reshape the output logits to feed into the sequence loss layer
      res
    }

  }

  object AddDim extends tf.learn.Layer[Output, Output]("AddDim") {
    override val layerType: String = "AddDim"

    override def _forward(input: Output)(implicit mode: tf.learn.Mode): Output = {
      input.reshape(Shape(batch_size, 1, truncated_backprop_length))
    }

  }


//  val logits = (AddDim >> rnn >> RNNOutputLayer).forward(batchX_placeholder)(TRAINING)
//
//  val predictions: Output = tf.argmax(logits, -1)
//
//  val losses = tf.sparseSoftmaxCrossEntropy(logits, batchY_placeholder)
//
//  val total_loss: Output = tf.sum(losses) /  batch_size
//
//  val train_step = tf.train.AdaGrad(0.01).minimize(total_loss)
//
//  val session = Session()
//  session.run(targets = tf.globalVariablesInitializer())


  val model = {
    val input = tf.learn.Input(FLOAT32, Shape(-1, -1))
    val trainInput = tf.learn.Input(INT32, Shape(-1))
    
    val layer = AddDim >> rnn >> RNNOutputLayer
    val loss = tf.learn.SparseSoftmaxCrossEntropy("Loss/CrossEntropy") >>
      tf.learn.Mean("Loss/Mean") >> tf.learn.ScalarSummary("Loss/Summary", "Loss")
    val optimizer = tf.train.AdaGrad(0.01)
    tf.learn.Model.supervised(input, layer, trainInput, loss, optimizer, tf.learn.ClipGradientsByGlobalNorm(5.0f))
  }





 // for(epoch <- 0 to 100) {

    val (x,y) = generateData()

    val xdata = tf.data.TensorSlicesDataset(x)
    val ydata = tf.data.TensorSlicesDataset(y)

    val (xt,yt) = generateData(truncated_backprop_length / 10)

    val xdata_test = tf.data.TensorSlicesDataset(xt)
    val ydata_test = tf.data.TensorSlicesDataset(yt)

    val evalTrainData = xdata.zip(ydata).batch(batch_size).prefetch(10)
    val evalTestData = xdata_test.zip(ydata_test).batch(batch_size).prefetch(10)

    val accMetric = tf.metrics.MapMetric(
      (v: (Output, Output)) => (v._1.argmax(-1), v._2), tf.metrics.Accuracy())

    val trainData =
      xdata.zip(ydata)
        .batch(batch_size)
        //.prefetch(10)

    val summariesDir = Paths.get("temp/rnn-example")
      val estimator = tf.learn.InMemoryEstimator(
        model,
        tf.learn.Configuration(Some(summariesDir)),
        tf.learn.StopCriteria(maxSteps = Some(100000)),
        Set(
          tf.learn.Evaluator(
            log = true, datasets = Seq(("Train", () => evalTrainData), ("Test", () => evalTestData)),
            metrics = Seq(accMetric), trigger = tf.learn.StepHookTrigger(100), name = "Evaluator"),
          tf.learn.LossLogger(trigger = tf.learn.StepHookTrigger(10)),
          tf.learn.StepRateLogger(log = false, summaryDir = summariesDir, trigger = tf.learn.StepHookTrigger(100)),
          tf.learn.SummarySaver(summariesDir, tf.learn.StepHookTrigger(10)),
          tf.learn.CheckpointSaver(summariesDir, tf.learn.StepHookTrigger(1000))),
        tensorBoardConfig = tf.learn.TensorBoardConfig(summariesDir, reloadInterval = 1))
      estimator.train(() => trainData, tf.learn.StopCriteria(maxSteps = Some(100000)))

//    for (i <- 0 until (total_series_length / truncated_backprop_length) / batch_size) {
//
//      val start_idx: Int = i * batch_size
//      val end_idx: Int = start_idx + batch_size
//
//      val batchX = x(start_idx :: end_idx, ::)
//      val batchY = y(start_idx :: end_idx)
//
//      val feeds = Map(
//        batchX_placeholder -> batchX,
//        batchY_placeholder -> batchY
//      )
//      val fetches: Seq[Output] = Seq(total_loss) //++ predictions_series
//      val trainLoss = session.run(feeds = feeds, fetches = fetches, targets = train_step)
//      // println(batchX.summarize())
//      println(trainLoss.head.scalar)
//
//      println(s"Predicted value: ${session.run(feeds = feeds, fetches = predictions).summarize()}")
//
//      session.deleteNativeReferences()
//
//      // println(s"Predicted value: ${session.run(feeds = feeds, fetches = predictions_series).summarize()}")
//      // println(s"True value: ${batchY.summarize()}")
//    }



 // }


}
