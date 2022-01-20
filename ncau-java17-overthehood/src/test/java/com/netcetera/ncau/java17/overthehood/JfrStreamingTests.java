package com.netcetera.ncau.java17.overthehood;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdk.jfr.Recording;
import  jdk.jfr.consumer.EventStream;

class JfrStreamingTests {
  
private Recording recording;

  @BeforeEach
  private void startRecording() throws IOException {
    this.recording = new Recording();
    this.recording.enable("jdk.ThreadSleep");
    this.recording.start();
  }

  @AfterEach
  private void stopRecording() throws IOException {
    this.recording.close();
  }

  @Test
  void streamFiles() throws IOException, InterruptedException {
    try (var eventStream = EventStream.openRepository()) {
      eventStream.setReuse(true);
      eventStream.onEvent("jdk.ThreadSleep", event -> {
        System.out.println("thread: " + event.getThread().getJavaName() + " slept for: " + event.getDuration());

        // initiate termination
        eventStream.close();
      });

      // start the processing in a different thread
      eventStream.startAsync();

      // generate enough events for flush to happen
      for (int i = 0; i < 1_000; i++) {
        Thread.sleep(1L);
      }

      eventStream.awaitTermination();
    }
  }
  

}
