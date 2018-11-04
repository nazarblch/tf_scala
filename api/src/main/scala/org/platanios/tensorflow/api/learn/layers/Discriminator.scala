package org.platanios.tensorflow.api.learn.layers

import org.platanios.tensorflow.api.learn.{Mode, TRAINING}
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.Shape
import org.platanios.tensorflow.api.ops.Gradients


abstract class Discriminator(override val name: String, f: Layer[Output, Output]) extends Layer[(Output, Output), Output](name) {

  def penalty(input: Output): Output
}

case class WasDiscriminator(override val name: String,
                            f: Layer[Output, Output]
                           ) extends Discriminator(name, f) {
  override val layerType: String = name
  val LAMBDA = 50d

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


case class InfoDiscriminator(override val name: String,
                         f: Layer[Output, Output]
                        ) extends Discriminator(name, f) {
  override val layerType: String = name


  override protected def _forward(input: (Output, Output))(implicit mode: Mode): Output = {
    val m1: Output = tf.mean(tf.log1p(f(input._1)(mode) + 0.000001))
    val shape = Shape(f(input._2)(mode).shape(0))
    val m2: Output = tf.mean(tf.log1p(Tensor.ones(FLOAT32, shape).toOutput * 0.999999 - f(input._2)(mode)))
    m1 + m2
  }


  def penalty(input: Output): Output = {
    0d
  }


}



