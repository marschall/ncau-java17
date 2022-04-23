package com.netcetera.ncau.java17.superficial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

@Disabled("JAX-B does not support records")
class XmlPersonTests {

  private JAXBContext context;

  @BeforeEach
  void setUp() throws JAXBException {
    this.context = JAXBContext.newInstance(XmlPerson.class);
  }

  @Test
  void serialization() throws JAXBException {
    Marshaller marshaller = this.context.createMarshaller();
    StringWriter writer = new StringWriter();
    marshaller.marshal(new XmlPerson("Hans", "Muster"), writer);

    String xml = writer.toString();
    assertEquals("<xmlPerson><firstName>Hans</firstName><lastName>Muster</lastName></xmlPerson>", xml);

    Unmarshaller unmarshaller = this.context.createUnmarshaller();
    XmlPerson unmarshalled = (XmlPerson) unmarshaller.unmarshal(new StringReader(xml));
    assertEquals(new XmlPerson("Hans", "Muster"), unmarshalled);
  }

}

