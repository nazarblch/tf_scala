package native_types.core.framework;

import native_types.adapters.ArraySlice;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import sun.java2d.Disposer;

import java.nio.*;

@Platform(include = {
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor.h",
        // "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/allocator.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_shape.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.pb.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

/** Represents the shape of a Tensor.
 *
 *  A tensor's shape is denoted by its number of dimensions and a size for each
 *  dimension.  For example, a Tensor represented by a 3 x 4 matrix would have
 *  a shape of 2-D, [3,4].
 *
 *  If you know the exact shape of your Tensor when you create the TensorShape
 *  object, you can specify it then, or you can create a TensorShape with
 *  zero dimensions and one element, and call AddDim() to add dimensions later. */
@Namespace("tensorflow") public class TensorShape extends TensorShapeBase {
    // static { Loader.load(); }

    public TensorShape(@Cast("tensorflow::int64*") @ArraySlice LongPointer dim_sizes) { super((Pointer)null); allocate(dim_sizes); }
    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice LongPointer dim_sizes);
    public TensorShape(@Cast("tensorflow::int64*") @ArraySlice LongBuffer dim_sizes) { super((Pointer)null); allocate(dim_sizes); }
    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice LongBuffer dim_sizes);
    public TensorShape(@Cast("tensorflow::int64*") @ArraySlice long... dim_sizes) {
        super((Pointer)null);
        allocate(dim_sizes);
    }
    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice long... dim_sizes);
    public TensorShape() { super((Pointer)null); allocate(); }
    private native void allocate();

    // public TensorShape(@Const @ByRef TensorShapeProto proto) { super((Pointer)null); allocate(proto); }
    // private native void allocate(@Const @ByRef TensorShapeProto proto);
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorShape(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public TensorShape(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public TensorShape position(long position) {
        return (TensorShape)super.position(position);
    }


    /** Allow a TensorShape to be used as a PartialTensorShape without copying */
    // public native @Const @ByRef @Name("operator const tensorflow::PartialTensorShape&") PartialTensorShape asPartialTensorShape();  // NOLINT(runtime/explicit)

    /** Returns true if {@code *this} and {@code b} have the same sizes. Ignores
     *  dimension names. */
    public native @Cast("bool") boolean IsSameSize(@Const @ByRef TensorShape b);
    public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef TensorShape b);
    public native @Cast("bool") @Name("operator !=") boolean notEquals(@Const @ByRef TensorShape b);

    /** Fill {@code *dsizes} from {@code *this}. */

    /** Same as {@code AsEigenDSizes()} but allows for {@code NDIMS > dims()} -- in
     *  which case we pad the rest of the sizes with 1. */
    // These CHECK fail to ease debugging.
    // REQUIRES: dims() == NDIMS

}