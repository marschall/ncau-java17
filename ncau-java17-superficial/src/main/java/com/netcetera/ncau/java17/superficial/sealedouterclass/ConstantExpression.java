package com.netcetera.ncau.java17.superficial.sealedouterclass;

record ConstantExpression(int i) implements Expression {

  @Override
  public int evaluate() {
    return i;
  }

}