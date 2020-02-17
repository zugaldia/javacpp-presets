#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" liboqs
    popd
    exit
fi

LIBOQS_VERSION=master
download https://github.com/open-quantum-safe/liboqs/archive/$LIBOQS_VERSION.zip liboqs-$LIBOQS_VERSION.zip
download https://teskalabs.blob.core.windows.net/openssl/openssl-dev-1.0.2t-android.tar.gz openssl-dev-1.0.2t-android.tar.gz

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`

echo "Decompressing archives..."
tar xf ../openssl-dev-1.0.2t-android.tar.gz
unzip -o -q ../liboqs-$LIBOQS_VERSION.zip
cd liboqs-$LIBOQS_VERSION

#
# General build instructions: https://github.com/open-quantum-safe/liboqs/blob/master/README.md
# Android-specific instructions: https://github.com/open-quantum-safe/liboqs/blob/master/configure-android
# Implementation heavily inspired on GSL: https://github.com/bytedeco/javacpp-presets/blob/master/gsl/cppbuild.sh
#

case $PLATFORM in
    android-arm)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" -DANDROID_ABI="armeabi-v7a" -DENABLE_SIG_PICNIC=OFF -DUSE_OPENSSL=OFF -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    android-arm64)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" -DANDROID_ABI="arm64-v8a" -DENABLE_SIG_PICNIC=OFF -DUSE_OPENSSL=OFF -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    android-x86)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" -DANDROID_ABI="x86" -DENABLE_SIG_PICNIC=OFF -DUSE_OPENSSL=OFF -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    android-x86_64)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_TOOLCHAIN_FILE="$ANDROID_NDK/build/cmake/android.toolchain.cmake" -DANDROID_ABI="x86_64" -DENABLE_SIG_PICNIC=OFF -DUSE_OPENSSL=OFF -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    linux-x86)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    linux-x86_64)
        mkdir build && cd build
        cmake -GNinja -DCMAKE_INSTALL_PREFIX="$INSTALL_PATH" ..
        ninja
        ninja install
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..
