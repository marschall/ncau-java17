package com.netcetera.ncau.java17.superficial.sealedouterclass;

import java.util.Objects;

record PlusExpression(Expression a, Expression b) implements Expression {

  public PlusExpression {
    Objects.requireNonNull(a, "a");
    Objects.requireNonNull(b, "b");
  }

  @Override
  public int evaluate() {
    return Math.addExact(a.evaluate(), b.evaluate());
  }

}