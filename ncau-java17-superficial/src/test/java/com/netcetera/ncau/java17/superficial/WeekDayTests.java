package com.netcetera.ncau.java17.superficial;

import static com.netcetera.ncau.java17.superficial.WeekDay.SATURDAY;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeekDayTests {

  @Test
  void switchExpression() {
    assertEquals("Monday", WeekDay.switchExpression(SATURDAY));
  }

  @Test
  void switchStagement() {
    assertEquals("Monday", WeekDay.switchStagement(SATURDAY));
  }

}
