package org.platanios.tensorflow.api.tensors


import native_types.c_api.eager.c_api
import native_types.core.framework.{TensorShape, Tensor => JTensor}
import org.platanios.tensorflow.api.Indexer
import org.platanios.tensorflow.api.tensors.ops.Basic.{BasicOps, stack}

// TODO: register in Disposer
class EagerTensor(nativeTensor: JTensor) extends TMPTensor(nativeTensor, nativeTensor.deallocate) {

  private val tfe_handle = c_api.TFE_NewTensorHandle(nativeTensor)


  //def apply(indexers: Indexer*): Tensor = this.slice(indexers: _*)

  // def slice(indexers: Indexer*): Tensor = BasicOps(this).slice(indexers: _*)
}
