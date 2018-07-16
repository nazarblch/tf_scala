package native_types.core.lib;

import native_types.adapters.StringPiece;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/lib/core/status.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class Status extends Pointer {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Status(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Status(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Status position(long position) {
        return (Status)super.position(position);
    }

    /** Create a success status. */
    public Status() { super((Pointer)null); allocate(); }
    private native void allocate();

    /** \brief Create a status with the specified error code and msg as a
     *  human-readable string containing more detailed information. */
    public Status(@Cast("tensorflow::error::Code") int code, @StringPiece BytePointer msg) { super((Pointer)null); allocate(code, msg); }
    private native void allocate(@Cast("tensorflow::error::Code") int code, @StringPiece BytePointer msg);
    public Status(@Cast("tensorflow::error::Code") int code, @StringPiece String msg) { super((Pointer)null); allocate(code, msg); }
    private native void allocate(@Cast("tensorflow::error::Code") int code, @StringPiece String msg);

    /** Copy the specified status. */
    public Status(@Const @ByRef Status s) { super((Pointer)null); allocate(s); }
    private native void allocate(@Const @ByRef Status s);
    public native @Name("operator =") void put(@Const @ByRef Status s);

    public static native @ByVal Status OK();

    /** Returns true iff the status indicates success. */
    public native @Cast("bool") boolean ok();

    public native @Cast("tensorflow::error::Code") int code();

    public native @StdString BytePointer error_message();

    public native @Cast("bool") @Name("operator ==") boolean equals(@Const @ByRef Status x);

    ///
    public native @Cast("bool") @Name("operator !=") boolean notEquals(@Const @ByRef Status x);

    /** \brief If {@code ok()}, stores {@code new_status} into {@code *this}.  If {@code !ok()},
     *  preserves the current status, but may augment with additional
     *  information about {@code new_status}.
     *
     *  Convenient way of keeping track of the first error encountered.
     *  Instead of:
     *    {@code if (overall_status.ok()) overall_status = new_status}
     *  Use:
     *    {@code overall_status.Update(new_status);} */
    public native void Update(@Const @ByRef Status new_status);

    /** \brief Return a string representation of this status suitable for
     *  printing. Returns the string {@code "OK"} for success. */
    public native @StdString BytePointer ToString();

    // Ignores any errors. This method does nothing except potentially suppress
    // complaints from any tools that are checking that errors are not dropped on
    // the floor.
    public native void IgnoreError();
}

