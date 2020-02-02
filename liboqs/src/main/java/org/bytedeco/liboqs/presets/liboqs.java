package org.bytedeco.liboqs.presets;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    target = "org.bytedeco.liboqs",
    global = "org.bytedeco.liboqs.global.liboqs",
    value = {
        @Platform(
            value = {
                "linux-x86",
                "linux-x86_64",
                "macosx-x86_64",
                "windows-x86",
                "windows-x86_64"},
            // Order here is important, otherwise we can hit a "illegal forward reference" error.
            // Refs: https://github.com/open-quantum-safe/liboqs/blob/master/Makefile.am
            include = {
                "oqs/oqs.h",
                "oqs/common.h",
                "oqs/rand.h",
                "oqs/aes.h",
                "oqs/sha2.h",
                "oqs/sha3.h",
                "oqs/kem.h",
                "oqs/kem_bike.h",
                "oqs/kem_kyber.h",
                //"oqs/kem_ledacrypt.h",
                "oqs/kem_newhope.h",
                "oqs/kem_ntru.h",
                "oqs/kem_saber.h",
                //"oqs/kem_threebears.h",
                "oqs/kem_frodokem.h",
                "oqs/kem_sike.h",
                "oqs/sig.h",
                "oqs/sig_dilithium.h",
                "oqs/sig_mqdss.h",
                "oqs/sig_sphincs.h",
                "oqs/sig_picnic.h",
                "oqs/sig_qtesla.h",
                "oqs/oqsconfig.h"},
            link = "oqs"
        )
    }
)
public class liboqs implements InfoMapper {
    static { Loader.checkVersion("org.bytedeco", "liboqs"); }

    public void map(InfoMap infoMap) {
        // This space intentionally left blank.
    }
}
