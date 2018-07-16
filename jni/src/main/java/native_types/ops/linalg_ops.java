package native_types.ops;

// #endif  // TENSORFLOW_CC_OPS_IO_OPS_H_


// Parsed from tensorflow/cc/ops/linalg_ops.h

// This file is MACHINE GENERATED! Do not edit.

// #ifndef TENSORFLOW_CC_OPS_LINALG_OPS_H_
// #define TENSORFLOW_CC_OPS_LINALG_OPS_H_

// This file is MACHINE GENERATED! Do not edit.

// #include "tensorflow/cc/framework/ops.h"
// #include "tensorflow/cc/framework/scope.h"
// #include "tensorflow/core/framework/tensor.h"
// #include "tensorflow/core/framework/tensor_shape.h"
// #include "tensorflow/core/framework/types.h"
// #include "tensorflow/core/lib/gtl/array_slice.h"

import native_types.cc.framework.Input;
import native_types.core.graph.Node;
import native_types.cc.framework.Output;
import native_types.cc.framework.Scope;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/ops/linalg_ops.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

public class linalg_ops {




    /** \defgroup linalg_ops Linalg Ops
     *  \{
     <p>
     *  Computes the Cholesky decomposition of one or more square matrices.
     *
     *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
     *  form square matrices.
     *
     *  The input has to be symmetric and positive definite. Only the lower-triangular
     *  part of the input will be used for this operation. The upper-triangular part
     *  will not be read.
     *
     *  The output is a tensor of the same shape as the input
     *  containing the Cholesky decompositions for all input submatrices {@code [..., :, :]}.
     *
     *  **Note**: The gradient computation on GPU is faster for large matrices but
     *  not for large batch dimensions when the submatrices are small. In this
     *  case it might be faster to use the CPU.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Shape is {@code [..., M, M]}.
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., M, M]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class Cholesky extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Cholesky(Pointer p) { super(p); }

        public Cholesky(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public native @ByVal @Name("operator tensorflow::Output")
        Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native Cholesky output(Output output);
    }

    /** Computes the reverse mode backpropagated gradient of the Cholesky algorithm.
     *
     *  For an explanation see "Differentiation of the Cholesky algorithm" by
     *  Iain Murray http://arxiv.org/abs/1602.07527.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * l: Output of batch Cholesky algorithm l = cholesky(A). Shape is {@code [..., M, M]}.
     *  Algorithm depends only on lower triangular part of the innermost matrices of
     *  this tensor.
     *  * grad: df/dl where f is some scalar function. Shape is {@code [..., M, M]}.
     *  Algorithm depends only on lower triangular part of the innermost matrices of
     *  this tensor.
     *
     *  Returns:
     *  * {@code Output}: Symmetrized version of df/dA . Shape is {@code [..., M, M]} */
    @Namespace("tensorflow::ops") @NoOffset public static class CholeskyGrad extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public CholeskyGrad(Pointer p) { super(p); }

