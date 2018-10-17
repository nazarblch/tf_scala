package org.platanios.tensorflow.examples
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.ops.Gradients.Registry
import org.platanios.tensorflow.api.ops.Math.cast
import org.platanios.tensorflow.api.ops.{Op, OutputLike, OutputOps, logger}
import org.platanios.tensorflow.api.ops.training.optimizers.{AdaGrad, Adam}
import org.platanios.tensorflow.api.types.{BFLOAT16, COMPLEX128, COMPLEX64, DataType, FLOAT16, FLOAT32, FLOAT64}


object CastGradient {

  def moy_cast[T <: OutputLike : OutputOps](x: T, dataType: DataType, name: String = "MoyCast"): T = {
    if (x.dataType == dataType) {
      x
    } else {
      if (x.dataType.isComplex && !dataType.isComplex)
        println("Casting complex tensors to real tensors discards the imaginary part.")
      implicitly[OutputOps[T]]
        .applyUnary(x, o => Op.Builder(opType = "Cast", name = name)
          .addInput(o)
          .setAttribute("DstT", dataType)
          .build().outputs(0))
    }
  }

  def castGrad(op: Op, outputGradients: Seq[OutputLike]): Seq[OutputLike] = {
    val outputGradient = outputGradients.head.toOutput
    println("castGrad")
    val supportedDataTypes = Seq(FLOAT16, FLOAT32, FLOAT64, BFLOAT16, COMPLEX64, COMPLEX128)
    val sourceDataType = op.inputs(0).dataType
    val destinationDataType = outputGradients.head.dataType
    if (supportedDataTypes.contains(sourceDataType) && supportedDataTypes.contains(destinationDataType))
      Seq(cast(outputGradients.head, sourceDataType))
    else
      Seq(null)
  }


  def main(args: Array[String]): Unit = {



    val input = tf.placeholder(FLOAT32, Shape(1))
    val variable = tf.variable("variable", FLOAT32, Shape(1), tf.ZerosInitializer)
    val castTest = moy_cast(variable * 10f, INT32)
    val loss = tf.sum(tf.square(moy_cast(castTest, FLOAT32) - input))

    Registry.register("Cast", castGrad)

    val trainOp = tf.train.AdaGrad(1.0).minimize(loss)


  }

}
