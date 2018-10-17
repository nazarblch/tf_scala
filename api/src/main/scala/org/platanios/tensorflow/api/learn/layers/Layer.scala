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

package org.platanios.tensorflow.api.learn.layers

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.learn._
import org.platanios.tensorflow.api.ops.variables.Variable.VariableGetter
import org.platanios.tensorflow.api.ops.variables.VariableScope.maybeWrapCustomVariableGetter
import org.platanios.tensorflow.api.ops.{Op, OpSpecification}
import org.platanios.tensorflow.api.ops.variables._
import org.platanios.tensorflow.api.types.DataType

import scala.util.DynamicVariable

/**
  *
  * '''NOTE:''' Subclasses must implement the `_forward` method. Callers should always use either the `forward` or the
  * `apply` methods.
  *
  * @param  name Name scope (also acting as variable scope) for this layer.
  *
  * @author Emmanouil Antonios Platanios
  */
abstract class Layer[T, R](
    val name: String
) {
  val layerType: String

  protected def _forward(input: T)(implicit mode: Mode): R

  def forward(input: T)(implicit mode: Mode): R = Op.createWith(
    nameScope = layerContext.value.nameScope,
    device = layerContext.value.device,
    deviceFunction = layerContext.value.deviceFunction
  ) {
    VariableScope.updatedScope(layerContext.value.variableScope, isPure = true) {
      if (name != null) {
        VariableScope.scope(name, isPure = true) {
          _forward(input)
        }
      } else {
        _forward(input)
      }
    }
  }

  def apply(input: T)(implicit mode: Mode): R = forward(input)

  def >>[S](other: Layer[R, S]): Compose[T, R, S] = compose(other)

  def >>[S](other: OutputLayer[R]): OutputLayer[T] = ComposeOutput(name, this, other)

  def :+(other: Layer[T, R]): Concatenate[T, R] = concatenate(other)

  def pair[T2, R2](other: Layer[T2, R2]): Concatenate12[T, R, T2, R2] = concatenate12(other)

  def ++(others: Seq[Layer[T, R]]): Concatenate[T, R] = concatenate(others: _*)

  def compose[S](other: Layer[R, S]): Compose[T, R, S] = Compose(name, this, other)

  def concatenate(others: Layer[T, R]*): Concatenate[T, R] = Concatenate(name, this +: others)

  def concatenate12[T2, R2](other: Layer[T2, R2]): Concatenate12[T, R, T2, R2] = Concatenate12(name, this, other)

  override def toString: String = layerType
}

abstract class OutputLayer[T](override val name: String) extends Layer[T, Output](name) {

  private def superlayerType = layerType

  private def super_forward(input: T)(implicit mode: Mode) = _forward(input)(mode)

  def * (a: Output): OutputLayer[T] = new OutputLayer[T](name) {
    override val layerType: String = superlayerType
    override protected def _forward(input: T)(implicit mode: Mode): Output = super_forward(input)(mode) * a
  }

  def + (other: OutputLayer[T]): OutputLayer[T] = new OutputLayer[T](name) {
    override val layerType: String = superlayerType
    override protected def _forward(input: T)(implicit mode: Mode): Output = super_forward(input)(mode) + other.forward(input)(mode)
  }
}

private[api] final case class LayerCreationContext(
    nameScope: String = "", variableScope: VariableScope = VariableScope(reuse = ReuseOrCreateNew),
    device: String = "", deviceFunction: OpSpecification => String = _.device)

object Layer {
  trait API {
    def currentNameScope: String = Layer.currentNameScope
    def currentVariableScope: VariableScope = Layer.currentVariableScope
    def currentDevice: String = Layer.currentDevice
    def currentDeviceFunction: OpSpecification => String = Layer.currentDeviceFunction

    def createWith[R](
        nameScope: String = null,
        device: String = "",
        deviceFunction: OpSpecification => String = op => op.device
    )(block: => R): R = {
      Layer.createWith(nameScope, device, deviceFunction)(block)
    }

    def nameScope[R](nameScope: String)(block: => R): R = Layer.createWith(nameScope = nameScope)(block)

    def device[R](device: String)(block: => R): R = Layer.createWith(device = device)(block)

    def deviceFunction[R](deviceFunction: OpSpecification => String)(block: => R): R = {
      Layer.createWith(deviceFunction = deviceFunction)(block)
    }

    def variableScope[R](
        name: String, reuse: ReuseAllowed = ReuseOrCreateNew, dataType: DataType = null,
        initializer: Initializer = null, regularizer: Regularizer = null, partitioner: Partitioner = null,
        cachingDevice: OpSpecification => String = null, customGetter: VariableGetter = null,
        isDefaultName: Boolean = false, isPure: Boolean = false
    )(block: => R): R = {
      Layer.createWithVariableScope(
        name, reuse, dataType, initializer, regularizer, partitioner, cachingDevice, customGetter, isPure)(block)
    }

    def updatedVariableScope[R](
        variableScope: VariableScope, reuse: ReuseAllowed = ReuseOrCreateNew, dataType: DataType = null,
        initializer: Initializer = null, regularizer: Regularizer = null, partitioner: Partitioner = null,
        cachingDevice: OpSpecification => String = null, customGetter: VariableGetter = null, isPure: Boolean = false
    )(block: => R): R = {
      Layer.createWithUpdatedVariableScope(
        variableScope, reuse, dataType, initializer, regularizer, partitioner, cachingDevice, customGetter,
        isPure)(block)
    }

    type Layer[T, R] = layers.Layer[T, R]
  }

  object API extends API

  /** Variable store object used when creating layers. This variable store is used to store created variables and keep
    * track of variable scope usages. */
  private[this] val variableStore: VariableStore = VariableStore()

