package org.platanios.tensorflow.api.learn.estimators

import org.platanios.tensorflow.api.learn.models.TrainableModel
import org.platanios.tensorflow.api.ops.io.data.IterableDataset
import org.platanios.tensorflow.api.ops.metrics.Metric
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.Graph
import org.platanios.tensorflow.api.core.client.SessionConfig
import org.platanios.tensorflow.api.learn.Counter
import org.platanios.tensorflow.api.{Output, Session, tf}

class IterativeEstimator[IT, IO, ID, IS, I, TT, TO, TD, TS, T](
                                                                model: TrainableModel[IT, IO, ID, IS, I, TT, TO, TD, TS, T],
                                                                metrics: Seq[Metric[(I, T), Output]],
                                                                gpuAllowMemoryGrowth: Boolean
                                                              ) {

  private val trainOps = model.buildTrainOps()
  private val evalOps = model.buildEvaluateValues(metrics)
  val conf = SessionConfig(gpuAllowMemoryGrowth = Some(gpuAllowMemoryGrowth))
  private val session = Session(sessionConfig = Some(conf))
  session.run(targets = tf.globalVariablesInitializer())


  private def sumSeq(ts1: Seq[Tensor], ts2: Seq[Tensor]): Seq[Tensor] = {
    val res = ts1.zip(ts2).map(p => p._1 + p._2)
    ts1.foreach(_.close())
    ts2.foreach(_.close())
    res
  }

  def train(dataset: IterableDataset[IT, IO, ID, IS, I, TT, TO, TD, TS, T]): Unit = {

    dataset.makeEpoch((feeds, iter) => {
          session.run(feeds = feeds, targets = trainOps.trainOp)
          session.deleteNativeReferences()
    })

    dataset.close()
  }


  def evaluate(dataset: IterableDataset[IT, IO, ID, IS, I, TT, TO, TD, TS, T]): Seq[Tensor] = {

    val res = dataset.makeEpoch((feeds, iter) => {
      session.run(feeds = feeds, fetches = evalOps)
    }).reduce(sumSeq).map(_ / dataset.iterationsCount)

    session.deleteNativeReferences()
    res
  }

}
