cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11  -frtti -fexceptions")

set(LIBOPENCV_JAVA ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java3.so)
add_library( lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${LIBOPENCV_JAVA})

include_directories( ${CMAKE_CURRENT_SOURCE_DIR}/include
                     ${CMAKE_CURRENT_SOURCE_DIR}
                     ${CMAKE_CURRENT_SOURCE_DIR}/jni_common
                     ${CMAKE_CURRENT_SOURCE_DIR}/jni_detections
                     ${CMAKE_CURRENT_SOURCE_DIR}/glog)

add_library( alignment SHARED
             alignment/FaceTracker.cpp
             alignment/jni_linker.cpp
             alignment/Tracking.cpp
             dlib/threads/threads_kernel_shared.cpp
             dlib/entropy_decoder/entropy_decoder_kernel_2.cpp
             dlib/base64/base64_kernel_1.cpp
             dlib/threads/threads_kernel_1.cpp
             dlib/threads/threads_kernel_2.cpp
             jni_common/jni_bitmap2mat.cpp
             jni_common/jni_fileutils.cpp
             jni_common/jni_utils.cpp
             jni_common/rgb2yuv.cpp
             jni_common/yuv2rgb.cpp
             jni_detections/jni_face_det.cpp
             jni_detections/jni_imageutils.cpp
             jni_detections/jni_pedestrian_det.cpp
             glog/logging.cc )

target_link_libraries( alignment
                        log
                        android
                        jnigraphics
                        lib_opencv)

