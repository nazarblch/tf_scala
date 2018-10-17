package native_types.core.framework;

import native_types.c_api.c_api;
import native_types.utils.StringArray;
import org.apache.commons.lang.NotImplementedException;
import org.bytedeco.javacpp.*;
import org.apache.commons.lang.ArrayUtils;


import java.lang.reflect.Array;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static native_types.c_api.c_api.TF_TensorData;
import static native_types.data_types.CppDataTypes.*;

public class TensorFactory<T> {

    private int dtype;
    private long[] shape;
    private long shape_product;


    public TensorFactory(int dtype, long[] shape) {
        this.dtype = dtype;
        this.shape = shape;
        shape_product = 1;
        for (long d: shape) {
            shape_product *= d;
        }
    }


    /** Returns {@code createBuffer(0)}. */
    private  <B extends Buffer> B createBuffer(Tensor t) {
        return (B)createBuffer(t.tensor_data(), 0, t.TotalBytes());
    }

    private  <B extends Buffer> B createBuffer(BytePointer ptr, int index, long size) {
        switch (dtype) {
            case DT_COMPLEX64:
            case DT_FLOAT:    return (B)new FloatPointer(ptr).position(index).capacity(size/4).asBuffer();
            case DT_DOUBLE:   return (B)new DoublePointer(ptr).position(index).capacity(size/8).asBuffer();
            case DT_QINT32:
            case DT_INT32:    return (B)new IntPointer(ptr).position(index).capacity(size/4).asBuffer();
            case DT_BOOL:
            case DT_QUINT8:
            case DT_UINT8:
            case DT_QINT8:
            case DT_INT8:     return (B) ptr.position(index).capacity(size).asBuffer();
            case DT_BFLOAT16:
            case DT_INT16:    return (B)new ShortPointer(ptr).position(index).capacity(size/2).asBuffer();
            case DT_INT64:    return (B)new LongPointer(ptr).position(index).capacity(size/8).asBuffer();
            case DT_STRING:
            default: assert false;
        }
        return null;
    }

    private  <B extends Buffer> void putArray(B b, Object data) {

        switch (dtype) {
            case DT_COMPLEX64:
            case DT_FLOAT:    ((FloatBuffer) b).put((float[]) data); return;
            case DT_DOUBLE:   ((DoubleBuffer) b).put((double[]) data); return;
            case DT_QINT32:
            case DT_INT32:    ((IntBuffer) b).put((int[]) data); return;
            case DT_BOOL:
                for (boolean o : (boolean []) data) {
                    byte vOut = (byte)(o ? 1:0);
                    ((ByteBuffer) b).put(vOut);
                }
                return;
            case DT_QUINT8:
            case DT_UINT8:
            case DT_QINT8:
            case DT_INT8:     ((ByteBuffer) b).put((byte[]) data); return;
            case DT_BFLOAT16:
            case DT_INT16:    ((ShortBuffer) b).put((short[]) data); return;
            case DT_INT64:   ((LongBuffer) b).put((long[]) data); return;
            case DT_STRING:
            default: assert false;
        }
    }


    private void putObjectArray(ByteBuffer b, List<T> data) {

        switch (dtype) {
            case DT_COMPLEX64: throw new NotImplementedException("DT_COMPLEX64");
            case DT_FLOAT:
                putArray(b.asFloatBuffer(),
                        ArrayUtils.toPrimitive(data.toArray(new Float[0])));
                return;
            case DT_DOUBLE:
                putArray(b.asDoubleBuffer(),
                        ArrayUtils.toPrimitive(data.toArray(new Double[0])));
                return;
            case DT_QINT32:
            case DT_INT32:
                putArray(b.asIntBuffer(),
                        ArrayUtils.toPrimitive(data.toArray(new Integer[0])));
                return;
            case DT_BOOL:
                for(Object o: data) {
                    Byte vOut = (byte)((Boolean) o ? 1:0);
                    b.put(vOut);
                }
                return;
            case DT_QUINT8:
            case DT_UINT8:
            case DT_QINT8:
            case DT_INT8:
                putArray(b,
                        ArrayUtils.toPrimitive(data.toArray(new Byte[0])));
                return;
            case DT_BFLOAT16:
            case DT_INT16:
                putArray(b.asShortBuffer(),
                        ArrayUtils.toPrimitive(data.toArray(new Short[0])));
                return;
            case DT_INT64:
                putArray(b.asLongBuffer(),
                        ArrayUtils.toPrimitive(data.toArray(new Long[0])));
                return;
            case DT_STRING:
                StringArray a = new StringArray(new BytePointer(b)).capacity(data.size()).limit(data.size());
                for (int i = 0; i < a.capacity(); i++) {
                    a.position(i).put((String) data.get(i));
                }
                return;
            default: assert false;
        }
    }



