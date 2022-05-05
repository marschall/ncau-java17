package com.netcetera.ncau.java17.superficial.sealedouterclass;
record TimesExpression(Expression a, Expression b) implements Expression {

  @Override
  public int evaluate() {
    return Math.multiplyExact(a.evaluate(), b.evaluate());
  }

}