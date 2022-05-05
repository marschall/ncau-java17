package com.netcetera.ncau.java17.superficial.sealedouterclass;

record NegateExpression(Expression e) implements Expression {

  @Override
  public int evaluate() {
    return Math.negateExact(e.evaluate());
  }

}