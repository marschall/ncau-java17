package com.netcetera.ncau.java17.superficial.sealedouterclass;

record PlusExpression(Expression a, Expression b) implements Expression {

  @Override
  public int evaluate() {
    return Math.addExact(a.evaluate(), b.evaluate());
  }

}