        public CholeskyGrad(@Const @ByRef Scope scope, @ByVal Input l,
                            @ByVal Input grad) { super((Pointer)null); allocate(scope, l, grad); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input l,
                                     @ByVal Input grad);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native CholeskyGrad output(Output output);
    }

    /** Computes the sign and the log of the absolute value of the determinant of
     *
     *  one or more square matrices.
     *
     *  The input is a tensor of shape {@code [N, M, M]} whose inner-most 2 dimensions
     *  form square matrices. The outputs are two tensors containing the signs and
     *  absolute values of the log determinants for all N input submatrices
     *  {@code [..., :, :]} such that the determinant = sign*exp(log_abs_determinant).
     *  The log_abs_determinant is computed as det(P)*sum(log(diag(LU))) where LU
     *  is the LU decomposition of the input and P is the corresponding
     *  permutation matrix.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Shape is {@code [N, M, M]}.
     *
     *  Returns:
     *  * {@code Output} sign: The signs of the log determinants of the inputs. Shape is {@code [N]}.
     *  * {@code Output} log_abs_determinant: The logs of the absolute values of the determinants
     *  of the N input matrices.  Shape is {@code [N]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class LogMatrixDeterminant extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public LogMatrixDeterminant(Pointer p) { super(p); }

        public LogMatrixDeterminant(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);

        public native @ByRef Output sign(); public native LogMatrixDeterminant sign(Output sign);
        public native @ByRef Output log_abs_determinant(); public native LogMatrixDeterminant log_abs_determinant(Output log_abs_determinant);
    }

    /** Computes the determinant of one or more square matrices.
     *
     *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor containing the determinants
     *  for all input submatrices {@code [..., :, :]}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Shape is {@code [..., M, M]}.
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [...]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixDeterminant extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixDeterminant(Pointer p) { super(p); }

        public MatrixDeterminant(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native MatrixDeterminant output(Output output);
    }

    /** Computes the matrix exponential of one or more square matrices:
     *
     *  exp(A) = \sum_{n=0}^\infty A^n/n!
     *
     *  The exponential is computed using a combination of the scaling and squaring
     *  method and the Pade approximation. Details can be founds in:
     *  Nicholas J. Higham, "The scaling and squaring method for the matrix exponential
     *  revisited," SIAM J. Matrix Anal. Applic., 26:1179-1193, 2005.
     *
     *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor of the same shape as the input
     *  containing the exponential for all input submatrices {@code [..., :, :]}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Shape is {@code [..., M, M]}.
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., M, M]}.
     *
     *  \compatibility(scipy)
     *  Equivalent to scipy.linalg.expm
     *  \end_compatibility */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixExponential extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixExponential(Pointer p) { super(p); }

        public MatrixExponential(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public native @ByRef Output output(); public native MatrixExponential output(Output output);
    }

    /** Computes the inverse of one or more square invertible matrices or their
     *
     *  adjoints (conjugate transposes).
     *
     *  The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
     *  form square matrices. The output is a tensor of the same shape as the input
     *  containing the inverse for all input submatrices {@code [..., :, :]}.
     *
     *  The op uses LU decomposition with partial pivoting to compute the inverses.
     *
     *  If a matrix is not invertible there is no guarantee what the op does. It
     *  may detect the condition and raise an exception or it may simply return a
     *  garbage result.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: Shape is {@code [..., M, M]}.
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., M, M]}.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.linalg.inv
     *  \end_compatibility */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixInverse extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixInverse(Pointer p) { super(p); }

        /** Optional attribute setters for MatrixInverse */
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
            public native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

            public native @Cast("bool") boolean adjoint_(); public native Attrs adjoint_(boolean adjoint_);
        }
        public MatrixInverse(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public MatrixInverse(@Const @ByRef Scope scope, @ByVal Input input,
                             @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native MatrixInverse output(Output output);
    }

    /** Solves systems of linear equations.
     *
     *  {@code Matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
     *  form square matrices. {@code Rhs} is a tensor of shape {@code [..., M, K]}. The {@code output} is
     *  a tensor shape {@code [..., M, K]}.  If {@code adjoint} is {@code False} then each output matrix
     *  satisfies {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
     *  If {@code adjoint} is {@code True} then each output matrix satisfies
     *  {@code adjoint(matrix[..., :, :]) * output[..., :, :] = rhs[..., :, :]}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * matrix: Shape is {@code [..., M, M]}.
     *  * rhs: Shape is {@code [..., M, K]}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * adjoint: Boolean indicating whether to solve with {@code matrix} or its (block-wise)
     *  adjoint.
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., M, K]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixSolve extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixSolve(Pointer p) { super(p); }

        /** Optional attribute setters for MatrixSolve */
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

            /** Boolean indicating whether to solve with {@code matrix} or its (block-wise)
             *  adjoint.
             *
             *  Defaults to false */
            public native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

            public native @Cast("bool") boolean adjoint_(); public native Attrs adjoint_(boolean adjoint_);
        }
        public MatrixSolve(@Const @ByRef Scope scope, @ByVal Input matrix,
                           @ByVal Input rhs) { super((Pointer)null); allocate(scope, matrix, rhs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix,
                                     @ByVal Input rhs);
        public MatrixSolve(@Const @ByRef Scope scope, @ByVal Input matrix,
                           @ByVal Input rhs, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, matrix, rhs, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix,
                                     @ByVal Input rhs, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native MatrixSolve output(Output output);
    }

    /** Solves one or more linear least-squares problems.
     *
     *  {@code matrix} is a tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
     *  form real or complex matrices of size {@code [M, N]}. {@code Rhs} is a tensor of the same
     *  type as {@code matrix} and shape {@code [..., M, K]}.
     *  The output is a tensor shape {@code [..., N, K]} where each output matrix solves
     *  each of the equations
     *  {@code matrix[..., :, :]} * {@code output[..., :, :]} = {@code rhs[..., :, :]}
     *  in the least squares sense.
     *
     *  We use the following notation for (complex) matrix and right-hand sides
     *  in the batch:
     *
     *  {@code matrix}=\\(A \in \mathbb{C}^{m \times n}\\),
     *  {@code rhs}=\\(B  \in \mathbb{C}^{m \times k}\\),
     *  {@code output}=\\(X  \in \mathbb{C}^{n \times k}\\),
     *  {@code l2_regularizer}=\\(\lambda \in \mathbb{R}\\).
     *
     *  If {@code fast} is {@code True}, then the solution is computed by solving the normal
     *  equations using Cholesky decomposition. Specifically, if \\(m \ge n\\) then
     *  \\(X = (A^H A + \lambda I)^{-1} A^H B\\), which solves the least-squares
     *  problem \\(X = \mathrm{argmin}_{Z \in \Re^{n \times k} } ||A Z - B||_F^2 + \lambda ||Z||_F^2\\).
     *  If \\(m \lt n\\) then {@code output} is computed as
     *  \\(X = A^H (A A^H + \lambda I)^{-1} B\\), which (for \\(\lambda = 0\\)) is the
     *  minimum-norm solution to the under-determined linear system, i.e.
     *  \\(X = \mathrm{argmin}_{Z \in \mathbb{C}^{n \times k} } ||Z||_F^2 \\),
     *  subject to \\(A Z = B\\). Notice that the fast path is only numerically stable
     *  when \\(A\\) is numerically full rank and has a condition number
     *  \\(\mathrm{cond}(A) \lt \frac{1}{\sqrt{\epsilon_{mach} } }\\) or \\(\lambda\\) is
     *  sufficiently large.
     *
     *  If {@code fast} is {@code False} an algorithm based on the numerically robust complete
     *  orthogonal decomposition is used. This computes the minimum-norm
     *  least-squares solution, even when \\(A\\) is rank deficient. This path is
     *  typically 6-7 times slower than the fast path. If {@code fast} is {@code False} then
     *  {@code l2_regularizer} is ignored.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * matrix: Shape is {@code [..., M, N]}.
     *  * rhs: Shape is {@code [..., M, K]}.
     *  * l2_regularizer: Scalar tensor.
     *
     *  \compatibility(numpy)
     *  Equivalent to np.linalg.lstsq
     *  \end_compatibility
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., N, K]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixSolveLs extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixSolveLs(Pointer p) { super(p); }

        /** Optional attribute setters for MatrixSolveLs */
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

            /** Defaults to true */
            public native @ByVal Attrs Fast(@Cast("bool") boolean x);

            public native @Cast("bool") boolean fast_(); public native Attrs fast_(boolean fast_);
        }
        public MatrixSolveLs(@Const @ByRef Scope scope, @ByVal Input matrix,
                             @ByVal Input rhs, @ByVal Input l2_regularizer) { super((Pointer)null); allocate(scope, matrix, rhs, l2_regularizer); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix,
                                     @ByVal Input rhs, @ByVal Input l2_regularizer);
        public MatrixSolveLs(@Const @ByRef Scope scope, @ByVal Input matrix,
                             @ByVal Input rhs, @ByVal Input l2_regularizer,
                             @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, matrix, rhs, l2_regularizer, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix,
                                     @ByVal Input rhs, @ByVal Input l2_regularizer,
                                     @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Fast(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native MatrixSolveLs output(Output output);
    }

    /** Solves systems of linear equations with upper or lower triangular matrices by
     *
     *  backsubstitution.
     *
     *  {@code matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form
     *  square matrices. If {@code lower} is {@code True} then the strictly upper triangular part
     *  of each inner-most matrix is assumed to be zero and not accessed.
     *  If {@code lower} is False then the strictly lower triangular part of each inner-most
     *  matrix is assumed to be zero and not accessed.
     *  {@code rhs} is a tensor of shape {@code [..., M, K]}.
     *
     *  The output is a tensor of shape {@code [..., M, K]}. If {@code adjoint} is
     *  {@code True} then the innermost matrices in {@code output} satisfy matrix equations
     *  {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
     *  If {@code adjoint} is {@code False} then the strictly then the  innermost matrices in
     *  {@code output} satisfy matrix equations
     *  {@code adjoint(matrix[..., i, k]) * output[..., k, j] = rhs[..., i, j]}.
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * matrix: Shape is {@code [..., M, M]}.
     *  * rhs: Shape is {@code [..., M, K]}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * lower: Boolean indicating whether the innermost matrices in {@code matrix} are
     *  lower or upper triangular.
     *  * adjoint: Boolean indicating whether to solve with {@code matrix} or its (block-wise)
     *           adjoint.
     *
     *  \compatibility(numpy)
     *  Equivalent to scipy.linalg.solve_triangular
     *  \end_compatibility
     *
     *  Returns:
     *  * {@code Output}: Shape is {@code [..., M, K]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class MatrixTriangularSolve extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public MatrixTriangularSolve(Pointer p) { super(p); }

        /** Optional attribute setters for MatrixTriangularSolve */
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

            /** Boolean indicating whether the innermost matrices in {@code matrix} are
             *  lower or upper triangular.
             *
             *  Defaults to true */

            ///
            ///
            public native @ByVal Attrs Lower(@Cast("bool") boolean x);

            /** Boolean indicating whether to solve with {@code matrix} or its (block-wise)
             *           adjoint.
             *
             *  \compatibility(numpy)
             *  Equivalent to scipy.linalg.solve_triangular
             *  \end_compatibility
             *
             *  Defaults to false */
            public native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

            public native @Cast("bool") boolean lower_(); public native Attrs lower_(boolean lower_);
            public native @Cast("bool") boolean adjoint_(); public native Attrs adjoint_(boolean adjoint_);
        }
        public MatrixTriangularSolve(@Const @ByRef Scope scope, @ByVal Input matrix, @ByVal Input rhs) { super((Pointer)null); allocate(scope, matrix, rhs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix, @ByVal Input rhs);
        public MatrixTriangularSolve(@Const @ByRef Scope scope, @ByVal Input matrix, @ByVal Input rhs, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, matrix, rhs, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input matrix, @ByVal Input rhs, @Const @ByRef Attrs attrs);
        public native @ByVal @Name("operator tensorflow::Output") Output asOutput();
        public native @ByVal @Name("operator tensorflow::Input") Input asInput();
        public native Node node();

        public static native @ByVal Attrs Lower(@Cast("bool") boolean x);
        public static native @ByVal Attrs Adjoint(@Cast("bool") boolean x);

        public native @ByRef Output output(); public native MatrixTriangularSolve output(Output output);
    }

    /** Computes the QR decompositions of one or more matrices.
     *
     *  Computes the QR decomposition of each inner matrix in {@code tensor} such that
     *  {@code tensor[..., :, :] = q[..., :, :] * r[..., :,:])}
     *
     *  <pre>{@code python
     *  # a is a tensor.
     *  # q is a tensor of orthonormal matrices.
     *  # r is a tensor of upper triangular matrices.
     *  q, r = qr(a)
     *  q_full, r_full = qr(a, full_matrices=True)
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
     *  form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * full_matrices: If true, compute full-sized {@code q} and {@code r}. If false
     *  (the default), compute only the leading {@code P} columns of {@code q}.
     *
     *  Returns:
     *  * {@code Output} q: Orthonormal basis for range of {@code a}. If {@code full_matrices} is {@code False} then
     *  shape is {@code [..., M, P]}; if {@code full_matrices} is {@code True} then shape is
     *  {@code [..., M, M]}.
     *  * {@code Output} r: Triangular factor. If {@code full_matrices} is {@code False} then shape is
     *  {@code [..., P, N]}. If {@code full_matrices} is {@code True} then shape is {@code [..., M, N]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class Qr extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Qr(Pointer p) { super(p); }

        /** Optional attribute setters for Qr */
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

            /** If true, compute full-sized {@code q} and {@code r}. If false
             *  (the default), compute only the leading {@code P} columns of {@code q}.
             *
             *  Defaults to false */
            public native @ByVal Attrs FullMatrices(@Cast("bool") boolean x);

            public native @Cast("bool") boolean full_matrices_(); public native Attrs full_matrices_(boolean full_matrices_);
        }
        public Qr(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public Qr(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs FullMatrices(@Cast("bool") boolean x);

        public native @ByRef Output q(); public native Qr q(Output q);
        public native @ByRef Output r(); public native Qr r(Output r);
    }

    /** Computes the eigen decomposition of one or more square self-adjoint matrices.
     *
     *  Computes the eigenvalues and (optionally) eigenvectors of each inner matrix in
     *  {@code input} such that {@code input[..., :, :] = v[..., :, :] * diag(e[..., :])}. The eigenvalues
     *  are sorted in non-decreasing order.
     *
     *  <pre>{@code python
     *  # a is a tensor.
     *  # e is a tensor of eigenvalues.
     *  # v is a tensor of eigenvectors.
     *  e, v = self_adjoint_eig(a)
     *  e = self_adjoint_eig(a, compute_v=False)
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: {@code Tensor} input of shape {@code [N, N]}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * compute_v: If {@code True} then eigenvectors will be computed and returned in {@code v}.
     *  Otherwise, only the eigenvalues will be computed.
     *
     *  Returns:
     *  * {@code Output} e: Eigenvalues. Shape is {@code [N]}.
     *  * {@code Output} v: Eigenvectors. Shape is {@code [N, N]}. */
    @Namespace("tensorflow::ops") @NoOffset public static class SelfAdjointEig extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public SelfAdjointEig(Pointer p) { super(p); }

        /** Optional attribute setters for SelfAdjointEig */
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

            /** If {@code True} then eigenvectors will be computed and returned in {@code v}.
             *  Otherwise, only the eigenvalues will be computed.
             *
             *  Defaults to true */
            public native @ByVal Attrs ComputeV(@Cast("bool") boolean x);

            public native @Cast("bool") boolean compute_v_(); public native Attrs compute_v_(boolean compute_v_);
        }
        public SelfAdjointEig(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public SelfAdjointEig(@Const @ByRef Scope scope, @ByVal Input input,
                              @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input,
                                     @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs ComputeV(@Cast("bool") boolean x);

        public native @ByRef Output e(); public native SelfAdjointEig e(Output e);
        public native @ByRef Output v(); public native SelfAdjointEig v(Output v);
    }

    /** Computes the singular value decompositions of one or more matrices.
     *
     *  Computes the SVD of each inner matrix in {@code input} such that
     *  {@code input[..., :, :] = u[..., :, :] * diag(s[..., :, :]) * transpose(v[..., :, :])}
     *
     *  <pre>{@code python
     *  # a is a tensor containing a batch of matrices.
     *  # s is a tensor of singular values for each matrix.
     *  # u is the tensor containing of left singular vectors for each matrix.
     *  # v is the tensor containing of right singular vectors for each matrix.
     *  s, u, v = svd(a)
     *  s, _, _ = svd(a, compute_uv=False)
     *  }</pre>
     *
     *  Arguments:
     *  * scope: A Scope object
     *  * input: A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
     *  form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
     *
     *  Optional attributes (see {@code Attrs}):
     *  * compute_uv: If true, left and right singular vectors will be
     *  computed and returned in {@code u} and {@code v}, respectively.
     *  If false, {@code u} and {@code v} are not set and should never referenced.
     *  * full_matrices: If true, compute full-sized {@code u} and {@code v}. If false
     *  (the default), compute only the leading {@code P} singular vectors.
     *  Ignored if {@code compute_uv} is {@code False}.
     *
     *  Returns:
     *  * {@code Output} s: Singular values. Shape is {@code [..., P]}.
     *  * {@code Output} u: Left singular vectors. If {@code full_matrices} is {@code False} then shape is
     *  {@code [..., M, P]}; if {@code full_matrices} is {@code True} then shape is
     *  {@code [..., M, M]}. Undefined if {@code compute_uv} is {@code False}.
     *  * {@code Output} v: Left singular vectors. If {@code full_matrices} is {@code False} then shape is
     *  {@code [..., N, P]}. If {@code full_matrices} is {@code True} then shape is {@code [..., N, N]}.
     *  Undefined if {@code compute_uv} is false. */
    @Namespace("tensorflow::ops") @NoOffset public static class Svd extends Pointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Svd(Pointer p) { super(p); }

        /** Optional attribute setters for Svd */
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

            /** If true, left and right singular vectors will be
             *  computed and returned in {@code u} and {@code v}, respectively.
             *  If false, {@code u} and {@code v} are not set and should never referenced.
             *
             *  Defaults to true */

            ///
            public native @ByVal Attrs ComputeUv(@Cast("bool") boolean x);

            /** If true, compute full-sized {@code u} and {@code v}. If false
             *  (the default), compute only the leading {@code P} singular vectors.
             *  Ignored if {@code compute_uv} is {@code False}.
             *
             *  Defaults to false */
            public native @ByVal Attrs FullMatrices(@Cast("bool") boolean x);

            public native @Cast("bool") boolean compute_uv_(); public native Attrs compute_uv_(boolean compute_uv_);
            public native @Cast("bool") boolean full_matrices_(); public native Attrs full_matrices_(boolean full_matrices_);
        }
        public Svd(@Const @ByRef Scope scope, @ByVal Input input) { super((Pointer)null); allocate(scope, input); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input);
        public Svd(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs) { super((Pointer)null); allocate(scope, input, attrs); }
        private native void allocate(@Const @ByRef Scope scope, @ByVal Input input, @Const @ByRef Attrs attrs);

        public static native @ByVal Attrs ComputeUv(@Cast("bool") boolean x);
        public static native @ByVal Attrs FullMatrices(@Cast("bool") boolean x);

        public native @ByRef Output s(); public native Svd s(Output s);
        public native @ByRef Output u(); public native Svd u(Output u);
        public native @ByRef Output v(); public native Svd v(Output v);
    }
}
