#include <iostream>

//#include "incl/tensorflow/cc/framework/scope.h"
#include "tensorflow/core/common_runtime/device_factory.h"
// #include "incl/tensorflow/core/common_runtime/rendezvous_mgr.h"
//#include "incl/tensorflow/core/common_runtime/eager/context.h"
#include "tensorflow/core/lib/core/status.h"
#include "tensorflow/c/eager/c_api.h"
#include "tensorflow/c/c_api.h"
#include "tensorflow/core/framework/tensor.h"
//#include "incl/tensorflow/core/common_runtime/eager/eager_operation.h"
//#include "/home/nazar/cpp/test/incl/tensorflow/core/common_runtime/eager/execute.h"

using namespace tensorflow;

TFE_Context* createContext() {
    TFE_ContextOptions* options = TFE_NewContextOptions();
    TFE_ContextOptionsSetAsync(options, 0);
    TF_Status* status = TF_NewStatus();
    return TFE_NewContext(options, status);
}



TFE_TensorHandle* reshape(TFE_TensorHandle* tensorHandle, TFE_TensorHandle* shapeHandle) {
    TFE_Context* context = createContext();
    TF_Status* status = TF_NewStatus();
    TFE_Op* op = TFE_NewOp(context, "Reshape", status);
    std::cout << "TFE_NewOp status = " << TF_GetCode(status) << std::endl;
    TFE_OpSetDevice(op, "/job:localhost/replica:0/task:0/device:CPU:0", status);
    std::cout << "TFE_OpSetDevice status = " << TF_GetCode(status) << std::endl;
    TFE_OpAddInput(op, tensorHandle, status);
    std::cout << "TFE_OpAddInput status = " << TF_GetCode(status) << std::endl;
    TFE_OpAddInput(op, shapeHandle, status);
    std::cout << "TFE_OpAddInput status = " << TF_GetCode(status) << std::endl;
    TF_DataType attr_Tshape = TFE_TensorHandleDataType(shapeHandle);
    TFE_OpSetAttrType(op, "Tshape", attr_Tshape);
    TF_DataType attr_T = TFE_TensorHandleDataType(tensorHandle);
    TFE_OpSetAttrType(op, "T", attr_T);
    TFE_TensorHandle* res = TFE_Exec(op, status);
    std::cout << "TFE_Execute status = " <<  TF_GetCode(status) << std::endl;
    return res;
}

const tensorflow::Tensor* getTensor(TFE_TensorHandle* h, TF_Status* status) {
    tensorflow::Device* d = nullptr;
    tensorflow::Device* op_device = nullptr;
    const tensorflow::Tensor* t = nullptr;
    status->status = h->handle->TensorAndDevice(&t, &d, &op_device);
    if (!status->status.ok()) return nullptr;
    if (d != nullptr) {
        std::cout << "TFE_TensorHandle is placed in device (not host) memory. Cannot return " << std::endl;
        status->status = tensorflow::errors::FailedPrecondition(
                "TFE_TensorHandle is placed in device (not host) memory. Cannot return "
                "a tensorflow::Tensor");
        return nullptr;
    }
    return t;
}

bool IsCPU(const tensorflow::Device* d) {
    return d == nullptr || d->tensorflow_gpu_device_info() == nullptr;
}