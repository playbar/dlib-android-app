#ifndef __JNI_FACE_DET_H__
#define __JNI_FACE_DET_H__


#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_com_tzutalin_dlib_FaceDet_jniNativeClassInit(JNIEnv* env, jclass _this);
JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_FaceDet_jniDetect(JNIEnv* env, jobject thiz, jstring imgPath);
JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_FaceDet_jniBitmapDetect(JNIEnv* env, jobject thiz, jobject bitmap);
JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_FaceDet_jniInit(JNIEnv* env, jobject thiz, jstring jLandmarkPath);
JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_FaceDet_jniDeInit(JNIEnv* env, jobject thiz);

#ifdef __cplusplus
}
#endif

#endif

