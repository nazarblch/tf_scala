package native_types.data_types;

import java.util.HashMap;

public class CppDataTypes {

    /** enum tensorflow::DataType */
    public static final int
            DT_INVALID = 0,
            DT_FLOAT = 1,
            DT_DOUBLE = 2,
            DT_INT32 = 3,
            DT_UINT8 = 4,
            DT_INT16 = 5,
            DT_INT8 = 6,
            DT_STRING = 7,
            DT_COMPLEX64 = 8,
            DT_INT64 = 9,
            DT_BOOL = 10,
            DT_QINT8 = 11,
            DT_QUINT8 = 12,
            DT_QINT32 = 13,
            DT_BFLOAT16 = 14,
            DT_QINT16 = 15,
            DT_QUINT16 = 16,
            DT_UINT16 = 17,
            DT_COMPLEX128 = 18,
            DT_HALF = 19,
            DT_RESOURCE = 20,
            DT_VARIANT = 21,
            DT_UINT32 = 22,
            DT_UINT64 = 23;

    public static HashMap<Class<?>, Integer> classDataTypes = new HashMap<>();

    static {
        classDataTypes.put(int.class, DT_INT32);
        classDataTypes.put(Integer.class, DT_INT32);
        classDataTypes.put(long.class, DT_INT64);
        classDataTypes.put(Long.class, DT_INT64);
        classDataTypes.put(float.class, DT_FLOAT);
        classDataTypes.put(Float.class, DT_FLOAT);
        classDataTypes.put(double.class, DT_DOUBLE);
        classDataTypes.put(Double.class, DT_DOUBLE);
        classDataTypes.put(byte.class, DT_STRING);
        classDataTypes.put(Byte.class, DT_STRING);
        classDataTypes.put(boolean.class, DT_BOOL);
        classDataTypes.put(Boolean.class, DT_BOOL);
        classDataTypes.put(String.class, DT_STRING);
    }


    public static <T> Integer fromClass(Class<T> type) {
        return classDataTypes.getOrDefault(type, null);
    }
}
