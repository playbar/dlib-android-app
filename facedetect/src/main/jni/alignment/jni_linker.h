#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL Java_com_face_landmark_FaceTracker_nativeInitializeFaceTracker( JNIEnv * env ,
            jobject instance, jstring trackerFilePath_ , jstring detectorXmlFilePath_ );

JNIEXPORT void JNICALL Java_com_face_landmark_FaceTracker_nativeDestroyFaceTracker(JNIEnv *env, jobject instance);

JNIEXPORT jfloatArray JNICALL Java_com_face_landmark_FaceTracker_nativeTrackFace(JNIEnv * env, jobject instance,
                                                                                 jbyteArray frame_, jint width, jint height, jint facing);

JNIEXPORT void JNICALL Java_com_face_landmark_FaceTracker_nativeReset(JNIEnv*env,jobject instance);

#ifdef __cplusplus
}
#endif