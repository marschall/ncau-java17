package com.netcetera.ncau.java17.underthehood;

import java.lang.ref.Cleaner;

final class ToBeCleanedByCleaner implements AutoCloseable {

  // Ideally you have only one per library
  private static final Cleaner CLEANER = Cleaner.create();

  private final Cleaner.Cleanable cleanable;
  private final NativeResource resource;

  ToBeCleanedByCleaner() {
    this.resource = new NativeResource();
    this.cleanable = CLEANER.register(this, new CleaningAction(resource));
  }

  @Override
  public void close() {
    this.cleanable.clean();
  }

  // must not reference ToBeCleanedByCleaner
  static final class CleaningAction implements Runnable {

    private final NativeResource resource;

    CleaningAction(NativeResource resource) {
      this.resource = resource;
    }

    @Override
    public void run() {
      this.resource.free();
    }
  }
}