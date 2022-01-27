package com.netcetera.ncau.java17.overthehood;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamTests {

  private static List<Person> getPersons() {
    return List.of(new Person(21), new Person(23), new Person(42));
  }

  @Test
  void testTeeing() {
    //@formatter:off
    AgeStatistics statistics = getPersons().stream()
        .collect(teeing(
            minBy(Comparator.comparingInt(Person::getAge)),
            maxBy(Comparator.comparingInt(Person::getAge)),
            (youngest,  oldest) -> new AgeStatistics(youngest.get(), oldest.get())));
    //@formatter:on
    assertEquals(21, statistics.getYoungest().getAge());
    assertEquals(42, statistics.getOldest().getAge());
  }

  static final class Person {

    // good candidate for record

    private final int age;

    Person(int age) {
      this.age = age;
    }

    int getAge() {
      return age;
    }

  }

  static final class AgeStatistics {

    // good candidate for record

    private final Person youngest;

    private final Person oldest;

    AgeStatistics(Person youngest, Person oldest) {
      this.youngest = youngest;
      this.oldest = oldest;
    }

    Person getYoungest() {
      return youngest;
    }

    Person getOldest() {
      return oldest;
    }

  }

}
