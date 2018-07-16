package native_types.cc.client;

import native_types.cc.framework.OutputVector;
import native_types.cc.framework.Scope;
import native_types.core.lib.Status;
import native_types.core.framework.TensorVector;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;


@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/client/client_session.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") public class ClientSession extends Pointer  {

    public ClientSession(Pointer p) { super(p); }

    public ClientSession(@Const @ByRef Scope scope) {super((Pointer)null); allocate(scope);}
    private native void allocate(@Const @ByRef Scope scope);


    public native @ByVal
    Status Run(@Const @ByRef OutputVector fetch_outputs, TensorVector outputs);

}