    /**
     * Creates a Tensor from a Java object.
     *
     * <p>A {@code Tensor} is a multi-dimensional array of elements of a limited set of types. Not all
     * Java objects can be converted to a {@code Tensor}. In particular, the argument {@code obj} must
     * be either a primitive (float, double, int, long, boolean, byte) or a multi-dimensional array of
     * one of those primitives. The argument {@code type} specifies how to interpret the first
     * argument as a TensorFlow type. For example:
     *
     * <pre>{@code
     * // Valid: A 64-bit integer scalar.
     * Tensor<Long> s = Tensor.create(42L, Long.class);
     *
     * // Valid: A 3x2 matrix of floats.
     * float[][] matrix = new float[3][2];
     * Tensor<Float> m = Tensor.create(matrix, Float.class);
     *
     * // Invalid: Will throw an IllegalArgumentException as an arbitrary Object
     * // does not fit into the TensorFlow type system.
     * Tensor<?> o = Tensor.create(new Object())
     *
     * // Invalid: Will throw an IllegalArgumentException since there are
     * // a differing number of elements in each row of this 2-D array.
     * int[][] twoD = new int[2][];
     * twoD[0] = new int[1];
     * twoD[1] = new int[2];
     * Tensor<Integer> x = Tensor.create(twoD, Integer.class);
     * }</pre>
     *
     * {@link String}-typed Tensors are multi-dimensional arrays of arbitrary byte sequences, so can
     * be initialized from arrays of {@code byte[]} elements. For example:
     *
     * <pre>{@code
     * // Valid: A String tensor.
     * Tensor<String> s = Tensor.create(new byte[]{1, 2, 3}, String.class);
     *
     * // Java Strings will need to be encoded into a byte-sequence.
     * String mystring = "foo";
     * Tensor<String> s = Tensor.create(mystring.getBytes("UTF-8"), String.class);
     *
     * // Valid: Matrix of String tensors.
     * // Each element might have a different length.
     * byte[][][] matrix = new byte[2][2][];
     * matrix[0][0] = "this".getBytes("UTF-8");
     * matrix[0][1] = "is".getBytes("UTF-8");
     * matrix[1][0] = "a".getBytes("UTF-8");
     * matrix[1][1] = "matrix".getBytes("UTF-8");
     * Tensor<String> m = Tensor.create(matrix, String.class);
     * }</pre>
     */
    public Tensor create(Object obj) {
        checkObjectShapeCompatibility(obj);
        Tensor t =  new Tensor(dtype, new TensorShape(shape));
        putObjectArray(t.getBuffer(), flatten(obj));
        return t;
    }


    public c_api.TF_Tensor createTF_Tensor(Object data) {
        checkObjectShapeCompatibility(data);
        assert  (dtype != DT_STRING);
        c_api.TF_Tensor t = new c_api.TF_Tensor(dtype, shape, shape.length, shape_product * elemByteSize(dtype));
        ByteBuffer b = new BytePointer(TF_TensorData(t)).position(0).capacity(shape_product * elemByteSize(dtype)).asByteBuffer();
        putObjectArray(b, flatten(data));
        return t;
    }

    public c_api.TF_Tensor createStringTF_Tensor(String[] data) {
        assert (dtype == DT_STRING);
        Tensor t = createStringTensor(data);
        ByteBuffer b = t.getBuffer();
        t.deallocate();
        return createFromBufferTF_Tensor(b.capacity(), b);
    }

    public Tensor createStringTensor(String[] data) {
        Tensor t = new Tensor(DT_STRING,  new TensorShape(shape));
        long size = numElements(shape);
        StringArray a = new StringArray(t.tensor_data()).capacity(size).limit(size);
        for (int i = 0; i < a.capacity(); i++) {
            a.position(i).put(data[i]);
        }
        return t;
    }


    public Tensor createFromArray(Object obj) {
        checkObjectShapeCompatibility(obj);
        Tensor t = new Tensor(dtype, new TensorShape(shape));
        Buffer b = createBuffer(t);
        if(dtype != DT_STRING) {
            putArray(b, obj);
        } else {
            return null;
           // putObjectArray(t.getBuffer(), Arrays.asList((String[]) obj));
        }
        return t;
    }

