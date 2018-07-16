package native_types.core.framework;

import native_types.core.common.MessageLite;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import java.nio.ByteBuffer;

@Platform(include = {
        "/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework/device_base.h"
        // "/home/nazar/Downloads/javacpp-presets-master/tensorflow/src/main/resources/org/bytedeco/javacpp/include/tensorflow_adapters.h"
})

@Namespace("tensorflow") @NoOffset public class DeviceAttributes extends MessageLite {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public DeviceAttributes(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public DeviceAttributes(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public DeviceAttributes position(long position) {
        return (DeviceAttributes)super.position(position);
    }

    public DeviceAttributes() { super((Pointer)null); allocate(); }
    private native void allocate();

    public DeviceAttributes(@Const @ByRef DeviceAttributes from) { super((Pointer)null); allocate(from); }
    private native void allocate(@Const @ByRef DeviceAttributes from);

    public native @ByRef @Name("operator =") DeviceAttributes put(@Const @ByRef DeviceAttributes from);
    //   #if LANG_CXX11
//   #endif
    // public native Arena GetArena();
    public native Pointer GetMaybeArenaPointer();
    public static native @Cast("const google::protobuf::Descriptor*") Pointer descriptor();
    public static native @Const @ByRef DeviceAttributes default_instance();

    public static native void InitAsDefaultInstance();  // FOR INTERNAL USE ONLY
    public static native @Const DeviceAttributes internal_default_instance();
    @MemberGetter public static native int kIndexInFileMessages();
    public static final int kIndexInFileMessages = kIndexInFileMessages();

    public native void UnsafeArenaSwap(DeviceAttributes other);
    public native void Swap(DeviceAttributes other);


    // implements Message ----------------------------------------------

    public native DeviceAttributes New();

    // public native DeviceAttributes New(Arena arena);
    public native void CopyFrom(@Cast("const google::protobuf::Message*") @ByRef MessageLite from);
    public native void MergeFrom(@Cast("const google::protobuf::Message*") @ByRef MessageLite from);
    public native void CopyFrom(@Const @ByRef DeviceAttributes from);
    public native void MergeFrom(@Const @ByRef DeviceAttributes from);
    public native void Clear();
    public native @Cast("bool") boolean IsInitialized();

    public native @Cast("size_t") long ByteSizeLong();
    //public native @Cast("bool") boolean MergePartialFromCodedStream(
    //        CodedInputStream input);
    //public native void SerializeWithCachedSizes(
    //        CodedOutputStream output);
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

    // string name = 1;
    public native void clear_name();
    @MemberGetter public static native int kNameFieldNumber();
    public static final int kNameFieldNumber = kNameFieldNumber();
    public native @StdString BytePointer name();
    public native void set_name(@StdString BytePointer value);
    public native void set_name(@StdString String value);
    //   #if LANG_CXX11
//   #endif
    public native void set_name(@Cast("const char*") BytePointer value, @Cast("size_t") long size);
    public native void set_name(String value, @Cast("size_t") long size);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer mutable_name();
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer release_name();
    public native void set_allocated_name(@StdString @Cast({"char*", "std::string*"}) BytePointer name);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer unsafe_arena_release_name();
    public native void unsafe_arena_set_allocated_name(
            @StdString @Cast({"char*", "std::string*"}) BytePointer name);

    // string device_type = 2;
    public native void clear_device_type();
    @MemberGetter public static native int kDeviceTypeFieldNumber();
    public static final int kDeviceTypeFieldNumber = kDeviceTypeFieldNumber();
    public native @StdString BytePointer device_type();
    public native void set_device_type(@StdString BytePointer value);
    public native void set_device_type(@StdString String value);
    //   #if LANG_CXX11
//   #endif
    public native void set_device_type(@Cast("const char*") BytePointer value, @Cast("size_t") long size);
    public native void set_device_type(String value, @Cast("size_t") long size);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer mutable_device_type();
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer release_device_type();
    public native void set_allocated_device_type(@StdString @Cast({"char*", "std::string*"}) BytePointer device_type);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer unsafe_arena_release_device_type();
    public native void unsafe_arena_set_allocated_device_type(
            @StdString @Cast({"char*", "std::string*"}) BytePointer device_type);

    // string physical_device_desc = 7;
    public native void clear_physical_device_desc();
    @MemberGetter public static native int kPhysicalDeviceDescFieldNumber();
    public static final int kPhysicalDeviceDescFieldNumber = kPhysicalDeviceDescFieldNumber();
    public native @StdString BytePointer physical_device_desc();
    public native void set_physical_device_desc(@StdString BytePointer value);
    public native void set_physical_device_desc(@StdString String value);
    //   #if LANG_CXX11
//   #endif
    public native void set_physical_device_desc(@Cast("const char*") BytePointer value, @Cast("size_t") long size);
    public native void set_physical_device_desc(String value, @Cast("size_t") long size);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer mutable_physical_device_desc();
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer release_physical_device_desc();
    public native void set_allocated_physical_device_desc(@StdString @Cast({"char*", "std::string*"}) BytePointer physical_device_desc);
    public native @StdString @Cast({"char*", "std::string*"}) BytePointer unsafe_arena_release_physical_device_desc();
    public native void unsafe_arena_set_allocated_physical_device_desc(
            @StdString @Cast({"char*", "std::string*"}) BytePointer physical_device_desc);

    // .tensorflow.DeviceLocality locality = 5;
    public native @Cast("bool") boolean has_locality();
    public native void clear_locality();
    @MemberGetter public static native int kLocalityFieldNumber();
    public static final int kLocalityFieldNumber = kLocalityFieldNumber();
    // public native @Const @ByRef DeviceLocality locality();
    // public native DeviceLocality release_locality();
    // public native DeviceLocality mutable_locality();
    // public native void set_allocated_locality(DeviceLocality locality);
    // public native void unsafe_arena_set_allocated_locality(
    //        DeviceLocality locality);
    // public native DeviceLocality unsafe_arena_release_locality();

    // int64 memory_limit = 4;
    public native void clear_memory_limit();
    @MemberGetter public static native int kMemoryLimitFieldNumber();
    public static final int kMemoryLimitFieldNumber = kMemoryLimitFieldNumber();
    public native @Cast("google::protobuf::int64") long memory_limit();
    public native void set_memory_limit(@Cast("google::protobuf::int64") long value);

    // fixed64 incarnation = 6;
    public native void clear_incarnation();
    @MemberGetter public static native int kIncarnationFieldNumber();
    public static final int kIncarnationFieldNumber = kIncarnationFieldNumber();
    public native @Cast("google::protobuf::uint64") long incarnation();
    public native void set_incarnation(@Cast("google::protobuf::uint64") long value);
}
