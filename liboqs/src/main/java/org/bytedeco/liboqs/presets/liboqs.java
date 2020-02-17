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
                "android-arm",
                "android-arm64",
                "android-x86",
                "android-x86_64",
                "linux-x86",
                "linux-x86_64"},
            // Order here is important, otherwise we can hit an "illegal forward reference" error.
            include = {
                "oqs/oqs.h"
                // "oqs/common.h",
                // "oqs/rand.h",
                // "oqs/aes.h",
                // "oqs/sha2.h",
                // "oqs/sha3.h",
                // "oqs/kem.h",
                // "oqs/kem_bike.h",
                // "oqs/kem_frodokem.h",
                // "oqs/kem_kyber.h",
                // "oqs/kem_ledacrypt.h",
                // "oqs/kem_newhope.h",
                // "oqs/kem_ntru.h",
                // "oqs/kem_saber.h",
                // "oqs/kem_sike.h",
                // "oqs/kem_threebears.h",
                // "oqs/sig.h",
                // "oqs/sig_dilithium.h",
                // "oqs/sig_mqdss.h",
                // "oqs/sig_picnic.h",
                // "oqs/sig_qtesla.h",
                // "oqs/sig_sphincs.h",
                // "oqs/oqsconfig.h",
            },
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
