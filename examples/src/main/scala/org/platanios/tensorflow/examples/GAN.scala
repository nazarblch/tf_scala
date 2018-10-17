package org.platanios.tensorflow.examples

import java.nio.file.Paths
import java.util.Random

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.client.FeedMap
import org.platanios.tensorflow.api.learn.layers.Discriminator
import org.platanios.tensorflow.api.learn.models.GANModel
import org.platanios.tensorflow.data.image.MNISTLoader
import org.platanios.tensorflow.api.ops.NN.{CNNDataFormat, NWCFormat, SameConvPadding}

object GAN extends App {

  val p = 100
  val batch_size = 100

  val dataSet = MNISTLoader.load(Paths.get("datasets/MNIST"))

  def gen_rand(n: Int, p: Int): Tensor = {
    val r = new Random()
    Tensor.fromArrayFloat(Array.fill(n)(
      Array.fill(p)(r.nextGaussian())
    ).flatten.map(_.toFloat)
    ).reshape(Shape(n, p))
  }

  def genData(from: Int, to: Int): Tensor = {
    val n = dataSet.trainImages.shape(0)
    dataSet.trainImages.slice(from % n :: to % n).cast(FLOAT32)
  }


  val e = tf.placeholder(FLOAT32, Shape(batch_size, p))
  val x = tf.placeholder(FLOAT32, Shape(batch_size, 28, 28, 1))


  val model = {
    val generator = tf.learn.Linear("Layer_1/Linear",  100) >> tf.learn.ReLU("Layer_1/ReLU", 0.1f) >>
                    tf.learn.Linear("Layer_2/Linear",  100) >> tf.learn.ReLU("Layer_2/ReLU", 0.1f) >>
                    tf.learn.Linear("Layer_3/Linear",  28 * 28) >> tf.learn.Reshape("reshape", Shape(batch_size, 28, 28, 1))

    val discriminator =
//     tf.learn.Conv2D("Discriminator/Layer_0/Conv2D", Shape(4, 4, 1, 20), 2, 2, SameConvPadding) >>
//      tf.learn.AddBias("Discriminator/Layer_0/Bias") >>
//      tf.learn.ReLU("Discriminator/Layer_0/ReLU", 0.1f) >>
//      tf.learn.MaxPool("Discriminator/Layer_0/MaxPool" , Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
//      tf.learn.Conv2D("Discriminator/Layer_1/Conv2D" , Shape(2, 2, 20, 32), 1, 1, SameConvPadding) >>
//      tf.learn.AddBias("Discriminator/Bias_1" ) >>
//      tf.learn.ReLU("Discriminator/Layer_1/ReLU" , 0.1f) >>
//      tf.learn.MaxPool("Discriminator/Layer_1/MaxPool" , Seq(1, 2, 2, 1), 1, 1, SameConvPadding) >>
      tf.learn.Flatten("Discriminator/Layer_3/Flatten" ) >>
     // tf.learn.Dropout("Discriminator/Layer_3/Drop" , 0.5f) >>
        tf.learn.Linear("Discriminator/Layer_2/Linear" , 100) >> tf.learn.ReLU("Discriminator/Layer_2/ReLU" , 0.1f) >>
      tf.learn.Linear("Discriminator/Layer_3/Linear" , 100) >> tf.learn.ReLU("Discriminator/Layer_3/ReLU" , 0.1f) >>
      tf.learn.Linear("Discriminator/OutputLayer/Linear" , 1)

    new GANModel(e, generator, x, Discriminator("Disc", discriminator), tf.train.Adam(0.0002), tf.learn.ClipGradientsByGlobalNorm(1.0f))
  }


  val gOp = model.buildGeneratorOp().op
  val dOp = model.buildDiscriminatorOp()
  val clipOp = model.buildDiscriminatorClipOp()

  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())




  for (i <- 0 to 10000) {

    val eb = gen_rand(batch_size, p)
    val xb = genData(batch_size * i, batch_size * i + batch_size)
    val feeds = FeedMap(Map(
      e -> eb,
      x -> xb
    ))


    for(j <- 0 to 0) {
      session.run(feeds = feeds, targets = dOp)
      session.run(feeds = feeds, targets = clipOp)
    }
    session.run(feeds = feeds, targets = gOp)
    val res = session.run(feeds = feeds, fetches = Seq(model.getLossValue(), model.generate()))

    println(res.head.scalar)
    val xg = res(1)
    // println(xg(1).summarize(28))
    if(i % 100 == 0) {
      //VanillaGAN.plot(xb(0))
      //VanillaGAN.plot(xb(1))
    }

    xg.close()
    eb.close()
    xb.close()

    session.deleteNativeReferences()
  }


}
