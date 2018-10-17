/* Copyright 2017-18, Emmanouil Antonios Platanios. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.platanios.tensorflow.api

import native_types.c_api.eager.c_api.TFE_Context
import org.platanios.tensorflow.api.core.client.SessionConfig

import scala.util.DynamicVariable

/**
  * @author Emmanouil Antonios Platanios
  */
package object tensors {
  private[api] val executionContext: DynamicVariable[Context] = {
    val sessionConfig = sys.env.get("TF_CUDA_VISIBLE_DEVICES")
        .map(devices => SessionConfig(gpuVisibleDevices = Some(devices.split(',').map(_.toInt))))
   new DynamicVariable[Context](Context(sessionConfig))
  }

  private[api] val eagerExecutionContext: TFE_Context = {
    new TFE_Context(executionContext.value.nativeHandle)
  }

  private[api] trait API
      extends tensors.ops.API
}