    public c_api.TF_Tensor createFromArrayTF_Tensor(Object data) {
        c_api.TF_Tensor t = new c_api.TF_Tensor(dtype, shape, shape.length, shape_product * elemByteSize(dtype));
        Buffer b = createBuffer(new BytePointer(TF_TensorData(t)), 0, shape_product * elemByteSize(dtype));
        if(dtype != DT_STRING) {
            putArray(b, data);
        } else {
            return null;
            // putObjectArray(new BytePointer(TF_TensorData(t)).asBuffer(), (String[]) data);
        }
        return t;
    }

    public Tensor createFromBuffer(long numBytes, ByteBuffer buffer) {

        if (numBytes / elemByteSize(dtype) != shape_product) {
            throw new IllegalArgumentException( "Shape of object does not match shape (expected ["
                    + shape_product
                    + "], got ["
                    + numBytes / elemByteSize(dtype)
                    + "])");
        }

        Tensor t = new Tensor(dtype, new TensorShape(shape));
        ByteBuffer b = t.tensor_data().position(0).capacity(buffer.capacity()).asByteBuffer();
        b.put(buffer);
        return t;
    }


    public c_api.TF_Tensor createFromBufferTF_Tensor(long numBytes, ByteBuffer buffer) {

        if (dtype != DT_STRING && numBytes / elemByteSize(dtype) != shape_product) {
            throw new IllegalArgumentException( "Shape of object does not match shape (expected ["
                    + shape_product
                    + "], got ["
                    + numBytes / elemByteSize(dtype)
                    + "])");
        }

        c_api.TF_Tensor t = new c_api.TF_Tensor(dtype, shape, shape.length, buffer.capacity());
        ByteBuffer b = new BytePointer(TF_TensorData(t)).position(0).capacity(buffer.capacity()).asByteBuffer();
        b.put(buffer);
        return t;
    }


    public Tensor fill(T v) {
        Tensor t =  new Tensor(dtype, new TensorShape(shape));
        List<T> data = Collections.nCopies((int) shape_product, v);
        if(dtype != DT_STRING) {
            putArray(t.getBuffer(), data.toArray());
        } else {
            putObjectArray(t.getBuffer(), data);
        }
        return t;
    }

    public c_api.TF_Tensor fillTF_Tensor(T v) {
        c_api.TF_Tensor t = new c_api.TF_Tensor(dtype, shape, shape.length, shape_product * elemByteSize(dtype));
        List<T> data = Collections.nCopies((int) shape_product, v);
        if(dtype != DT_STRING) {
            ByteBuffer b = new BytePointer(TF_TensorData(t)).position(0).capacity(shape_product * elemByteSize(dtype)).asByteBuffer();
            putObjectArray(b, data);
        } else {
            return null;
        }
        return t;
    }


