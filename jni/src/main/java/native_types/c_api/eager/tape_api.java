package native_types.c_api.eager;


import native_types.adapters.ArraySlice;
import native_types.core.framework.Tensor;
import native_types.loader.LibLoader;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/eager/c_api.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/c_api.h",
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/eager/tape.h"
})

@Namespace("tensorflow")  @NoOffset public class tape_api {

    static {
        LibLoader.load();
    }

    @Namespace("eager")  @Opaque public static class TapeTensor extends Pointer {


        protected TapeTensor(Pointer p) {
            super(p);
        }

        //TODO: Delete
        public TapeTensor(long address) {
            super((Pointer)null);
            super.address = address;
        }

    }

    @Name("eager::OpTapeEntry< jobject* (jobject*) >")
    public class OpTapeEntry extends Pointer {

    }


    @Name("eager:: GradientTape< int*, int* (int*) >")
    public static class GradientTape extends Pointer {
        public GradientTape(boolean persistent) {
            allocate(persistent);
        }
        public native void allocate(boolean persistent);

        public native boolean ShouldRecord(@ArraySlice long[] tensor_ids,
                                           @Cast("gtl::ArraySlice<DataType>") @ArraySlice int[] dtypes);

        public native void Watch(@Cast("int64") long tensor_id);
//
//        void RecordOperation(const string& op_type,
//                             gtl::ArraySlice<TapeTensor> output_tensors,
//                             gtl::ArraySlice<int64> input_tensor_id,
//                             gtl::ArraySlice<tensorflow::DataType> input_dtypes,
//                             BackwardFunction* backward_function,
//                       const std::function<void()>& backward_function_deleter);
//
//        void DeleteTrace(int64 tensor_id);
//
//        // Consumes the internal state of the tape (so cannot be called more than
//        // once) and produces the gradient of the target tensors with respect to the
//        // source tensors. The output gradients are used if not empty and not
//        // null. The result is populated with one tensor per target element.
//        Status ComputeGradient(const VSpace<Gradient, BackwardFunction>& vspace,
//                               gtl::ArraySlice<int64> target_tensor_ids,
//                               gtl::ArraySlice<int64> source_tensor_id,
//                               gtl::ArraySlice<Gradient*> output_gradients,
//                               std::vector<Gradient*>* result);
//
//        bool IsPersistent() const { return persistent_; }
    }

    public static void main(String[] args) throws InterruptedException {
        GradientTape tape = new GradientTape(true);
        long[] data = {1,2,4};
        int[] data1 = {1,2,4};
        tape.Watch(1);
    }



}
