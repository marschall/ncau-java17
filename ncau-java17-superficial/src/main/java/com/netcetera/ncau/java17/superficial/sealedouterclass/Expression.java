package com.netcetera.ncau.java17.superficial.sealedouterclass;

public sealed interface Expression
  permits ConstantExpression, PlusExpression, TimesExpression, NegateExpression {

  int evaluate();

}
