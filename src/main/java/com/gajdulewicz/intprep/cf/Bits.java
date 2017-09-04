package com.gajdulewicz.intprep.cf;

public class Bits {
  static int insertBits(int n, int a, int b, int k) {

    for (int i = 0; a + i <= b; i++) {
      n = (n & (~(1 << a + i))) | (k & (1 << i)) << (a);
    }
    return n;
  }

  static int numberOf1Bits(int n) {
    int res = 0;
    while (n != 0) {
      n &= (n - 1);
      res++;
    }
    return res;
  }

  static double bitsToFloat(int bits) {
    double intp = (double) ((bits) >>> 16);
    double frap = (double) (bits & 0xFFFF);
    while (frap >= 1.) {
      frap /= 10.;
    }
    return intp + frap;
  }
}
