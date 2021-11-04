package com.netcetera.ncau.java17.underthehood;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

import org.junit.jupiter.api.Test;

public class PhantomReferenceTests {

  @Test
  void phantomReferenceExample() throws IllegalArgumentException, InterruptedException {
    ReferenceQueue<ToBeCleanedByPhantomReference> referenceQueue = new ReferenceQueue<>();

    NativeResource resource = new NativeResource();
    ToBeCleanedByPhantomReference toBeCleaned = new ToBeCleanedByPhantomReference(resource);
    new ToBeCleanedReference(toBeCleaned, referenceQueue, resource);

    ToBeCleanedReference reference = (ToBeCleanedReference) referenceQueue.remove(100L);
    assertNotNull(reference);
    reference.getResource().free();
  }

  static final class ToBeCleanedReference extends PhantomReference<ToBeCleanedByPhantomReference> {

    private final NativeResource resource;

    public ToBeCleanedReference(ToBeCleanedByPhantomReference referent,
        ReferenceQueue<? super ToBeCleanedByPhantomReference> queue, NativeResource resource) {
      super(referent, queue);
      this.resource = resource;
    }

    NativeResource getResource() {
      return resource;
    }

  }

}
