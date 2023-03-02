package com.netcetera.ncau.java17.overthehood;

import java.lang.ref.Cleaner;

final class JavaFileWithCleaner implements AutoCloseable {

  // Ideally you have only one per library
  // starts a daemon thread
  private static final Cleaner CLEANER = Cleaner.create();

  private final Cleaner.Cleanable closingAction;
  private final NativeFile nativeFile;

  JavaFileWithCleaner() {
    this.nativeFile = new NativeFile();
    this.closingAction = CLEANER.register(this, new ClosingAction(nativeFile));
  }

  @Override
  public void close() {
    this.closingAction.clean();
  }

  // must not reference ToBeCleanedByCleaner
  static final class ClosingAction implements Runnable {

    private final NativeFile resource;

    ClosingAction(NativeFile resource) {
      this.resource = resource;
    }

    @Override
    public void run() {
      this.resource.close();
    }
  }
}