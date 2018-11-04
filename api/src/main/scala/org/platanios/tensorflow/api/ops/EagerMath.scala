package org.platanios.tensorflow.api.ops

import org.platanios.tensorflow.api.tensors.Tensor
import org.platanios.tensorflow.api.tensors.ops.{Math => TensorMath}

private[api] trait EagerMath  {
  def matmul(a: EagerOutput, b: EagerOutput, transposeA: Boolean = false, transposeB: Boolean = false, conjugateA: Boolean = false,
              conjugateB: Boolean = false, aIsSparse: Boolean = false, bIsSparse: Boolean = false, name: String = "MatMul"): EagerOutput = {
    val output: Output = Math.matmul(a.output, b.output)
    val tensor: Tensor = TensorMath.matmul(a.tensor, b.tensor)
    new EagerOutput(output, tensor)
  }
}