    private List<T> flatten(Object object) {
        List<T> l = new ArrayList<T>();

        if (object.getClass().isArray() && !object.getClass().getComponentType().isArray()) {
            for (int i = 0; i < Array.getLength(object); i++) {
                l.add((T) Array.get(object, i));
            }
        } else if (object.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(object); i++) {
                l.addAll(flatten(Array.get(object, i)));
            }
        }
        return l;
    }


    private void checkObjectShapeCompatibility(Object obj) {
        if(!objectCompatWithType(obj)) {
            throw new IllegalArgumentException( "DataType of object does not match T (expected "
                    + dtype
                    + ", got "
                    + dataTypeOf(obj)
                    + ")");
        }

        long[] os = new long[numDimensions(obj, dtype)];
        fillShape(obj, 0, os);
        if(numElements(os) != shape_product) {
            throw new IllegalArgumentException( "Shape of object does not match shape (expected "
                    + Arrays.toString(shape)
                    + ", got "
                    + Arrays.toString(os)
                    + ")");
        }
    }

    private static int numElements(long[] shape) {
        // assumes a fully-known shape
        int n = 1;
        for (int i = 0; i < shape.length; i++) {
            n *= (int) shape[i];
        }
        return n;
    }

    private static int elemByteSize(int dataType) {
        switch (dataType) {
            case DT_FLOAT:
            case DT_INT32:
                return 4;
            case DT_DOUBLE:
            case DT_INT64:
                return 8;
            case DT_BOOL:
            case DT_UINT8:
                return 1;
            case DT_STRING:
                throw new IllegalArgumentException("STRING tensors do not have a fixed element size");
        }
        throw new IllegalArgumentException("DataType " + dataType + " is not supported yet");
    }

    private static void throwExceptionIfNotByteOfByteArrays(Object array) {
        if (!array.getClass().getName().equals("[[B")) {
            throw new IllegalArgumentException(
                    "object cannot be converted to a Tensor as it includes an array with null elements");
        }
    }



    /** The class for the data type to which Java object o corresponds. */
    private static Class<?> baseObjType(Object o) {
        Class<?> c = o.getClass();
        while (c.isArray()) {
            c = c.getComponentType();
        }
        return c;
    }

    /**
     * The default TensorFlow data type to which Java object o corresponds. Some Java objects
     * represent more than one TensorFlow data type; for example, 'byte' can represent both {@code
     * uint8} and {@code string}, with the latter being the default interpretation.
     */
    private static int dataTypeOf(Object o) {
        Class<?> c = baseObjType(o);
        return dataTypeFromClass(c);
    }

    private static int dataTypeFromClass(Class<?> c) {
        Integer ret = classDataTypes.get(c);
        if (ret != null) {
            return ret;
        }
        throw new IllegalArgumentException("cannot create Tensors of type " + c.getName());
    }

    /**
     * Return the number of dimensions of the tensor that object {@code o} represents as a tensor
     * whose datatype is {@code dtype}. Normally this is the same as the number of dimensions of o
     * itself, but is one smaller for tensors of strings.
     *
     * @param o The object to inspect. It must be a valid representation of the given data type.
     * @param dtype The expected data type of the tensor.
     */
    private static int numDimensions(Object o, int dtype) {
        int ret = numArrayDimensions(o);
        return ret;
    }

    /** Returns the number of dimensions of the array object o. Returns 0 if o is not an array. */
    private static int numArrayDimensions(Object o) {
        Class<?> c = o.getClass();
        int i = 0;
        while (c.isArray()) {
            c = c.getComponentType();
            i++;
        }
        return i;
    }

    public Object toPrimitive(T[] data) {
        switch (dtype) {
            case DT_COMPLEX64:
            case DT_FLOAT:    return ArrayUtils.toPrimitive((Float[]) data);
            case DT_DOUBLE:   return ArrayUtils.toPrimitive((Double[]) data);
            case DT_QINT32:
            case DT_INT32:    return ArrayUtils.toPrimitive((Integer[]) data);
            case DT_BOOL:
            case DT_QUINT8:
            case DT_UINT8:
            case DT_QINT8:
            case DT_INT8:     return ArrayUtils.toPrimitive((Byte[]) data);
            case DT_BFLOAT16:
            case DT_INT16:    return  ArrayUtils.toPrimitive((Short[]) data);
            case DT_INT64:    return ArrayUtils.toPrimitive((Long[]) data);
            case DT_STRING:
            default: assert false;
        }
        return null;
    }



    /**
     * Fills in the remaining entries in the shape array starting from position {@code dim} with the
     * dimension sizes of the multidimensional array o. Checks that all arrays reachable from o have
     * sizes consistent with the filled-in shape, throwing IllegalArgumentException otherwise.
     */
    private static void fillShape(Object o, int dim, long[] shape) {
        if (shape == null || dim == shape.length) {
            return;
        }
        final int len = Array.getLength(o);
        if (len == 0) {
            throw new IllegalArgumentException("cannot create Tensors with a 0 dimension");
        }
        if (shape[dim] == 0) {
            shape[dim] = len;
        } else if (shape[dim] != len) {
            throw new IllegalArgumentException(
                    String.format("mismatched lengths (%d and %d) in dimension %d", shape[dim], len, dim));
        }
        for (int i = 0; i < len; ++i) {
            fillShape(Array.get(o, i), dim + 1, shape);
        }
    }

    /** Returns whether the object {@code obj} can represent a tensor with data type {@code dtype}. */

    private boolean objectCompatWithType(Object obj) {
        Class<?> c = baseObjType(obj);
        Integer dto = dataTypeFromClass(c);
        int nd = numDimensions(obj, dto);
        if (!c.isPrimitive() && c != String.class && nd != 0) {
            throw new IllegalArgumentException(
                    "cannot create non-scalar Tensors from arrays of boxed values");
        }
        if (dto.equals(dtype)) {
            return true;
        }
        if (dto == DT_STRING && dtype == DT_UINT8) {
            return true;
        }
        return false;
    }

    private boolean objectCompatWithShape(Object obj) {

        long[] shapeObj = new long[numDimensions(obj, dtype)];
        fillShape(obj, 0, shapeObj);

        if (shapeObj.length != shape.length) {
            return  false;
        }

        for (int i = 0; i < shape.length; i++) {
            if(shape[i] != shapeObj[i]) {
                return false;
            }
        }

        return true;
    }



}
