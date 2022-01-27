package com.netcetera.ncau.java17.overthehood;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.junit.jupiter.api.Test;

class PhantomReferenceTests {

  @Test
  void phantomReferenceExample() throws IllegalArgumentException, InterruptedException {
    ReferenceQueue<ToBeCleanedByPhantomReference> referenceQueue = new ReferenceQueue<>();

    NativeResource resource = new NativeResource();
    ToBeCleanedByPhantomReference toBeCleaned = new ToBeCleanedByPhantomReference(resource);
    new NativeResourcePhantomReference(toBeCleaned, referenceQueue, resource);

    // would have to be called in a loop in a dedicated thread
    NativeResourcePhantomReference reference = (NativeResourcePhantomReference) referenceQueue.remove(100L);
    if (reference != null) {
      reference.getResource().free();
    }
  }

  // must not reference ToBeCleanedByPhantomReference
  // therefore we need a direct reference to NativeResource
  static final class NativeResourcePhantomReference extends PhantomReference<ToBeCleanedByPhantomReference> {

    private final NativeResource resource;

    NativeResourcePhantomReference(ToBeCleanedByPhantomReference referent,
        ReferenceQueue<? super ToBeCleanedByPhantomReference> queue, NativeResource resource) {
      super(referent, queue);
      this.resource = resource;
    }

    NativeResource getResource() {
      return resource;
    }

  }

}
