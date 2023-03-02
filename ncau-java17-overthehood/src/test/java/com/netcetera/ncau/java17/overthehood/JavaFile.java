package com.netcetera.ncau.java17.overthehood;

final class JavaFile {

  private final NativeFile nativeFile;

  JavaFile(NativeFile nativeFile) {
    this.nativeFile = nativeFile;
  }

  void close() {
    this.nativeFile.close();
  }

}