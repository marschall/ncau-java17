package com.netcetera.ncau.java17.superficial.sealedouterclass;

import java.util.Objects;

record TimesExpression(Expression a, Expression b) implements Expression {
  
  public TimesExpression {
    Objects.requireNonNull(a, "a");
    Objects.requireNonNull(b, "b");
  }

  @Override
  public int evaluate() {
    return Math.multiplyExact(a.evaluate(), b.evaluate());
  }

}