package native_types.c_api;

import native_types.loader.LibLoader;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.platanios.tensorflow.jni.*;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;


@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/c/c_api.h",
        "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})


public class c_api {

    static {
        LibLoader.load();
        //URL[] urls = {};
        //Loader.loadLibrary(urls, "jnifoo");  // Specify name of our wrapper library
        //Loader.load();  // This method can be used if JNI wrapper named as class, i.e. "jniAbc"
    }
    // --------------------------------------------------------------------------


    // TF_DataTypeSize returns the sizeof() for the underlying type corresponding
// to the given TF_DataType enum value. Returns 0 for variable length types
// (eg. TF_STRING) or on failure.
    public static native @Cast("size_t") long TF_DataTypeSize(@Cast("TF_DataType") int dt);

// --------------------------------------------------------------------------
// TF_Code holds an error code.  The enum values here are identical to
// corresponding values in error_codes.proto.
    /** enum TF_Code */
    public static final int
            TF_OK = 0,
            TF_CANCELLED = 1,
            TF_UNKNOWN = 2,
            TF_INVALID_ARGUMENT = 3,
            TF_DEADLINE_EXCEEDED = 4,
            TF_NOT_FOUND = 5,
            TF_ALREADY_EXISTS = 6,
            TF_PERMISSION_DENIED = 7,
            TF_UNAUTHENTICATED = 16,
            TF_RESOURCE_EXHAUSTED = 8,
            TF_FAILED_PRECONDITION = 9,
            TF_ABORTED = 10,
            TF_OUT_OF_RANGE = 11,
            TF_UNIMPLEMENTED = 12,
            TF_INTERNAL = 13,
            TF_UNAVAILABLE = 14,
            TF_DATA_LOSS = 15;

    public static void throwExeptionByCode(TF_Status status) {

        int tf_code = TF_GetCode(status);
        String msg = TF_Message(status).getString();

        switch (tf_code) {
            case TF_CANCELLED: throw new CancelledException("Message = " + msg);
            case TF_UNKNOWN: throw new UnknownException("Message = " + msg);
            case TF_INVALID_ARGUMENT: throw new InvalidArgumentException("Message = " + msg);
            case TF_DEADLINE_EXCEEDED: throw new DeadlineExceededException("Message = " + msg);
            case TF_NOT_FOUND: throw new NotFoundException("Message = " + msg);
            case TF_ALREADY_EXISTS: throw new AlreadyExistsException("Message = " + msg);
            case TF_PERMISSION_DENIED: throw new PermissionDeniedException("Message = " + msg);
            case TF_UNAUTHENTICATED: throw new UnauthenticatedException("Message = " + msg);
            case TF_RESOURCE_EXHAUSTED: throw new ResourceExhaustedException("Message = " + msg);
            case TF_FAILED_PRECONDITION: throw new FailedPreconditionException("Message = " + msg);
            case TF_ABORTED: throw new AbortedException("Message = " + msg);
            case TF_OUT_OF_RANGE: throw new OutOfRangeException("Message = " + msg);
            case TF_UNIMPLEMENTED: throw new UnimplementedException("Message = " + msg);
            case TF_INTERNAL: throw new InternalException("Message = " + msg);
            case TF_UNAVAILABLE: throw new UnavailableException("Message = " + msg);
            case TF_DATA_LOSS: throw new DataLossException("Message = " + msg);
            default:
        }
    }

    // --------------------------------------------------------------------------
// TF_Status holds error information.  It either has an OK code, or
// else an error code with an associated error message.
    @Opaque public static class TF_Status extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_Status() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Status(Pointer p) { super(p); }
    }

    // Return a new status object.
    public static native TF_Status TF_NewStatus();

    // Delete a previously created status object.
    public static native void TF_DeleteStatus(TF_Status arg0);

    // Record <code, msg> in *s.  Any previous information is lost.
// A common use is to clear a status: TF_SetStatus(s, TF_OK, "");
    public static native void TF_SetStatus(TF_Status s, @Cast("TF_Code") int code,
                                           @Cast("const char*") BytePointer msg);
    public static native void TF_SetStatus(TF_Status s, @Cast("TF_Code") int code,
                                           String msg);

    // Return the code record in *s.
    public static native @Cast("TF_Code") int TF_GetCode(@Const TF_Status s);

    // Return a pointer to the (null-terminated) error message in *s.  The
