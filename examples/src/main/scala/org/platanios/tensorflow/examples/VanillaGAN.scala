package org.platanios.tensorflow.examples

import java.nio.file.Paths
import java.util.Random

import org.platanios.tensorflow.api._
import breeze.linalg.DenseMatrix
import breeze.plot.Figure
import breeze.stats.distributions.Uniform
import org.platanios.tensorflow.api.Tensor
import org.platanios.tensorflow.api.core.client.FeedMap
import org.platanios.tensorflow.api.ops.variables.{GlorotNormalInitializer, RandomNormalInitializer, ZerosInitializer}
import org.platanios.tensorflow.data.image.MNISTLoader

object VanillaGAN extends App {

  val r = new Random()

  def gen_rand(n: Int, p: Int): Tensor = {
    Tensor.fromArrayFloat(
      Array.fill(n * p)(2 * r.nextDouble() - 1).map(_.toFloat)
    ).reshape(Shape(n, p))
  }

  def tensor2matrix(t: Tensor): DenseMatrix[Double] = {
    val data = t.entriesIterator.map(_.toString.toDouble).toArray
    DenseMatrix(data).reshape(t.shape(0), t.shape(1))
  }

  def plot(t: Tensor): Unit = {
    import breeze.plot._
    val f2 = Figure()
    f2.subplot(0) += image(tensor2matrix(t))
    f2.saveas("image.png")
  }

  val mnist = MNISTLoader.load(Paths.get("datasets/MNIST")).trainImages
  println(mnist.shape(0))

  val image_dim = 784 // 28*28 pixels
  val gen_hidden_dim =  256
  val disc_hidden_dim = 256
  val noise_dim = 40 // Noise data points

  val num_steps = 10000
  val batch_size = 128
  val learning_rate = 0.0001

  val weights = Map(
    "gen_hidden1" -> tf.variable("gen_hidden1", FLOAT32, Shape(noise_dim, gen_hidden_dim), RandomNormalInitializer(standardDeviation = 1d / math.sqrt(noise_dim/2))),
    "gen_out" -> tf.variable("gen_out", FLOAT32, Shape(gen_hidden_dim, image_dim),  RandomNormalInitializer(standardDeviation = 1d / math.sqrt(gen_hidden_dim/2))),
    "disc_hidden1" -> tf.variable("disc_hidden1", FLOAT32, Shape(image_dim, disc_hidden_dim),  RandomNormalInitializer(standardDeviation = 1d / math.sqrt(image_dim/2))),
    "disc_out" -> tf.variable("disc_out", FLOAT32, Shape(disc_hidden_dim, 1),  RandomNormalInitializer(standardDeviation = 1d / math.sqrt(disc_hidden_dim/2)))
  )
  val  biases = Map(
    "gen_hidden1" -> tf.variable("gen_hidden1_bias", FLOAT32, Shape(gen_hidden_dim), ZerosInitializer),
    "gen_out" -> tf.variable("gen_out_bias", FLOAT32, Shape(image_dim), ZerosInitializer),
    "disc_hidden1" -> tf.variable("disc_hidden1_bias", FLOAT32, Shape(disc_hidden_dim), ZerosInitializer),
    "disc_out" -> tf.variable("disc_out_bias", FLOAT32, Shape(1), ZerosInitializer)
  )

  // Generator
  def generator(x: Output): Output = {
    var hidden_layer = tf.matmul(x, weights("gen_hidden1"))
    hidden_layer = tf.add(hidden_layer, biases("gen_hidden1") )
    hidden_layer = tf.relu(hidden_layer)
    var out_layer = tf.matmul(hidden_layer, weights("gen_out") )
    out_layer = tf.add(out_layer, biases("gen_out") )
    out_layer = tf.sigmoid(out_layer)
    out_layer
  }


  // Discriminator
  def discriminator(x: Output): Output = {
    var hidden_layer = tf.matmul(x, weights("disc_hidden1"))
    hidden_layer = tf.add(hidden_layer, biases("disc_hidden1") )
    hidden_layer = tf.relu(hidden_layer)
    var out_layer = tf.matmul(hidden_layer, weights("disc_out") )
    out_layer = tf.add(out_layer, biases("disc_out") )
    out_layer = tf.sigmoid(out_layer)
    out_layer
  }

