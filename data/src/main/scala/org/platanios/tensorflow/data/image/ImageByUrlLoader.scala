package org.platanios.tensorflow.data.image

import java.awt.{FlowLayout, Image}
import java.awt.image.{BufferedImage, WritableRaster}
import java.net.URL

import com.typesafe.scalalogging.Logger
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.data.Loader
import org.slf4j.LoggerFactory
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, JFrame, JLabel}
import org.platanios.tensorflow.api.core.Shape

object ImageByUrlLoader extends Loader {
  override protected val logger = Logger(LoggerFactory.getLogger("Image Loader"))

  def displayImage(img: BufferedImage): Unit = {
    val icon=new ImageIcon(img);
    val frame=new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setSize(img.getWidth,img.getHeight);
    val lbl=new JLabel();
    lbl.setIcon(icon);
    frame.add(lbl);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }



  def imageToByteArray(bImage: BufferedImage): Unit = {
    import java.io.ByteArrayOutputStream
    val bos = new ByteArrayOutputStream
    ImageIO.write(bImage, "jpg", bos)
    bos.toByteArray
  }

  def tensorToImage(t: Tensor): BufferedImage = {
      val image = new BufferedImage(t.shape(1), t.shape(2), BufferedImage.TYPE_INT_RGB)

    println(image.getHeight)

      for (x <- 0 until t.shape(1)) {
        for (y <- 0 until t.shape(2)) {
          val avg = t(0, x, y, 0).scalar.toString.toInt
          val p = (255 << 24) | (avg << 16) | (avg << 8) | avg
          image.setRGB(x, y, p)
        }
      }

      image
  }

  def load(url: String): Array[Float] = {
    val img = ImageIO.read(new URL(url).openStream())
    val w: Int = img.getWidth
    val h: Int = img.getHeight
    val size = 80

    val bimage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
    val bGr = bimage.createGraphics
    val size_img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH)
    bGr.drawImage(size_img, 0, 0, null)

    val data: Array[Float] = Array.fill(size * size)(0)


    for (x <- 0 until size) {
      for (y <- 0 until size) {

        var p: Int = bimage.getRGB(x, y)

        val a: Int = (p >> 24) & 0xff
        val r: Int = (p >> 16) & 0xff
        val g: Int = (p >> 8) & 0xff
        val b: Int = p & 0xff

        //calculate average
        val avg: Int = (r + g + b) / 3

        //replace RGB value with avg
        // p = (a << 24) | (avg << 16) | (avg << 8) | avg

        data(size * x + y) = avg.toFloat
      }
    }

    // displayImage(bimage)

    data
  }
}
