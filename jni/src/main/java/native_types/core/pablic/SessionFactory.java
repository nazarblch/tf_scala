package native_types.core.pablic;

import native_types.core.lib.Status;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/public/session.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/common_runtime/session_factory.h"
})

public class SessionFactory {

     public static native @ByVal
     Status NewSession(@Const @ByRef SessionOptions options, @Cast("tensorflow::Session**") PointerPointer out_session);
     public static native @ByVal Status NewSession(@Const @ByRef SessionOptions options, @ByPtrPtr Session out_session);

}
