package com.netcetera.ncau.java17.superficial;

import static java.util.Locale.US;

public enum WeekDay {

  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  private static String capitalize(WeekDay weekDay) {
    String name = weekDay.name();
    return name.substring(0, 1) + name.substring(1).toLowerCase(US);
  }

  static String nextWorkDayName_switchStatement(WeekDay weekDay) {
    WeekDay nextWorkDay;
    switch (weekDay) {
      case FRIDAY:
      case SATURDAY:
      case SUNDAY:
        nextWorkDay = MONDAY;
        break;
      case MONDAY:
        nextWorkDay = TUESDAY;
        break;
      case TUESDAY:
        nextWorkDay = WEDNESDAY;
        break;
      case WEDNESDAY:
        nextWorkDay = THURSDAY;
        break;
      case THURSDAY:
        nextWorkDay = FRIDAY;
        break;
      default:
        // required to access nextWorkDay
        // even though switch is exhaustive
        throw new IncompatibleClassChangeError("unsupported member: " + weekDay);
    }
    return capitalize(nextWorkDay);
  }

  static String nextWorkDayName_switchExpression(WeekDay weekDay) {
    //@formatter:off
    WeekDay nextWorkDay = switch (weekDay) {
      case FRIDAY, SATURDAY, SUNDAY -> MONDAY;
      case MONDAY                   -> TUESDAY;
      case TUESDAY                  -> WEDNESDAY;
      case WEDNESDAY                -> THURSDAY;
      case THURSDAY                 -> {
        // return would return from the method
        yield FRIDAY;
      }
    };
    //@formatter:on
    return capitalize(nextWorkDay);
  }
}