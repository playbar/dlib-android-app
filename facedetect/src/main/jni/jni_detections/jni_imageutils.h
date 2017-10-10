#ifndef __JNI_IMAGEUTILS_H__
#define __JNI_IMAGEUTILS_H__

#ifdef __cplusplus
extern "C" {
#endif



JNIEXPORT void JNICALL Java_com_tzutalin_dlibtest_ImageUtils_convertYUV420SPToARGB8888(
        JNIEnv* env, jclass clazz, jbyteArray input, jintArray output, jint width,
        jint height, jboolean halfSize);

JNIEXPORT void JNICALL Java_com_tzutalin_dlibtest_ImageUtils_convertYUV420ToARGB8888(
        JNIEnv* env, jclass clazz, jbyteArray y, jbyteArray u, jbyteArray v,
        jintArray output, jint width, jint height, jint y_row_stride,
        jint uv_row_stride, jint uv_pixel_stride, jboolean halfSize);

JNIEXPORT void JNICALL
Java_com_tzutalin_dlibtest_ImageUtils_convertYUV420SPToRGB565(JNIEnv* env, jclass clazz,
                                                              jbyteArray input,
                                                              jbyteArray output, jint width,
                                                              jint height);

JNIEXPORT void JNICALL
Java_com_tzutalin_dlibtest_ImageUtils_convertARGB8888ToYUV420SP(JNIEnv* env, jclass clazz,
                                                                jintArray input,
                                                                jbyteArray output, jint width,
                                                                jint height);

JNIEXPORT void JNICALL
Java_com_tzutalin_dlibtest_ImageUtils_convertRGB565ToYUV420SP(JNIEnv* env, jclass clazz,
                                                              jbyteArray input,
                                                              jbyteArray output, jint width,
                                                              jint height);



#ifdef __cplusplus
}
#endif

#endif

