#!/bin/bash

/Users/saraanshwadkar/Library/Android/sdk/cmake/3.22.1/bin/cmake -G "Unix Makefiles" \
 -DCMAKE_CXX_STANDARD=17 \
 -DCMAKE_CXX_STANDARD_REQUIRED=1 \
 -DCMAKE_CXX_EXTENSIONS=0 \
 -DCMAKE_CXX_FLAGS="-std=c++17" \
 -DCMAKE_TOOLCHAIN_FILE=$ANDROID_NDK/build/cmake/android.toolchain.cmake \
 -DANDROID_ABI=arm64-v8a \
 -DCMAKE_ANDROID_API=25 \
 -DANDROID_NATIVE_API_LEVEL=25 \
 -DANDROID_TOOLCHAIN=clang \
 -DCMAKE_MAKE_PROGRAM=$ANDROID_NDK/prebuilt/darwin-x86_64/bin/make \
 -DATOMIC=$ANDROID_NDK/toolchains/llvm/prebuilt/darwin-x86_64/lib64/clang/14.0.6/lib/linux/aarch64/libatomic.a \
 -DWITH_CSCORE=OFF \
 -DWITH_GUI=OFF \
 -DWITH_WPIMATH=OFF \
 -DWITH_WPILIB=OFF \
 -DWITH_SIMULATION_MODULES=OFF \
 -DJAVA_AWT_LIBRARY=NotNeeded \
 -DJAVA_JVM_LIBRARY=NotNeeded \
 -DJAVA_INCLUDE_PATH2=NotNeeded \
 -DJAVA_AWT_INCLUDE_PATH=NotNeeded \
 -S . -B build-cmake
