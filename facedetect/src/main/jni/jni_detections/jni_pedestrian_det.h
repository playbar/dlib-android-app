#ifndef __JNI_PEDESTRIAN_DET_H__
#define __JNI_PEDESTRIAN_DET_H__

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_PedestrianDet_jniDetect(JNIEnv* env, jobject thiz, jstring jImgPath);
JNIEXPORT jobjectArray JNICALL Java_com_tzutalin_dlib_PedestrianDet_jniBitmapDetect(JNIEnv* env, jobject thiz, jobject bitmap);
JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_PedestrianDet_jniInit(JNIEnv* env, jobject thiz);
JNIEXPORT jint JNICALL Java_com_tzutalin_dlib_PedestrianDet_jniDeInit(JNIEnv* env, jobject thiz);

#ifdef __cplusplus
}
#endif

#endif
