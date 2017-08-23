package com.gajdulewicz.intprep;

import java.util.Scanner;

public class LonelyInteger {
  static int lonelyinteger(int[] a) {
    int res = 0;
    for (int i = 0; i < a.length; i++) {
      res = res ^ a[i];
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] a = new int[n];
    for (int a_i = 0; a_i < n; a_i++) {
      a[a_i] = in.nextInt();
    }
    int result = lonelyinteger(a);
    System.out.println(result);
  }
}