  /** Returns the name scope of the current layer creation context. */
  private[layers] def currentNameScope: String = {
    if (layerContext.value.nameScope == "")
      ""
    else
      s"${layerContext.value.nameScope}/"
  }

  /** Returns the variable scope of the current layer creation context. */
  private[layers] def currentVariableScope: VariableScope = {
    layerContext.value.variableScope
  }

  /** Returns the device of the current layer creation context. */
  private[layers] def currentDevice: String = {
    layerContext.value.device
  }

  /** Returns the device function of the current layer creation context. */
  private[layers] def currentDeviceFunction: OpSpecification => String = {
    layerContext.value.deviceFunction
  }

  private[api] def createWith[R](
      nameScope: String = null,
      device: String = "",
      deviceFunction: OpSpecification => String = _.device
  )(block: => R): R = {
    var updatedContext = layerContext.value
    val newNameScope: String = Op.mergeNameScope(nameScope, updatedContext.nameScope, identity[String])
    updatedContext = updatedContext.copy(nameScope = newNameScope)
    val newDevice: String = Op.mergeDevice(device, updatedContext.device)
    updatedContext = updatedContext.copy(device = newDevice)
    val newDeviceFunction: OpSpecification => String = Op.mergeDeviceFunction(
      deviceFunction, updatedContext.deviceFunction, updatedContext.device)
    updatedContext = updatedContext.copy(deviceFunction = newDeviceFunction)
    layerContext.withValue(updatedContext)(block)
  }

  // TODO: There is a lot of code duplicated between here and the variables package.

  private[api] def createWithVariableScope[R](
      name: String, reuse: ReuseAllowed = ReuseOrCreateNew, dataType: DataType = null, initializer: Initializer = null,
      regularizer: Regularizer = null, partitioner: Partitioner = null, cachingDevice: OpSpecification => String = null,
      underlyingGetter: VariableGetter = null, isDefaultName: Boolean = false, isPure: Boolean = false
  )(block: => R): R = {
    if (reuse == ReuseExistingOnly && isDefaultName)
      throw new IllegalArgumentException(
        "'reuse' cannot be set to 'ReuseExistingOnly' with 'isDefaultName' set to 'true'.")
    val variableScopeStore = VariableScopeStore.current
    val oldVariableScope = variableScopeStore.scope
    val newName = {
      val uniqueName = if (isDefaultName) VariableScope.unique(name) else name
      if (oldVariableScope.name != null && oldVariableScope.name != "")
        s"${oldVariableScope.name}/$uniqueName"
      else
        uniqueName
    }
    variableScopeStore.enterVariableScope(newName)
    val newVariableScope = VariableScope(
      // TODO: !!! [VARIABLES] Have 'name' as first argument in order to be consistent.
      reuse = if (reuse == ReuseOrCreateNew) oldVariableScope.reuse else reuse,
      name = newName,
      dataType = if (dataType == null) oldVariableScope.dataType else dataType,
      initializer = if (initializer == null) oldVariableScope.initializer else initializer,
      regularizer = if (regularizer == null) oldVariableScope.regularizer else regularizer,
      partitioner = if (partitioner == null) oldVariableScope.partitioner else partitioner,
      cachingDevice = if (cachingDevice == null) oldVariableScope.cachingDevice else cachingDevice,
      nameScope = name,
      underlyingGetter = {
        if (underlyingGetter == null)
          oldVariableScope.underlyingGetter
        else
          maybeWrapCustomVariableGetter(underlyingGetter, oldVariableScope.underlyingGetter)
      })
    variableScopeStore.scope = newVariableScope
    val result = if (isPure) block else Op.createWithNameScope(name)(block)
    variableScopeStore.closeVariableSubScopes(newName)
    variableScopeStore.scope = oldVariableScope
    result
  }

  private[api] def createWithUpdatedVariableScope[R](
      variableScope: VariableScope, reuse: ReuseAllowed = ReuseOrCreateNew, dataType: DataType = null,
      initializer: Initializer = null, regularizer: Regularizer = null, partitioner: Partitioner = null,
      cachingDevice: OpSpecification => String = null, underlyingGetter: VariableGetter = null, isPure: Boolean = false
  )(block: => R): R = {
    val variableScopeStore = VariableScopeStore.current
    val oldVariableScope = variableScopeStore.scope
    val oldVariableScopeCounts = variableScopeStore.variableScopeCounts
    variableScopeStore.enterVariableScope(variableScope.name)
    val newVariableScope = VariableScope(
      reuse = if (reuse == ReuseOrCreateNew) variableScope.reuse else reuse,
      name = variableScope.name,
      dataType = if (dataType == null) variableScope.dataType else dataType,
      initializer = if (initializer == null) variableScope.initializer else initializer,
      regularizer = if (regularizer == null) variableScope.regularizer else regularizer,
      partitioner = if (partitioner == null) variableScope.partitioner else partitioner,
      cachingDevice = if (cachingDevice == null) variableScope.cachingDevice else cachingDevice,
      nameScope = variableScope.nameScope,
      underlyingGetter = {
        if (underlyingGetter == null)
          variableScope.underlyingGetter
        else
          maybeWrapCustomVariableGetter(underlyingGetter, variableScope.underlyingGetter)
      })
    variableScopeStore.scope = newVariableScope
    val result = if (isPure) block else Op.createWithNameScope(variableScope.name.split("/").last)(block)
    variableScopeStore.closeVariableSubScopes(variableScope.name)
    variableScopeStore.variableScopeCounts = oldVariableScopeCounts
    variableScopeStore.scope = oldVariableScope
    result
  }
}
