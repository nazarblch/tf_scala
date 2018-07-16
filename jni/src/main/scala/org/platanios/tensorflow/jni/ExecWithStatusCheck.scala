package org.platanios.tensorflow.jni

import native_types.c_api.c_api._

object ExecWithStatusCheck {
  def apply[T](f: TF_Status => T): T = {
    val status = TF_NewStatus
    val res = f(status)
    if (TF_GetCode(status) != 0) throwExeptionByCode(status)
    TF_DeleteStatus(status)
    res
  }
}
