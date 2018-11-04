package org.platanios.tensorflow.api.ops.variables

import org.platanios.tensorflow.api.core.Shape
import org.platanios.tensorflow.api.tensors.{Tensor, TensorIndexedSlices, TensorLike}
import org.platanios.tensorflow.api.types.DataType

class EagerVariable(private var data: Tensor) extends TensorLike {
  /** Data type of this tensor. */
  override val dataType: DataType = data.dataType
  /** Shape of this tensor. */
  override val shape: Shape = data.shape
  /** Device on which this tensor is stored. */
  override val device: String = data.device

  /** Returns the [[Tensor]] that this [[TensorLike]] object represents. */
  override def toTensor: Tensor = data

  /** Returns an [[TensorIndexedSlices]] that has the same value as this [[TensorLike]].
    *
    * @return [[TensorIndexedSlices]] that has the same value as this [[TensorLike]].
    */
  override def toTensorIndexedSlices: TensorIndexedSlices = data.toTensorIndexedSlices

  def update(new_data: Tensor): Unit = {
    assert(new_data.shape equals shape)
    assert(new_data.dataType equals dataType)
    data = new_data
  }


}
