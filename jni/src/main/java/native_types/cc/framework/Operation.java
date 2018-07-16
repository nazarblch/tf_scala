package native_types.cc.framework;


import native_types.core.graph.Node;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;


@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/ops.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class Operation extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Operation(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Operation(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Operation position(long position) {
        return (Operation)super.position(position);
    }

    public Operation() { super((Pointer)null); allocate(); }
    private native void allocate();
    public Operation(Node n) { super((Pointer)null); allocate(n); }
    private native void allocate(Node n);

    public native int num_inputs();
    public native @Cast("tensorflow::DataType") int input_type(int o);
    public native @ByVal
    Output input(int i);

    public native int num_outputs();
    public native @Cast("tensorflow::DataType") int output_type(int o);
    public native @ByVal Output output(int i);

    public native Node node();

    public native @Cast("tensorflow::uint64") long hash(int index);

    public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef Operation other);
}
