package native_types.core.framework;

import native_types.core.lib.Status;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/op.h"
        //"/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") public class OpRegistryInterface extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public OpRegistryInterface(Pointer p) { super(p); }


    // Returns an error status and sets *op_reg_data to nullptr if no OpDef is
    // registered under that name, otherwise returns the registered OpDef.
    // Caller must not delete the returned pointer.
    public native @ByVal
    Status LookUp(@StdString BytePointer op_type_name,
                  @Cast("const tensorflow::OpRegistrationData**") PointerPointer op_reg_data);
    public native @ByVal Status LookUp(@StdString BytePointer op_type_name,
                                       @Const @ByPtrPtr OpRegistrationData op_reg_data);
    public native @ByVal Status LookUp(@StdString String op_type_name,
                                       @Const @ByPtrPtr OpRegistrationData op_reg_data);

    // Shorthand for calling LookUp to get the OpDef.
    public native @ByVal Status LookUpOpDef(@StdString BytePointer op_type_name, @Cast("const tensorflow::OpDef**") PointerPointer op_def);
    public native @ByVal Status LookUpOpDef(@StdString BytePointer op_type_name, @Const @ByPtrPtr OpDef op_def);
    public native @ByVal Status LookUpOpDef(@StdString String op_type_name, @Const @ByPtrPtr OpDef op_def);
}