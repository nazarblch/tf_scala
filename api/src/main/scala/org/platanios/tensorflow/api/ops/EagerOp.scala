package org.platanios.tensorflow.api.ops

import java.io.InvalidClassException

import native_types.c_api.eager.c_api.{TFE_Op, _}
import org.bytedeco.javacpp.{BytePointer, Pointer}
import org.platanios.tensorflow.api.{Tensor, types}
import org.platanios.tensorflow.api.types.DataType
import org.platanios.tensorflow.jni.ExecWithStatusCheck

class EagerOp private (op: TFE_Op) extends Pointer {

  def execute(): Tensor = {
    val th: TFE_TensorHandle = eagerExecute(op)
    // th.deallocate()
    //TFE_DeleteOp(handle)
    op.deallocate()
    Tensor.fromNativeHandle(th)
  }

}



object EagerOp {


  final case class Builder(opType: String, opName: String) {

    private var built: Boolean = false
    private var inputs: Seq[Tensor] = Seq.empty
    private var device: Option[String] = Some("/job:localhost/replica:0/task:0/device:CPU:0")
    private var attributes: Map[String, Any] = Map.empty

    def addInputs(data: Tensor*): Unit = {
      inputs = inputs ++ data
    }

    def setDevice(dname: String): Unit = {
      device = Some(dname)
    }

    def setGPU(index: Int): Unit = {
      device = Some("/job:localhost/replica:0/task:0/device:GPU:" + index)
    }

    def addAttribute[A](k: String, v: A): Unit = {
      attributes += k -> v
    }

    def build(context: TFE_Context): EagerOp = {
      val op: TFE_Op = new TFE_Op(opType, context)

      ExecWithStatusCheck(s => TFE_OpSetDevice(op, new BytePointer(device.get), s))

      inputs.foreach(_.opAddInput(op))

      attributes.foreach({case (str, attr) => attr match {
        case v: Long => TFE_OpSetAttrInt(op, new BytePointer(str), v)
        case v: DataType => TFE_OpSetAttrTypeInt(op, new BytePointer(str), v.cValue)
          // println(name + " "+ v)
        case _ => throw new InvalidClassException(attr.toString)
      }})

      built = true

      new EagerOp(op)
    }

  }


}
