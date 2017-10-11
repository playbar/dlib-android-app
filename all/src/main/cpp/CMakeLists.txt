cmake_minimum_required(VERSION 3.4.1)


set(CMAKE_BUILD_TYPE "Release")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O2 -std=c++11 -frtti -fexceptions")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -mfloat-abi=softfp")


add_library(lib_cv SHARED IMPORTED)
set_target_properties(lib_cv PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java3.so)

add_library(lib_cpufeatures STATIC IMPORTED)
set_target_properties(lib_cpufeatures PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libcpufeatures.a)

add_library(lib_IlmImf STATIC IMPORTED)
set_target_properties(lib_IlmImf PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libIlmImf.a)

add_library(lib_jasper STATIC IMPORTED)
set_target_properties(lib_jasper PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibjasper.a)

add_library(lib_jpeg STATIC IMPORTED)
set_target_properties(lib_jpeg PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibjpeg.a)

add_library(lib_png STATIC IMPORTED)
set_target_properties(lib_png PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibpng.a)

add_library(lib_protobuf STATIC IMPORTED)
set_target_properties(lib_protobuf PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibprotobuf.a)

add_library(lib_tiff STATIC IMPORTED)
set_target_properties(lib_tiff PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibtiff.a)


add_library(lib_webp STATIC IMPORTED)
set_target_properties(lib_webp PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/liblibwebp.a)


add_library(lib_calib3d STATIC IMPORTED)
set_target_properties(lib_calib3d PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_calib3d.a)

add_library(lib_core STATIC IMPORTED)
set_target_properties(lib_core PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_core.a)

add_library(lib_dnn STATIC IMPORTED)
set_target_properties(lib_dnn PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_dnn.a)

add_library(lib_features2d STATIC IMPORTED)
set_target_properties(lib_features2d PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_features2d.a)

add_library(lib_flann STATIC IMPORTED)
set_target_properties(lib_flann PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_flann.a)

add_library(lib_highgui STATIC IMPORTED)
set_target_properties(lib_highgui PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_highgui.a)

add_library(lib_imgcodecs STATIC IMPORTED)
set_target_properties(lib_imgcodecs PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_imgcodecs.a)

add_library(lib_imgpro STATIC IMPORTED)
set_target_properties(lib_imgpro PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_imgproc.a)

add_library(lib_ml STATIC IMPORTED)
set_target_properties(lib_ml PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_ml.a)

add_library(lib_objdetect STATIC IMPORTED)
set_target_properties(lib_objdetect PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_objdetect.a)

add_library(lib_photo STATIC IMPORTED)
set_target_properties(lib_photo PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_photo.a)

add_library(lib_shape STATIC IMPORTED)
set_target_properties(lib_shape PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_shape.a)

add_library(lib_stitching STATIC IMPORTED)
set_target_properties(lib_stitching PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_stitching.a)

add_library(lib_superres STATIC IMPORTED)
set_target_properties(lib_superres PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_superres.a)

add_library(lib_video STATIC IMPORTED)
set_target_properties(lib_video PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_video.a)

add_library(lib_videoio STATIC IMPORTED)
set_target_properties(lib_videoio PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_videoio.a)

add_library(lib_videostab STATIC IMPORTED)
set_target_properties(lib_videostab PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_videostab.a)

add_library(lib_tbb STATIC IMPORTED)
set_target_properties(lib_tbb PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libtbb.a)

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
                        z
                        dl
                        m
                        #lib_cv
                        lib_dnn
                        lib_ml
                        lib_objdetect
                        lib_shape
                        lib_stitching
                        lib_superres
                        lib_videostab
                        lib_calib3d
                        lib_features2d
                        lib_highgui
                        lib_videoio
                        lib_imgcodecs
                        lib_video
                        lib_photo
                        lib_imgpro
                        lib_flann
                        lib_core

                        lib_cpufeatures
                        lib_IlmImf
                        lib_jasper
                        lib_jpeg
                        lib_png
                        lib_protobuf
                        lib_tiff
                        lib_webp
                        lib_tbb
                        lib_hal
                        jnigraphics
                        android)

###########################

