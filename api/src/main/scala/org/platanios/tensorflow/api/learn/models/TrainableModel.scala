package org.platanios.tensorflow.api.learn.models

import org.platanios.tensorflow.api.Output
import org.platanios.tensorflow.api.core.Graph
import org.platanios.tensorflow.api.learn._
import org.platanios.tensorflow.api.learn.layers.{Input, Layer}
import org.platanios.tensorflow.api.ops.{Math, Op}
import org.platanios.tensorflow.api.ops.metrics.Metric
import org.platanios.tensorflow.api.ops.training.optimizers.Optimizer
import org.platanios.tensorflow.api.types.FLOAT32

class TrainableModel[IT, IO, ID, IS, I, TT, TO, TD, TS, T](
                                                            override val input: Input[IT, IO, _, ID, IS],
                                                            override val layer: Layer[IO, I],
                                                            val trainInput: Input[TT, TO, _, TD, TS],
                                                            val trainInputLayer: Layer[TO, T],
                                                            val loss: Layer[(I, T), Output],
                                                            val optimizer: Optimizer,
                                                            val clipGradients: ClipGradients = NoClipGradients,
                                                            override protected val colocateGradientsWithOps: Boolean = false
                                                          ) extends SimpleInferenceModel[IT, IO, ID, IS, I](input, layer)
  with SupervisedTrainableModel[IT, IO, ID, IS, I, TT, TO, TD, TS, T] {

  //TODO: make implicit input to placeholder cast
  private val placeholders_in = input.createPlaceholder
  private val placeholders_out = trainInput.createPlaceholder
  val placeholders: Seq[Output] = input.createPlaceholderSeq(placeholders_in) ++ trainInput.createPlaceholderSeq(placeholders_out)

  def buildTrainOp(): Op = {
    implicit val mode: Mode = TRAINING

    val layerOutput = layer(placeholders_in)
    val trainLayerOutput = trainInputLayer(placeholders_out)
    val lossOutput = loss((layerOutput, trainLayerOutput))
    val iteration = Counter.getOrCreate(Graph.Keys.GLOBAL_STEP, local = false)
    val gradientsAndVariables = optimizer.computeGradients(
      lossOutput, colocateGradientsWithOps = colocateGradientsWithOps)
    val clippedGradientsAndVariables = clipGradients(gradientsAndVariables)
    val trainOp = optimizer.applyGradients(clippedGradientsAndVariables, Some(iteration))
    // val trainOp = optimizer.minimize(lossOutput)
    trainOp
  }

  private val inputIterator = input.zip(trainInput).apply()

  def buildTrainOps(): Model.SupervisedTrainOps[IT, IO, ID, IS, I, TT, TO, TD, TS, T] = {
    implicit val mode: Mode = TRAINING

    val layerOutput = layer(placeholders_in)
    val trainLayerOutput = trainInputLayer(placeholders_out)

    val lossOutput = Math.cast(
      loss((layerOutput, trainLayerOutput)), FLOAT32, name = "LossCast")
    val iteration = Counter.getOrCreate(Graph.Keys.GLOBAL_STEP, local = false)
    val gradientsAndVariables = optimizer.computeGradients(
      lossOutput, colocateGradientsWithOps = colocateGradientsWithOps)
    val clippedGradientsAndVariables = clipGradients(gradientsAndVariables)
    val trainOp = optimizer.applyGradients(clippedGradientsAndVariables, Some(iteration))
    Model.SupervisedTrainOps(
      inputIterator, inputIterator.next(), layerOutput, trainLayerOutput, lossOutput, gradientsAndVariables, trainOp)
  }

  def buildEvaluateValues(metrics: Seq[Metric[(I, T), Output]]): Seq[Output] = {
    implicit val mode: Mode = EVALUATION

    val layerOutput = layer(placeholders_in)
    val trainLayerOutput = trainInputLayer(placeholders_out)
    metrics.map(_.compute((layerOutput, trainLayerOutput)))
  }

  def buildEvaluateOps(
                        metrics: Seq[Metric[(I, T), Output]]
                      ): Model.EvaluateOps[(IT, TT), (IO, TO), (ID, TD), (IS, TS), I] = {
    implicit val mode: Mode = EVALUATION

    val layerOutput = layer(placeholders_in)
    val trainLayerOutput = trainInputLayer(placeholders_out)
    val streamingInstances = metrics.map(_.streaming((layerOutput, trainLayerOutput)))
    Model.EvaluateOps(
      inputIterator, inputIterator.next(), layerOutput,
      streamingInstances.map(_.value), streamingInstances.map(_.update), streamingInstances.map(_.reset))
  }

}

