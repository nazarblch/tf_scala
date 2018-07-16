package native_types.core.framework;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/op.h"
        //"/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class OpRegistrationData extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public OpRegistrationData(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public OpRegistrationData(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public OpRegistrationData position(long position) {
        return (OpRegistrationData)super.position(position);
    }

    public OpRegistrationData() { super((Pointer)null); allocate(); }
    private native void allocate();
    public OpRegistrationData(@Const @ByRef OpDef def) { super((Pointer)null); allocate(def); }
    private native void allocate(@Const @ByRef OpDef def);
    public OpRegistrationData(@Const @ByRef OpDef def, @Cast("const tensorflow::OpShapeInferenceFn*") @ByRef Pointer fn,
                              @Cast("bool") boolean is_function/*=false*/) { super((Pointer)null); allocate(def, fn, is_function); }
    private native void allocate(@Const @ByRef OpDef def, @Cast("const tensorflow::OpShapeInferenceFn*") @ByRef Pointer fn,
                                 @Cast("bool") boolean is_function/*=false*/);
    public OpRegistrationData(@Const @ByRef OpDef def, @Cast("const tensorflow::OpShapeInferenceFn*") @ByRef Pointer fn) { super((Pointer)null); allocate(def, fn); }
    private native void allocate(@Const @ByRef OpDef def, @Cast("const tensorflow::OpShapeInferenceFn*") @ByRef Pointer fn);

    public native @ByRef OpDef op_def(); public native OpRegistrationData op_def(OpDef op_def);
   //  @MemberSetter public native OpRegistrationData shape_inference_fn(@ByVal ShapeInferenceFn shape_inference_fn);
    public native @Cast("bool") boolean is_function_op(); public native OpRegistrationData is_function_op(boolean is_function_op);
}