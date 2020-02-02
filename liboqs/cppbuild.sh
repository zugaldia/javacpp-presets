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

mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`

#git clone https://github.com/open-quantum-safe/liboqs.git liboqs-$LIBOQS_VERSION
echo "Decompressing archives..."
unzip -o ../liboqs-$LIBOQS_VERSION.zip

cd liboqs-$LIBOQS_VERSION

case $PLATFORM in
    linux-x86|windows-x86)
        autoreconf -i
        ./configure "--prefix=$INSTALL_PATH" "CFLAGS=-m32" "CXXFLAGS=-m32" "LDFLAGS=-m32"
        make -j $MAKEJ
        make install
        ;;
    linux-x86_64|macosx-*|windows-x86_64)
        autoreconf -i
        ./configure --prefix=$INSTALL_PATH
        make -j $MAKEJ
        make install
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..
