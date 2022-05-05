package com.netcetera.ncau.java17.superficial.sealedouterclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SExpressionPrinterTests {

  @Test
  void print() {
    Expression twoPlusThree = new PlusExpression(new ConstantExpression(2), new ConstantExpression(3));
    assertEquals("(+ 2 3)", new SExpressionPrinter().print(twoPlusThree));
  }

}
