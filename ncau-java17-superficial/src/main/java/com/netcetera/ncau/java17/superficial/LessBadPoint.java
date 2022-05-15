package com.netcetera.ncau.java17.superficial;

public record LessBadPoint(int x, int y) {

  public LessBadPoint {
    if (x > 100) {
      x = 100;
    }
    if (y > 100) {
      y = 100;
    }
  }

}
