package com.netcetera.ncau.java17.underthehood;

import java.nio.file.FileSystems;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class StackTraceTests {

  @Test
  void hiddenClassAndNullPointer() {
    Optional.of(FileSystems.getDefault()).map(fileSystem -> {
      return fileSystem.getRootDirectories().iterator().next().getParent().getFileName();
    });
  }

}
