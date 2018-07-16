package native_types.cc.framework;

import native_types.core.graph.Node;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/ops.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

/** Represents a tensor value produced by an Operation. */
@Namespace("tensorflow") @NoOffset public class Output extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Output(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Output(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Output position(long position) {
        return (Output)super.position(position);
    }

    public Output() { super((Pointer)null); allocate(); }
    private native void allocate();
    public Output(Node n) { super((Pointer)null); allocate(n); }
    private native void allocate(Node n);
    public Output(Node n, int index) { super((Pointer)null); allocate(n, index); }
    private native void allocate(Node n, int index);
    public Output(@Const @ByRef Operation op, int index) { super((Pointer)null); allocate(op, index); }
    private native void allocate(@Const @ByRef Operation op, int index);

    public native @ByVal Operation op();
    public native Node node();
    public native int index();
    public native @Cast("tensorflow::DataType") int type();
    public native @StdString BytePointer name();
    public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef Output other);

    public native @Cast("tensorflow::uint64") long hash();
}
