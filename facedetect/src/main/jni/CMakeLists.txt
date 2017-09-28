cmake_minimum_required(VERSION 3.4.1)

set(pathLIBOPENCV_JAVA ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java3.so)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11  -frtti -fexceptions")

file( GLOB MANIPULATION_SOURCES ${CMAKE_CURRENT_SOURCE_DIR}/alignment/*.cpp)
file( GLOB MANIPULATION_HEADERS ${CMAKE_CURRENT_SOURCE_DIR}/alignment/*.h)

add_library( jni-linker SHARED
             alignment/jni_linker.cpp )

add_library( lib_opencv SHARED IMPORTED )
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${pathLIBOPENCV_JAVA})
include_directories( ${CMAKE_CURRENT_SOURCE_DIR}/include)

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