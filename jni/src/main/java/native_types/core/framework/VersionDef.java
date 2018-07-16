package native_types.core.framework;

import native_types.core.common.MessageLite;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import java.nio.ByteBuffer;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/versions.h",
        //"/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class VersionDef extends MessageLite {
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public VersionDef(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public VersionDef(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public VersionDef position(long position) {
        return (VersionDef)super.position(position);
    }

    public VersionDef() { super((Pointer)null); allocate(); }
    private native void allocate();

    public VersionDef(@Const @ByRef VersionDef from) { super((Pointer)null); allocate(from); }
    private native void allocate(@Const @ByRef VersionDef from);

    public native @ByRef @Name("operator =") VersionDef put(@Const @ByRef VersionDef from);
    //   #if LANG_CXX11
//   #endif
 //   public native Arena GetArena();
    public native Pointer GetMaybeArenaPointer();
    public static native @Cast("const google::protobuf::Descriptor*") Pointer descriptor();
    public static native @Const @ByRef VersionDef default_instance();

    public static native void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
    public static native @Const VersionDef internal_default_instance();
    @MemberGetter public static native int kIndexInFileMessages();
    public static final int kIndexInFileMessages = kIndexInFileMessages();

    public native void UnsafeArenaSwap(VersionDef other);
    public native void Swap(VersionDef other);


    // implements Message ----------------------------------------------

    public native VersionDef New();

 //   public native VersionDef New(Arena arena);
    public native void CopyFrom(@Cast("const google::protobuf::Message*") @ByRef MessageLite from);
    public native void MergeFrom(@Cast("const google::protobuf::Message*") @ByRef MessageLite from);
    public native void CopyFrom(@Const @ByRef VersionDef from);
    public native void MergeFrom(@Const @ByRef VersionDef from);
    public native void Clear();
    public native @Cast("bool") boolean IsInitialized();

    public native @Cast("size_t") long ByteSizeLong();
//    public native @Cast("bool") boolean MergePartialFromCodedStream(
//            CodedInputStream input);
//    public native void SerializeWithCachedSizes(
//            CodedOutputStream output);
    public native @Cast("google::protobuf::uint8*") BytePointer InternalSerializeWithCachedSizesToArray(
            @Cast("bool") boolean deterministic, @Cast("google::protobuf::uint8*") BytePointer target);
    public native @Cast("google::protobuf::uint8*") ByteBuffer InternalSerializeWithCachedSizesToArray(
            @Cast("bool") boolean deterministic, @Cast("google::protobuf::uint8*") ByteBuffer target);
    public native @Cast("google::protobuf::uint8*") byte[] InternalSerializeWithCachedSizesToArray(
            @Cast("bool") boolean deterministic, @Cast("google::protobuf::uint8*") byte[] target);
    public native int GetCachedSize();

    public native @ByVal @Cast("google::protobuf::Metadata*") Pointer GetMetadata();

    // nested types ----------------------------------------------------

    // accessors -------------------------------------------------------

    // repeated int32 bad_consumers = 3;
    public native int bad_consumers_size();
    public native void clear_bad_consumers();
    @MemberGetter public static native int kBadConsumersFieldNumber();
    public static final int kBadConsumersFieldNumber = kBadConsumersFieldNumber();
    public native @Cast("google::protobuf::int32") int bad_consumers(int index);
    public native void set_bad_consumers(int index, @Cast("google::protobuf::int32") int value);
    public native void add_bad_consumers(@Cast("google::protobuf::int32") int value);

    // int32 producer = 1;
    public native void clear_producer();
    @MemberGetter public static native int kProducerFieldNumber();
    public static final int kProducerFieldNumber = kProducerFieldNumber();
    public native @Cast("google::protobuf::int32") int producer();
    public native void set_producer(@Cast("google::protobuf::int32") int value);

    // int32 min_consumer = 2;
    public native void clear_min_consumer();
    @MemberGetter public static native int kMinConsumerFieldNumber();
    public static final int kMinConsumerFieldNumber = kMinConsumerFieldNumber();
    public native @Cast("google::protobuf::int32") int min_consumer();
    public native void set_min_consumer(@Cast("google::protobuf::int32") int value);
}
// ===================================================================