package com.netcetera.ncau.java17.underthehood;

final class ToBeCleanedByPhantomReference {

  private final NativeResource resource;

  ToBeCleanedByPhantomReference(NativeResource resource) {
    this.resource = resource;
  }

}