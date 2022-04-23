package com.netcetera.ncau.java17.superficial;

import java.util.Objects;

public record PersonVerbose(String firstName, String lastName) {

  public PersonVerbose {
    Objects.requireNonNull(firstName, "firstName");
    Objects.requireNonNull(lastName, "lastName");
  }

}
