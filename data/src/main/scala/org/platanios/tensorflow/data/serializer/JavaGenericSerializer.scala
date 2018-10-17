package org.platanios.tensorflow.data.serializer

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

import scala.reflect.ClassTag

trait JavaGenericSerializer {

  protected def genericLoad[T : ClassTag](path: String) : T = {
    val fis = new FileInputStream(path)
    val oin = new ObjectInputStream( fis )
    val obj = oin.readObject.asInstanceOf[T]
    oin.close()
    obj
  }

  protected def genericSave[T](smth : T, path: String) : Unit = {
    val fos = new FileOutputStream(path)
    val o = new ObjectOutputStream( fos )
    o writeObject smth
    o.close()
  }
}