// return value points to memory that is only usable until the next
// mutation to *s.  Always returns an empty string if TF_GetCode(s) is
// TF_OK.
    public static native @Cast("const char*") BytePointer TF_Message(@Const TF_Status s);

    // --------------------------------------------------------------------------
// TF_Buffer holds a pointer to a block of data and its associated length.
// Typically, the data consists of a serialized protocol buffer, but other data
// may also be held in a buffer.
//
// By default, TF_Buffer itself does not do any memory management of the
// pointed-to block.  If need be, users of this struct should specify how to
// deallocate the block by setting the `data_deallocator` function pointer.
    public static class TF_Buffer extends Pointer {
        /** Default native constructor. */
        public TF_Buffer() { super((Pointer)null); allocate(); }
        /** Native array allocator. Access with {@link Pointer#position(long)}. */
        public TF_Buffer(long size) { super((Pointer)null); allocateArray(size); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Buffer(Pointer p) { super(p); }
        private native void allocate();
        private native void allocateArray(long size);
        @Override public TF_Buffer position(long position) {
            return (TF_Buffer)super.position(position);
        }

        public native @Const Pointer data(); public native TF_Buffer data(Pointer data);
        public native @Cast("size_t") long length(); public native TF_Buffer length(long length);
        public static class Data_deallocator_Pointer_long extends FunctionPointer {
            static { Loader.load(); }
            /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
            public    Data_deallocator_Pointer_long(Pointer p) { super(p); }
            protected Data_deallocator_Pointer_long() { allocate(); }
            private native void allocate();
            public native void call(Pointer data, @Cast("size_t") long length);
        }
        public native Data_deallocator_Pointer_long data_deallocator(); public native TF_Buffer data_deallocator(Data_deallocator_Pointer_long data_deallocator);
    }

    // Makes a copy of the input and sets an appropriate deallocator.  Useful for
// passing in read-only, input protobufs.
    public static native TF_Buffer TF_NewBufferFromString(@Const Pointer proto,
                                                          @Cast("size_t") long proto_len);

    // Useful for passing *out* a protobuf.
    public static native TF_Buffer TF_NewBuffer();

    public static native void TF_DeleteBuffer(TF_Buffer arg0);

    public static native @ByVal TF_Buffer TF_GetBuffer(TF_Buffer buffer);

// --------------------------------------------------------------------------
// TF_Tensor holds a multi-dimensional array of elements of a single data type.
// For all types other than TF_STRING, the data buffer stores elements
// in row major order.  E.g. if data is treated as a vector of TF_DataType:
//
//   element 0:   index (0, ..., 0)
//   element 1:   index (0, ..., 1)
//   ...
//
// The format for TF_STRING tensors is:
//   start_offset: array[uint64]
//   data:         byte[...]
//
//   The string length (as a varint), followed by the contents of the string
//   is encoded at data[start_offset[i]]]. TF_StringEncode and TF_StringDecode
//   facilitate this encoding.

    @Opaque public static class TF_Tensor extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_Tensor() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Tensor(Pointer p) { super(p); }
    }

    // Return a new tensor that holds the bytes data[0,len-1].
