package com.netcetera.ncau.java17.underthehood;

import org.junit.jupiter.api.Test;

class CleanerTests {

  @Test
  void test() {
    try (var toBeCleaned = new ToBeCleanedByCleaner()) {
      
    }
  }

}
