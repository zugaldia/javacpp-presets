#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" liboqs
    popd
    exit
fi

LIBOQS_VERSION=0.2.0
download https://github.com/open-quantum-safe/liboqs/archive/$LIBOQS_VERSION.zip liboqs-$LIBOQS_VERSION.zip
download https://teskalabs.blob.core.windows.net/openssl/openssl-dev-1.0.2t-android.tar.gz openssl-dev-1.0.2t-android.tar.gz

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`

echo "Decompressing archives..."
tar xf ../openssl-dev-1.0.2t-android.tar.gz
unzip -o -q ../liboqs-$LIBOQS_VERSION.zip
cd liboqs-$LIBOQS_VERSION

# Enables cross compiling
patch -p1 < ../../../liboqs-cross.patch
patch -p1 < ../../../liboqs-no-error.patch

#
# General build instructions: https://github.com/open-quantum-safe/liboqs/blob/master/README.md
# Android-specific instructions: https://github.com/open-quantum-safe/liboqs/blob/master/configure-android
# Implementation heavily inspired on GSL: https://github.com/bytedeco/javacpp-presets/blob/master/gsl/cppbuild.sh
#

case $PLATFORM in
    android-arm)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"
        export LIBS="-ldl -lm -lc"
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH" --host="armv7a-linux-androideabi" --with-sysroot="${ANDROID_ROOT}" --with-openssl="$INSTALL_PATH/openssl/armeabi-v7a" --disable-sig-picnic
        make -j $MAKEJ
        make install
        ;;
    android-arm64)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"
        export LIBS="-ldl -lm -lc"
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH" --host="aarch64-linux-android" --with-sysroot="${ANDROID_ROOT}" --with-openssl="$INSTALL_PATH/openssl/arm64-v8a" --disable-sig-picnic
        make -j $MAKEJ
        make install
        ;;
    android-x86)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"
        export LIBS="-ldl -lm -lc"
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH" --host="i686-linux-android" --with-sysroot="${ANDROID_ROOT}" --with-openssl="$INSTALL_PATH/openssl/x86" --disable-sig-picnic
        make -j $MAKEJ
        make install
        ;;
    android-x86_64)
        export AR="$ANDROID_PREFIX-ar"
        export RANLIB="$ANDROID_PREFIX-ranlib"
        export CC="$ANDROID_CC $ANDROID_FLAGS"
        export STRIP="$ANDROID_PREFIX-strip"
        export LIBS="-ldl -lm -lc"
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH" --host="x86_64-linux-android" --with-sysroot="${ANDROID_ROOT}" --with-openssl="$INSTALL_PATH/openssl/x86_64" --disable-sig-picnic
        make -j $MAKEJ
        make install
        ;;
    linux-x86)
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH"
        make -j $MAKEJ
        make install
        ;;
    linux-x86_64)
        autoreconf -i
        ./configure --prefix="$INSTALL_PATH"
        make -j $MAKEJ
        make install
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..
