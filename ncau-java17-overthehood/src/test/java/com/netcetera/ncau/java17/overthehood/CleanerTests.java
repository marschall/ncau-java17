package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CleanerTests {

  @Test
  void cleanExplicitly() {
    try (var explicitlyCleaned = new JavaFileWithCleaner()) {
      
    }
  }

  @Test
  void cleanByGc() {
    var cleanedByGc = new JavaFileWithCleaner();
    assertNotNull(cleanedByGc);
  }

}
