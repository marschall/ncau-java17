package com.netcetera.ncau.java17.benchmarks;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.openjdk.jmh.annotations.Mode.Throughput;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Throughput)
@OutputTimeUnit(MICROSECONDS)
@State(Benchmark)
public class ByteBufferBenchmarks {

  private CharBuffer charBuffer;
  private ByteArrayInputStream byteArrayInputStream;
  private InputStreamReader reader;

  @Setup
  public void setup() {
    int arraySize = 128;
    byte[] inputArray = new byte[arraySize];
    for (int i = 0; i < inputArray.length; i++) {
      inputArray[i] = (byte) i;

    }
    this.charBuffer = CharBuffer.allocate(arraySize);
    this.byteArrayInputStream = new ByteArrayInputStream(inputArray);
    this.byteArrayInputStream.mark(arraySize);
    this.reader = new InputStreamReader(byteArrayInputStream);
  }

  @Benchmark
  public int encode() throws IOException {
    this.byteArrayInputStream.reset();
    this.charBuffer.clear();
    return this.reader.read(charBuffer);
  }

}
