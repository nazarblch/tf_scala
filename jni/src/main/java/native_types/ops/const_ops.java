package native_types.ops;

import native_types.cc.framework.Input;
import native_types.cc.framework.Output;
import native_types.cc.framework.Scope;
import native_types.core.framework.Tensor;
import native_types.core.framework.TensorShape;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/cc/ops/const_op.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

public class const_ops {
    /** \defgroup const_op Const Op
     *  \{ */

    @Namespace("tensorflow::ops") public static native @ByVal
    Output Const(@Const @ByRef Scope scope, @Const @ByRef Input.Initializer val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @ByRef Tensor val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, byte val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, short val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, int val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, long val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, float val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, double val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, boolean val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @StdString String val);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @StdString BytePointer val);

    // @Namespace("tensorflow::ops") public static native @ByVal Output ConstFromProto(@Const @ByRef Scope scope, @Const @ByRef TensorProto proto);

    // @Namespace("tensorflow::ops") public static native @ByVal NodeBuilder.NodeOut AsNodeOut(@Const @ByRef Scope scope, @Const @ByRef Input inp);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @Cast("const unsigned char") byte v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, short v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, int v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @Cast("const long long") long v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, float v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, double v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @Cast("const bool") boolean v, @Const @ByVal TensorShape shape);

    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @StdString BytePointer v, @Const @ByVal TensorShape shape);
    @Namespace("tensorflow::ops") public static native @ByVal Output Const(@Const @ByRef Scope scope, @StdString String v, @Const @ByVal TensorShape shape);

    // @Namespace("tensorflow::ops") public static native @ByVal NodeOutVector AsNodeOutList(@Const @ByRef Scope scope, @Const @ByRef InputList inp);
}
