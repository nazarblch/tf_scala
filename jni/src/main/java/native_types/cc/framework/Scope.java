package native_types.cc.framework;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/scope.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class Scope extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Scope(Pointer p) { super(p); }

    public Scope(@Const @ByRef Scope other) { super((Pointer)null); allocate(other); }
    private native void allocate(@Const @ByRef Scope other);

    // public Scope(Impl impl) { super((Pointer)null); allocate(impl); }
    // private native void allocate(Impl impl);

    public native @ByRef @Name("operator =") Scope put(@Const @ByRef Scope other);

    // The following functions are for users making graphs. They return brand new
    // scopes, or scopes derived from an existing scope object.

    /** Return a new scope.
     *  This creates a new graph and all operations constructed in this graph
     *  should use the returned object as the "root" scope. */
    public static native @ByVal Scope NewRootScope();

//    /** Return a new scope. Ops created with this scope will have
//     *  {@code name/child_scope_name} as the prefix. The actual name will be unique
//     *  in the current scope. All other properties are inherited from the current
//     *  scope. If {@code child_scope_name} is empty, the {@code /} is elided. */
//    public native @ByVal Scope NewSubScope(@StdString BytePointer child_scope_name);
//    public native @ByVal Scope NewSubScope(@StdString String child_scope_name);
//
//    /** Return a new scope. All ops created within the returned scope will have
//     *  names of the form {@code name/op_name[_suffix]}. */
//    public native @ByVal Scope WithOpName(@StdString BytePointer op_name);
    public native @ByVal Scope WithOpName(@StdString String op_name);
//
//    /** Return a new scope. All ops created within the returned scope will have as
//     *  control dependencies the union of operations in the control_deps vector
//     *  and the control dependencies of the current scope. */
//    public native @ByVal Scope WithControlDependencies(
//            @ArraySlice Operation control_deps);
//    /** Same as above, but convenient to add control dependency on the operation
//     *  producing the control_dep output. */
//    public native @ByVal Scope WithControlDependencies(@Const @ByRef Output control_dep);
//
//    /** Return a new scope. All ops created within the returned scope will have no
//     *  control dependencies on other operations. */
//    public native @ByVal Scope WithNoControlDependencies();
//
//    /** Return a new scope. All ops created within the returned scope will have
//     *  the device field set to 'device'. */
//    public native @ByVal Scope WithDevice(@StdString BytePointer device);
//    public native @ByVal Scope WithDevice(@StdString String device);
//
//    /** Return a new scope. All ops created within the returned scope will be
//     *  co-located on the device where op is placed.
//     *  NOTE: This function is intended to be use internal libraries only for
//     *  controlling placement of ops on to devices. Public use is not encouraged
//     *  because the implementation of device placement is subject to change. */
//    public native @ByVal Scope ColocateWith(@Const @ByRef Operation op);
//    /** Convenience function for above. */
//    public native @ByVal Scope ColocateWith(@Const @ByRef Output out);
//    /** Clear all colocation constraints. */
//    public native @ByVal Scope ClearColocation();
//
//    /** Return a new scope. The op-constructor functions taking the returned scope
//     *  as the scope argument will exit as soon as an error is detected, instead
//     *  of setting the status on the scope. */
//    public native @ByVal Scope ExitOnError();
//
//    /** Return a new scope. All ops created with the new scope will have
//     *  kernel_label as the value for their '_kernel' attribute; */
//    public native @ByVal Scope WithKernelLabel(@StdString BytePointer kernel_label);
//    public native @ByVal Scope WithKernelLabel(@StdString String kernel_label);
//
//    // The following functions are for scope object consumers.
//
//    /** Return a unique name, using default_name if an op name has not been
//     *  specified. */
//    public native @StdString BytePointer GetUniqueNameForOp(@StdString BytePointer default_name);
//    public native @StdString String GetUniqueNameForOp(@StdString String default_name);
//
//    /** Update the status on this scope.
//     *  Note: The status object is shared between all children of this scope.
//     *  If the resulting status is not Status::OK() and exit_on_error_ is set on
//     *  this scope, this function exits by calling LOG(FATAL). */
//    public native void UpdateStatus(@Const @ByVal Status s);
//
//    // START_SKIP_DOXYGEN
//
//    /** Update the builder with properties accumulated in this scope. Does not set
//     *  status(). */
//    // TODO(skyewm): NodeBuilder is not part of public API
//    // public native void UpdateBuilder(NodeBuilder builder);
//    // END_SKIP_DOXYGEN
//
//    public native @Cast("bool") boolean ok();
//
//    // TODO(skyewm): Graph is not part of public API
//    public native Graph graph();
//
//    // TODO(skyewm): Graph is not part of public API
//    public native @SharedPtr Graph graph_as_shared_ptr();
//
//    public native @ByVal Status status();
//
//    /** If status() is Status::OK(), convert the Graph object stored in this scope
//     *  to a GraphDef proto and return Status::OK(). Otherwise, return the error
//     *  status as is without performing GraphDef conversion. */
//    public native @ByVal Status ToGraphDef(GraphDef gdef);
//
//    // START_SKIP_DOXYGEN
//
//    /** If status() is Status::OK(), construct a Graph object using the default
//     *  GraphConstructorOptions, and return Status::OK if graph construction was
//     *  successful. Otherwise, return the error status. */
//    // TODO(josh11b, keveman): Make this faster; right now it converts
//    // Graph->GraphDef->Graph.  This cleans up the graph (e.g. adds
//    // edges from the source and to the sink node, resolves back edges
//    // by name), and makes sure the resulting graph is valid.
//    public native @ByVal Status ToGraph(Graph g);
//
//    // Calls AddNode() using this scope's ShapeRefiner. This exists in the public
//    // API to prevent custom op wrappers from needing access to shape_refiner.h or
//    // scope_internal.h.
//    // TODO(skyewm): remove this from public API
//    public native @ByVal Status DoShapeInference(Node node);
//
//    // Creates a new root scope that causes all DoShapeInference() calls to return
//    // Status::OK() (on the returned scope and any subscopes). Used for testing.
//    // TODO(skyewm): fix tests that still require this and eventually remove, or
//    // at least remove from public API
//    public static native @ByVal Scope DisabledShapeInferenceScope();
//    // END_SKIP_DOXYGEN
//
//    public native @StdVector Operation control_deps();
//
//    // START_SKIP_DOXYGEN
    @Opaque public static class Impl extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public Impl() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public Impl(Pointer p) { super(p); }

//        public Impl(Graph graph, Status status, NameMap name_map, ShapeRefiner refiner, boolean disable_shape_inference) {
//            super((Pointer)null);
//            allocate(graph, status, name_map, refiner, disable_shape_inference);
//        }
//        private native void allocate(Graph graph, Status status, NameMap name_map, ShapeRefiner refiner, boolean disable_shape_inference);

    }

    public native Impl impl();
}