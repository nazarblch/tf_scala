package native_types.ops;

import native_types.adapters.ArraySlice;
import native_types.cc.framework.Input;
import native_types.cc.framework.InputList;
import native_types.cc.framework.Output;
import native_types.cc.framework.Scope;
import native_types.core.graph.Node;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import java.nio.FloatBuffer;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/ops/math_ops.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/ops/const_op.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/ops/math_ops_internal.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

public class math_ops {


// #ifndef TENSORFLOW_CC_OPS_MATH_OPS_H_
// #define TENSORFLOW_CC_OPS_MATH_OPS_H_

// This file is MACHINE GENERATED! Do not edit.

// #include "tensorflow/cc/framework/ops.h"
// #include "tensorflow/cc/framework/scope.h"
// #include "tensorflow/core/framework/tensor.h"
// #include "tensorflow/core/framework/tensor_shape.h"
// #include "tensorflow/core/framework/types.h"
// #include "tensorflow/core/lib/gtl/array_slice.h"

    /** \defgroup math_ops Math Ops
     *  \{
     <p>
     *  Computes the absolute value of a tensor.
     *
     *  Given a tensor {@code x}, this operation returns a tensor containing the absolute
     *  value of each element in {@code x}. For example, if x is an input element and y is
     *  an output element, this operation computes \\(y = |x|\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Abs extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Abs(Pointer p) { super(p); }

        public Abs(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output")
        Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Abs y(Output y);
    }

    /** Returns the element-wise sum of a list of tensors.
     *
     *  {@code tf.accumulate_n_v2} performs the same operation as {@code tf.add_n}, but does not
     *  wait for all of its inputs to be ready before beginning to sum. This can
     *  save memory if inputs are ready at different times, since minimum temporary
     *  storage is proportional to the output size rather than the inputs size.
     *
     *  Unlike the original {@code accumulate_n}, {@code accumulate_n_v2} is differentiable.
     *
     *  Returns a {@code Tensor} of same shape and type as the elements of {@code inputs}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * inputs: A list of {@code Tensor} objects, each with same shape and type.
     *  * shape: Shape of elements of {@code inputs}.
     *
     *  Returns:
     *  * {@code Output}: The sum tensor. */
//    @Namespace("tensorflow::ops") @NoOffset public static class AccumulateNV2 extends Pointer {
//        static { Loader.load(); }
//        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
//        public AccumulateNV2(Pointer p) { super(p); }
//
//        public AccumulateNV2(@Const @ByRef Scope scope, @ByVal InputList inputs,
//                             @ByVal PartialTensorShape shape) { super((Pointer)null); allocate(scope, inputs, shape); }
//        private native void allocate(@Const @ByRef Scope scope, @ByVal InputList inputs,
//                                     @ByVal PartialTensorShape shape);
//        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
//        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
//        public native Node node();
//
//        public native @ByRef Output sum(); public native AccumulateNV2 sum(Output sum);
//    }

    /** Computes acos of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Acos extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Acos(Pointer p) { super(p); }

        public Acos(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Acos y(Output y);
    }

    /** Computes inverse hyperbolic cosine of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Acosh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Acosh(Pointer p) { super(p); }

        public Acosh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Acosh y(Output y);
    }

    /** Returns x + y element-wise.
     *
     *  *NOTE*: {@code Add} supports broadcasting. {@code AddN} does not. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Add extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Add(Pointer p) { super(p); }

        public Add(@Const @ByRef Scope scope, @ByVal Input x,
                   @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Add z(Output z);
    }

    /** Add all input tensors element wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * inputs: Must all be the same size and shape.
     *
     *  Returns:
     *  * {@code Output}: The sum tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class AddN extends Pointer {
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public AddN(Pointer p) { super(p); }

        public AddN(@Const @ByRef Scope scope, @ByVal InputList inputs) { super((Pointer)null); allocate(scope, inputs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal InputList inputs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output sum();
        public native AddN sum(Output sum);
    }

    /** Returns x + y element-wise.
     *
     *  *NOTE*: {@code Add} supports broadcasting. {@code AddN} does not. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class AddV2 extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public AddV2(Pointer p) { super(p); }

        public AddV2(@Const @ByRef Scope scope, @ByVal Input x,
                     @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native AddV2 z(Output z);
    }

    /** Computes the "logical and" of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceAll */
    @Namespace("tensorflow::ops") @NoOffset public static class All extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public All(Pointer p) { super(p); }

