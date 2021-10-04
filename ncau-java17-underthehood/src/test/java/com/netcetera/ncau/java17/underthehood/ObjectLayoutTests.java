package com.netcetera.ncau.java17.underthehood;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * Demonstrates the new, improved object field layout.
 * 
 * @see <a href="https://bugs.openjdk.java.net/browse/JDK-8237767" title="JDK-8237767">Field layout computation overhaul</a>
 */
class ObjectLayoutTests {


  @Test
  void gapInHeader() {
    System.out.println(ClassLayout.parseClass(GapInHeaderSubclass.class).toPrintable());
  }

  @Test
  void moreGapInHeader() {
    System.out.println(ClassLayout.parseClass(MoreGapInHeaderSubclass.class).toPrintable());
  }

  @Test
  void gapInSuperclass() {
    System.out.println(ClassLayout.parseClass(GapInSubclass.class).toPrintable());
  }

  static abstract class GapInHeaderSuperclass {

    private long superclassField;

    GapInHeaderSuperclass(long superclassField) {
      this.superclassField = superclassField;
    }

  }

  static final class GapInHeaderSubclass extends GapInHeaderSuperclass {

    private int sublassField;

    GapInHeaderSubclass(long superclassField, int sublassField) {
      super(superclassField);
      this.sublassField = sublassField;
    }

  }

  static abstract class MoreGapInHeaderSuperclass {

    private byte superclassField;

    MoreGapInHeaderSuperclass(byte superclassField) {
      this.superclassField = superclassField;
    }

  }

  static final class MoreGapInHeaderSubclass extends MoreGapInHeaderSuperclass {

    private byte sublassField;

    MoreGapInHeaderSubclass(byte superclassField, byte sublassField) {
      super(superclassField);
      this.sublassField = sublassField;
    }

  }

  static abstract class GapInSuperclass {

    private int movedToHeader;

    private short inSuperclass;

    GapInSuperclass(int movedToHeader, short inSuperclass) {
      this.movedToHeader = movedToHeader;
      this.inSuperclass = inSuperclass;
    }

  }

  static final class GapInSubclass extends GapInSuperclass {

    private short alsoInSuperclass;

    GapInSubclass(int movedToHeader, short inSuperclass, short alsoInSuperclass) {
      super(movedToHeader, inSuperclass);
      this.alsoInSuperclass = alsoInSuperclass;
    }

  }

}