//
// The data will be deallocated by a subsequent call to TF_DeleteTensor via:
//      (*deallocator)(data, len, deallocator_arg)
// Clients must provide a custom deallocator function so they can pass in
// memory managed by something like numpy.
//
// May return NULL (and invoke the deallocator) if the provided data buffer
// (data, len) is inconsistent with a tensor of the given TF_DataType
// and the shape specified by (dima, num_dims).
    public static class Deallocator_Pointer_long_Pointer extends FunctionPointer {
        static { Loader.load(); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public    Deallocator_Pointer_long_Pointer(Pointer p) { super(p); }
        protected Deallocator_Pointer_long_Pointer() { allocate(); }
        private native void allocate();
        public native void call(Pointer data, @Cast("size_t") long len, Pointer arg);
    }
    public static native TF_Tensor TF_NewTensor(
            @Cast("TF_DataType") int arg0, @Cast("const int64_t*") LongPointer dims, int num_dims, Pointer data, @Cast("size_t") long len,
            Deallocator_Pointer_long_Pointer deallocator,
            Pointer deallocator_arg);
    public static native TF_Tensor TF_NewTensor(
            @Cast("TF_DataType") int arg0, @Cast("const int64_t*") LongBuffer dims, int num_dims, Pointer data, @Cast("size_t") long len,
            Deallocator_Pointer_long_Pointer deallocator,
            Pointer deallocator_arg);
    public static native TF_Tensor TF_NewTensor(
            @Cast("TF_DataType") int arg0, @Cast("const int64_t*") long[] dims, int num_dims, Pointer data, @Cast("size_t") long len,
            Deallocator_Pointer_long_Pointer deallocator,
            Pointer deallocator_arg);

    // Allocate and return a new Tensor.
//
// This function is an alternative to TF_NewTensor and should be used when
// memory is allocated to pass the Tensor to the C API. The allocated memory
// satisfies TensorFlow's memory alignment preferences and should be preferred
// over calling malloc and free.
//
// The caller must set the Tensor values by writing them to the pointer returned
// by TF_TensorData with length TF_TensorByteSize.
    public static native TF_Tensor TF_AllocateTensor(@Cast("TF_DataType") int arg0,
                                                     @Cast("const int64_t*") LongPointer dims,
                                                     int num_dims, @Cast("size_t") long len);
    public static native TF_Tensor TF_AllocateTensor(@Cast("TF_DataType") int arg0,
                                                     @Cast("const int64_t*") LongBuffer dims,
                                                     int num_dims, @Cast("size_t") long len);
    public static native TF_Tensor TF_AllocateTensor(@Cast("TF_DataType") int arg0,
                                                     @Cast("const int64_t*") long[] dims,
                                                     int num_dims, @Cast("size_t") long len);

    // Deletes `tensor` and returns a new TF_Tensor with the same content if
// possible. Returns nullptr and leaves `tensor` untouched if not.
    public static native TF_Tensor TF_TensorMaybeMove(TF_Tensor tensor);

    // Destroy a tensor.
    public static native void TF_DeleteTensor(TF_Tensor arg0);

    public static native @Cast("TF_DataType") int TF_TensorType(@Const TF_Tensor arg0);

    // Return the number of dimensions that the tensor has.
    public static native int TF_NumDims(@Const TF_Tensor arg0);

    // Return the length of the tensor in the "dim_index" dimension.
// REQUIRES: 0 <= dim_index < TF_NumDims(tensor)
    public static native @Cast("int64_t") long TF_Dim(@Const TF_Tensor tensor, int dim_index);

    // Return the size of the underlying data in bytes.
    public static native @Cast("size_t") long TF_TensorByteSize(@Const TF_Tensor arg0);

    // Return a pointer to the underlying data buffer.
    public static native Pointer TF_TensorData(@Const TF_Tensor arg0);

    // --------------------------------------------------------------------------
// Encode the string `src` (`src_len` bytes long) into `dst` in the format
// required by TF_STRING tensors. Does not write to memory more than `dst_len`
// bytes beyond `*dst`. `dst_len` should be at least
// TF_StringEncodedSize(src_len).
//
// On success returns the size in bytes of the encoded string.
// Returns an error into `status` otherwise.
    public static native @Cast("size_t") long TF_StringEncode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("char*") BytePointer dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringEncode(String src, @Cast("size_t") long src_len,
                                                              @Cast("char*") ByteBuffer dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringEncode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("char*") byte[] dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringEncode(String src, @Cast("size_t") long src_len,
                                                              @Cast("char*") BytePointer dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringEncode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("char*") ByteBuffer dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringEncode(String src, @Cast("size_t") long src_len,
                                                              @Cast("char*") byte[] dst, @Cast("size_t") long dst_len,
                                                              TF_Status status);

    // Decode a string encoded using TF_StringEncode.
//
// On success, sets `*dst` to the start of the decoded string and `*dst_len` to
// its length. Returns the number of bytes starting at `src` consumed while
// decoding. `*dst` points to memory within the encoded buffer.  On failure,
// `*dst` and `*dst_len` are undefined and an error is set in `status`.
//
// Does not read memory more than `src_len` bytes beyond `src`.
    public static native @Cast("size_t") long TF_StringDecode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") PointerPointer dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr BytePointer dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(String src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr ByteBuffer dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr byte[] dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(String src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr BytePointer dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(@Cast("const char*") BytePointer src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr ByteBuffer dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);
    public static native @Cast("size_t") long TF_StringDecode(String src, @Cast("size_t") long src_len,
                                                              @Cast("const char**") @ByPtrPtr byte[] dst, @Cast("size_t*") SizeTPointer dst_len,
                                                              TF_Status status);

    // Return the size in bytes required to encode a string `len` bytes long into a
// TF_STRING tensor.
    public static native @Cast("size_t") long TF_StringEncodedSize(@Cast("size_t") long len);

    // --------------------------------------------------------------------------
// TF_SessionOptions holds options that can be passed during session creation.
    @Opaque public static class TF_SessionOptions extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_SessionOptions() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_SessionOptions(Pointer p) { super(p); }
    }

    // Return a new options object.
    public static native TF_SessionOptions TF_NewSessionOptions();

    // Set the target in TF_SessionOptions.options.
// target can be empty, a single entry, or a comma separated list of entries.
// Each entry is in one of the following formats :
// "local"
// ip:port
// host:port
    public static native void TF_SetTarget(TF_SessionOptions options,
                                           @Cast("const char*") BytePointer target);
    public static native void TF_SetTarget(TF_SessionOptions options,
                                           String target);

    // Set the config in TF_SessionOptions.options.
// config should be a serialized tensorflow.ConfigProto proto.
// If config was not parsed successfully as a ConfigProto, record the
// error information in *status.
    public static native void TF_SetConfig(TF_SessionOptions options,
                                           @Const Pointer proto, @Cast("size_t") long proto_len,
                                           TF_Status status);

    // Destroy an options object.
    public static native void TF_DeleteSessionOptions(TF_SessionOptions arg0);

// TODO(jeff,sanjay):
// - export functions to set Config fields

// --------------------------------------------------------------------------
// The new graph construction API, still under development.

    // Represents a computation graph.  Graphs may be shared between sessions.
// Graphs are thread-safe when used as directed below.
    @Opaque public static class TF_Graph extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_Graph() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Graph(Pointer p) { super(p); }
    }

    // Return a new graph object.
    public static native TF_Graph TF_NewGraph();

    // Destroy an options object.  Graph will be deleted once no more
// TFSession's are referencing it.
    public static native void TF_DeleteGraph(TF_Graph arg0);

    // Operation being built. The underlying graph must outlive this.
    @Opaque public static class TF_OperationDescription extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_OperationDescription() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_OperationDescription(Pointer p) { super(p); }
    }

    // Operation that has been added to the graph. Valid until the graph is
