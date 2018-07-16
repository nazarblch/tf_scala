package native_types.core.framework;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static native_types.data_types.CppDataTypes.DT_INT64;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/allocator.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_shape.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/types.pb.h"
})

/** Base class for TensorShape and PartialTensorShape.
 *  The class is templatized by either TensorShape or PartialTensorShape to
 *  allow skipping known/unknown checks in the TensorShape case, but the
 *  representation is shared exactly for fast conversion. */
@Name("tensorflow::TensorShapeBase<tensorflow::TensorShape>") @NoOffset public class TensorShapeBase extends TensorShapeRep  {

//    @Documented @Retention(RetentionPolicy.RUNTIME)
//    @Target({ElementType.METHOD, ElementType.PARAMETER})
//    @Cast({"tensorflow::gtl::ArraySlice", "&"}) @Adapter("ArraySliceAdapter")
//    public @interface ArraySlice { String value() default ""; }

    // static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorShapeBase(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public TensorShapeBase(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public TensorShapeBase position(long position) {
        return (TensorShapeBase)super.position(position);
    }

    /** \brief Construct a {@code TensorShapeBase} from the provided sizes.
     *  REQUIRES: {@code dim_sizes[i] >= 0} (or >= -1 for PartialTensorShape) */
//    public TensorShapeBase(@Cast("tensorflow::int64*") @ArraySlice LongPointer dim_sizes) { super((Pointer)null); allocate(dim_sizes); }
//    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice LongPointer dim_sizes);
//    public TensorShapeBase(@Cast("tensorflow::int64*") @ArraySlice LongBuffer dim_sizes) { super((Pointer)null); allocate(dim_sizes); }
//    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice LongBuffer dim_sizes);
//    public TensorShapeBase(@Cast("tensorflow::int64*") @ArraySlice long... dim_sizes) { super((Pointer)null); allocate(dim_sizes); }
//    private native void allocate(@Cast("tensorflow::int64*") @ArraySlice long... dim_sizes);

    /** Construct an empty TensorShape, or an unknown rank PartialTensorShape */
    public TensorShapeBase() { super((Pointer)null); allocate(); }
    private native void allocate();

    // public TensorShapeBase(@Const @ByRef TensorShapeProto proto) { super((Pointer)null); allocate(proto); }
    // private native void allocate(@Const @ByRef TensorShapeProto proto);

    /** Returns {@code true} iff {@code proto} is a valid tensor shape. */
    // For TensorShape, the proto shape must be fully defined.
    // public static native @Cast("bool") boolean IsValid(@Const @ByRef TensorShapeProto proto);

    /** Returns {@code OK} iff {@code proto} is a valid tensor shape, and a descriptive error
     *  status otherwise. */
    // public static native @ByVal Status IsValidShape(@Const @ByRef TensorShapeProto proto);

    /** \brief Add a dimension to the end ("inner-most").
     *  REQUIRES: {@code size >= 0} */
    public native void AddDim(@Cast("tensorflow::int64") long size);

    /** Appends all the dimensions from {@code shape}. */
    public native void AppendShape(@Const @ByRef TensorShapeBase shape);

    /** \brief Insert a dimension somewhere in the {@code TensorShape}.
     *  REQUIRES: {@code 0 <= d <= dims()}
     *  REQUIRES: {@code size >= 0} */
    public native void InsertDim(int d, @Cast("tensorflow::int64") long size);

    /** \brief Modifies the size of the dimension {@code d} to be {@code size}
     *  REQUIRES: {@code 0 <= d < dims()}
     *  REQUIRES: {@code size >= 0} */
    public native void set_dim(int d, @Cast("tensorflow::int64") long size);

    /** \brief Removes dimension {@code d} from the {@code TensorShape}.
     *  REQUIRES: {@code 0 <= d < dims()} */
    public native void RemoveDim(int d);

    /** \brief Removes last {@code n} dimensions from the {@code TensorShape}.
     *  REQUIRES: {@code 0 <= n <= dims()} */
    public native void RemoveLastDims(int n);

    /** \brief Removes the dimensions in range {@code [begin:end)} from {@code TensorShape}.
     *  Negative values of {@code end} are interpreted as {@code dims() + end + 1} (as in
     *  Python). The same is true for negative values of {@code begin}. REQUIRES:
     *  {@code -(dims()+1) <= begin <= dims()} REQUIRES: {@code -(dims()+1) <= end <= dims()} */
    public native void RemoveDimRange(int begin, int end);

    /** Return whether the rank is unknown */
    public native @Cast("bool") boolean unknown_rank();

    /** Return the number of dimensions in the tensor.
     *  Can be -1 meaning unknown rank for PartialTensorShape. */
    public native int dims();

    /** \brief Returns the number of elements in dimension {@code d}.
     *  REQUIRES: {@code 0 <= d < dims()} */
    // TODO(touts): Rename to `dimension()` to match
    // `Eigen::Tensor::dimension()`?
    public native @Cast("tensorflow::int64") long dim_size(int d);

    /** Returns sizes of all dimensions. */
    // Returns an empty list for unknown rank PartialTensorShape.
    // public native @ByVal LongVector dim_sizes();

    /** Return true iff the rank and all of the dimensions are well defined */
    // TODO(irving): Rename to is_fully_defined now that it's fast.
    public native @Cast("bool") boolean IsFullyDefined();

    public Tensor toTensor() {
        long[] data = new long[dims()];
        for (int i = 0; i < dims(); i++) {
            data[i] = dim_size(i);
        }
        long[] ss = {dims()};
        return new TensorFactory<Long>(DT_INT64, ss).createFromArray(data);
    }

    /** Fill {@code *proto} from {@code *this}. */
    // public native void AsProto(TensorShapeProto proto);

    /** For iterating through the dimensions. */
    // public native @ByVal TensorShapeIter begin();
    // public native @ByVal TensorShapeIter end();
}

/** Outputs {@code TensorShapeBase} to {@code std::ostream}. */