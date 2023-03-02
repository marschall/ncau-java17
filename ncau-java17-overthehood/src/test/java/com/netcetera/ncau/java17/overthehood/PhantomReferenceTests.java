package com.netcetera.ncau.java17.overthehood;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.junit.jupiter.api.Test;

class PhantomReferenceTests {

  @Test
  void phantomReferenceExample() throws IllegalArgumentException, InterruptedException {
    ReferenceQueue<JavaFile> referenceQueue = new ReferenceQueue<>();

    NativeFile nativeFile = new NativeFile();
    JavaFile toBeCleaned = new JavaFile(nativeFile);
    new NativeFilePhantomReference(toBeCleaned, referenceQueue, nativeFile);

    // would have to be called in a loop in a dedicated thread
    NativeFilePhantomReference reference = (NativeFilePhantomReference) referenceQueue.remove(100L);
    if (reference != null) {
      reference.getNativeFile().close();
    }
  }

  // must not reference ToBeCleanedByPhantomReference
  // therefore we need a direct reference to NativeFile      
  static final class NativeFilePhantomReference extends PhantomReference<JavaFile> {

    private final NativeFile nativeFile;

    NativeFilePhantomReference(JavaFile referent,
        ReferenceQueue<? super JavaFile> queue, NativeFile nativeFile) {
      super(referent, queue);
      this.nativeFile = nativeFile;
    }

    NativeFile getNativeFile() {
      return nativeFile;
    }

  }

}
