package com.netcetera.ncau.java17.superficial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

class PersonTests {

  private Person hans;

  @BeforeEach
  void setUp() {
    this.hans = new Person("Hans", "Muster");
  }

  @Test
  void equalsHashCode() {
    Person hans2 = new Person("Hans", "Muster");

    assertNotSame(this.hans, hans2);
    assertEquals(this.hans, hans2);
    assertEquals(this.hans.hashCode(), hans2.hashCode());
  }

  @Test
  void getter() {
    assertEquals("Hans", this.hans.firstName());
    assertEquals("Muster", this.hans.lastName());
  }

  @Test
  void fromJson() throws JacksonException {
    String json = """
                  {
                    "firstName": "Hans",
                    "lastName": "Muster"
                  }
                  """;
    Person fromJson = new ObjectMapper().readerFor(Person.class).readValue(json);
    assertEquals(this.hans, fromJson);
  }

  @Test
  void toJson() throws JacksonException {
    String json = new ObjectMapper().writerFor(Person.class).writeValueAsString(this.hans);
    assertEquals("""
                 {"firstName":"Hans","lastName":"Muster"}""", json);
    assertEquals("""
                 {\
                 "firstName":"Hans",\
                 "lastName":"Muster"\
                 }""", json);
  }

  @Test
  void serialization() throws IOException, ClassNotFoundException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
      oos.writeObject(this.hans);
    }
    try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
      assertEquals(this.hans, ois.readObject());
    }
  }

}
