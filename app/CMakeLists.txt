# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/jni/main.cpp src/main/jni/main.h src/main/jni/ModelRegistration.cpp src/main/jni/ModelRegistration.h src/main/jni/PnPProblem.cpp src/main/jni/PnPProblem.h src/main/jni/RobustMatcher.cpp src/main/jni/RobustMatcher.h src/main/jni/Utils.cpp src/main/jni/Utils.h src/main/jni/CameraCalibrator.cpp src/main/jni/CameraCalibrator.h src/main/jni/MyRobustMatcher.cpp src/main/jni/Line2D.cpp src/main/jni/Line2D.h src/main/jni/MSAC.cpp src/main/jni/MSAC.h src/main/jni/errorNIETO.cpp src/main/jni/errorNIETO.h src/main/jni/lmmin.cpp src/main/jni/lmmin.h)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
