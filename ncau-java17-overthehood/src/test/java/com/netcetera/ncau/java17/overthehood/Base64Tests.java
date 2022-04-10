package com.netcetera.ncau.java17.overthehood;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.junit.jupiter.api.Test;

class Base64Tests {

  @Test
  void decode() {
    Decoder decoder = Base64.getDecoder();
    byte[] expected = "Many hands make light work.".getBytes(US_ASCII);
    assertArrayEquals(expected, decoder.decode("TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu"));
  }

  @Test
  void encode() {
    Encoder encoder = Base64.getEncoder();
    byte[] input = "Many hands make light work.".getBytes(US_ASCII);
    assertEquals("TWFueSBoYW5kcyBtYWtlIGxpZ2h0IHdvcmsu", encoder.encodeToString(input));
  }

}
