package org.platanios.tensorflow.api.learn.models

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.Graph
import org.platanios.tensorflow.api.learn._
import org.platanios.tensorflow.api.learn.layers.{Discriminator, Input, Layer}
import org.platanios.tensorflow.api.ops.training.optimizers.Optimizer

class GANModel [I, IG](
                                                       val generatorInput: IG,
                                                       val generator: Layer[IG, Output],
                                                       val realData: Output,
                                                       val loss: Discriminator,
                                                       val optimizer: Optimizer,
                                                       val clipGradients: ClipGradients = NoClipGradients
                                                     ) {

  def getLossValue(): Output = {
    implicit val mode: Mode = TRAINING
    loss((generator(generatorInput), realData))
  }

  def buildGeneratorOp(): Op = {
    implicit val mode: Mode = TRAINING
    val g_out: Output = generator(generatorInput)
    val g_vars = g_out.graph.globalVariables
    val g_loss = loss((g_out, realData))
    optimizer.minimize(g_loss, variables = g_vars)
  }

  def buildDiscriminatorOp(): Op = {
    implicit val mode: Mode = TRAINING
    val g_out: Output = tf.stopGradient(generator(generatorInput))
    val pen: Output = loss.penalty(g_out) * 0.5 + loss.penalty(realData) * 0.5
    val d_loss: Output = loss((g_out, realData))  * (-1d) + pen
    val d_vars = d_loss.graph.globalVariables.filter(_.name.startsWith("Discriminator"))
    d_vars.foreach(v => println(v.name))
    optimizer.minimize(d_loss, variables = d_vars)
  }

  def buildDiscriminatorClipOp(): Seq[Op] = {
    implicit val mode: Mode = TRAINING
    val d_loss: Output = loss((realData, realData))
    val d_vars = d_loss.graph.globalVariables.filter(_.name.startsWith("Discriminator"))
    val clip = d_vars.toSeq.map(v => v.assign(v.clipByValue(-1f, 1f)).op)
    clip
  }


  def generate(): Output = {
    generator(generatorInput)(INFERENCE)
  }

}
