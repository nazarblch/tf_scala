package org.platanios.tensorflow.examples



import native_types.core.framework.{TensorFactory, TensorShape}
import native_types.data_types.CppDataTypes._
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.ops.Basic
import org.platanios.tensorflow.api.tensors.ClassicTensor


object TestTensor extends App {

  val t = Tensor(1,2)
  val t1 = Tensor(1,2, 3)
  val t2 = Tensor(t, t1)

  println(t.cpu().summarize())


  Tensor.makeProto(t)


  println(t.reshape(Shape(4)).cpu().summarize())
}