        /** Optional attribute setters for All */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public All(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public All(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native All output(Output output);
    }

///
///
///
///
///
///
///

    /** Returns the argument of a complex number.
     *
     *  Given a tensor {@code input} of complex numbers, this operation returns a tensor of
     *  type {@code float} that is the argument of each element in {@code input}. All elements in
     *  {@code input} must be complex numbers of the form \\(a + bj\\), where *a*
     *  is the real part and *b* is the imaginary part.
     *
     *  The argument returned by this operation is of the form \\(atan2(b, a)\\).
     *
     *  For example:
     *
     *  <pre>{@code
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.angle(input) ==> [2.0132, 1.056]
     *  }</pre>
     *
     *  \compatibility(numpy)
     *  Equivalent to np.angle.
     *  \end_compatibility
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Angle extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Angle(Pointer p) { super(p); }

        /** Optional attribute setters for Angle */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_FLOAT */
            public native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Tout_(); public native Attrs Tout_(int Tout_);
        }
        public Angle(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public Angle(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output output(); public native Angle output(Output output);
    }

    /** Computes the "logical or" of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceAny */
    @Namespace("tensorflow::ops") @NoOffset public static class Any extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Any(Pointer p) { super(p); }

        /** Optional attribute setters for Any */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Any(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Any(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Any output(Output output);
    }

///
///

    /** Returns the truth value of abs(x-y) < tolerance element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class ApproximateEqual extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public ApproximateEqual(Pointer p) { super(p); }

        /** Optional attribute setters for ApproximateEqual */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to 1e-05 */
            public native @ByVal Attrs Tolerance(float x);

            public native float tolerance_(); public native Attrs tolerance_(float tolerance_);
        }
        public ApproximateEqual(@Const @ByRef Scope scope, @ByVal Input x,
                                @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public ApproximateEqual(@Const @ByRef Scope scope, @ByVal Input x,
                                @ByVal Input y, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, y, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tolerance(float x);

        public native @ByRef Output z(); public native ApproximateEqual z(Output z);
    }

    /** Returns the index with the largest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * dimension: int32 or int64, must be in the range {@code [-rank(input), rank(input))}.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class ArgMax extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public ArgMax(Pointer p) { super(p); }

        /** Optional attribute setters for ArgMax */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_INT64 */
            public native @ByVal Attrs OutputType(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int output_type_(); public native Attrs output_type_(int output_type_);
        }
        public ArgMax(@Const @ByRef Scope scope, @ByVal Input input,
                      @ByVal Input dimension) { super((Pointer)null); allocate(scope, input, dimension); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input dimension);
        public ArgMax(@Const @ByRef Scope scope, @ByVal Input input,
                      @ByVal Input dimension, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, dimension, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input dimension, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs OutputType(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output output(); public native ArgMax output(Output output);
    }

    /** Returns the index with the smallest value across dimensions of a tensor.
     *
     *  Note that in case of ties the identity of the return value is not guaranteed.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * dimension: int32 or int64, must be in the range {@code [-rank(input), rank(input))}.
     *  Describes which dimension of the input Tensor to reduce across. For vectors,
     *  use dimension = 0.
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class ArgMin extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public ArgMin(Pointer p) { super(p); }

        /** Optional attribute setters for ArgMin */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_INT64 */
            public native @ByVal Attrs OutputType(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int output_type_(); public native Attrs output_type_(int output_type_);
        }
        public ArgMin(@Const @ByRef Scope scope, @ByVal Input input,
                      @ByVal Input dimension) { super((Pointer)null); allocate(scope, input, dimension); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input dimension);
        public ArgMin(@Const @ByRef Scope scope, @ByVal Input input,
                      @ByVal Input dimension, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, dimension, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input dimension, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs OutputType(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output output(); public native ArgMin output(Output output);
    }

    /** Computes asin of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Asin extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Asin(Pointer p) { super(p); }

        public Asin(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Asin y(Output y);
    }

    /** Computes inverse hyperbolic sine of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Asinh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Asinh(Pointer p) { super(p); }

        public Asinh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Asinh y(Output y);
    }

    /** Computes atan of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Atan extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Atan(Pointer p) { super(p); }

        public Atan(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Atan y(Output y);
    }

    /** Computes arctangent of {@code y/x} element-wise, respecting signs of the arguments.
     *
     *  This is the angle \( \theta \in [-\pi, \pi] \) such that
     *  \[ x = r \cos(\theta) \]
     *  and
     *  \[ y = r \sin(\theta) \]
     *  where \(r = \sqrt(x^2 + y^2) \).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Atan2 extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Atan2(Pointer p) { super(p); }

        public Atan2(@Const @ByRef Scope scope, @ByVal Input y,
                     @ByVal Input x) { super((Pointer)null); allocate(scope, y, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input y,
                                     @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Atan2 z(Output z);
    }

    /** Computes inverse hyperbolic tangent of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Atanh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Atanh(Pointer p) { super(p); }

        public Atanh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Atanh y(Output y);
    }

    /** Multiplies slices of two tensors in batches.
     *
     *  Multiplies all slices of {@code Tensor} {@code x} and {@code y} (each slice can be
     *  viewed as an element of a batch), and arranges the individual results
     *  in a single output tensor of the same batch size. Each of the
     *  individual slices can optionally be adjointed (to adjoint a matrix
     *  means to transpose and conjugate it) before multiplication by setting
     *  the {@code adj_x} or {@code adj_y} flag to {@code True}, which are by default {@code False}.
     *
     *  The input tensors {@code x} and {@code y} are 2-D or higher with shape {@code [..., r_x, c_x]}
     *  and {@code [..., r_y, c_y]}.
     *
     *  The output tensor is 2-D or higher with shape {@code [..., r_o, c_o]}, where:
     *
     *      r_o = c_x if adj_x else r_x
     *      c_o = r_y if adj_y else c_y
     *
     *  It is computed as:
     *
     *      output[..., :, :] = matrix(x[..., :, :]) * matrix(y[..., :, :])
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * x: 2-D or higher with shape {@code [..., r_x, c_x]}.
     *  * y: 2-D or higher with shape {@code [..., r_y, c_y]}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * adj_x: If {@code True}, adjoint the slices of {@code x}. Defaults to {@code False}.
     *  * adj_y: If {@code True}, adjoint the slices of {@code y}. Defaults to {@code False}.
     *
     *  Returns:
     *  * {@code Output}: 3-D or higher with shape {@code [..., r_o, c_o]} */
    @Namespace("tensorflow::ops") @NoOffset public static class BatchMatMul extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public BatchMatMul(Pointer p) { super(p); }

        /** Optional attribute setters for BatchMatMul */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If {@code True}, adjoint the slices of {@code x}. Defaults to {@code False}.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs AdjX(@Cast("bool") boolean x);

            /** If {@code True}, adjoint the slices of {@code y}. Defaults to {@code False}.
             *
             *  Defaults to false */
            public native @ByVal Attrs AdjY(@Cast("bool") boolean x);

            public native @Cast("bool") boolean adj_x_(); public native Attrs adj_x_(boolean adj_x_);
            public native @Cast("bool") boolean adj_y_(); public native Attrs adj_y_(boolean adj_y_);
        }
        public BatchMatMul(@Const @ByRef Scope scope, @ByVal Input x,
                           @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public BatchMatMul(@Const @ByRef Scope scope, @ByVal Input x,
                           @ByVal Input y, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, y, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs AdjX(@Cast("bool") boolean x);
        public static native @ByVal Attrs AdjY(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native BatchMatMul output(Output output);
    }

    /** Compute the regularized incomplete beta integral \\(I_x(a, b)\\).
     *
     *  The regularized incomplete beta integral is defined as:
     *
     *
     *  \\(I_x(a, b) = \frac{B(x; a, b)}{B(a, b)}\\)
     *
     *  where
     *
     *
     *  \\(B(x; a, b) = \int_0^x t^{a-1} (1 - t)^{b-1} dt\\)
     *
     *
     *  is the incomplete beta function and \\(B(a, b)\\) is the *complete*
     *  beta function.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Betainc extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Betainc(Pointer p) { super(p); }

        public Betainc(@Const @ByRef Scope scope, @ByVal Input a,
                       @ByVal Input b, @ByVal Input x) { super((Pointer)null); allocate(scope, a, b, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Betainc z(Output z);
    }

    /** Counts the number of occurrences of each value in an integer array.
     *
     *  Outputs a vector with length {@code size} and the same dtype as {@code weights}. If
     *  {@code weights} are empty, then index {@code i} stores the number of times the value {@code i} is
     *  counted in {@code arr}. If {@code weights} are non-empty, then index {@code i} stores the sum of
     *  the value in {@code weights} at each index where the corresponding value in {@code arr} is
     *  {@code i}.
     *
     *  Values in {@code arr} outside of the range [0, size) are ignored.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * arr: int32 {@code Tensor}.
     *  * size: non-negative int32 scalar {@code Tensor}.
     *  * weights: is an int32, int64, float32, or float64 {@code Tensor} with the same
     *  shape as {@code arr}, or a length-0 {@code Tensor}, in which case it acts as all weights
     *  equal to 1.
     *
     *  Returns:
     *  * {@code Output}: 1D {@code Tensor} with length equal to {@code size}. The counts or summed weights for
     *  each value in the range [0, size). */
    @Namespace("tensorflow::ops") @NoOffset public static class Bincount extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Bincount(Pointer p) { super(p); }

        public Bincount(@Const @ByRef Scope scope, @ByVal Input arr,
                        @ByVal Input size, @ByVal Input weights) { super((Pointer)null); allocate(scope, arr, size, weights); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input arr,
                                     @ByVal Input size, @ByVal Input weights);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output bins(); public native Bincount bins(Output bins);
    }

    /** Bucketizes 'input' based on 'boundaries'.
     *
     *  For example, if the inputs are
     *      boundaries = [0, 10, 100]
     *      input = [[-5, 10000]
     *               [150,   10]
     *               [5,    100]]
     *
     *  then the output will be
     *      output = [[0, 3]
     *                [3, 2]
     *                [1, 3]]
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Any shape of Tensor contains with int or float type.
     *  * boundaries: A sorted list of floats gives the boundary of the buckets.
     *
     *  Returns:
     *  * {@code Output}: Same shape with 'input', each value of input replaced with bucket index.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.digitize.
     *  \end_compatibility */
    @Namespace("tensorflow::ops") @NoOffset public static class Bucketize extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Bucketize(Pointer p) { super(p); }

        public Bucketize(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice FloatPointer boundaries) { super((Pointer)null); allocate(scope, input, boundaries); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice FloatPointer boundaries);
        public Bucketize(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice FloatBuffer boundaries) { super((Pointer)null); allocate(scope, input, boundaries); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice FloatBuffer boundaries);
        public Bucketize(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice float... boundaries) { super((Pointer)null); allocate(scope, input, boundaries); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @ArraySlice float... boundaries);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native Bucketize output(Output output);
    }

    /** Cast x of type SrcT to y of DstT.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Name("class tensorflow::ops::Cast") @NoOffset public static class CastOp extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public CastOp(Pointer p) { super(p); }

        public CastOp(@Const @ByRef Scope scope, @ByVal Input x, @Cast("tensorflow::DataType") int DstT) { super((Pointer)null); allocate(scope, x, DstT); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x, @Cast("tensorflow::DataType") int DstT);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native CastOp y(Output y);
    }

    /** Returns element-wise smallest integer in not less than x.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Ceil extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Ceil(Pointer p) { super(p); }

        public Ceil(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Ceil y(Output y);
    }

    /** Clips tensor values to a specified min and max.
     *
     *  Given a tensor {@code t}, this operation returns a tensor of the same type and
     *  shape as {@code t} with its values clipped to {@code clip_value_min} and {@code clip_value_max}.
     *  Any values less than {@code clip_value_min} are set to {@code clip_value_min}. Any values
     *  greater than {@code clip_value_max} are set to {@code clip_value_max}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * t: A {@code Tensor}.
     *  * clip_value_min: A 0-D (scalar) {@code Tensor}, or a {@code Tensor} with the same shape
     *  as {@code t}. The minimum value to clip by.
     *  * clip_value_max: A 0-D (scalar) {@code Tensor}, or a {@code Tensor} with the same shape
     *  as {@code t}. The maximum value to clip by.
     *
     *  Returns:
     *  * {@code Output}: A clipped {@code Tensor} with the same shape as input 't'. */
    @Namespace("tensorflow::ops") @NoOffset public static class ClipByValue extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public ClipByValue(Pointer p) { super(p); }

        public ClipByValue(@Const @ByRef Scope scope, @ByVal Input t,
                           @ByVal Input clip_value_min, @ByVal Input clip_value_max) { super((Pointer)null); allocate(scope, t, clip_value_min, clip_value_max); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input t,
                                     @ByVal Input clip_value_min, @ByVal Input clip_value_max);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native ClipByValue output(Output output);
    }

    /** Compare values of {@code input} to {@code threshold} and pack resulting bits into a {@code uint8}.
     *
     *  Each comparison returns a boolean {@code true} (if {@code input_value > threshold})
     *  or and {@code false} otherwise.
     *
     *  This operation is useful for Locality-Sensitive-Hashing (LSH) and other
     *  algorithms that use hashing approximations of cosine and {@code L2} distances;
     *  codes can be generated from an input via:
     *
     *  <pre>{@code python
     *  codebook_size = 50
     *  codebook_bits = codebook_size * 32
     *  codebook = tf.get_variable('codebook', [x.shape[-1].value, codebook_bits],
     *                             dtype=x.dtype,
     *                             initializer=tf.orthogonal_initializer())
     *  codes = compare_and_threshold(tf.matmul(x, codebook), threshold=0.)
     *  codes = tf.bitcast(codes, tf.int32)  # go from uint8 to int32
     *  # now codes has shape x.shape[:-1] + [codebook_size]
     *  }</pre>
     *
     *  **NOTE**: Currently, the innermost dimension of the tensor must be divisible
     *  by 8.
     *
     *  Given an {@code input} shaped {@code [s0, s1, ..., s_n]}, the output is
     *  a {@code uint8} tensor shaped {@code [s0, s1, ..., s_n / 8]}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Values to compare against {@code threshold} and bitpack.
     *  * threshold: Threshold to compare against.
     *
     *  Returns:
     *  * {@code Output}: The bitpacked comparisons. */
    @Namespace("tensorflow::ops") @NoOffset public static class CompareAndBitpack extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public CompareAndBitpack(Pointer p) { super(p); }

        public CompareAndBitpack(@Const @ByRef Scope scope, @ByVal Input input,
                                 @ByVal Input threshold) { super((Pointer)null); allocate(scope, input, threshold); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input threshold);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native CompareAndBitpack output(Output output);
    }

    /** Converts two real numbers to a complex number.
     *
     *  Given a tensor {@code real} representing the real part of a complex number, and a
     *  tensor {@code imag} representing the imaginary part of a complex number, this
     *  operation returns complex numbers elementwise of the form \\(a + bj\\), where
     *  *a* represents the {@code real} part and *b* represents the {@code imag} part.
     *
     *  The input tensors {@code real} and {@code imag} must have the same shape.
     *
     *  For example:
     *
     *  <pre>{@code
     *  # tensor 'real' is [2.25, 3.25]
     *  # tensor `imag` is [4.75, 5.75]
     *  tf.complex(real, imag) ==> [[2.25 + 4.75j], [3.25 + 5.75j]]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The out tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Complex extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Complex(Pointer p) { super(p); }

        /** Optional attribute setters for Complex */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_COMPLEX64 */
            public native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Tout_(); public native Attrs Tout_(int Tout_);
        }
        public Complex(@Const @ByRef Scope scope, @ByVal Input real,
                       @ByVal Input imag) { super((Pointer)null); allocate(scope, real, imag); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input real,
                                     @ByVal Input imag);
        public Complex(@Const @ByRef Scope scope, @ByVal Input real,
                       @ByVal Input imag, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, real, imag, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input real,
                                     @ByVal Input imag, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output out(); public native Complex out(Output out);
    }

