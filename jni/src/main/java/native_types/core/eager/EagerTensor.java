package native_types.core.eager;

import native_types.core.framework.Tensor;

import native_types.c_api.c_api;
import native_types.c_api.eager.c_api.*;

import native_types.core.framework.TensorFactory;
import native_types.core.framework.TensorShape;
import native_types.data_types.CppDataTypes;
import org.apache.commons.lang.ArrayUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.platanios.tensorflow.jni.WithTmpTensor;

import java.util.*;

import static native_types.c_api.c_api.*;
import static native_types.c_api.eager.c_api.*;
import static native_types.data_types.CppDataTypes.DT_INT32;


public class EagerTensor extends Pointer {

    private TFE_TensorHandle handle;
    //TODO: deallocate with handle
    private c_api.TF_Status status = TF_NewStatus();

    protected int dtype;

    public TFE_TensorHandle getHandle() {
        return handle;
    }

//    protected static class DeleteDeallocator extends TFE_TensorHandle implements Pointer.Deallocator {
//        DeleteDeallocator(TFE_TensorHandle s) { super(s); }
//        @Override public void deallocate() {
//            System.out.println("delete EagerTensor");
//            TFE_DeleteTensorHandle(this);
//        }
//    }

    public EagerTensor(Tensor t) {
        super((Pointer)null);
        TFE_TensorHandle h = new TFE_TensorHandle(t);
        if (h != null) {
            handle = h;
            dtype = t.dtype();
           // deallocator(new DeleteDeallocator(handle));
        }
    }

    public EagerTensor(c_api.TF_Tensor t) {
        super((Pointer)null);
        TFE_TensorHandle h = new TFE_TensorHandle(t);
        if (h != null) {
            handle = h;
            dtype = TF_TensorType(t);
            // deallocator(new DeleteDeallocator(handle));
        }
    }

    public EagerTensor(TFE_TensorHandle h) {
        super((Pointer)null);
        if (h != null) {
            handle = h;
            dtype = TFE_TensorHandleDataType(h);
            //deallocator(new DeleteDeallocator(handle));
        }
    }

    protected EagerTensor copyToDevice(String name) {
        TFE_TensorHandle cp = TFE_TensorHandleCopyToDevice(handle, name, status);
        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        } else {
            delete();
        }
        return new EagerTensor(cp);
    }

    private EagerTensor copyToCPU() {
        return copyToDevice("/device:CPU:0");
    }

    private EagerTensor copyToGPU(int index) {
        return copyToDevice("/device:GPU:" + index);
    }

    private EagerTensor exec_op(String op_name, String[] attrNames, EagerTensor[] args, HashMap<String, Long> map) {

        List<String> names = new ArrayList<>();
        names.add("T");
        names.addAll(Arrays.asList(attrNames));

        List<TFE_TensorHandle> handles = new ArrayList<>();
        handles.add(handle);
        for(EagerTensor t: args) {
            handles.add(t.getHandle());
        }

        TFE_TensorHandle res = native_types.c_api.eager.c_api.exec_op(op_name, names, handles, map);

        return new EagerTensor(res);
    }

    public EagerTensor stridedSlice(int[] begin, int[] end, int[] strides, long begin_mask, long end_mask, long ellipsis_mask, long new_axis_mask, long shrink_axis_mask) {
        TensorFactory f1 = new TensorFactory(CppDataTypes.DT_INT32, new long[] {begin.length});
        TensorFactory f2 = new TensorFactory(CppDataTypes.DT_INT32, new long[] {end.length});
        TensorFactory f3 = new TensorFactory(CppDataTypes.DT_INT32, new long[] {strides.length});

        Tensor t1 = f1.createFromArray(begin);
        Tensor t2 = f2.createFromArray(end);
        Tensor t3 = f3.createFromArray(strides);

        String[] attrNames = {"Index"};
        EagerTensor[] handles = {new EagerTensor(t1), new EagerTensor(t2), new EagerTensor(t3)};
        HashMap<String, Long> map = new HashMap<>();
        map.put("begin_mask", begin_mask);
        map.put("end_mask", end_mask);
        map.put("ellipsis_mask", ellipsis_mask);
        map.put("new_axis_mask", new_axis_mask);
        map.put("shrink_axis_mask", shrink_axis_mask);

        EagerTensor res = exec_op("StridedSlice", attrNames, handles, map);
        t1.deallocate();
        t2.deallocate();
        t3.deallocate();
        return res;
    }


    public long[] dims() {
        return Dims(handle);
    }


    public EagerTensor reshape(TensorShape shape) {
        Tensor t = shape.toTensor();
        TFE_TensorHandle shapeHandle = new TFE_TensorHandle(t);
        String[] attrNames = {"Tshape"};
        EagerTensor[] handles = {new EagerTensor(shapeHandle)};
        EagerTensor res = exec_op("Reshape", attrNames, handles, new HashMap<>());
        handles[0].delete();
        t.deallocate();
        return res;
    }

    public Tensor toNativeTensor() {
        EagerTensor e = copyToCPU();
        Tensor t = TFE_TensorHandleUnderlyingTensorInHostMemory(e.getHandle(), status);
        if(TF_GetCode(status) != 0) {
            throwExeptionByCode(status);
        } else {
            // e.delete();
        }
        return t;
    }

    public void delete() {
        deallocate();
    }
}
