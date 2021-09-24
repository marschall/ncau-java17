package com.netcetera.ncau.java17.underthehood;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class StackTraceTests {

  @Test
  void hiddenClassAndNullPointer() {
    Paths.get("").getFileName().endsWith(".txt");
  }

}