// deleted -- in particular adding a new operation to the graph does not
// invalidate old TF_Operation* pointers.
    @Opaque public static class TF_Operation extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_Operation() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Operation(Pointer p) { super(p); }
    }

    // Represents a specific input of an operation.
    public static class TF_Input extends Pointer {
        static { Loader.load(); }
        /** Default native constructor. */
        public TF_Input() { super((Pointer)null); allocate(); }
        /** Native array allocator. Access with {@link Pointer#position(long)}. */
        public TF_Input(long size) { super((Pointer)null); allocateArray(size); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Input(Pointer p) { super(p); }
        private native void allocate();
        private native void allocateArray(long size);
        @Override public TF_Input position(long position) {
            return (TF_Input)super.position(position);
        }

        public native TF_Operation oper(); public native TF_Input oper(TF_Operation oper);
        public native int index(); public native TF_Input index(int index);  // The index of the input within oper.
    }

    // Represents a specific output of an operation.
    public static class TF_Output extends Pointer {
        static { Loader.load(); }
        /** Default native constructor. */
        public TF_Output() { super((Pointer)null); allocate(); }
        /** Native array allocator. Access with {@link Pointer#position(long)}. */
        public TF_Output(long size) { super((Pointer)null); allocateArray(size); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Output(Pointer p) { super(p); }
        private native void allocate();
        private native void allocateArray(long size);
        @Override public TF_Output position(long position) {
            return (TF_Output)super.position(position);
        }

        public native TF_Operation oper(); public native TF_Output oper(TF_Operation oper);
        public native int index(); public native TF_Output index(int index);  // The index of the output within oper.
    }

    // TF_Function is a grouping of operations with defined inputs and outputs.
// Once created and added to graphs, functions can be invoked by creating an
// operation whose operation type matches the function name.
    @Opaque public static class TF_Function extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_Function() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_Function(Pointer p) { super(p); }
    }

    // Function definition options. TODO(iga): Define and implement
    @Opaque public static class TF_FunctionOptions extends Pointer {
        /** Empty constructor. Calls {@code super((Pointer)null)}. */
        public TF_FunctionOptions() { super((Pointer)null); }
        /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
        public TF_FunctionOptions(Pointer p) { super(p); }
    }


}
