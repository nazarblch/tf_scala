package native_types.utils;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include={
        "<string>"
})

@Name("std::string") public class StringArray extends Pointer {
    public StringArray(Pointer p) { super(p); }
    public StringArray() { allocate(); }
    private native void allocate();
    public StringArray(StringArray p) { allocate(p); }
    private native void allocate(@ByRef StringArray p);
    public StringArray(BytePointer s, long count) { allocate(s, count); }
    private native void allocate(@Cast("char*") BytePointer s, long count);
    public StringArray(String s) { allocate(s); }
    private native void allocate(String s);
    public native @Name("operator=") @ByRef StringArray put(@ByRef StringArray str);
    public native @Name("operator=") @ByRef StringArray put(String str);
    @Override public StringArray position(long position) {
        return (StringArray)super.position(position);
    }

    public native @Cast("size_t") long size();
    public native void resize(@Cast("size_t") long n);

    @Index public native @Cast("char") int get(@Cast("size_t") long pos);
    public native StringArray put(@Cast("size_t") long pos, int c);
    public native @Cast("const char*") BytePointer data();

    @Override public String toString() {
        long length = size();
        byte[] bytes = new byte[length < Integer.MAX_VALUE ? (int)length : Integer.MAX_VALUE];
        data().get(bytes);
        return new String(bytes);
    }
}