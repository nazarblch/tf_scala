package native_types.core.pablic;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/public/session_options.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class SessionOptions extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public SessionOptions(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public SessionOptions(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public SessionOptions position(long position) {
        return (SessionOptions)super.position(position);
    }

    /** The environment to use. */

    ///
    ///
    ///
    ///
    ///
//    public native Env env(); public native SessionOptions env(Env env);

    /** \brief The TensorFlow runtime to connect to.
     *
     *  If 'target' is empty or unspecified, the local TensorFlow runtime
     *  implementation will be used.  Otherwise, the TensorFlow engine
     *  defined by 'target' will be used to perform all computations.
     *
     *  "target" can be either a single entry or a comma separated list
     *  of entries. Each entry is a resolvable address of the
     *  following format:
     *    local
     *    ip:port
     *    host:port
     *    ... other system-specific formats to identify tasks and jobs ...
     *
     *  NOTE: at the moment 'local' maps to an in-process service-based
     *  runtime.
     *
     *  Upon creation, a single session affines itself to one of the
     *  remote processes, with possible load balancing choices when the
     *  "target" resolves to a list of possible processes.
     *
     *  If the session disconnects from the remote process during its
     *  lifetime, session calls may fail immediately. */
    public native @StdString BytePointer target(); public native SessionOptions target(BytePointer target);

    /** Configuration options. */
//    public native @ByRef ConfigProto config(); public native SessionOptions config(ConfigProto config);

    public SessionOptions() { super((Pointer)null); allocate(); }
    private native void allocate();
}