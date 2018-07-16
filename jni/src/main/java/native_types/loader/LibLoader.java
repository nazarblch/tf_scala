package native_types.loader;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Objects;

public class LibLoader {
    public static void load() {
        System.load("/home/nazar/tensorflow/bazel-bin/tensorflow/libtensorflow_cc.so");
        System.load("/home/nazar/tensorflow/bazel-bin/tensorflow/libtensorflow_framework.so");
        System.load("/home/nazar/tensorflow_scala/so/libnative_types.so");
    }

    public static File core_file = new File("/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core");
    public static File framework_file = new File("/home/nazar/tensorflow_scala/jni/src/main/native/include/tensorflow/core/framework");

    private static HashMap<String, File> map = new HashMap<String, File>();
    static {
        map.put("core", core_file);
        map.put("framework", framework_file);
    }

    public static String find_file(String name, String dir) {
        return Objects.requireNonNull(map.get(dir).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().equals(name);
            }
        }))[0].getAbsolutePath();
    }

}
