package org.platanios.tensorflow.api.tensors

import org.platanios.tensorflow.api.core._
import org.platanios.tensorflow.api.core.client.Session
import org.platanios.tensorflow.api.core.exception._
import org.platanios.tensorflow.api.implicits.Implicits._
import org.platanios.tensorflow.api.io.NPY
import org.platanios.tensorflow.api.ops.{Basic, Output}
import org.platanios.tensorflow.api.tensors.ops.Basic.{BasicOps, stack}
import org.platanios.tensorflow.api.tensors.ops.{Math, Random}
import org.platanios.tensorflow.api.types._
import org.platanios.tensorflow.api.utilities.{Closeable, Disposer, NativeHandleWrapper}
import org.platanios.tensorflow.api.utilities.Proto.{Serializable => ProtoSerializable}
import org.platanios.tensorflow.jni.{Tensor => NativeTensor}
import org.platanios.tensorflow.jni.generated.tensors.{Sparse => NativeTensorOpsSparse}
import com.google.protobuf.ByteString
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import org.tensorflow.framework.TensorProto
import java.nio._
import java.nio.charset.Charset
import java.nio.file.Path

import native_types.core.framework.{TensorShape, Tensor => JTensor}

import scala.collection.{TraversableLike, breakOut, mutable}
import scala.language.{higherKinds, postfixOps}

/** Tensor (i.e., multi-dimensional array).
  *
  * Tensors are the main data structure underlying all operations in TensorFlow. They represent multi-dimensional arrays
  * of various data types (e.g., [[FLOAT32]]). Operations involving tensors can be of two types:
  *   - '''Eager:''' Operations directly executed on the tensor arguments, returning a new tensor. For example:
  *     {{{
  *       val a = Tensor(2.0, 4.5, 3.0, -1.2)
  *       val b = Tensor(Tensor(0.2, 0.4), Tensor(-2.3, 5.0))
  *       a.reshape(Shape(2, 2)) + b == Tensor(Tensor(2.2, 4.9), Tensor(0.7, 3.8))
  *     }}}
  *   - '''Symbolic:''' Operations that need to be constructed as part of a computational [[Graph]] before being
  *     executing using a [[Session]]. For example:
  *     {{{
  *       val a = tf.placeholder(FLOAT64, Shape(4))               // Symbolic placeholder for value of a
  *       val b = tf.placeholder(FLOAT64, Shape(2, 2))            // Symbolic placeholder for the value of b
  *       val add = tf.reshape(a, Shape(2, 2)) + b                // Symbolic representation of the computation
  *       val result = Session.run(
  *         feeds = Map(
  *           a -> Tensor(2.0, 4.5, 3.0, -1.2),
  *           b -> Tensor(Tensor(0.2, 0.4), Tensor(-2.3, 5.0))),
  *         fetches = add)                                        // Performs the actual computation
  *       result == Tensor(Tensor(2.2, 4.9), Tensor(0.7, 3.8))
  *     }}}
  *     // TODO: [OPS] Update doc when we enrich op outputs similarly to tensors.
  *
  * @author Emmanouil Antonios Platanios
  */
