package com.netcetera.ncau.java17.superficial.sealedouterclass;

import java.util.Objects;

record NegateExpression(Expression e) implements Expression {
  
  public NegateExpression {
    Objects.requireNonNull(e, "e");
  }

  @Override
  public int evaluate() {
    return Math.negateExact(e.evaluate());
  }

}