#include <android/bitmap.h>
#include <jni_common/jni_bitmap2mat.h>
#include <jni_common/jni_primitives.h>
#include <jni_common/jni_fileutils.h>
#include <jni_common/jni_utils.h>
#include <detector.h>
#include "mylog.h"
#include "jni_face_det.h"
#include <jni.h>


using namespace cv;

extern JNI_VisionDetRet* g_pJNI_VisionDetRet;

namespace {

#define JAVA_NULL 0
    using DetectorPtr = DLibHOGFaceDetector*;
//using DetectorPtr = OpencvHOGDetctor*;

    class JNI_FaceDet {
    public:
        JNI_FaceDet(JNIEnv* env) {
            jclass clazz = env->FindClass(CLASSNAME_FACE_DET);
            mNativeContext = env->GetFieldID(clazz, "mNativeFaceDetContext", "J");
            env->DeleteLocalRef(clazz);
        }

        DetectorPtr getDetectorPtrFromJava(JNIEnv* env, jobject thiz) {
            DetectorPtr const p = (DetectorPtr)env->GetLongField(thiz, mNativeContext);
            return p;
        }

        void setDetectorPtrToJava(JNIEnv* env, jobject thiz, jlong ptr) {
            env->SetLongField(thiz, mNativeContext, ptr);
        }

        jfieldID mNativeContext;
    };

// Protect getting/setting and creating/deleting pointer between java/native
    std::mutex gLock;

    std::shared_ptr<JNI_FaceDet> getJNI_FaceDet(JNIEnv* env) {
        static std::once_flag sOnceInitflag;
        static std::shared_ptr<JNI_FaceDet> sJNI_FaceDet;
        std::call_once(sOnceInitflag, [env]() {
            sJNI_FaceDet = std::make_shared<JNI_FaceDet>(env);
        });
        return sJNI_FaceDet;
    }

    DetectorPtr const getDetectorPtr(JNIEnv* env, jobject thiz) {
        std::lock_guard<std::mutex> lock(gLock);
        return getJNI_FaceDet(env)->getDetectorPtrFromJava(env, thiz);
    }

// The function to set a pointer to java and delete it if newPtr is empty
    void setDetectorPtr(JNIEnv* env, jobject thiz, DetectorPtr newPtr) {
        std::lock_guard<std::mutex> lock(gLock);
        DetectorPtr oldPtr = getJNI_FaceDet(env)->getDetectorPtrFromJava(env, thiz);
        if (oldPtr != JAVA_NULL) {
                    DLOG(INFO) << "setMapManager delete old ptr : " << oldPtr;
            delete oldPtr;
        }

        if (newPtr != JAVA_NULL) {
                    DLOG(INFO) << "setMapManager set new ptr : " << newPtr;
        }

        getJNI_FaceDet(env)->setDetectorPtrToJava(env, thiz, (jlong)newPtr);
    }

}  // end unnamespace




JNIEXPORT void JNICALL Java_com_tzutalin_dlib_FaceDet_jniNativeClassInit(JNIEnv* env, jclass _this)
{
    LOGI("Java_com_tzutalin_dlib_FaceDet_jniNativeClassInit");
}

jobjectArray getDetectResult(JNIEnv* env, DetectorPtr faceDetector, const int& size)
{
    LOG(INFO) << "getFaceRet";
    jobjectArray jDetRetArray = JNI_VisionDetRet::createJObjectArray(env, size);
    for (int i = 0; i < size; i++)
    {
        jobject jDetRet = JNI_VisionDetRet::createJObject(env);
        env->SetObjectArrayElement(jDetRetArray, i, jDetRet);
        dlib::rectangle rect = faceDetector->getResult()[i];
        g_pJNI_VisionDetRet->setRect(env, jDetRet, rect.left(), rect.top(), rect.right(), rect.bottom());
//      cv::Rect rect = faceDetector->getResult()[i];
//    g_pJNI_VisionDetRet->setRect(env, jDetRet, rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
        g_pJNI_VisionDetRet->setLabel(env, jDetRet, "face");
        std::unordered_map<int, dlib::full_object_detection>& faceShapeMap = faceDetector->getFaceShapeMap();
        if (faceShapeMap.find(i) != faceShapeMap.end())
        {
            dlib::full_object_detection shape = faceShapeMap[i];
            for (unsigned long j = 0; j < shape.num_parts(); j++)
            {
                int x = shape.part(j).x();
                int y = shape.part(j).y();
                // Call addLandmark
                g_pJNI_VisionDetRet->addLandmark(env, jDetRet, x, y);
            }
        }
    }
    return jDetRetArray;
}

JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_FaceDet_jniDetect(JNIEnv* env, jobject thiz, jstring imgPath)
{
    LOG(INFO) << "jniFaceDet";
    const char* img_path = env->GetStringUTFChars(imgPath, 0);
    DetectorPtr detPtr = getDetectorPtr(env, thiz);
    int size = detPtr->det(std::string(img_path));
    env->ReleaseStringUTFChars(imgPath, img_path);
    LOG(INFO) << "det face size: " << size;
    return getDetectResult(env, detPtr, size);
}

JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_FaceDet_jniBitmapDetect(JNIEnv* env, jobject thiz, jobject bitmap)
{
    LOG(INFO) << "jniBitmapFaceDet";
    cv::Mat rgbaMat;
    cv::Mat bgrMat;
    jniutils::ConvertBitmapToRGBAMat(env, bitmap, rgbaMat, true);
    cv::cvtColor(rgbaMat, bgrMat, cv::COLOR_RGBA2BGR);
    DetectorPtr detPtr = getDetectorPtr(env, thiz);
    jint size = detPtr->det(bgrMat);
#if 0
    cv::Mat rgbMat;
  cv::cvtColor(bgrMat, rgbMat, cv::COLOR_BGR2RGB);
  cv::imwrite("/sdcard/ret.jpg", rgbaMat);
#endif
    LOG(INFO) << "det face size: " << size;
    return getDetectResult(env, detPtr, size);
}

JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_FaceDet_jniInit(JNIEnv* env, jobject thiz, jstring jLandmarkPath)
{
    LOG(INFO) << "jniInit";
    std::string landmarkPath = jniutils::convertJStrToString(env, jLandmarkPath);
    DetectorPtr detPtr = new DLibHOGFaceDetector(landmarkPath);
//    DetectorPtr detPtr = new OpencvHOGDetctor();
    setDetectorPtr(env, thiz, detPtr);
    return JNI_OK;
}

JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_FaceDet_jniDeInit(JNIEnv* env, jobject thiz)
{
    LOG(INFO) << "jniDeInit";
    setDetectorPtr(env, thiz, JAVA_NULL);
    return JNI_OK;
}


