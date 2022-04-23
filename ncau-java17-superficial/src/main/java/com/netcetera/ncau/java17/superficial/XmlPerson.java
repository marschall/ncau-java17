package com.netcetera.ncau.java17.superficial;

import static jakarta.xml.bind.annotation.XmlAccessType.PROPERTY;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(PROPERTY)
public record XmlPerson(String firstName, String lastName) {

}
