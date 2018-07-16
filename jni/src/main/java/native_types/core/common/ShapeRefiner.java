package native_types.core.common;

import native_types.core.framework.OpRegistryInterface;
import native_types.core.framework.VersionDef;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/common_runtime/shape_refiner.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @Opaque public  class ShapeRefiner extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public ShapeRefiner() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ShapeRefiner(Pointer p) { super(p); }

    public ShapeRefiner(@Const @ByRef VersionDef versions, @Const OpRegistryInterface ops) {
        super((Pointer)null);
        allocate(versions, ops);
    }
    private native void allocate(@Const @ByRef VersionDef versions, @Const  OpRegistryInterface ops);
}