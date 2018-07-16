package native_types.cc.framework;

import native_types.adapters.ArraySlice;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/ops.h"
})

@Namespace("tensorflow") @NoOffset public class InputList extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public InputList(Pointer p) { super(p); }

    /** Implicitly convert a list of outputs to a list of inputs. This is useful
     *  to write code such as ops::Concat(ops::Split(x, 4)). */
    public InputList(@Cast("const tensorflow::OutputList*") @ByRef OutputVector out) { super((Pointer)null); allocate(out); }
    private native void allocate(@Cast("const tensorflow::OutputList*") @ByRef OutputVector out);

    public InputList(@ArraySlice Input inputs) { super((Pointer)null); allocate(inputs); }
    private native void allocate(@ArraySlice Input inputs);
}

