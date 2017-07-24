package com.gajdulewicz.intprep;

import java.util.Scanner;

public class IsPrime {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int p = in.nextInt();
    for (int a0 = 0; a0 < p; a0++) {
      int n = in.nextInt();
      System.out.println(isPrimeFast(n) ? "Prime" : "Not prime");
    }
  }

  static boolean isPrimeFast(int n) {
    if (n == 1 || n == 0 || n == 2) {
      return false;
    }
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