class TMPTensor (
                                  nativeTensor: JTensor,
                                    override protected val closeFn: () => Unit
                            )
  extends
    // TensorLike
   Closeable
  // with ProtoSerializable
   {

  /** Lock for the native handle. */
  private[api] object Lock
  private[api] def NativeHandleLock = Lock

  /** Data type of this tensor. */
  val dataType: DataType = DataType.fromCValue(nativeTensor.dtype())

  /** Shape of this tensor. */
  val shape: Shape = Shape.fromSeq(Seq.range(0, nativeTensor.dims()).map(i => nativeTensor.dim_size(i).toInt))

  /** Rank of this tensor (i.e., number of dimensions). */
  def rank: Int = shape.rank

  /** Number of elements contained in this tensor. */
  def size: Long = shape.numElements

  private[api] def getElementAtFlattenedIndex(index: Int): dataType.ScalaType = {
    val data = nativeTensor.tensor_data()
    val buffer = data.asBuffer()
    val value = dataType match {
      case STRING =>
        val offset = INT64.byteSize * size.toInt + INT64.getElementFromBuffer(buffer, index * INT64.byteSize).toInt
        dataType.getElementFromBuffer(buffer, offset)
      case _ => dataType.getElementFromBuffer(buffer, index * dataType.byteSize)
    }
    data.deallocate()
    buffer.clear()
    value
  }

  @throws[InvalidShapeException]
  def scalar: dataType.ScalaType = {
    if (size != 1)
      throw InvalidShapeException(
        "'Tensor.scalar' can only be called for scalar tensors (i.e., containing only one element).")
    getElementAtFlattenedIndex(0)
  }

  // def getBuffer: Array[Byte] = NativeTensor.buffer(resolve()).order(ByteOrder.nativeOrder).array()

  def entriesIterator: Iterator[dataType.ScalaType] = new Iterator[dataType.ScalaType] {
    private var i             : Int  = 0
    private val data = nativeTensor.tensor_data()
    private val buffer      : ByteBuffer = data.asBuffer()
    private val stringOffset: Int        = INT64.byteSize * TMPTensor.this.size.toInt

    override def hasNext: Boolean = {
      val hasNext = i < TMPTensor.this.size.toInt
      if (!hasNext && buffer.capacity() > 0) {
        buffer.clear()
        data.deallocate()
      }
      hasNext
    }

    override def next(): dataType.ScalaType = {
      val nextElement = dataType match {
        case STRING =>
          dataType.getElementFromBuffer(
            buffer, stringOffset + INT64.getElementFromBuffer(buffer, i * INT64.byteSize).toInt)
        case _ =>
          dataType.getElementFromBuffer(buffer, i * dataType.byteSize)
      }
      i += 1
      nextElement
    }
  }


  override def toString: String = s"$dataType$shape"

  /** Returns this tensor. */
  // override def toTensor: Tensor = ???

  /** Returns an [[TensorIndexedSlices]] that has the same value as this [[TensorLike]].
    *
    * @return [[TensorIndexedSlices]] that has the same value as this [[TensorLike]].
    */
//  override def toTensorIndexedSlices: TensorIndexedSlices = {
//    val denseShape = shape.toTensor(INT32)
//    TensorIndexedSlices(indices = 0 until shape(0), values = this, denseShape = denseShape)
//  }

  //override def toProto: TensorProto = toTensorProto

  /** Constructs and returns a [[TensorProto]] object that represents this tensor.
    *
    * @return Constructed [[TensorProto]].
    */
  // def toTensorProto: TensorProto = Tensor.makeProto(this)

  /** Writes this tensor to the provided file, using the Numpy (i.e., `.npy`) file format. Note that this method will
    * replace the file, if it already exists. */
  // def writeNPY(file: Path, fortranOrder: Boolean = false): Unit = NPY.write(this, file, fortranOrder)

//  override def equals(that: Any): Boolean = that match {
//    case that: Tensor =>
//      this.shape == that.shape &&
//        this.dataType == that.dataType &&
//        Math.all(Math.equal(this, that)).scalar.asInstanceOf[Boolean]
//    case _ => false
//  }

  override def hashCode(): Int = {
    // TODO: !!! [TENSORS] Find a more efficient way to do this.
    val prime = 31
    var result = 1
    result = prime * result + dataType.hashCode
    result = prime * result + shape.hashCode
    result = prime * result + nativeTensor.hashCode()
    result
  }

  // def toOutput: Output = Basic.constant(this.cpu())

  /** Closes this [[Tensor]] and releases any resources associated with it. Note that an [[Tensor]] is not
    * usable after it has been closed. */
  override def close(): Unit = closeFn()

  /** Device on which this tensor is stored. */
  // override val device: String = ???
}