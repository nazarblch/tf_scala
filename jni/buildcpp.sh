#!/bin/bash

cd /home/nazar/tensorflow_scala/jni/target/scala-2.12/classes

# Generate the C++ jni code and the shared library
# Note that the intermediate c++ file is not saved,
# If you need to see the intermediate c++ file then include javacpp option
# -nocompile which will neither compile nor delete the generated c++ file.
#
# Note that the default output directory is the bin directory...
java -jar /home/nazar/Downloads/javacpp.jar \
-cp ../bin \
-d /home/nazar/tensorflow_scala/so \
-o native_types \
-Xcompiler -I/home/nazar/tensorflow_scala/jni/src/main/native/include \
-Xcompiler -I/home/nazar/tensorflow_scala/jni/src/main/native/include/third_party \
-Xcompiler -L/home/nazar/tensorflow_scala/temp \
-Xcompiler -ltensorflow_cc  \
-Xcompiler -ltensorflow_framework  \
-Xcompiler -std=c++11 \
-Xcompiler -D_GLIBCXX_USE_CXX11_ABI=0 \
native_types/core/framework/* \
native_types/core/public/* \
native_types/core/graph/* \
native_types/core/lib/* \
native_types/core/common/* \
native_types/cc/framework/* \
native_types/cc/client/* \
native_types/utils/* \
native_types/ops/* \
native_types/c_api/c_api \
native_types/c_api/eager/c_api

#-properties linux-x86_64-cuda

