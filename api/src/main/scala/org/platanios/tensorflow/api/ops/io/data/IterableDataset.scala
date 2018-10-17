package org.platanios.tensorflow.api.ops.io.data

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.client.FeedMap
import org.platanios.tensorflow.api.learn.models.TrainableModel


class IterableDataset[IT, IO, ID, IS, I, TT, TO, TD, TS, T](val model: TrainableModel[IT, IO, ID, IS, I, TT, TO, TD, TS, T], val data: Seq[Tensor], val batch_size: Int) {

  private val placeholders = model.placeholders
  assert(placeholders.length == data.length)
  placeholders.zip(data).foreach(p => assert(p._1.shape.asArray.drop(1) sameElements p._2.shape.asArray.drop(1)))

  val size: Int = data.head.shape(0)
  val iterationsCount = size / batch_size

  def makeIteration[A](iter: Int)(f: (FeedMap, Int) => A): A = {
    val from = iter * batch_size
    val to = from + batch_size
    val batch = data.map(_.slice(from :: to))
    val map: FeedMap = FeedMap(placeholders.zip(batch).toMap)
    val res = f(map, iter)
    batch.foreach(_.close())
    res
  }

  def makeEpoch[A](f: (FeedMap, Int) => A): Seq[A] = {
    Seq.range(0 , size / batch_size).map(i => makeIteration(i)(f))
    //close()
  }


  def close(): Unit = {
    data.foreach(_.close())
  }


}