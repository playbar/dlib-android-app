cmake_minimum_required(VERSION 3.4.1)

set(pathOPENCV D:/face/OpenCV-android-sdk)
set(pathPROJECT D:/github/dlib-android-app)

set(pathLIBOPENCV_JAVA ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java3.so)


set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11  -frtti -fexceptions")

file( GLOB MANIPULATION_SOURCES src/main/jni/*.cpp)
file( GLOB MANIPULATION_HEADERS src/main/jni/*.h)


add_library( jni-linker SHARED
             src/main/jni/jni_linker.cpp )


add_library( lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${pathLIBOPENCV_JAVA})
include_directories( ${CMAKE_CURRENT_SOURCE_DIR}/src/main/jni/include)


add_library( manipulation-lib SHARED ${MANIPULATION_SOURCES} ${MANIPULATION_HEADERS})
set_target_properties( manipulation-lib PROPERTIES LINKER_LANGUAGE CXX )


find_library(log-lib
              log )

find_library( android-lib android)

target_link_libraries( manipulation-lib
                        lib_opencv)

target_link_libraries(jni-linker
                       ${log-lib}
                       ${android-lib}
                       manipulation-lib
                       lib_opencv)