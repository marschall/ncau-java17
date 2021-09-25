package com.netcetera.ncau.java17.benchmarks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class Main {

  public static void main(String[] args) throws IOException {
    int arraySize = 128;
    byte[] inputArray = new byte[arraySize];
    for (int i = 0; i < inputArray.length; i++) {
      inputArray[i] = (byte) i;

    }
    CharBuffer charBuffer = CharBuffer.allocate(arraySize);
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputArray);
    byteArrayInputStream.mark(arraySize);
    InputStreamReader reader = new InputStreamReader(byteArrayInputStream);

    for (int i = 0; i < 5; i++) {

      byteArrayInputStream.reset();
      charBuffer.clear();
      System.out.println(reader.read(charBuffer));
      
    }
  }

}
