package com.netcetera.ncau.java17.overthehood;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        .map(Person::getAge)
        .collect(teeing(
              minBy(Integer::compare),
              maxBy(Integer::compare),
              (min,  max) -> new AgeStatistics(min.get(), max.get())));
    //@formatter:on
    assertEquals(21, statistics.getMinimumAge());
    assertEquals(42, statistics.getMaximumAge());

    // in this case IntStream.summaryStatistics() -> IntSummaryStatistics may be better
  }

  static final class Person {

    private final int age;

    Person(int age) {
      this.age = age;
    }

    int getAge() {
      return age;
    }

  }

  static final class AgeStatistics {

    private final int minimumAge;

    private final int maximumAge;

    AgeStatistics(int minimumAge, int maximumAge) {
      this.minimumAge = minimumAge;
      this.maximumAge = maximumAge;
    }

    int getMinimumAge() {
      return minimumAge;
    }

    int getMaximumAge() {
      return maximumAge;
    }

  }

}
