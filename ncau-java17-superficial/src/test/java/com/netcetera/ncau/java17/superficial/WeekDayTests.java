package com.netcetera.ncau.java17.superficial;

import static com.netcetera.ncau.java17.superficial.WeekDay.SATURDAY;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeekDayTests {

  @Test
  void switchExpression() {
    assertEquals("Monday", WeekDay.nextWorkDayName_switchExpression(SATURDAY));
  }

  @Test
  void switchStatement() {
    assertEquals("Monday", WeekDay.nextWorkDayName_switchStatement(SATURDAY));
  }

}
