JavaCPP Presets for liboqs
==========================

Introduction
------------
This directory contains the JavaCPP Presets module for `liboqs`, a C library for quantum-safe cryptography:

 * liboqs 0.2.0  https://github.com/open-quantum-safe/liboqs

Please refer to the parent README.md file for more detailed information about the JavaCPP Presets.


Documentation
-------------
Java API documentation is available here:

 * http://bytedeco.org/javacpp-presets/liboqs/apidocs/


Sample Usage
------------
Here is a simple example of liboqs ported to Java from this C source file:

 * https://github.com/bytedeco/liboqs/#example

We can use [Maven 3](http://maven.apache.org/) to download and install automatically all the class files as well as the native binaries. To run this sample code, after creating the `pom.xml` and `HelloWorldTest.java` source files below, simply execute on the command line:
```bash
 $ mvn compile exec:java
```

### The `pom.xml` build file
```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.bytedeco.liboqs</groupId>
    <artifactId>liboqs-test</artifactId>
    <version>1.5.2-SNAPSHOT</version>
    <properties>
        <exec.mainClass>HelloWorldTest</exec.mainClass>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.bytedeco</groupId>
            <artifactId>liboqs-platform</artifactId>
            <version>0.2.0-1.5.2-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <sourceDirectory>.</sourceDirectory>
    </build>
</project>
```

### The `HelloWorldTest.java` source file
```kotlin
import org.bytedeco.javacpp.SizeTPointer
import org.bytedeco.liboqs.global.liboqs.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test


class OqsTests {

    @ExperimentalStdlibApi
    @Test
    fun testQTesla() {
        val text = "Quantum mechanics is certainly imposing. But an inner voice tells me that it is not yet the real thing."
        val message = text.encodeToByteArray()
        val publicKey = ByteArray(OQS_SIG_qTesla_p_I_length_public_key)
        val secretKey = ByteArray(OQS_SIG_qTesla_p_I_length_secret_key)
        val rc1 = OQS_SIG_qTesla_p_I_keypair(publicKey, secretKey)
        assertEquals(OQS_SUCCESS, rc1)

        val signature = ByteArray(OQS_SIG_qTesla_p_I_length_signature)
        val signatureLen = SizeTPointer(0)
        val rc2 = OQS_SIG_qTesla_p_I_sign(signature, signatureLen, message, text.length.toLong(), secretKey)
        assertEquals(OQS_SUCCESS, rc2)

        val rc3 = OQS_SIG_qTesla_p_I_verify(message, text.length.toLong(), signature, signatureLen.get(), publicKey)
        assertEquals(OQS_SUCCESS, rc3)

        val rc4 = OQS_SIG_qTesla_p_I_verify(
                // Modify original text
                text.replace("a", "z").encodeToByteArray(),
                text.length.toLong(),
                signature,
                signatureLen.get(),
                publicKey)
        assertNotEquals(OQS_SUCCESS, rc4)
    }
}
```

Build from source
-----------------

You can manually build and (locally) install the Maven artifacts for a specific platform by running:

```
mvn clean install --projects .,liboqs -Djavacpp.platform=<platform> -Djavacpp.platform.root=<ndk-path>
mvn clean install -f platform --projects ../liboqs/platform -Djavacpp.platform.host -Djavacpp.platform=<platform>
```

Where:

- `<platform>` can be one of: `android-arm`, `android-arm64`, `android-x86`, `android-x86_64`, `linux-x86`, or `linux-x86_64`.
- `<ndk-path>` is the path to Android's NDK installation (e.g. `~/Android/Sdk/ndk/21.0.6113669`).
