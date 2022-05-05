package com.netcetera.ncau.java17.superficial.sealedouterclass;

import java.util.Objects;

public final class SExpressionPrinter {

  public String print(Expression e) {
    Objects.requireNonNull(e);
    StringBuilder buffer = new StringBuilder();
    printTo(e, buffer);
    return buffer.toString();
  }

  private void printTo(Expression e, StringBuilder buffer) {
    // we could also use a visitor here
    if (e instanceof ConstantExpression c) {
      buffer.append(c.i());
    } else {
      buffer.append('(');
      if (e instanceof PlusExpression p) {
        buffer.append("+ ");
        printTo(p.a(), buffer);
        buffer.append(' ');
        printTo(p.b(), buffer);
      }
      if (e instanceof TimesExpression t) {
        buffer.append("* ");
        printTo(t.a(), buffer);
        buffer.append(' ');
        printTo(t.b(), buffer);
      }
      if (e instanceof NegateExpression n) {
        buffer.append("- ");
        printTo(n.e(), buffer);
      }
      buffer.append(')');
    }
  }

}