package native_types.core.framework;

import native_types.utils.StringArray;
import org.bytedeco.javacpp.*;
import org.apache.commons.lang.ArrayUtils;


import java.lang.reflect.Array;
import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        return (B)createBuffer(t, 0);
    }

    private  <B extends Buffer> B createBuffer(Tensor t, int index) {
        BytePointer ptr = t.tensor_data();
        long size = t.TotalBytes();
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

    private  <B extends Buffer> void putData(B b, Object data) {

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


    public Tensor fill(T v) {

        Tensor t =  new Tensor(dtype, new TensorShape(shape));
        List<T> data = Collections.nCopies((int) shape_product, v);
        putData(t, data.toArray());

        return t;
    }


    private void putData(Tensor t, Object[] data) {

        BytePointer ptr = t.tensor_data();
        long size = t.TotalBytes();
        ByteBuffer b = ptr.position(0).capacity(size).asBuffer();

        switch (dtype) {
            case DT_COMPLEX64:
            case DT_FLOAT:
                for(Object o: data) {
                   b.putFloat((Float) o);
                }
                return;
            case DT_DOUBLE:
                for(Object o: data) {
                    b.putDouble((Double) o);
                }
                return;
            case DT_QINT32:
            case DT_INT32:
                for(Object o: data) {
                    b.putInt((Integer) o);
                }
                return;
            case DT_BOOL:
            case DT_QUINT8:
            case DT_UINT8:
            case DT_QINT8:
            case DT_INT8:
                for(Object o: data) {
                    b.put((Byte) o);
                }
                return;
            case DT_BFLOAT16:
            case DT_INT16:
                for(Object o: data) {
                    b.putShort((Short) o);
                }
                return;
            case DT_INT64:
                for(Object o: data) {
                    b.putLong((Long) o);
                }
                return;
            case DT_STRING:
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
    public Tensor create(Object obj) throws Exception {
        if(!objectCompatWithType(obj)) {
            throw new IllegalArgumentException( "DataType of object does not match T (expected "
                    + dtype
                    + ", got "
                    + dataTypeOf(obj)
                    + ")");
        }

        if(!objectCompatWithShape(obj)) {
            long[] os = new long[numDimensions(obj, dtype)];
            fillShape(obj, 0, os);
            throw new IllegalArgumentException( "Shape of object does not match shape (expected "
                    + Arrays.toString(shape)
                    + ", got "
                    + Arrays.toString(os)
                    + ")");
        }

        Tensor t =  new Tensor(dtype, new TensorShape(shape));

        if (dtype != DT_STRING) {
            putData(t, flatten(obj).toArray());
        } else {
            throw new Exception("not implemented");
        }
        return t;
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


    private List<T> flatten(Object object) {
        List<T> l = new ArrayList<T>();
        if (object.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(object); i++) {
                l.addAll(flatten(Array.get(object, i)));
            }
        } else if (object instanceof List) {
            for (Object element : (List<?>) object) {
                l.addAll(flatten(element));
            }
        } else {
            l.add((T) object);
        }
        return l;
    }



    public Tensor createFromArray(Object data) {

        if(!objectCompatWithType(data)) {
            throw new IllegalArgumentException( "DataType of object does not match T (expected "
                    + dtype
                    + ", got "
                    + dataTypeOf(data)
                    + ")");
        }

        if (Array.getLength(data) != shape_product) {
            throw new IllegalArgumentException( "Shape of object does not match shape (expected ["
                    + shape_product
                    + "], got ["
                    + Array.getLength(data)
                    + "])");
        }

        Tensor t = new Tensor(dtype, new TensorShape(shape));
        Buffer b = createBuffer(t);
        putData(b, data);
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
        ByteBuffer b = t.tensor_data().position(0).capacity(shape_product).asByteBuffer();
        b.put(buffer);
        return t;
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
        if (dtype == DT_STRING && ret > 0) {
            return ret - 1;
        }
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
