package native_types.cc.framework;

import native_types.core.common.ShapeRefiner;
import native_types.core.lib.Status;
import native_types.core.graph.Graph;
import org.bytedeco.javacpp.annotation.*;


@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/scope.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/framework/scope_internal.h"
})

public class scope_factory {

    @Namespace("tensorflow") public static native @ByVal
    Scope NewInternalScope(Graph graph, Status status, ShapeRefiner refiner);
}
