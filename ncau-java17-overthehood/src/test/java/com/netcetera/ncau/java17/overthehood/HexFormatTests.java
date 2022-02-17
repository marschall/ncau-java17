package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HexFormat;

import org.junit.jupiter.api.Test;



class HexFormatTests {

  @Test
  void hexFormat() {
    ///@formatter:off
    HexFormat hexFormat = HexFormat.ofDelimiter(" ")
                                   .withUpperCase()
                                   .withPrefix("0x");
    ///@formatter:on
    byte[] aff = new byte[]{0x0A, (byte) 0xFF};
    assertEquals("0x0A 0xFF", hexFormat.formatHex(aff));
    assertArrayEquals(aff, hexFormat.parseHex("0x0A 0xFF"));
  }

}