  // Build Networks
  // Network Inputs
  val gen_input = tf.placeholder(FLOAT32, Shape(-1, noise_dim), "input_noise")
  val disc_input = tf.placeholder(FLOAT32, Shape(-1, image_dim), "disc_input")

  // Build Generator Network
  val gen_sample = generator(gen_input)

  // Build 2 Discriminator Networks (one from noise input, one from generated samples)
  val disc_real = discriminator(disc_input)
  val disc_fake = discriminator(gen_sample)


  // Build Loss
  val gen_loss = tf.mean(tf.log(Tensor.ones(FLOAT32, Shape(batch_size)).toOutput - disc_fake + 0.000001))
  val disc_loss = tf.mean(tf.log(disc_real + 0.00000001) + tf.log(Tensor.ones(FLOAT32, Shape(batch_size)).toOutput - discriminator(gen_sample.stopGradient()) + 0.00000001))


  // Build Optimizers
  val optimizer_gen = tf.train.Adam(learning_rate)
  val optimizer_disc = tf.train.Adam(learning_rate/5)

  // Training Variables for each optimizer
  // By default in TensorFlow, all variables are updated by each optimizer, so we
  // need to precise for each one of them the specific variables to update.
  // Generator Network Variables
  val gen_vars: Set[Variable] = weights.filter(_._1.startsWith("gen")).values.toSet ++ biases.filter(_._1.startsWith("gen")).values.toSet
  // Discriminator Network Variables
  val disc_vars: Set[Variable] = weights.filter(_._1.startsWith("disc")).values.toSet ++ biases.filter(_._1.startsWith("disc")).values.toSet

  println(gen_vars)
  println(disc_vars)

  // Create training operations
  val train_gen = optimizer_gen.minimize(gen_loss, variables=gen_vars, name = "gen_min")
  val train_disc = optimizer_disc.minimize(disc_loss * (-1d), variables=disc_vars, name = "disc_min")

  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

  // Training
  for(i <- 0 until num_steps) {
    // Prepare Data
    // Get the next batch of MNIST data (only images are needed, not labels)
    var from = (i * batch_size) % mnist.shape(0)
    if (from + batch_size >=  mnist.shape(0)) {
      from = 0
    }
    val to = from + batch_size
    // println(from +","+ to)
    val batch_x = mnist(from :: to, ::, ::).reshape(Shape(batch_size, image_dim)).cast(FLOAT32)
    // Generate noise to feed to the generator
    val batch_z = gen_rand(batch_size, noise_dim)

    //plot(batch_x(1).reshape(Shape(28, 28)))

    // Train
    val feeds = FeedMap(Map(disc_input -> batch_x, gen_input -> batch_z))
    session.run(feeds = feeds, targets = train_disc)
    session.run(feeds = feeds, targets = train_gen)
    val res = session.run(feeds = feeds, fetches = Seq(gen_loss, disc_loss))
    if (i % 2000 == 0 || i == 1) {
      //plot(batch_x(10).reshape(Shape(28, 28)))
      //plot(batch_x(20).reshape(Shape(28, 28)))
      println(s"Step ${i} Generator Loss: ${res(0).scalar}  Discriminator Loss: ${res(1).scalar} ")
    }
    session.deleteNativeReferences()

  }

  // Testing
  val batch_z = gen_rand(batch_size, noise_dim)

  val feeds = FeedMap(Map(gen_input -> batch_z))
  val res_imgs = session.run(feeds = feeds, fetches = gen_sample)

  plot(res_imgs(0).reshape(Shape(28, 28)))
  plot(res_imgs(10).reshape(Shape(28, 28)))
  //plot(res_imgs(20).reshape(Shape(28, 28)))

  val res_w: Tensor = session.run(feeds = feeds, fetches = biases("gen_hidden1").toOutput)

  println(res_w.summarize())



}
