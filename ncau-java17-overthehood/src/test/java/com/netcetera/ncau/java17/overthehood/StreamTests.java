package com.netcetera.ncau.java17.overthehood;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class StreamTests {

  @Test
  void testFlatMapToInt() {
    //@formatter:off
    StringBuilder concatenated = Stream.of("one", "two", "three")
                                       .flatMapToInt(StreamTests::intStreamOfChars)
                                       .collect(
                                             StringBuilder::new,
                                             (builder, c) -> builder.append((char) c),
                                             StringBuilder::append);
    //@formatter:on
    assertEquals("onetwothree", concatenated.toString());
  }

  @Test
  void testMapMulti() {
    //@formatter:off
    StringBuilder concatenated = Stream.of("one", "two", "three")
                                       .mapMultiToInt((s, downstream) -> {
                                         for (int i = 0; i < s.length(); i++) {
                                           downstream.accept(s.charAt(i));
                                         }})
                                       .collect(
                                             StringBuilder::new,
                                             (builder, c) -> builder.append((char) c),
                                             StringBuilder::append);
    //@formatter:on
    assertEquals("onetwothree", concatenated.toString());
  }

  private static IntStream intStreamOfChars(String s) {
    // IntStream because there is no CharStream
    //@formatter:off
    return IntStream.range(0, s.length())
                    .map(i -> s.charAt(i));
    //@formatter:on
  }

  private static List<Person> getPersons() {
    return List.of(new Person(21), new Person(23), new Person(42));
  }

  @Test
  void testTeeing() {
    //@formatter:off
    AgeStatistics statistics = getPersons().stream()
        .collect(
            teeing(
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