    /** Computes the complex absolute value of a tensor.
     *
     *  Given a tensor {@code x} of complex numbers, this operation returns a tensor of type
     *  {@code float} or {@code double} that is the absolute value of each element in {@code x}. All
     *  elements in {@code x} must be complex numbers of the form \\(a + bj\\). The absolute
     *  value is computed as \\( \sqrt{a^2 + b^2}\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class ComplexAbs extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public ComplexAbs(Pointer p) { super(p); }

        /** Optional attribute setters for ComplexAbs */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_FLOAT */
            public native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Tout_(); public native Attrs Tout_(int Tout_);
        }
        public ComplexAbs(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public ComplexAbs(@Const @ByRef Scope scope, @ByVal Input x, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output y(); public native ComplexAbs y(Output y);
    }

    /** Returns the complex conjugate of a complex number.
     *
     *  Given a tensor {@code input} of complex numbers, this operation returns a tensor of
     *  complex numbers that are the complex conjugate of each element in {@code input}. The
     *  complex numbers in {@code input} must be of the form \\(a + bj\\), where *a* is the
     *  real part and *b* is the imaginary part.
     *
     *  The complex conjugate returned by this operation is of the form \\(a - bj\\).
     *
     *  For example:
     *
     *  <pre>{@code
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.conj(input) ==> [-2.25 - 4.75j, 3.25 - 5.75j]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Conj extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Conj(Pointer p) { super(p); }

        public Conj(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native Conj output(Output output);
    }

    /** Computes cos of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cos extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cos(Pointer p) { super(p); }

        public Cos(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Cos y(Output y);
    }

    /** Computes hyperbolic cosine of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cosh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cosh(Pointer p) { super(p); }

        public Cosh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Cosh y(Output y);
    }

    /** Compute the pairwise cross product.
     *
     *  {@code a} and {@code b} must be the same shape; they can either be simple 3-element vectors,
     *  or any shape where the innermost dimension is 3. In the latter case, each pair
     *  of corresponding 3-element vectors is cross-multiplied independently.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * a: A tensor containing 3-element vectors.
     *  * b: Another tensor, of same type and shape as {@code a}.
     *
     *  Returns:
     *  * {@code Output}: Pairwise cross product of the vectors in {@code a} and {@code b}. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cross extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cross(Pointer p) { super(p); }

        public Cross(@Const @ByRef Scope scope, @ByVal Input a,
                     @ByVal Input b) { super((Pointer)null); allocate(scope, a, b); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output product(); public native Cross product(Output product);
    }

    /** Compute the cumulative product of the tensor {@code x} along {@code axis}.
     *
     *  By default, this op performs an inclusive cumprod, which means that the first
     *  element of the input is identical to the first element of the output:
     *
     *  <pre>{@code python
     *  tf.cumprod([a, b, c])  # => [a, a * b, a * b * c]
     *  }</pre>
     *
     *  By setting the {@code exclusive} kwarg to {@code True}, an exclusive cumprod is
     *  performed instead:
     *
     *  <pre>{@code python
     *  tf.cumprod([a, b, c], exclusive=True)  # => [1, a, a * b]
     *  }</pre>
     *
     *  By setting the {@code reverse} kwarg to {@code True}, the cumprod is performed in the
     *  opposite direction:
     *
     *  <pre>{@code python
     *  tf.cumprod([a, b, c], reverse=True)  # => [a * b * c, b * c, c]
     *  }</pre>
     *
     *  This is more efficient than using separate {@code tf.reverse} ops.
     *
     *  The {@code reverse} and {@code exclusive} kwargs can also be combined:
     *
     *  <pre>{@code python
     *  tf.cumprod([a, b, c], exclusive=True, reverse=True)  # => [b * c, c, 1]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * x: A {@code Tensor}. Must be one of the following types: {@code float32}, {@code float64},
     *  {@code int64}, {@code int32}, {@code uint8}, {@code uint16}, {@code int16}, {@code int8}, {@code complex64},
     *  {@code complex128}, {@code qint8}, {@code quint8}, {@code qint32}, {@code half}.
     *  * axis: A {@code Tensor} of type {@code int32} (default: 0). Must be in the range
     *  {@code [-rank(x), rank(x))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * exclusive: If {@code True}, perform exclusive cumprod.
     *  * reverse: A {@code bool} (default: False).
     *
     *  Returns:
     *  * {@code Output}: The out tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cumprod extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cumprod(Pointer p) { super(p); }

        /** Optional attribute setters for Cumprod */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If {@code True}, perform exclusive cumprod.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs Exclusive(@Cast("bool") boolean x);

            /** A {@code bool} (default: False).
             *
             *  Defaults to false */
            public native @ByVal Attrs Reverse(@Cast("bool") boolean x);

            public native @Cast("bool") boolean exclusive_(); public native Attrs exclusive_(boolean exclusive_);
            public native @Cast("bool") boolean reverse_(); public native Attrs reverse_(boolean reverse_);
        }
        public Cumprod(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input axis) { super((Pointer)null); allocate(scope, x, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input axis);
        public Cumprod(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Exclusive(@Cast("bool") boolean x);
        public static native @ByVal Attrs Reverse(@Cast("bool") boolean x);

        public native @ByRef Output out(); public native Cumprod out(Output out);
    }

    /** Compute the cumulative sum of the tensor {@code x} along {@code axis}.
     *
     *  By default, this op performs an inclusive cumsum, which means that the first
     *  element of the input is identical to the first element of the output:
     *
     *  <pre>{@code python
     *  tf.cumsum([a, b, c])  # => [a, a + b, a + b + c]
     *  }</pre>
     *
     *  By setting the {@code exclusive} kwarg to {@code True}, an exclusive cumsum is
     *  performed instead:
     *
     *  <pre>{@code python
     *  tf.cumsum([a, b, c], exclusive=True)  # => [0, a, a + b]
     *  }</pre>
     *
     *  By setting the {@code reverse} kwarg to {@code True}, the cumsum is performed in the
     *  opposite direction:
     *
     *  <pre>{@code python
     *  tf.cumsum([a, b, c], reverse=True)  # => [a + b + c, b + c, c]
     *  }</pre>
     *
     *  This is more efficient than using separate {@code tf.reverse} ops.
     *
     *  The {@code reverse} and {@code exclusive} kwargs can also be combined:
     *
     *  <pre>{@code python
     *  tf.cumsum([a, b, c], exclusive=True, reverse=True)  # => [b + c, c, 0]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * x: A {@code Tensor}. Must be one of the following types: {@code float32}, {@code float64},
     *  {@code int64}, {@code int32}, {@code uint8}, {@code uint16}, {@code int16}, {@code int8}, {@code complex64},
     *  {@code complex128}, {@code qint8}, {@code quint8}, {@code qint32}, {@code half}.
     *  * axis: A {@code Tensor} of type {@code int32} (default: 0). Must be in the range
     *  {@code [-rank(x), rank(x))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * exclusive: If {@code True}, perform exclusive cumsum.
     *  * reverse: A {@code bool} (default: False).
     *
     *  Returns:
     *  * {@code Output}: The out tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cumsum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cumsum(Pointer p) { super(p); }

        /** Optional attribute setters for Cumsum */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If {@code True}, perform exclusive cumsum.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs Exclusive(@Cast("bool") boolean x);

            /** A {@code bool} (default: False).
             *
             *  Defaults to false */
            public native @ByVal Attrs Reverse(@Cast("bool") boolean x);

            public native @Cast("bool") boolean exclusive_(); public native Attrs exclusive_(boolean exclusive_);
            public native @Cast("bool") boolean reverse_(); public native Attrs reverse_(boolean reverse_);
        }
        public Cumsum(@Const @ByRef Scope scope, @ByVal Input x,
                      @ByVal Input axis) { super((Pointer)null); allocate(scope, x, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input axis);
        public Cumsum(@Const @ByRef Scope scope, @ByVal Input x,
                      @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Exclusive(@Cast("bool") boolean x);
        public static native @ByVal Attrs Reverse(@Cast("bool") boolean x);

        public native @ByRef Output out(); public native Cumsum out(Output out);
    }

    /** Computes Psi, the derivative of Lgamma (the log of the absolute value of
     *
     *  {@code Gamma(x)}), element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Digamma extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Digamma(Pointer p) { super(p); }

        public Digamma(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Digamma y(Output y);
    }

    /** Returns x / y element-wise.
     *
     *  *NOTE*: {@code Div} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Div extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Div(Pointer p) { super(p); }

        public Div(@Const @ByRef Scope scope, @ByVal Input x,
                   @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Div z(Output z);
    }

    /** Returns the truth value of (x == y) element-wise.
     *
     *  *NOTE*: {@code Equal} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Equal extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Equal(Pointer p) { super(p); }

        public Equal(@Const @ByRef Scope scope, @ByVal Input x,
                     @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Equal z(Output z);
    }

    /** Computes the Gauss error function of {@code x} element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Erf extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Erf(Pointer p) { super(p); }

        public Erf(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Erf y(Output y);
    }

    /** Computes the complementary error function of {@code x} element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Erfc extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Erfc(Pointer p) { super(p); }

        public Erfc(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Erfc y(Output y);
    }

    /** Computes exponential of x element-wise.  \\(y = e^x\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Exp extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Exp(Pointer p) { super(p); }

        public Exp(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Exp y(Output y);
    }

    /** Computes exponential of x - 1 element-wise.
     *
     *  I.e., \\(y = (\exp x) - 1\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Expm1 extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Expm1(Pointer p) { super(p); }

        public Expm1(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Expm1 y(Output y);
    }

    /** Returns element-wise largest integer not greater than x.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Floor extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Floor(Pointer p) { super(p); }

        public Floor(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Floor y(Output y);
    }

    /** Returns x // y element-wise.
     *
     *  *NOTE*: {@code FloorDiv} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class FloorDiv extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public FloorDiv(Pointer p) { super(p); }

        public FloorDiv(@Const @ByRef Scope scope, @ByVal Input x,
                        @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native FloorDiv z(Output z);
    }

    /** Returns element-wise remainder of division. When {@code x < 0} xor {@code y < 0} is
     *
     *  true, this follows Python semantics in that the result here is consistent
     *  with a flooring divide. E.g. {@code floor(x / y) * y + mod(x, y) = x}.
     *
     *  *NOTE*: {@code FloorMod} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class FloorMod extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public FloorMod(Pointer p) { super(p); }

        public FloorMod(@Const @ByRef Scope scope, @ByVal Input x,
                        @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native FloorMod z(Output z);
    }

    /** Returns the truth value of (x > y) element-wise.
     *
     *  *NOTE*: {@code Greater} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Greater extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Greater(Pointer p) { super(p); }

        public Greater(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Greater z(Output z);
    }

    /** Returns the truth value of (x >= y) element-wise.
     *
     *  *NOTE*: {@code GreaterEqual} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class GreaterEqual extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public GreaterEqual(Pointer p) { super(p); }

        public GreaterEqual(@Const @ByRef Scope scope, @ByVal Input x,
                            @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native GreaterEqual z(Output z);
    }

    /** Return histogram of values.
     *
     *  Given the tensor {@code values}, this operation returns a rank 1 histogram counting
     *  the number of entries in {@code values} that fall into every bin.  The bins are
     *  equal width and determined by the arguments {@code value_range} and {@code nbins}.
     *
     *  <pre>{@code python
     *  # Bins will be:  (-inf, 1), [1, 2), [2, 3), [3, 4), [4, inf)
     *  nbins = 5
     *  value_range = [0.0, 5.0]
     *  new_values = [-1.0, 0.0, 1.5, 2.0, 5.0, 15]
     *
     *  with tf.get_default_session() as sess:
     *    hist = tf.histogram_fixed_width(new_values, value_range, nbins=5)
     *    variables.global_variables_initializer().run()
     *    sess.run(hist) => [2, 1, 1, 0, 2]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * values: Numeric {@code Tensor}.
     *  * value_range: Shape [2] {@code Tensor} of same {@code dtype} as {@code values}.
     *  values <= value_range[0] will be mapped to hist[0],
     *  values >= value_range[1] will be mapped to hist[-1].
     *  * nbins: Scalar {@code int32 Tensor}.  Number of histogram bins.
     *
     *  Returns:
     *  * {@code Output}: A 1-D {@code Tensor} holding histogram of values. */
    @Namespace("tensorflow::ops") @NoOffset public static class HistogramFixedWidth extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public HistogramFixedWidth(Pointer p) { super(p); }

        /** Optional attribute setters for HistogramFixedWidth */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_INT32 */
            public native @ByVal Attrs Dtype(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int dtype_(); public native Attrs dtype_(int dtype_);
        }
        public HistogramFixedWidth(@Const @ByRef Scope scope, @ByVal Input values, @ByVal Input value_range,
                                   @ByVal Input nbins) { super((Pointer)null); allocate(scope, values, value_range, nbins); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input values, @ByVal Input value_range,
                                     @ByVal Input nbins);
        public HistogramFixedWidth(@Const @ByRef Scope scope, @ByVal Input values, @ByVal Input value_range,
                                   @ByVal Input nbins, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, values, value_range, nbins, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input values, @ByVal Input value_range,
                                     @ByVal Input nbins, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Dtype(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output out(); public native HistogramFixedWidth out(Output out);
    }

    /** Compute the lower regularized incomplete Gamma function {@code Q(a, x)}.
     *
     *  The lower regularized incomplete Gamma function is defined as:
     *
     *
     *  \\(P(a, x) = gamma(a, x) / Gamma(a) = 1 - Q(a, x)\\)
     *
     *  where
     *
     *  \\(gamma(a, x) = int_{0}^{x} t^{a-1} exp(-t) dt\\)
     *
     *  is the lower incomplete Gamma function.
     *
     *  Note, above {@code Q(a, x)} ({@code Igammac}) is the upper regularized complete
     *  Gamma function.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Igamma extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Igamma(Pointer p) { super(p); }

        public Igamma(@Const @ByRef Scope scope, @ByVal Input a,
                      @ByVal Input x) { super((Pointer)null); allocate(scope, a, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Igamma z(Output z);
    }

    /** Compute the upper regularized incomplete Gamma function {@code Q(a, x)}.
     *
     *  The upper regularized incomplete Gamma function is defined as:
     *
     *  \\(Q(a, x) = Gamma(a, x) / Gamma(a) = 1 - P(a, x)\\)
     *
     *  where
     *
     *  \\(Gamma(a, x) = int_{x}^{\infty} t^{a-1} exp(-t) dt\\)
     *
     *  is the upper incomplete Gama function.
     *
     *  Note, above {@code P(a, x)} ({@code Igamma}) is the lower regularized complete
     *  Gamma function.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Igammac extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Igammac(Pointer p) { super(p); }

        public Igammac(@Const @ByRef Scope scope, @ByVal Input a,
                       @ByVal Input x) { super((Pointer)null); allocate(scope, a, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Igammac z(Output z);
    }

    /** Returns the imaginary part of a complex number.
     *
     *  Given a tensor {@code input} of complex numbers, this operation returns a tensor of
     *  type {@code float} that is the imaginary part of each element in {@code input}. All
     *  elements in {@code input} must be complex numbers of the form \\(a + bj\\), where *a*
     *  is the real part and *b* is the imaginary part returned by this operation.
     *
     *  For example:
     *
     *  <pre>{@code
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.imag(input) ==> [4.75, 5.75]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Imag extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Imag(Pointer p) { super(p); }

        /** Optional attribute setters for Imag */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_FLOAT */
            public native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Tout_(); public native Attrs Tout_(int Tout_);
        }
        public Imag(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public Imag(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output output(); public native Imag output(Output output);
    }

    /** Computes the reciprocal of x element-wise.
     *
     *  I.e., \\(y = 1 / x\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Inv extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Inv(Pointer p) { super(p); }

        public Inv(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Inv y(Output y);
    }

    /** Returns which elements of x are finite.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.isfinite
     *  \end_compatibility
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class IsFinite extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public IsFinite(Pointer p) { super(p); }

        public IsFinite(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native IsFinite y(Output y);
    }

    /** Returns which elements of x are Inf.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.isinf
     *  \end_compatibility
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class IsInf extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public IsInf(Pointer p) { super(p); }

        public IsInf(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native IsInf y(Output y);
    }

    /** Returns which elements of x are NaN.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.isnan
     *  \end_compatibility
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class IsNan extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public IsNan(Pointer p) { super(p); }

        public IsNan(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native IsNan y(Output y);
    }

    /** Returns the truth value of (x < y) element-wise.
     *
     *  *NOTE*: {@code Less} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Less extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Less(Pointer p) { super(p); }

        public Less(@Const @ByRef Scope scope, @ByVal Input x,
                    @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Less z(Output z);
    }

    /** Returns the truth value of (x <= y) element-wise.
     *
     *  *NOTE*: {@code LessEqual} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class LessEqual extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LessEqual(Pointer p) { super(p); }

        public LessEqual(@Const @ByRef Scope scope, @ByVal Input x,
                         @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native LessEqual z(Output z);
    }

    /** Computes the log of the absolute value of {@code Gamma(x)} element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Lgamma extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Lgamma(Pointer p) { super(p); }

        public Lgamma(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Lgamma y(Output y);
    }

    /** Generates values in an interval.
     *
     *  A sequence of {@code num} evenly-spaced values are generated beginning at {@code start}.
     *  If {@code num > 1}, the values in the sequence increase by {@code stop - start / num - 1},
     *  so that the last one is exactly {@code stop}.
     *
     *  For example:
     *
     *  <pre>{@code
     *  tf.linspace(10.0, 12.0, 3, name="linspace") => [ 10.0  11.0  12.0]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * start: First entry in the range.
     *  * stop: Last entry in the range.
     *  * num: Number of values to generate.
     *
     *  Returns:
     *  * {@code Output}: 1-D. The generated values. */
    @Namespace("tensorflow::ops") @NoOffset public static class LinSpace extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LinSpace(Pointer p) { super(p); }

        public LinSpace(@Const @ByRef Scope scope, @ByVal Input start,
                        @ByVal Input stop, @ByVal Input num) { super((Pointer)null); allocate(scope, start, stop, num); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input start,
                                     @ByVal Input stop, @ByVal Input num);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native LinSpace output(Output output);
    }

    /** Computes natural logarithm of x element-wise.
     *
     *  I.e., \\(y = \log_e x\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Log extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Log(Pointer p) { super(p); }

        public Log(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Log y(Output y);
    }

    /** Computes natural logarithm of (1 + x) element-wise.
     *
     *  I.e., \\(y = \log_e (1 + x)\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Log1p extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Log1p(Pointer p) { super(p); }

        public Log1p(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Log1p y(Output y);
    }

    /** Returns the truth value of x AND y element-wise.
     *
     *  *NOTE*: {@code LogicalAnd} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class LogicalAnd extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LogicalAnd(Pointer p) { super(p); }

        public LogicalAnd(@Const @ByRef Scope scope, @ByVal Input x,
                          @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native LogicalAnd z(Output z);
    }

    /** Returns the truth value of NOT x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class LogicalNot extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LogicalNot(Pointer p) { super(p); }

        public LogicalNot(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native LogicalNot y(Output y);
    }

    /** Returns the truth value of x OR y element-wise.
     *
     *  *NOTE*: {@code LogicalOr} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class LogicalOr extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LogicalOr(Pointer p) { super(p); }

        public LogicalOr(@Const @ByRef Scope scope, @ByVal Input x,
                         @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native LogicalOr z(Output z);
    }

    /** Multiply the matrix "a" by the matrix "b".
     *
     *  The inputs must be two-dimensional matrices and the inner dimension of
     *  "a" (after being transposed if transpose_a is true) must match the
     *  outer dimension of "b" (after being transposed if transposed_b is
     *  true).
     *
     *  *Note*: The default kernel implementation for MatMul on GPUs uses
     *  cublas.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Optional attributes (see {@code Attrs}):
     *  * transpose_a: If true, "a" is transposed before multiplication.
     *  * transpose_b: If true, "b" is transposed before multiplication.
     *
     *  Returns:
     *  * {@code Output}: The product tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class MatMul extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatMul(Pointer p) { super(p); }

        /** Optional attribute setters for MatMul */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, "a" is transposed before multiplication.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs TransposeA(@Cast("bool") boolean x);

            /** If true, "b" is transposed before multiplication.
             *
             *  Defaults to false */
            public native @ByVal Attrs TransposeB(@Cast("bool") boolean x);

            public native @Cast("bool") boolean transpose_a_(); public native Attrs transpose_a_(boolean transpose_a_);
            public native @Cast("bool") boolean transpose_b_(); public native Attrs transpose_b_(boolean transpose_b_);
        }
        public MatMul(@Const @ByRef Scope scope, @ByVal Input a,
                      @ByVal Input b) { super((Pointer)null); allocate(scope, a, b); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b);
        public MatMul(@Const @ByRef Scope scope, @ByVal Input a,
                      @ByVal Input b, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, a, b, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs TransposeA(@Cast("bool") boolean x);
        public static native @ByVal Attrs TransposeB(@Cast("bool") boolean x);

        public native @ByRef Output product(); public native MatMul product(Output product);
    }

    /** Computes the maximum of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceMax */
    @Namespace("tensorflow::ops") @NoOffset public static class Max extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Max(Pointer p) { super(p); }

        /** Optional attribute setters for Max */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Max(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Max(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Max output(Output output);
    }

///
///
///

    /** Returns the max of x and y (i.e. x > y ? x : y) element-wise.
     *
     *  *NOTE*: {@code Maximum} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Maximum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Maximum(Pointer p) { super(p); }

        public Maximum(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Maximum z(Output z);
    }

    /** Computes the mean of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceMean */
    @Namespace("tensorflow::ops") @NoOffset public static class Mean extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Mean(Pointer p) { super(p); }

        /** Optional attribute setters for Mean */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Mean(@Const @ByRef Scope scope, @ByVal Input input,
                    @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Mean(@Const @ByRef Scope scope, @ByVal Input input,
                    @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Mean output(Output output);
    }

///
///
///
///
///

    /** Computes the minimum of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceMin */
    @Namespace("tensorflow::ops") @NoOffset public static class Min extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Min(Pointer p) { super(p); }

        /** Optional attribute setters for Min */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Min(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Min(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Min output(Output output);
    }

///
///
///

    /** Returns the min of x and y (i.e. x < y ? x : y) element-wise.
     *
     *  *NOTE*: {@code Minimum} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Minimum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Minimum(Pointer p) { super(p); }

        public Minimum(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Minimum z(Output z);
    }

    /** Returns element-wise remainder of division. This emulates C semantics in that
     *
     *  the result here is consistent with a truncating divide. E.g.
     *  {@code tf.truncatediv(x, y) * y + truncate_mod(x, y) = x}.
     *
     *  *NOTE*: {@code Mod} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Mod extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Mod(Pointer p) { super(p); }

        public Mod(@Const @ByRef Scope scope, @ByVal Input x,
                   @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Mod z(Output z);
    }

    /** Returns x * y element-wise.
     *
     *  *NOTE*: {@code Multiply} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor.
     *
     *  Aliases:
     *  * Mul */
    @Namespace("tensorflow::ops") @NoOffset public static class Multiply extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Multiply(Pointer p) { super(p); }

        public Multiply(@Const @ByRef Scope scope, @ByVal Input x,
                        @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Multiply z(Output z);
    }

///
///
///
///

    /** Computes numerical negative value element-wise.
     *
     *  I.e., \\(y = -x\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor.
     *
     *  Aliases:
     *  * Neg */
    @Namespace("tensorflow::ops") @NoOffset public static class Negate extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Negate(Pointer p) { super(p); }

        public Negate(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Negate y(Output y);
    }

///
///
///

    /** Returns the truth value of (x != y) element-wise.
     *
     *  *NOTE*: {@code NotEqual} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class NotEqual extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public NotEqual(Pointer p) { super(p); }

        public NotEqual(@Const @ByRef Scope scope, @ByVal Input x,
                        @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native NotEqual z(Output z);
    }

    /** Compute the polygamma function \\(\psi^{(n)}(x)\\).
     *
     *  The polygamma function is defined as:
     *
     *
     *  \\(\psi^{(n)}(x) = \frac{d^n}{dx^n} \psi(x)\\)
     *
     *  where \\(\psi(x)\\) is the digamma function.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Polygamma extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Polygamma(Pointer p) { super(p); }

        public Polygamma(@Const @ByRef Scope scope, @ByVal Input a,
                         @ByVal Input x) { super((Pointer)null); allocate(scope, a, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Polygamma z(Output z);
    }

    /** Computes the power of one value to another.
     *
     *  Given a tensor {@code x} and a tensor {@code y}, this operation computes \\(x^y\\) for
     *  corresponding elements in {@code x} and {@code y}. For example:
     *
     *  <pre>{@code
     *  # tensor 'x' is [[2, 2]], [3, 3]]
     *  # tensor 'y' is [[8, 16], [2, 3]]
     *  tf.pow(x, y) ==> [[256, 65536], [9, 27]]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Pow extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Pow(Pointer p) { super(p); }

        public Pow(@Const @ByRef Scope scope, @ByVal Input x,
                   @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Pow z(Output z);
    }

    /** Computes the product of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceProd */
    @Namespace("tensorflow::ops") @NoOffset public static class Prod extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Prod(Pointer p) { super(p); }

        /** Optional attribute setters for Prod */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Prod(@Const @ByRef Scope scope, @ByVal Input input,
                    @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Prod(@Const @ByRef Scope scope, @ByVal Input input,
                    @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Prod output(Output output);
    }

///
///
///
///
///
///

    /** Convert the quantized 'input' tensor into a lower-precision 'output', using the
     *
     *  actual distribution of the values to maximize the usage of the lower bit depth
     *  and adjusting the output min and max ranges accordingly.
     *
     *  [input_min, input_max] are scalar floats that specify the range for the float
     *  interpretation of the 'input' data. For example, if input_min is -1.0f and
     *  input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *
     *  This operator tries to squeeze as much precision as possible into an output with
     *  a lower bit depth by calculating the actual min and max values found in the
     *  data. For example, maybe that quint16 input has no values lower than 16,384 and
     *  none higher than 49,152. That means only half the range is actually needed, all
     *  the float interpretations are between -0.5f and 0.5f, so if we want to compress
     *  the data into a quint8 output, we can use that range rather than the theoretical
     *  -1.0f to 1.0f that is suggested by the input min and max.
     *
     *  In practice, this is most useful for taking output from operations like
     *  QuantizedMatMul that can produce higher bit-depth outputs than their inputs and
     *  may have large potential output ranges, but in practice have a distribution of
     *  input values that only uses a small fraction of the possible range. By feeding
     *  that output into this operator, we can reduce it from 32 bits down to 8 with
     *  minimal loss of accuracy.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input_min: The float value that the minimum quantized input value represents.
     *  * input_max: The float value that the maximum quantized input value represents.
     *  * out_type: The type of the output. Should be a lower bit depth than Tinput.
     *
     *  Returns:
     *  * {@code Output} output
     *  * {@code Output} output_min: The float value that the minimum quantized output value represents.
     *  * {@code Output} output_max: The float value that the maximum quantized output value represents. */
    @Namespace("tensorflow::ops") @NoOffset public static class QuantizeDownAndShrinkRange extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public QuantizeDownAndShrinkRange(Pointer p) { super(p); }

        public QuantizeDownAndShrinkRange(@Const @ByRef Scope scope,
                                          @ByVal Input input, @ByVal Input input_min, @ByVal Input input_max, @Cast("tensorflow::DataType") int out_type) { super((Pointer)null); allocate(scope, input, input_min, input_max, out_type); }
        private native void allocate(@Const @ByRef Scope scope,
                                     @ByVal Input input, @ByVal Input input_min, @ByVal Input input_max, @Cast("tensorflow::DataType") int out_type);

        public native @ByRef Output output(); public native QuantizeDownAndShrinkRange output(Output output);
        public native @ByRef Output output_min(); public native QuantizeDownAndShrinkRange output_min(Output output_min);
        public native @ByRef Output output_max(); public native QuantizeDownAndShrinkRange output_max(Output output_max);
    }

    /** Returns x + y element-wise, working on quantized buffers.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * min_x: The float value that the lowest quantized {@code x} value represents.
     *  * max_x: The float value that the highest quantized {@code x} value represents.
     *  * min_y: The float value that the lowest quantized {@code y} value represents.
     *  * max_y: The float value that the highest quantized {@code y} value represents.
     *
     *  Returns:
     *  * {@code Output} z
     *  * {@code Output} min_z: The float value that the lowest quantized output value represents.
     *  * {@code Output} max_z: The float value that the highest quantized output value represents.
     *
     *  *NOTE*: {@code QuantizedAdd} supports limited forms of broadcasting. More about
     *  broadcasting [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html) */
    @Namespace("tensorflow::ops") @NoOffset public static class QuantizedAdd extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public QuantizedAdd(Pointer p) { super(p); }

        /** Optional attribute setters for QuantizedAdd */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_QINT32 */
            public native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Toutput_(); public native Attrs Toutput_(int Toutput_);
        }
        public QuantizedAdd(@Const @ByRef Scope scope, @ByVal Input x,
                            @ByVal Input y, @ByVal Input min_x,
                            @ByVal Input max_x, @ByVal Input min_y,
                            @ByVal Input max_y) { super((Pointer)null); allocate(scope, x, y, min_x, max_x, min_y, max_y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @ByVal Input min_x,
                                     @ByVal Input max_x, @ByVal Input min_y,
                                     @ByVal Input max_y);
        public QuantizedAdd(@Const @ByRef Scope scope, @ByVal Input x,
                            @ByVal Input y, @ByVal Input min_x,
                            @ByVal Input max_x, @ByVal Input min_y,
                            @ByVal Input max_y, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, y, min_x, max_x, min_y, max_y, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @ByVal Input min_x,
                                     @ByVal Input max_x, @ByVal Input min_y,
                                     @ByVal Input max_y, @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output z(); public native QuantizedAdd z(Output z);
        public native @ByRef Output min_z(); public native QuantizedAdd min_z(Output min_z);
        public native @ByRef Output max_z(); public native QuantizedAdd max_z(Output max_z);
    }

    /** Perform a quantized matrix multiplication of  {@code a} by the matrix {@code b}.
     *
     *  The inputs must be two-dimensional matrices and the inner dimension of
     *  {@code a} (after being transposed if {@code transpose_a} is non-zero) must match the
     *  outer dimension of {@code b} (after being transposed if {@code transposed_b} is
     *  non-zero).
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * a: Must be a two-dimensional tensor.
     *  * b: Must be a two-dimensional tensor.
     *  * min_a: The float value that the lowest quantized {@code a} value represents.
     *  * max_a: The float value that the highest quantized {@code a} value represents.
     *  * min_b: The float value that the lowest quantized {@code b} value represents.
     *  * max_b: The float value that the highest quantized {@code b} value represents.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * transpose_a: If true, {@code a} is transposed before multiplication.
     *  * transpose_b: If true, {@code b} is transposed before multiplication.
     *  * Tactivation: The type of output produced by activation function
     *  following this operation.
     *
     *  Returns:
     *  * {@code Output} out
     *  * {@code Output} min_out: The float value that the lowest quantized output value represents.
     *  * {@code Output} max_out: The float value that the highest quantized output value represents. */
    @Namespace("tensorflow::ops") @NoOffset public static class QuantizedMatMul extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public QuantizedMatMul(Pointer p) { super(p); }

        /** Optional attribute setters for QuantizedMatMul */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_QINT32 */

            ///
            public native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);

            /** If true, {@code a} is transposed before multiplication.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs TransposeA(@Cast("bool") boolean x);

            /** If true, {@code b} is transposed before multiplication.
             *
             *  Defaults to false */

            ///
            public native @ByVal Attrs TransposeB(@Cast("bool") boolean x);

            /** The type of output produced by activation function
             *  following this operation.
             *
             *  Defaults to DT_QUINT8 */
            public native @ByVal Attrs Tactivation(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Toutput_(); public native Attrs Toutput_(int Toutput_);
            public native @Cast("bool") boolean transpose_a_(); public native Attrs transpose_a_(boolean transpose_a_);
            public native @Cast("bool") boolean transpose_b_(); public native Attrs transpose_b_(boolean transpose_b_);
            public native @Cast("tensorflow::DataType") int Tactivation_(); public native Attrs Tactivation_(int Tactivation_);
        }
        public QuantizedMatMul(@Const @ByRef Scope scope, @ByVal Input a,
                               @ByVal Input b, @ByVal Input min_a,
                               @ByVal Input max_a, @ByVal Input min_b,
                               @ByVal Input max_b) { super((Pointer)null); allocate(scope, a, b, min_a, max_a, min_b, max_b); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b, @ByVal Input min_a,
                                     @ByVal Input max_a, @ByVal Input min_b,
                                     @ByVal Input max_b);
        public QuantizedMatMul(@Const @ByRef Scope scope, @ByVal Input a,
                               @ByVal Input b, @ByVal Input min_a,
                               @ByVal Input max_a, @ByVal Input min_b,
                               @ByVal Input max_b, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, a, b, min_a, max_a, min_b, max_b, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b, @ByVal Input min_a,
                                     @ByVal Input max_a, @ByVal Input min_b,
                                     @ByVal Input max_b, @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);
        public static native @ByVal Attrs TransposeA(@Cast("bool") boolean x);
        public static native @ByVal Attrs TransposeB(@Cast("bool") boolean x);
        public static native @ByVal Attrs Tactivation(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output out(); public native QuantizedMatMul out(Output out);
        public native @ByRef Output min_out(); public native QuantizedMatMul min_out(Output min_out);
        public native @ByRef Output max_out(); public native QuantizedMatMul max_out(Output max_out);
    }

    /** Returns x * y element-wise, working on quantized buffers.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * min_x: The float value that the lowest quantized {@code x} value represents.
     *  * max_x: The float value that the highest quantized {@code x} value represents.
     *  * min_y: The float value that the lowest quantized {@code y} value represents.
     *  * max_y: The float value that the highest quantized {@code y} value represents.
     *
     *  Returns:
     *  * {@code Output} z
     *  * {@code Output} min_z: The float value that the lowest quantized output value represents.
     *  * {@code Output} max_z: The float value that the highest quantized output value represents.
     *
     *  *NOTE*: {@code QuantizedMul} supports limited forms of broadcasting. More about
     *  broadcasting [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html) */
    @Namespace("tensorflow::ops") @NoOffset public static class QuantizedMul extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public QuantizedMul(Pointer p) { super(p); }

        /** Optional attribute setters for QuantizedMul */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_QINT32 */
            public native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Toutput_(); public native Attrs Toutput_(int Toutput_);
        }
        public QuantizedMul(@Const @ByRef Scope scope, @ByVal Input x,
                            @ByVal Input y, @ByVal Input min_x,
                            @ByVal Input max_x, @ByVal Input min_y,
                            @ByVal Input max_y) { super((Pointer)null); allocate(scope, x, y, min_x, max_x, min_y, max_y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @ByVal Input min_x,
                                     @ByVal Input max_x, @ByVal Input min_y,
                                     @ByVal Input max_y);
        public QuantizedMul(@Const @ByRef Scope scope, @ByVal Input x,
                            @ByVal Input y, @ByVal Input min_x,
                            @ByVal Input max_x, @ByVal Input min_y,
                            @ByVal Input max_y, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, x, y, min_x, max_x, min_y, max_y, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y, @ByVal Input min_x,
                                     @ByVal Input max_x, @ByVal Input min_y,
                                     @ByVal Input max_y, @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs Toutput(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output z(); public native QuantizedMul z(Output z);
        public native @ByRef Output min_z(); public native QuantizedMul min_z(Output min_z);
        public native @ByRef Output max_z(); public native QuantizedMul max_z(Output max_z);
    }

    /** Creates a sequence of numbers.
     *
     *  This operation creates a sequence of numbers that begins at {@code start} and
     *  extends by increments of {@code delta} up to but not including {@code limit}.
     *
     *  For example:
     *
     *  <pre>{@code
     *  # 'start' is 3
     *  # 'limit' is 18
     *  # 'delta' is 3
     *  tf.range(start, limit, delta) ==> [3, 6, 9, 12, 15]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * start: 0-D (scalar). First entry in the sequence.
     *  * limit: 0-D (scalar). Upper limit of sequence, exclusive.
     *  * delta: 0-D (scalar). Optional. Default is 1. Number that increments {@code start}.
     *
     *  Returns:
     *  * {@code Output}: 1-D. */
    @Namespace("tensorflow::ops") @NoOffset public static class Range extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Range(Pointer p) { super(p); }

        public Range(@Const @ByRef Scope scope, @ByVal Input start,
                     @ByVal Input limit, @ByVal Input delta) { super((Pointer)null); allocate(scope, start, limit, delta); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input start,
                                     @ByVal Input limit, @ByVal Input delta);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native Range output(Output output);
    }

    /** Returns the real part of a complex number.
     *
     *  Given a tensor {@code input} of complex numbers, this operation returns a tensor of
     *  type {@code float} that is the real part of each element in {@code input}. All elements in
     *  {@code input} must be complex numbers of the form \\(a + bj\\), where *a* is the real
     *   part returned by this operation and *b* is the imaginary part.
     *
     *  For example:
     *
     *  <pre>{@code
     *  # tensor 'input' is [-2.25 + 4.75j, 3.25 + 5.75j]
     *  tf.real(input) ==> [-2.25, 3.25]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Real extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Real(Pointer p) { super(p); }

        /** Optional attribute setters for Real */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to DT_FLOAT */
            public native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

            public native @Cast("tensorflow::DataType") int Tout_(); public native Attrs Tout_(int Tout_);
        }
        public Real(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public Real(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Tout(@Cast("tensorflow::DataType") int x);

        public native @ByRef Output output(); public native Real output(Output output);
    }

    /** Returns x / y element-wise for real types.
     *
     *  If {@code x} and {@code y} are reals, this will return the floating-point division.
     *
     *  *NOTE*: {@code Div} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class RealDiv extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public RealDiv(Pointer p) { super(p); }

        public RealDiv(@Const @ByRef Scope scope, @ByVal Input x,
                       @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native RealDiv z(Output z);
    }

    /** Computes the reciprocal of x element-wise.
     *
     *  I.e., \\(y = 1 / x\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Reciprocal extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Reciprocal(Pointer p) { super(p); }

        public Reciprocal(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Reciprocal y(Output y);
    }

    /** Given a quantized tensor described by (input, input_min, input_max), outputs a
     *
     *  range that covers the actual values present in that tensor.  This op is
     *  typically used to produce the requested_output_min and requested_output_max for
     *  Requantize.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input_min: The float value that the minimum quantized input value represents.
     *  * input_max: The float value that the maximum quantized input value represents.
     *
     *  Returns:
     *  * {@code Output} output_min: The computed min output.
     *  * {@code Output} output_max: the computed max output. */
    @Namespace("tensorflow::ops") @NoOffset public static class RequantizationRange extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public RequantizationRange(Pointer p) { super(p); }

        public RequantizationRange(@Const @ByRef Scope scope, @ByVal Input input, @ByVal Input input_min, @ByVal Input input_max) { super((Pointer)null); allocate(scope, input, input_min, input_max); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @ByVal Input input_min, @ByVal Input input_max);

        public native @ByRef Output output_min(); public native RequantizationRange output_min(Output output_min);
        public native @ByRef Output output_max(); public native RequantizationRange output_max(Output output_max);
    }

    /** Convert the quantized 'input' tensor into a lower-precision 'output', using the
     *
     *  output range specified with 'requested_output_min' and 'requested_output_max'.
     *
     *  [input_min, input_max] are scalar floats that specify the range for the float
     *  interpretation of the 'input' data. For example, if input_min is -1.0f and
     *  input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input_min: The float value that the minimum quantized input value represents.
     *  * input_max: The float value that the maximum quantized input value represents.
     *  * requested_output_min: The float value that the minimum quantized output value represents.
     *  * requested_output_max: The float value that the maximum quantized output value represents.
     *  * out_type: The type of the output. Should be a lower bit depth than Tinput.
     *
     *  Returns:
     *  * {@code Output} output
     *  * {@code Output} output_min: The requested_output_min value is copied into this output.
     *  * {@code Output} output_max: The requested_output_max value is copied into this output. */
    @Namespace("tensorflow::ops") @NoOffset public static class Requantize extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Requantize(Pointer p) { super(p); }

        public Requantize(@Const @ByRef Scope scope, @ByVal Input input,
                          @ByVal Input input_min, @ByVal Input input_max,
                          @ByVal Input requested_output_min, @ByVal Input requested_output_max, @Cast("tensorflow::DataType") int out_type) { super((Pointer)null); allocate(scope, input, input_min, input_max, requested_output_min, requested_output_max, out_type); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input input_min, @ByVal Input input_max,
                                     @ByVal Input requested_output_min, @ByVal Input requested_output_max, @Cast("tensorflow::DataType") int out_type);

        public native @ByRef Output output(); public native Requantize output(Output output);
        public native @ByRef Output output_min(); public native Requantize output_min(Output output_min);
        public native @ByRef Output output_max(); public native Requantize output_max(Output output_max);
    }

    /** Returns element-wise integer closest to x.
     *
     *  If the result is midway between two representable values,
     *  the even representable is chosen.
     *  For example:
     *
     *  <pre>{@code
     *  rint(-1.5) ==> -2.0
     *  rint(0.5000001) ==> 1.0
     *  rint([-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0]) ==> [-2., -2., -0., 0., 2., 2., 2.]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Rint extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Rint(Pointer p) { super(p); }

        public Rint(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Rint y(Output y);
    }

    /** Rounds the values of a tensor to the nearest integer, element-wise.
     *
     *  Rounds half to even.  Also known as bankers rounding. If you want to round
     *  according to the current system rounding mode use std::cint.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Round extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Round(Pointer p) { super(p); }

        public Round(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Round y(Output y);
    }

    /** Computes reciprocal of square root of x element-wise.
     *
     *  I.e., \\(y = 1 / \sqrt{x}\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Rsqrt extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Rsqrt(Pointer p) { super(p); }

        public Rsqrt(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Rsqrt y(Output y);
    }

    /** Computes the maximum along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \max_j(data_j)\\) where {@code max} is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the max is empty for a given segment ID {@code i}, {@code output[i] = 0}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMax.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.  Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SegmentMax extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SegmentMax(Pointer p) { super(p); }

        public SegmentMax(@Const @ByRef Scope scope, @ByVal Input data,
                          @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SegmentMax output(Output output);
    }

    /** Computes the mean along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \frac{\sum_j data_j}{N}\\) where {@code mean} is
     *  over {@code j} such that {@code segment_ids[j] == i} and {@code N} is the total number of
     *  values summed.
     *
     *  If the mean is empty for a given segment ID {@code i}, {@code output[i] = 0}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMean.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.  Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SegmentMean extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SegmentMean(Pointer p) { super(p); }

        public SegmentMean(@Const @ByRef Scope scope, @ByVal Input data,
                           @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SegmentMean output(Output output);
    }

    /** Computes the minimum along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \min_j(data_j)\\) where {@code min} is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the min is empty for a given segment ID {@code i}, {@code output[i] = 0}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMin.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.  Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SegmentMin extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SegmentMin(Pointer p) { super(p); }

        public SegmentMin(@Const @ByRef Scope scope, @ByVal Input data,
                          @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SegmentMin output(Output output);
    }

    /** Computes the product along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \prod_j data_j\\) where the product is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the product is empty for a given segment ID {@code i}, {@code output[i] = 1}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentProd.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.  Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SegmentProd extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SegmentProd(Pointer p) { super(p); }

        public SegmentProd(@Const @ByRef Scope scope, @ByVal Input data,
                           @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SegmentProd output(Output output);
    }

    /** Computes the sum along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  \\(output_i = \sum_j data_j\\) where sum is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the sum is empty for a given segment ID {@code i}, {@code output[i] = 0}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/SegmentSum.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.  Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SegmentSum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SegmentSum(Pointer p) { super(p); }

        public SegmentSum(@Const @ByRef Scope scope, @ByVal Input data,
                          @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SegmentSum output(Output output);
    }

    /** Selects elements from {@code x} or {@code y}, depending on {@code condition}.
     *
     *  The {@code x}, and {@code y} tensors must all have the same shape, and the
     *  output will also have that shape.
     *
     *  The {@code condition} tensor must be a scalar if {@code x} and {@code y} are scalars.
     *  If {@code x} and {@code y} are vectors or higher rank, then {@code condition} must be either a
     *  scalar, a vector with size matching the first dimension of {@code x}, or must have
     *  the same shape as {@code x}.
     *
     *  The {@code condition} tensor acts as a mask that chooses, based on the value at each
     *  element, whether the corresponding element / row in the output should be
     *  taken from {@code x} (if true) or {@code y} (if false).
     *
     *  If {@code condition} is a vector and {@code x} and {@code y} are higher rank matrices, then
     *  it chooses which row (outer dimension) to copy from {@code x} and {@code y}.
     *  If {@code condition} has the same shape as {@code x} and {@code y}, then it chooses which
     *  element to copy from {@code x} and {@code y}.
     *
     *  For example:
     *
     *  <pre>{@code python
     *  # 'condition' tensor is [[True,  False]
     *  #                        [False, True]]
     *  # 't' is [[1, 2],
     *  #         [3, 4]]
     *  # 'e' is [[5, 6],
     *  #         [7, 8]]
     *  select(condition, t, e)  # => [[1, 6], [7, 4]]
     *
     *
     *  # 'condition' tensor is [True, False]
     *  # 't' is [[1, 2],
     *  #         [3, 4]]
     *  # 'e' is [[5, 6],
     *  #         [7, 8]]
     *  select(condition, t, e) ==> [[1, 2],
     *                               [7, 8]]
     *
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * x: = A {@code Tensor} which may have the same shape as {@code condition}.
     *  If {@code condition} is rank 1, {@code x} may have higher rank,
     *  but its first dimension must match the size of {@code condition}.
     *  * y: = A {@code Tensor} with the same type and shape as {@code x}.
     *
     *  Returns:
     *  * {@code Output}: = A {@code Tensor} with the same type and shape as {@code x} and {@code y}. */
    @Namespace("tensorflow::ops") @NoOffset public static class Where3 extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Where3(Pointer p) { super(p); }

        public Where3(@Const @ByRef Scope scope, @ByVal Input condition,
                      @ByVal Input x, @ByVal Input y) { super((Pointer)null); allocate(scope, condition, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input condition,
                                     @ByVal Input x, @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native Where3 output(Output output);
    }

    /** Computes sigmoid of {@code x} element-wise.
     *
     *  Specifically, {@code y = 1 / (1 + exp(-x))}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Sigmoid extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sigmoid(Pointer p) { super(p); }

        public Sigmoid(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Sigmoid y(Output y);
    }

    /** Returns an element-wise indication of the sign of a number.
     *
     *  {@code y = sign(x) = -1} if {@code x < 0}; 0 if {@code x == 0}; 1 if {@code x > 0}.
     *
     *  For complex numbers, {@code y = sign(x) = x / |x|} if {@code x != 0}, otherwise {@code y = 0}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Sign extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sign(Pointer p) { super(p); }

        public Sign(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Sign y(Output y);
    }

    /** Computes sin of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Sin extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sin(Pointer p) { super(p); }

        public Sin(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Sin y(Output y);
    }

    /** Computes hyperbolic sine of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Sinh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sinh(Pointer p) { super(p); }

        public Sinh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Sinh y(Output y);
    }

    /** Multiply matrix "a" by matrix "b".
     *
     *  The inputs must be two-dimensional matrices and the inner dimension of "a" must
     *  match the outer dimension of "b". This op is optimized for the case where at
     *  least one of "a" or "b" is sparse. The breakeven for using this versus a dense
     *  matrix multiply on one platform was 30% zero values in the sparse matrix.
     *
     *  The gradient computation of this operation will only take advantage of sparsity
     *  in the input gradient when that gradient comes from a Relu.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The product tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseMatMul extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseMatMul(Pointer p) { super(p); }

        /** Optional attribute setters for SparseMatMul */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** Defaults to false */
            public native @ByVal Attrs TransposeA(@Cast("bool") boolean x);

            /** Defaults to false */
            public native @ByVal Attrs TransposeB(@Cast("bool") boolean x);

            /** Defaults to false */
            public native @ByVal Attrs AIsSparse(@Cast("bool") boolean x);

            /** Defaults to false */
            public native @ByVal Attrs BIsSparse(@Cast("bool") boolean x);

            public native @Cast("bool") boolean transpose_a_(); public native Attrs transpose_a_(boolean transpose_a_);
            public native @Cast("bool") boolean transpose_b_(); public native Attrs transpose_b_(boolean transpose_b_);
            public native @Cast("bool") boolean a_is_sparse_(); public native Attrs a_is_sparse_(boolean a_is_sparse_);
            public native @Cast("bool") boolean b_is_sparse_(); public native Attrs b_is_sparse_(boolean b_is_sparse_);
        }
        public SparseMatMul(@Const @ByRef Scope scope, @ByVal Input a,
                            @ByVal Input b) { super((Pointer)null); allocate(scope, a, b); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b);
        public SparseMatMul(@Const @ByRef Scope scope, @ByVal Input a,
                            @ByVal Input b, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, a, b, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input a,
                                     @ByVal Input b, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs TransposeA(@Cast("bool") boolean x);
        public static native @ByVal Attrs TransposeB(@Cast("bool") boolean x);
        public static native @ByVal Attrs AIsSparse(@Cast("bool") boolean x);
        public static native @ByVal Attrs BIsSparse(@Cast("bool") boolean x);

        public native @ByRef Output product(); public native SparseMatMul product(Output product);
    }

    /** Computes the mean along sparse segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Like {@code SegmentMean}, but {@code segment_ids} can have rank less than {@code data}'s first
     *  dimension, selecting a subset of dimension 0, specified by {@code indices}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentMean extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentMean(Pointer p) { super(p); }

        public SparseSegmentMean(@Const @ByRef Scope scope, @ByVal Input data,
                                 @ByVal Input indices, @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, indices, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input indices, @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentMean output(Output output);
    }

    /** Computes gradients for SparseSegmentMean.
     *
     *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
     *  value is output_dim0.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * grad: gradient propagated to the SparseSegmentMean op.
     *  * indices: indices passed to the corresponding SparseSegmentMean op.
     *  * segment_ids: segment_ids passed to the corresponding SparseSegmentMean op.
     *  * output_dim0: dimension 0 of "data" passed to SparseSegmentMean op.
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentMeanGrad extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentMeanGrad(Pointer p) { super(p); }

        public SparseSegmentMeanGrad(@Const @ByRef Scope scope, @ByVal Input grad, @ByVal Input indices, @ByVal Input segment_ids, @ByVal Input output_dim0) { super((Pointer)null); allocate(scope, grad, indices, segment_ids, output_dim0); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input grad, @ByVal Input indices, @ByVal Input segment_ids, @ByVal Input output_dim0);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentMeanGrad output(Output output);
    }

    /** Computes the mean along sparse segments of a tensor.
     *
     *  Like {@code SparseSegmentMean}, but allows missing ids in {@code segment_ids}. If an id is
     *  misisng, the {@code output} tensor at that position will be zeroed.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *  * num_segments: Should equal the number of distinct segment IDs.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which has size
     *  {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentMeanWithNumSegments extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentMeanWithNumSegments(Pointer p) { super(p); }

        public SparseSegmentMeanWithNumSegments(@Const @ByRef Scope scope,
                                                @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                                @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, indices, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope,
                                     @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                     @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentMeanWithNumSegments output(Output output);
    }

    /** Computes the sum along sparse segments of a tensor divided by the sqrt of N.
     *
     *  N is the size of the segment being reduced.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentSqrtN extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentSqrtN(Pointer p) { super(p); }

        public SparseSegmentSqrtN(@Const @ByRef Scope scope, @ByVal Input data,
                                  @ByVal Input indices, @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, indices, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input indices, @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentSqrtN output(Output output);
    }

    /** Computes gradients for SparseSegmentSqrtN.
     *
     *  Returns tensor "output" with same shape as grad, except for dimension 0 whose
     *  value is output_dim0.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * grad: gradient propagated to the SparseSegmentSqrtN op.
     *  * indices: indices passed to the corresponding SparseSegmentSqrtN op.
     *  * segment_ids: segment_ids passed to the corresponding SparseSegmentSqrtN op.
     *  * output_dim0: dimension 0 of "data" passed to SparseSegmentSqrtN op.
     *
     *  Returns:
     *  * {@code Output}: The output tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentSqrtNGrad extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentSqrtNGrad(Pointer p) { super(p); }

        public SparseSegmentSqrtNGrad(@Const @ByRef Scope scope, @ByVal Input grad, @ByVal Input indices, @ByVal Input segment_ids, @ByVal Input output_dim0) { super((Pointer)null); allocate(scope, grad, indices, segment_ids, output_dim0); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input grad, @ByVal Input indices, @ByVal Input segment_ids, @ByVal Input output_dim0);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentSqrtNGrad output(Output output);
    }

    /** Computes the sum along sparse segments of a tensor divided by the sqrt of N.
     *
     *  N is the size of the segment being reduced.
     *
     *  Like {@code SparseSegmentSqrtN}, but allows missing ids in {@code segment_ids}. If an id is
     *  misisng, the {@code output} tensor at that position will be zeroed.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *  * num_segments: Should equal the number of distinct segment IDs.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentSqrtNWithNumSegments extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentSqrtNWithNumSegments(Pointer p) { super(p); }

        public SparseSegmentSqrtNWithNumSegments(@Const @ByRef Scope scope,
                                                 @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                                 @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, indices, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope,
                                     @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                     @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentSqrtNWithNumSegments output(Output output);
    }

    /** Computes the sum along sparse segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Like {@code SegmentSum}, but {@code segment_ids} can have rank less than {@code data}'s first
     *  dimension, selecting a subset of dimension 0, specified by {@code indices}.
     *
     *  For example:
     *
     *  <pre>{@code python
     *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
     *
     *  # Select two rows, one segment.
     *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 0]))
     *  # => [[0 0 0 0]]
     *
     *  # Select two rows, two segment.
     *  tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 1]))
     *  # => [[ 1  2  3  4]
     *  #     [-1 -2 -3 -4]]
     *
     *  # Select all rows, two segments.
     *  tf.sparse_segment_sum(c, tf.constant([0, 1, 2]), tf.constant([0, 0, 1]))
     *  # => [[0 0 0 0]
     *  #     [5 6 7 8]]
     *
     *  # Which is equivalent to:
     *  tf.segment_sum(c, tf.constant([0, 0, 1]))
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code k}, the number of segments. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentSum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentSum(Pointer p) { super(p); }

        public SparseSegmentSum(@Const @ByRef Scope scope, @ByVal Input data,
                                @ByVal Input indices, @ByVal Input segment_ids) { super((Pointer)null); allocate(scope, data, indices, segment_ids); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input indices, @ByVal Input segment_ids);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentSum output(Output output);
    }

    /** Computes the sum along sparse segments of a tensor.
     *
     *  Like {@code SparseSegmentSum}, but allows missing ids in {@code segment_ids}. If an id is
     *  misisng, the {@code output} tensor at that position will be zeroed.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  For example:
     *
     *  <pre>{@code python
     *  c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
     *
     *  tf.sparse_segment_sum_with_num_segments(
     *      c, tf.constant([0, 1]), tf.constant([0, 0]), num_segments=3)
     *  # => [[0 0 0 0]
     *  #     [0 0 0 0]
     *  #     [0 0 0 0]]
     *
     *  tf.sparse_segment_sum_with_num_segments(c,
     *                                          tf.constant([0, 1]),
     *                                          tf.constant([0, 2],
     *                                          num_segments=4))
     *  # => [[ 1  2  3  4]
     *  #     [ 0  0  0  0]
     *  #     [-1 -2 -3 -4]
     *  #     [ 0  0  0  0]]
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * indices: A 1-D tensor. Has same rank as {@code segment_ids}.
     *  * segment_ids: A 1-D tensor. Values should be sorted and can be repeated.
     *  * num_segments: Should equal the number of distinct segment IDs.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class SparseSegmentSumWithNumSegments extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SparseSegmentSumWithNumSegments(Pointer p) { super(p); }

        public SparseSegmentSumWithNumSegments(@Const @ByRef Scope scope,
                                               @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                               @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, indices, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope,
                                     @ByVal Input data, @ByVal Input indices, @ByVal Input segment_ids,
                                     @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native SparseSegmentSumWithNumSegments output(Output output);
    }

    /** Computes square root of x element-wise.
     *
     *  I.e., \\(y = \sqrt{x} = x^{1/2}\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Sqrt extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sqrt(Pointer p) { super(p); }

        public Sqrt(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Sqrt y(Output y);
    }

    /** Computes square of x element-wise.
     *
     *  I.e., \\(y = x * x = x^2\\).
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Square extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Square(Pointer p) { super(p); }

        public Square(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Square y(Output y);
    }

    /** Returns (x - y)(x - y) element-wise.
     *
     *  *NOTE*: {@code SquaredDifference} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class SquaredDifference extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SquaredDifference(Pointer p) { super(p); }

        public SquaredDifference(@Const @ByRef Scope scope, @ByVal Input x,
                                 @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native SquaredDifference z(Output z);
    }

    /** Returns x - y element-wise.
     *
     *  *NOTE*: {@code Subtract} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor.
     *
     *  Aliases:
     *  * Sub */
    @Namespace("tensorflow::ops") @NoOffset public static class Subtract extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Subtract(Pointer p) { super(p); }

        public Subtract(@Const @ByRef Scope scope, @ByVal Input x,
                        @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Subtract z(Output z);
    }

