package com.netcetera.ncau.java17.overthehood;

final class NativeFile {
    
  // int fd

  NativeFile() {
    super();
  }

  void close() {
    // syscall close(fd)
    System.out.println("close()");
  }

}
