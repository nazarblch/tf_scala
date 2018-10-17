package native_types.core.framework;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import native_types.core.framework.Tensor;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/tensor.h",
        // "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h",
        "<vector>"

})

@Name("std::vector<tensorflow::Tensor>") public class TensorVector extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TensorVector(Pointer p) { super(p); }
    public TensorVector(Tensor value) { this(1); put(0, value); }
    public TensorVector(Tensor ... array) { this(array.length); put(array); }
    public TensorVector()       { allocate();  }
    public TensorVector(long n) { allocate(n); }
    private native void allocate();
    private native void allocate(@Cast("size_t") long n);
    public native @Name("operator=") @ByRef TensorVector put(@ByRef TensorVector x);

    public boolean empty() { return size() == 0; }
    public native long size();
    public void clear() { resize(0); }
    public native void resize(@Cast("size_t") long n);

    @Index(function = "at") public native @ByRef Tensor get(@Cast("size_t") long i);
    public native TensorVector put(@Cast("size_t") long i, Tensor value);

    public native @ByVal Iterator begin();
    public native @ByVal Iterator end();
    @NoOffset @Name("iterator") public static class Iterator extends Pointer {
        public Iterator(Pointer p) { super(p); }
        public Iterator() { }

        public native @Name("operator++") @ByRef Iterator increment();
        public native @Name("operator==") boolean equals(@ByRef Iterator it);
        public native @Name("operator*") @ByRef @Const Tensor get();
    }

    public Tensor[] get() {
        Tensor[] array = new Tensor[size() < Integer.MAX_VALUE ? (int)size() : Integer.MAX_VALUE];
        for (int i = 0; i < array.length; i++) {
            array[i] = get(i);
        }
        return array;
    }
    @Override public String toString() {
        return java.util.Arrays.toString(get());
    }

    public Tensor pop_back() {
        long size = size();
        Tensor value = get(size - 1);
        resize(size - 1);
        return value;
    }
    public TensorVector push_back(Tensor value) {
        long size = size();
        resize(size + 1);
        return put(size, value);
    }
    public TensorVector put(Tensor value) {
        if (size() != 1) { resize(1); }
        return put(0, value);
    }
    public TensorVector put(Tensor ... array) {
        if (size() != array.length) { resize(array.length); }
        for (int i = 0; i < array.length; i++) {
            put(i, array[i]);
        }
        return this;
    }
}