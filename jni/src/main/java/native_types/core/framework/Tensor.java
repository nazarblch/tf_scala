package native_types.core.framework;


import native_types.adapters.StringPiece;
import native_types.c_api.eager.c_api;
import native_types.cc.client.ClientSession;
import native_types.cc.framework.InputList;
import native_types.cc.framework.Output;
import native_types.cc.framework.OutputVector;
import native_types.cc.framework.Scope;
import native_types.core.eager.EagerTensor;
import native_types.indexer.TensorIndexer;
import native_types.loader.LibLoader;
import native_types.ops.const_ops;
import native_types.ops.math_ops;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Indexer;

import static native_types.c_api.eager.c_api.deleteTensor;
import static native_types.c_api.eager.c_api.reshape;
import static native_types.data_types.CppDataTypes.*;


@Platform(
        include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor.h",
                "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/op.h",
                "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor_types.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})




// "link" tells javacpp which original library should be linked (if not specified, "Abc" will be used)
@Namespace("tensorflow") @NoOffset public  class Tensor extends Pointer {

    static {
        LibLoader.load();
    }


    public Tensor(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Tensor(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Tensor position(long position) {
        return (Tensor)super.position(position);
    }

    public Tensor() { super((Pointer)null); allocate(); }
    private native void allocate();

    public Tensor(@Cast("tensorflow::DataType") int type, @Const @ByRef TensorShape shape) {
        super((Pointer)null);
        allocate(type, shape);
        // Pointer.withDeallocator(this);
    }

    public Tensor(@Cast("tensorflow::DataType") int type, long[] shape) {
        super((Pointer)null);
        allocate(type, new TensorShape(shape));
        // Pointer.withDeallocator(this);
    }

    private native void allocate(@Cast("tensorflow::DataType") int type, @Const @ByRef TensorShape shape);

    /** Returns the data type. */
    public native @Cast("tensorflow::DataType") int dtype();

    public native @Const @ByRef TensorShape shape();

    public native int dims();

    /** Convenience accessor for the tensor shape. */
    public native @Cast("tensorflow::int64") long dim_size(int d);

    public native @StringPiece BytePointer tensor_data();

    public native @Cast("size_t") long TotalBytes();



    public <I extends Indexer> I createIndexer() {
        return (new TensorIndexer(this).createIndexer());
    }


    @Override public void deallocate() {
        if(!isNull()) {
            deleteTensor(this);
            setNull();
        }
    }



    public static void main(String[] args) {


        long[] shape = {2, 3};
        float[] data = {1, 2, 3, 4, 5, 6};
        TensorFactory tf = new TensorFactory(DT_FLOAT, shape);

        try(Tensor abc = tf.createFromArray(data)) {

            EagerTensor e = new EagerTensor(abc);
            EagerTensor e1 = e.reshape(new TensorShape(3,2));


            System.out.println(e1.toNativeTensor().createIndexer().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

//        Scope scope = Scope.NewRootScope();
//
//        Output ones = const_ops.Const(scope.WithOpName("ones"), tf.createFromArray(data));
//        Output sixes = const_ops.Const(scope.WithOpName("sixes"), tf.createFromArray(data));
//        Output tens = const_ops.Const(scope.WithOpName("tens"), tf.createFromArray(data));
//
//        OutputVector ov = new OutputVector(ones, sixes, tens);
//        InputList inputList = new InputList(ov);
//        math_ops.AddN add = new math_ops.AddN(scope.WithOpName("add"), inputList);
//
//        TensorVector outputs = new TensorVector();
//        ClientSession session = new ClientSession(scope);
//
//        session.Run(new OutputVector(add.asOutput()), outputs);
//
//        System.out.println(outputs.get(0).createIndexer());


        //Scope.Impl impl = new Scope.Impl(graph, new Status(), new NameMap(), refiner, true);
        // Scope scope = scope_factory.NewInternalScope(graph, new Status(), refiner);
//        Output ones = linalg_ops.Const(scope.WithOpName("ones"), 1, shape);
//        Output sixes = linalg_ops.Const(scope.WithOpName("sixes"), 6, shape);
//        Output tens = linalg_ops.Const(scope.WithOpName("tens"), 10, shape);

    }
}

