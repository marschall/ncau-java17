package com.netcetera.ncau.java17.overthehood;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.CompactNumberFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class CompactNumberFormatTests {

  @Test
  void siUnits() {
    String[] compactPatterns = {
        "0 byte", "00 byte", "000 byte",
        "0 kB", "00 kB", "000 kB",
        "0 MB", "00 MB", "000 MB",
        "0 GB", "00 GB", "000 GB",
        "0 TB", "00 TB", "000 TB" };

    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);

    CompactNumberFormat siNumberFormat = new CompactNumberFormat(decimalFormat.toPattern(),
        decimalFormat.getDecimalFormatSymbols(), compactPatterns);

    assertEquals("1 byte", siNumberFormat.format(1));
    assertEquals("1 kB", siNumberFormat.format(1000));
    assertEquals("1 MB", siNumberFormat.format(1000_000));
    assertEquals("10 MB", siNumberFormat.format(10_000_000));
    assertEquals("1 GB", siNumberFormat.format(1000_000_000));
  }

  @Test
  void meters() {
    String[] compactPatterns = {
        "0 m", "00 m", "00 m",
        "0 km", "00 km", "000 km"};

    DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);

    CompactNumberFormat siNumberFormat = new CompactNumberFormat(decimalFormat.toPattern(),
        decimalFormat.getDecimalFormatSymbols(), compactPatterns);

    assertEquals("1 m", siNumberFormat.format(1));
    assertEquals("2 m", siNumberFormat.format(2));
    assertEquals("1 km", siNumberFormat.format(1000));
    assertEquals("10 km", siNumberFormat.format(10_000));
  }

}
