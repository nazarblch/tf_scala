package native_types.core.framework;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/allocator.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_shape.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.pb.h"
})

/** Internal representation for both TensorShape and PartialTensorShape. */
@Namespace("tensorflow") @NoOffset public class TensorShapeRep extends Pointer {
    // static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorShapeRep(Pointer p) { super(p); }


    /** Copy the specified shape */
    public TensorShapeRep(@Const @ByRef TensorShapeRep b) { super((Pointer)null); allocate(b); }
    private native void allocate(@Const @ByRef TensorShapeRep b);
    public native @Name("operator =") void put(@Const @ByRef TensorShapeRep b);

    /** Move the specified shape.  After moving, <b> is safe for destruction and */
    // can be reassigned into, but its dimensions and number of elements can be
    // nonsensical (e.g., negative dimension sizes, or number of elements not
    // properly recomputed).

    /** Clear a tensor shape, producing the scalar shape. */
    public native void Clear();

    // Maximum number of dimensions in a tensor.
    // It's 254 because 255 = kUnknownRank is used to represent unknown rank.

    ///
    public static native int MaxDimensions();

    /** \brief Returns the number of elements in the tensor.
     *
     *  We use {@code int64} and not {@code size_t} to be compatible with {@code Eigen::Tensor}
     *  which uses {@code ptrdiff_t}.  For PartialTensorShape, -1 means not fully
     *  defined. */
    public native @Cast("tensorflow::int64") long num_elements();

    /** For error messages. */
    public native @StdString BytePointer DebugString();
    // public static native @StdString BytePointer DebugString(@Const @ByRef TensorShapeProto proto);

    public native void DumpRep();
}
