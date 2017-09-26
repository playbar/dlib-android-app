cmake_minimum_required(VERSION 3.4.1)


set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -frtti -fexceptions")


add_library(lib_cv SHARED IMPORTED)
set_target_properties(lib_cv PROPERTIES IMPORTED_LOCATION
    ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java3.so)

add_library(android_dlib SHARED
            dlib/base64/base64_kernel_1.cpp
            dlib/bigint/bigint_kernel_1.cpp
            dlib/bigint/bigint_kernel_2.cpp
            dlib/bit_stream/bit_stream_kernel_1.cpp
            dlib/entropy_decoder/entropy_decoder_kernel_1.cpp
            dlib/entropy_decoder/entropy_decoder_kernel_2.cpp
            dlib/entropy_encoder/entropy_encoder_kernel_1.cpp
            dlib/entropy_encoder/entropy_encoder_kernel_2.cpp
            dlib/md5/md5_kernel_1.cpp
            dlib/tokenizer/tokenizer_kernel_1.cpp
            dlib/unicode/unicode.cpp
            dlib/data_io/image_dataset_metadata.cpp
            dlib/data_io/mnist.cpp
            dlib/sockets/sockets_kernel_1.cpp
             dlib/bsp/bsp.cpp
             dlib/dir_nav/dir_nav_kernel_1.cpp
             dlib/dir_nav/dir_nav_kernel_2.cpp
             dlib/dir_nav/dir_nav_extensions.cpp
             dlib/linker/linker_kernel_1.cpp
             dlib/logger/extra_logger_headers.cpp
             dlib/logger/logger_kernel_1.cpp
             dlib/logger/logger_config_file.cpp
             dlib/misc_api/misc_api_kernel_1.cpp
             dlib/misc_api/misc_api_kernel_2.cpp
             dlib/sockets/sockets_extensions.cpp
             dlib/sockets/sockets_kernel_2.cpp
             dlib/sockstreambuf/sockstreambuf.cpp
             dlib/sockstreambuf/sockstreambuf_unbuffered.cpp
             dlib/server/server_kernel.cpp
             dlib/server/server_iostream.cpp
             dlib/server/server_http.cpp
             dlib/threads/multithreaded_object_extension.cpp
             dlib/threads/threaded_object_extension.cpp
             dlib/threads/threads_kernel_1.cpp
             dlib/threads/threads_kernel_2.cpp
             dlib/threads/threads_kernel_shared.cpp
             dlib/threads/thread_pool_extension.cpp
             dlib/threads/async.cpp
             dlib/timer/timer.cpp
             dlib/stack_trace.cpp
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
                        jnigraphics
                        android)

###########################

