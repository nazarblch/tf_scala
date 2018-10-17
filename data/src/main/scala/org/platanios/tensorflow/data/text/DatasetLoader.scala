package org.platanios.tensorflow.data.text

import org.platanios.tensorflow.api.Shape
import org.platanios.tensorflow.api.tensors.Tensor
import org.platanios.tensorflow.data.serializer.JavaGenericSerializer

import scala.reflect.ClassTag

object DatasetLoader extends JavaGenericSerializer {

  def load(path: String): (Array[Array[Array[Double]]], Array[Int]) = {
    genericLoad[(Array[Array[Array[Double]]], Array[Int])](path)
  }

  def loadDataset(path: String, from: Int, to: Int): Dataset = {
    val data = load(path)
    println("loaded")
    val X = data._1.slice(from, to)
    val Y = data._2.slice(from, to)
    val shape = Shape(X.length, X.maxBy(_.length).length, X.maxBy(_.length).head.length)
    new Dataset(X.map(xi => xi.flatten.map(_.toFloat)), shape, Y)
  }

  def save(path: String,  x: Array[Array[Array[Double]]], y: Array[Int]): Unit = {
    genericSave((x,y), path)
  }

  def suffle[T: ClassTag](arr: IndexedSeq[T]): Array[T] = new scala.util.Random().shuffle(arr).toArray

}


class Dataset(X: Array[Array[Float]], XShape: Shape, Y: Array[Int]) {

  assert(X.length == Y.length)
  assert(X.length == XShape(0))

  println("Creating Dataset: " + XShape.summarize())

  val max_sent_len = XShape(1)
  val w2v_dim =  XShape(-1)

  def slice(from: Int, to: Int): Dataset = {
    val newShape = Shape(Array(Y.slice(from, to).length) ++ XShape.asArray.drop(1))
    new Dataset(X.slice(from, to), newShape, Y.slice(from, to))
  }

  def getTensorX: Tensor = {
    val len: Int = XShape.asArray.drop(1).product
    val x = X.map(xi => xi ++ Array.fill(len - xi.length)(0f)).flatMap(x => x)
    Tensor.fromArrayFloat(x).reshape(XShape)
  }

  def getTensorY: Tensor = {
    Tensor.fromArrayInt(Y)
  }

  def getTensorMask: Tensor = {
    val lens: Array[Int] = X.map(xi => xi.length / w2v_dim)
    val mask = lens.map(li => {
      val mi: Array[Boolean] = Array.fill(max_sent_len)(false)
      mi(li - 1) = true
      mi
    })
    Tensor.fromArrayBool(mask.flatMap(x => x)).reshape(Shape(XShape(0), max_sent_len))
  }

  def getTensorLens: Tensor = {
    val lens: Array[Int] = X.map(xi => xi.length / w2v_dim)
    Tensor.fromArrayInt(lens).reshape(Shape(XShape(0)))
  }

  def suffle: Dataset = {
    val z = DatasetLoader.suffle(X.zip(Y))
    new Dataset(z.map(_._1), XShape, z.map(_._2))
  }

  def filter(min_len: Int, max_len: Int): Dataset = {
    val lens: Array[Int] = X.map(xi => xi.length / XShape(-1))
    val z = X.zip(Y).zip(lens).filter(pi => pi._2 < max_len && pi._2 >= min_len).map(_._1)

    new Dataset(z.map(_._1), Shape(Array(z.length) ++ XShape.asArray.drop(1)), z.map(_._2))
  }

  def size: Int = Y.length

}
