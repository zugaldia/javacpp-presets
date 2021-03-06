// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.arrow.global.arrow.*;


/** A mock FileSystem implementation that holds its contents in memory.
 * 
 *  Useful for validating the FileSystem API, writing conformance suite,
 *  and bootstrapping FileSystem-based APIs. */
@Namespace("arrow::fs::internal") @NoOffset @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class MockFileSystem extends FileSystem {
    static { Loader.load(); }

  public MockFileSystem(@ByVal @Cast("arrow::fs::TimePoint*") Pointer current_time) { super((Pointer)null); allocate(current_time); }
  private native void allocate(@ByVal @Cast("arrow::fs::TimePoint*") Pointer current_time);

  // XXX It's not very practical to have to explicitly declare inheritance
  // of default overrides.
  public native @ByVal Status GetTargetStats(@StdString String path, FileStats out);
  public native @ByVal Status GetTargetStats(@StdString BytePointer path, FileStats out);
  public native @ByVal Status GetTargetStats(@Const @ByRef Selector select, @StdVector FileStats out);

  public native @ByVal Status CreateDir(@StdString String path, @Cast("bool") boolean recursive/*=true*/);
  public native @ByVal Status CreateDir(@StdString String path);
  public native @ByVal Status CreateDir(@StdString BytePointer path, @Cast("bool") boolean recursive/*=true*/);
  public native @ByVal Status CreateDir(@StdString BytePointer path);

  public native @ByVal Status DeleteDir(@StdString String path);
  public native @ByVal Status DeleteDir(@StdString BytePointer path);
  public native @ByVal Status DeleteDirContents(@StdString String path);
  public native @ByVal Status DeleteDirContents(@StdString BytePointer path);

  public native @ByVal Status DeleteFile(@StdString String path);
  public native @ByVal Status DeleteFile(@StdString BytePointer path);

  public native @ByVal Status Move(@StdString String src, @StdString String dest);
  public native @ByVal Status Move(@StdString BytePointer src, @StdString BytePointer dest);

  public native @ByVal Status CopyFile(@StdString String src, @StdString String dest);
  public native @ByVal Status CopyFile(@StdString BytePointer src, @StdString BytePointer dest);

  public native @ByVal Status OpenInputStream(@StdString String path,
                           @SharedPtr InputStream out);
  public native @ByVal Status OpenInputStream(@StdString BytePointer path,
                           @SharedPtr InputStream out);

  public native @ByVal Status OpenInputFile(@StdString String path,
                         @SharedPtr RandomAccessFile out);
  public native @ByVal Status OpenInputFile(@StdString BytePointer path,
                         @SharedPtr RandomAccessFile out);

  public native @ByVal Status OpenOutputStream(@StdString String path,
                            @SharedPtr OutputStream out);
  public native @ByVal Status OpenOutputStream(@StdString BytePointer path,
                            @SharedPtr OutputStream out);

  public native @ByVal Status OpenAppendStream(@StdString String path,
                            @SharedPtr OutputStream out);
  public native @ByVal Status OpenAppendStream(@StdString BytePointer path,
                            @SharedPtr OutputStream out);

  // Contents-dumping helpers to ease testing.
  // Output is lexicographically-ordered by full path.
  public native @StdVector DirInfo AllDirs();
  public native @StdVector FileInfo AllFiles();

  @Opaque public static class Impl extends Pointer {
      /** Empty constructor. Calls {@code super((Pointer)null)}. */
      public Impl() { super((Pointer)null); }
      /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
      public Impl(Pointer p) { super(p); }
  }
}
