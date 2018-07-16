package native_types.c_api.eager;

import native_types.core.framework.Tensor;
import native_types.loader.LibLoader;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.*;
import native_types.c_api.c_api.TF_Status;
import org.platanios.tensorflow.jni.ExecWithStatusCheck;
import scala.Array;
import scala.Function1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static native_types.c_api.c_api.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/eager/c_api.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/eager/c_api_internal.h",
        //"/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/eager/api_helper.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/c_api.h"
})


public class c_api {

    private static TFE_Context context;

    static {
        LibLoader.load();
        context = createContext();
    }

    @Opaque public static class TFE_TensorHandle extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TFE_TensorHandle() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TFE_TensorHandle(Pointer p) { super(p); }

        public TFE_TensorHandle(long address) {super((Pointer)null); super.address = address; }

    }

    @Opaque public static class TFE_Op extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TFE_Op() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TFE_Op(Pointer p) { super(p); }

    }

    @Opaque public static class TFE_Context extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TFE_Context() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TFE_Context(Pointer p) { super(p); }

    }

    @Opaque public static class TFE_ContextOptions extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TFE_ContextOptions() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TFE_ContextOptions(Pointer p) { super(p); }

    }


    public static native void TFE_OpSetAttrInt(TFE_Op op, @Cast("const char*") BytePointer attr_name, @Cast("int64_t") long value);

    public static native @Cast("const char*") BytePointer TFE_TensorHandleDeviceName(TFE_TensorHandle h, TF_Status status);

    public static native void TFE_DeleteTensorHandle(TFE_TensorHandle h);

    public static native TFE_Context TFE_NewContext(@Const TFE_ContextOptions opts, TF_Status status);

    public static native void TFE_ContextOptionsSetAsync(TFE_ContextOptions options, @Cast("unsigned char") byte async);

    public static native TFE_ContextOptions TFE_NewContextOptions();

    public static native TFE_TensorHandle TFE_NewTensorHandle(@Const @ByRef Tensor t);

    public static native TFE_TensorHandle TFE_NewTensorHandle(native_types.c_api.c_api.TF_Tensor t, native_types.c_api.c_api.TF_Status status);

    public static native @Const Tensor TFE_TensorHandleUnderlyingTensorInHostMemory(TFE_TensorHandle h, native_types.c_api.c_api.TF_Status status);

    public static native TFE_Op TFE_NewOp(TFE_Context ctx, @Cast("const char*") String op_or_function_name, native_types.c_api.c_api.TF_Status status);

    public static TFE_Op TFE_NewOp(@Cast("const char*") String op_or_function_name, native_types.c_api.c_api.TF_Status status) {
        return TFE_NewOp(context, op_or_function_name, status);
    }

    public static native int TFE_TensorHandleNumDims(TFE_TensorHandle h, TF_Status status);

    public static int NumDims(TFE_TensorHandle h) {
        return ExecWithStatusCheck.apply(s -> TFE_TensorHandleNumDims(h, s));
    }

    public static long[] Dims(TFE_TensorHandle h) {
        int n = NumDims(h);
        long [] res = new long[n];
        for (int i = 0; i < n; i++) {
            int finalI = i;
            res[i] = ExecWithStatusCheck.apply(s -> TFE_TensorHandleDim(h, finalI, s));
        }
        return res;
    }

    public static native @Cast("int64_t") long TFE_TensorHandleDim(TFE_TensorHandle h, int dim_index, TF_Status status);


    public static native void TFE_OpSetDevice(TFE_Op op, @Cast("const char*") BytePointer device_name, TF_Status status);

    public static native void TFE_OpAddInput(TFE_Op op, TFE_TensorHandle h, TF_Status status);
    public static native @Cast("TF_DataType") int TFE_TensorHandleDataType(TFE_TensorHandle h);

    public static native void TFE_OpSetAttrTypeInt(TFE_Op op, @Cast("const char*") BytePointer attr_name, int value);

    public static native void TFE_Execute(TFE_Op op, @Cast("TFE_TensorHandle**") PointerPointer<TFE_TensorHandle> retvals, @Cast("int*") int[] num_retvals, TF_Status status);

    public static native TFE_TensorHandle TFE_Exec(TFE_Op op, TF_Status status);



    public static native native_types.c_api.c_api.TF_Tensor TFE_TensorHandleResolve(TFE_TensorHandle h, TF_Status status);

    public static native TFE_TensorHandle TFE_TensorHandleCopyToDevice(TFE_TensorHandle h, TFE_Context ctx, @Cast("const char*") String device_name, TF_Status status);
    public static TFE_TensorHandle TFE_TensorHandleCopyToDevice(TFE_TensorHandle h, @Cast("const char*") String device_name, TF_Status status) {
        return TFE_TensorHandleCopyToDevice(h, context, device_name, status);
    }


    public static native void deleteTensor(Tensor tensor);

    public static TFE_Context createContext() {
        TFE_ContextOptions options = TFE_NewContextOptions();
        byte async = 0;
        TFE_ContextOptionsSetAsync(options, async);
        TF_Status status = TF_NewStatus();
        TFE_Context res =  TFE_NewContext(options, status);
        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        }
        return res;
    }

    public static TFE_TensorHandle exec_op(String op_name, List<String> attrNames, List<TFE_TensorHandle> handles, HashMap<String, Long> intAttrs) {

        // assert (attrNames.size() == handles.size());

        TF_Status status = TF_NewStatus();

        TFE_Op op = TFE_NewOp(context, op_name, status);

        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        }

        TFE_OpSetDevice(op, new BytePointer("/job:localhost/replica:0/task:0/device:CPU:0"), status);

        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        }

        for(TFE_TensorHandle h: handles) {
            TFE_OpAddInput(op, h, status);
            if(TF_GetCode(status) != 0) {
                throwExeptionByCode(status);
            }
        }

        for(int i = 0; i < attrNames.size(); i++) {
            int attr_Tshape = TFE_TensorHandleDataType(handles.get(i));
            TFE_OpSetAttrTypeInt(op, new BytePointer(attrNames.get(i)), attr_Tshape);
        }


        for(Map.Entry<String, Long> e: intAttrs.entrySet()) {
            TFE_OpSetAttrInt(op, new BytePointer(e.getKey()), e.getValue());
        }


        TFE_TensorHandle res = TFE_Exec(op, status);
        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        }

        TF_DeleteStatus(status);

        return res;
    }

    public static Tensor reshape(Tensor tensor, Tensor shape) {

        TFE_TensorHandle tensorHandle = c_api.TFE_NewTensorHandle(tensor);
        TFE_TensorHandle shapeHandle = c_api.TFE_NewTensorHandle(shape);

        TF_Status status = TF_NewStatus();

        String[] attrNames = {"T", "Tshape"};
        TFE_TensorHandle[] handles = {tensorHandle, shapeHandle};

        TFE_TensorHandle res = exec_op("Reshape", Arrays.asList(attrNames), Arrays.asList(handles), new HashMap<>());

        TFE_TensorHandle res_cp = TFE_TensorHandleCopyToDevice(res, context, "/device:CPU:0", status);
        //TFE_DeleteTensorHandle(res);
        System.out.println("CP status = " + TF_GetCode(status));

        return TFE_TensorHandleUnderlyingTensorInHostMemory(res_cp, status);

    }




}