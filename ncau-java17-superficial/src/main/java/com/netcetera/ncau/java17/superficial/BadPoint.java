package com.netcetera.ncau.java17.superficial;

record BadPoint(int x, int y) {
  
  // bad style because its accessor methods "silently" adjust the state of a record instance
  // better in constructor

  public int x() {
    return this.x < 100 ? this.x : 100;
  }

  public int y() {
    return this.y < 100 ? this.y : 100;
  }
}
