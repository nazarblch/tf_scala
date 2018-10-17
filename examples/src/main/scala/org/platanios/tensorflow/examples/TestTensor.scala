package org.platanios.tensorflow.examples



import native_types.core.framework.{TensorFactory, TensorShape}
import native_types.data_types.CppDataTypes._
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.ops.Basic
import org.platanios.tensorflow.api.tensors.ClassicTensor


object TestTensor extends App {


  def test () {
    val t = Tensor.fromArrayInt(Array(1, 2))
    println(t)
    //t.close()
  }
  val t2 = Tensor("0,0,0", "0,0,1")
  println(t2.summarize())


  for (i <- 0 to 100000) {
    val t = Tensor.fromArrayFloat(Array.fill(1000 * 200 * 100)(2f))
    val t2 = t.reshape(Shape(1000, 200, 100))
    t.close()
    println(i)
    println(t2.summarize())

    val ct = t2.resolve()
    ct.close()

    t2.close()
  }


  test()

  System.gc()

  Thread.sleep(2000)




}
