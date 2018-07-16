package native_types.utils;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include={
        "<vector>",
        "<string>",
        "<iostream>",
        "<unordered_map>"
})

@Name("std::unordered_map<string, int>") public class NameMap extends Pointer {

    public NameMap(Pointer p) { super(p); }
    public NameMap()       { allocate();  }
    private native void allocate();
    private native void allocate(@Cast("size_t") long n);
    public native @Name("operator=") @ByRef NameMap put(@ByRef NameMap x);
}
