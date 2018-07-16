package native_types.indexer;

import native_types.core.framework.Tensor;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.indexer.*;

import static native_types.data_types.CppDataTypes.*;
import static native_types.data_types.CppDataTypes.DT_STRING;

public class TensorIndexer implements Indexable {

    Tensor tensor;

    public TensorIndexer(Tensor t) {
        tensor = t;
    }

    /** Returns {@code createIndexer(true)}. */
    public <I extends Indexer> I createIndexer() {
        return (I)createIndexer(true);
    }

    @Override public <I extends Indexer> I createIndexer(boolean direct) {
        BytePointer ptr = tensor.tensor_data();
        int size = (int)tensor.TotalBytes();
        boolean complex = tensor.dtype() == DT_COMPLEX64;
        int dims = complex ? tensor.dims() + 1 : tensor.dims();
        long[] sizes = new long[dims];
        long[] strides = new long[dims];
        sizes[dims - 1] = complex ? 2 : (int) tensor.dim_size(dims - 1);
        strides[dims - 1] = 1;
        for (int i = dims - 2; i >= 0; i--) {
            sizes[i] = (int) tensor.dim_size(i);
            strides[i] = sizes[i + 1] * strides[i + 1];
        }
        switch (tensor.dtype()) {
            case DT_COMPLEX64:
            case DT_FLOAT:    return (I) FloatIndexer.create(new FloatPointer(ptr).capacity(size/4), sizes, strides, direct).indexable(this);
            case DT_DOUBLE:   return (I) DoubleIndexer.create(new DoublePointer(ptr).capacity(size/8), sizes, strides, direct).indexable(this);
            case DT_QINT32:
            case DT_INT32:    return (I) IntIndexer.create(new IntPointer(ptr).capacity(size/4), sizes, strides, direct).indexable(this);
            case DT_BOOL:
            case DT_QUINT8:
            case DT_UINT8:    return (I)UByteIndexer.create(ptr.capacity(size), sizes, strides, direct).indexable(this);
            case DT_QINT8:
            case DT_INT8:     return (I)ByteIndexer.create(ptr.capacity(size), sizes, strides, direct).indexable(this);
            case DT_BFLOAT16: return (I)UShortIndexer.create(new ShortPointer(ptr).capacity(size/2), sizes, strides, direct).indexable(this);
            case DT_INT16:    return (I)ShortIndexer.create(new ShortPointer(ptr).capacity(size/2), sizes, strides, direct).indexable(this);
            case DT_INT64:    return (I)LongIndexer.create(new LongPointer(ptr).capacity(size/8), sizes, strides, direct).indexable(this);
            case DT_STRING:
            default: assert false;
        }
        return null;
    }
}