///
///
///
///
///

    /** Computes the sum of elements across dimensions of a tensor.
     *
     *  Reduces {@code input} along the dimensions given in {@code axis}. Unless
     *  {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
     *  {@code axis}. If {@code keep_dims} is true, the reduced dimensions are
     *  retained with length 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: The tensor to reduce.
     *  * axis: The dimensions to reduce. Must be in the range
     *  {@code [-rank(input), rank(input))}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * keep_dims: If true, retain reduced dimensions with length 1.
     *
     *  Returns:
     *  * {@code Output}: The reduced tensor.
     *
     *  Aliases:
     *  * ReduceSum */
    @Namespace("tensorflow::ops") @NoOffset public static class Sum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Sum(Pointer p) { super(p); }

        /** Optional attribute setters for Sum */
        public static class Attrs extends Pointer {
            static { Loader.load(); }
            /** Default native constructor. */
            public Attrs() { super((Pointer)null); allocate(); }
            /** Native array allocator. Access with {@link Pointer#position(long)}. */
            public Attrs(long size) { super((Pointer)null); allocateArray(size); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public Attrs(Pointer p) { super(p); }
            private native void allocate();
            private native void allocateArray(long size);
            @Override public Attrs position(long position) {
                return (Attrs)super.position(position);
            }

            /** If true, retain reduced dimensions with length 1.
             *
             *  Defaults to false */
            public native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

            public native @Cast("bool") boolean keep_dims_(); public native Attrs keep_dims_(boolean keep_dims_);
        }
        public Sum(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis) { super((Pointer)null); allocate(scope, input, axis); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis);
        public Sum(@Const @ByRef Scope scope, @ByVal Input input,
                   @ByVal Input axis, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, axis, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @ByVal Input axis, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs KeepDims(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native Sum output(Output output);
    }

///
///

    /** Computes tan of x element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Tan extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Tan(Pointer p) { super(p); }

        public Tan(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Tan y(Output y);
    }

    /** Computes hyperbolic tangent of {@code x} element-wise.
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The y tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Tanh extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Tanh(Pointer p) { super(p); }

        public Tanh(@Const @ByRef Scope scope, @ByVal Input x) { super((Pointer)null); allocate(scope, x); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output y(); public native Tanh y(Output y);
    }

    /** Returns x / y element-wise for integer types.
     *
     *  Truncation designates that negative numbers will round fractional quantities
     *  toward zero. I.e. -7 / 5 = -1. This matches C semantics but it is different
     *  than Python semantics. See {@code FloorDiv} for a division function that matches
     *  Python Semantics.
     *
     *  *NOTE*: {@code TruncateDiv} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class TruncateDiv extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TruncateDiv(Pointer p) { super(p); }

        public TruncateDiv(@Const @ByRef Scope scope, @ByVal Input x,
                           @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native TruncateDiv z(Output z);
    }

    /** Returns element-wise remainder of division. This emulates C semantics in that
     *
     *  the result here is consistent with a truncating divide. E.g. {@code truncate(x / y) *
     *  y + truncate_mod(x, y) = x}.
     *
     *  *NOTE*: {@code TruncateMod} supports broadcasting. More about broadcasting
     *  [here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class TruncateMod extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TruncateMod(Pointer p) { super(p); }

        public TruncateMod(@Const @ByRef Scope scope, @ByVal Input x,
                           @ByVal Input y) { super((Pointer)null); allocate(scope, x, y); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input y);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native TruncateMod z(Output z);
    }

    /** Computes the maximum along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the maximum such that:
     *
     *  \\(output_i = \max_j data_j\\) where max is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the maximum is empty for a given segment ID {@code i}, it outputs the smallest
     *  possible value for the specific numeric type,
     *  {@code output[i] = numeric_limits<T>::lowest()}.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentMax.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class UnsortedSegmentMax extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public UnsortedSegmentMax(Pointer p) { super(p); }

        public UnsortedSegmentMax(@Const @ByRef Scope scope, @ByVal Input data,
                                  @ByVal Input segment_ids, @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids, @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native UnsortedSegmentMax output(Output output);
    }

    /** Computes the minimum along segments of a tensor.
     *
     *  Read \{$math_ops#segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the minimum such that:
     *
     *  \\(output_i = \min_j data_j\\) where min is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If the minimum is empty for a given segment ID {@code i}, it outputs the largest
     *  possible value for the specific numeric type,
     *  {@code output[i] = numeric_limits<T>::max()}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class UnsortedSegmentMin extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public UnsortedSegmentMin(Pointer p) { super(p); }

        public UnsortedSegmentMin(@Const @ByRef Scope scope, @ByVal Input data,
                                  @ByVal Input segment_ids, @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids, @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native UnsortedSegmentMin output(Output output);
    }

    /** Computes the product along segments of a tensor.
     *
     *  Read \{$math_ops#segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  This operator is similar to the unsorted segment sum operator found
     *  [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
     *  Instead of computing the sum over segments, it computes the product of all
     *  entries belonging to a segment such that:
     *
     *  \\(output_i = \prod_j data_j\\) where the product is over {@code j} such
     *  that {@code segment_ids[j] == i}.
     *
     *  If there is no entry for a given segment ID {@code i}, it outputs 1.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A 1-D tensor whose rank is equal to the rank of {@code data}'s
     *  first dimension.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for dimension 0 which
     *  has size {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class UnsortedSegmentProd extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public UnsortedSegmentProd(Pointer p) { super(p); }

        public UnsortedSegmentProd(@Const @ByRef Scope scope, @ByVal Input data,
                                   @ByVal Input segment_ids, @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids, @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native UnsortedSegmentProd output(Output output);
    }

    /** Computes the sum along segments of a tensor.
     *
     *  Read \{$math_ops#Segmentation$the section on segmentation} for an explanation of
     *  segments.
     *
     *  Computes a tensor such that
     *  {@code (output[i] = sum_{j...} data[j...]} where the sum is over tuples {@code j...} such
     *  that {@code segment_ids[j...] == i}.  Unlike {@code SegmentSum}, {@code segment_ids}
     *  need not be sorted and need not cover all values in the full
     *  range of valid values.
     *
     *  If the sum is empty for a given segment ID {@code i}, {@code output[i] = 0}.
     *  If the given segment ID {@code i} is negative, the value is dropped and will not be
     *  added to the sum of the segment.
     *
     *  {@code num_segments} should equal the number of distinct segment IDs.
     *
     *  <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
     *  <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentSum.png" alt>
     *  </div>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * segment_ids: A tensor whose shape is a prefix of {@code data.shape}.
     *
     *  Returns:
     *  * {@code Output}: Has same shape as data, except for the first {@code segment_ids.rank}
     *  dimensions, which are replaced with a single dimension which has size
     *  {@code num_segments}. */
    @Namespace("tensorflow::ops") @NoOffset public static class UnsortedSegmentSum extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public UnsortedSegmentSum(Pointer p) { super(p); }

        public UnsortedSegmentSum(@Const @ByRef Scope scope, @ByVal Input data,
                                  @ByVal Input segment_ids, @ByVal Input num_segments) { super((Pointer)null); allocate(scope, data, segment_ids, num_segments); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input data,
                                     @ByVal Input segment_ids, @ByVal Input num_segments);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native UnsortedSegmentSum output(Output output);
    }

    /** Compute the Hurwitz zeta function \\(\zeta(x, q)\\).
     *
     *  The Hurwitz zeta function is defined as:
     *
     *
     *  \\(\zeta(x, q) = \sum_{n=0}^{\infty} (q + n)^{-x}\\)
     *
     *  Arguments:
     *  * scope: A Scope object
     *
     *  Returns:
     *  * {@code Output}: The z tensor. */
    @Namespace("tensorflow::ops") @NoOffset public static class Zeta extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Zeta(Pointer p) { super(p); }

        public Zeta(@Const @ByRef Scope scope, @ByVal Input x,
                    @ByVal Input q) { super((Pointer)null); allocate(scope, x, q); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input x,
                                     @ByVal Input q);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output z(); public native Zeta z(Output z);
    }

}
