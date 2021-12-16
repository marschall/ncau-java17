package com.netcetera.ncau.java17.overthehood;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.util.Optional;

public record ComplexNumber(double real, double imaginary) implements Constable {

  @Override
  public Optional<? extends ConstantDesc> describeConstable() {
    // TODO Auto-generated method stub
    return null;
  }

}
