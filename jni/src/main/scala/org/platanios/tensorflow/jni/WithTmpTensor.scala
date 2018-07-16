package org.platanios.tensorflow.jni
import native_types.core.framework.{TensorFactory, TensorShape, Tensor => JTensor}
import native_types.data_types.CppDataTypes

object WithTmpTensor {

  def apply[T](data: Array[T], shape: Array[Long])(f: JTensor => Any): Unit = {
    val dtype = CppDataTypes.fromClass(data.head.getClass)
    val factory = new TensorFactory(dtype, shape)
    val t = factory.createFromArray(data)
    f(t)
    t.deallocate()
  }


  def apply(data: Object, dtype: Int, shape: Array[Long])(f: JTensor => Any): Unit = {
    val factory = new TensorFactory(dtype, shape)
    val t = factory.create(data)
    f(t)
    t.deallocate()
  }


}
