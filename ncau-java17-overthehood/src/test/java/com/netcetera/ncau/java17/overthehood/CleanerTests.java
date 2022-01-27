package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CleanerTests {

  @Test
  void cleanExplicitly() {
    try (var explicitlyCleaned = new ToBeCleanedByCleaner()) {
      
    }
  }

  @Test
  void cleanByGc() {
    var cleanedByGc = new ToBeCleanedByCleaner();
    assertNotNull(cleanedByGc);
  }

}
