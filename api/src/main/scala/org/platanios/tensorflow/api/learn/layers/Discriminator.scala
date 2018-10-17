package org.platanios.tensorflow.api.learn.layers

import org.platanios.tensorflow.api.learn.{Mode, TRAINING}
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.Shape
import org.platanios.tensorflow.api.ops.Gradients

case class Discriminator(override val name: String,
                            f: Layer[Output, Output]
                           ) extends Layer[(Output, Output), Output](name) {
  override val layerType: String = name
  val LAMBDA = 5d

  private def mean(input: Output, mode: Mode): Output =  tf.mean(f(input)(mode))

  override protected def _forward(input: (Output, Output))(implicit mode: Mode): Output = {
    val m1: Output = mean(input._1, mode)
    val m2: Output = mean(input._2, mode)
    m1 - m2
  }

  def penalty(input: Output): Output = {
    val g = Gradients.gradients(Seq(f(input)(TRAINING)), Seq(input)).head.toOutput
    val norms = g.square.sum(Array.range(1, g.shape.asArray.length)).sqrt
    (norms - 1d).square.mean() * LAMBDA

  }
}



