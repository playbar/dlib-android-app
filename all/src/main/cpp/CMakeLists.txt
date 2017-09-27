cmake_minimum_required(VERSION 3.4.1)


set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -frtti -fexceptions")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -mfloat-abi=softfp")


add_library(lib_cv SHARED IMPORTED)
set_target_properties(lib_cv PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java3.so)

add_library(lib_cpu STATIC IMPORTED)
set_target_properties(lib_cpu PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libcpufeatures.a)

add_library(lib_imf STATIC IMPORTED)
set_target_properties(lib_imf PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libIlmImf.a)

add_library(lib_jpeg STATIC IMPORTED)
set_target_properties(lib_jpeg PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibjpeg.a)

add_library(lib_png STATIC IMPORTED)
set_target_properties(lib_png PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibpng.a)


add_library(lib_core STATIC IMPORTED)
set_target_properties(lib_core PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_core.a)

add_library(lib_imgcodecs STATIC IMPORTED)
set_target_properties(lib_imgcodecs PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_imgcodecs.a)

add_library(lib_imgpro STATIC IMPORTED)
set_target_properties(lib_imgpro PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_imgproc.a)

add_library(lib_objdetect STATIC IMPORTED)
set_target_properties(lib_objdetect PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_objdetect.a)

add_library(lib_hal STATIC IMPORTED)
set_target_properties(lib_hal PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libtegra_hal.a)

add_library(android_dlib SHARED
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
            glog/logging.cc
           )

target_include_directories(android_dlib PRIVATE
                          ${CMAKE_CURRENT_SOURCE_DIR}
                          ${CMAKE_CURRENT_SOURCE_DIR}/jni_common
                          ${CMAKE_CURRENT_SOURCE_DIR}/jni_detections
                          ${CMAKE_CURRENT_SOURCE_DIR}/glog
                          ${CMAKE_CURRENT_SOURCE_DIR}/include
                          )

# Include libraries needed for hello-jnicallback lib
target_link_libraries(android_dlib
                        log
                        lib_cv
                        #lib_cpu
                        #lib_imf
                        #lib_jpeg
                        #lib_png
                        #lib_core
                        #lib_imgcodecs
                        #lib_imgpro
                        #lib_objdetect
                        #lib_hal
                        jnigraphics
                        android)

###########################

