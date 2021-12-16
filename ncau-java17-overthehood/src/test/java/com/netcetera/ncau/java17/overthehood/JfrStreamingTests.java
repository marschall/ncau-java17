package com.netcetera.ncau.java17.overthehood;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import  jdk.jfr.consumer.EventStream;

class JfrStreamingTests {

  @Test
  void streamFiles() throws IOException, InterruptedException {
    try (var eventStream = EventStream.openRepository()) {
      eventStream.setReuse(true);
      eventStream.onEvent("org.junit.TestExecution", event -> {
        System.out.println("test executed: ");
      });
      eventStream.onEvent("org.junit.TestPlanExecution", event -> {
        System.out.println("plan executed: ");
      });
    }
  }

}
