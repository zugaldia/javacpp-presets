// Targeted by JavaCPP version 1.5: DO NOT EDIT THIS FILE

package org.bytedeco.cuda.nvml;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.cuda.cudart.*;
import static org.bytedeco.cuda.global.cudart.*;

import static org.bytedeco.cuda.global.nvml.*;


/**
 * Structure containing GRID licensable feature information
 */
@Properties(inherit = org.bytedeco.cuda.presets.nvml.class)
public class nvmlGridLicensableFeature_t extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public nvmlGridLicensableFeature_t() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public nvmlGridLicensableFeature_t(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public nvmlGridLicensableFeature_t(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public nvmlGridLicensableFeature_t position(long position) {
        return (nvmlGridLicensableFeature_t)super.position(position);
    }

    /** Licensed feature code */
    public native @Cast("nvmlGridLicenseFeatureCode_t") int featureCode(); public native nvmlGridLicensableFeature_t featureCode(int setter);
    /** Non-zero if feature is currently licensed, otherwise zero */
    public native @Cast("unsigned int") int featureState(); public native nvmlGridLicensableFeature_t featureState(int setter);
    public native @Cast("char") byte licenseInfo(int i); public native nvmlGridLicensableFeature_t licenseInfo(int i, byte setter);
    @MemberGetter public native @Cast("char*") BytePointer licenseInfo();
}
