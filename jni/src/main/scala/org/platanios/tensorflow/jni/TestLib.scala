package org.platanios.tensorflow.jni

import java.nio.{Buffer, IntBuffer}

import native_types.c_api.c_api
import native_types.c_api.c_api.TF_Tensor
import org.bytedeco.javacpp.{FloatPointer, IntPointer, Pointer}


object TestLib extends App {

   val tft = new TF_Tensor(3, Array(4l), 1, 4 * 4)
   // Pointer.memcpy(tft, new FloatPointer(Array(1f,2f,3f,5f):_*), 4 * 4)
   val data: Pointer = c_api.TF_TensorData(tft)
   data.put(new IntPointer(Array(1,2,3,5):_*))
   println(c_api.TF_TensorType(tft))

}
