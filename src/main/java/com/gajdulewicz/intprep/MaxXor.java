package com.gajdulewicz.intprep;

import java.util.Scanner;

public class MaxXor {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int lower = s.nextInt();
    int upper = s.nextInt();
    System.out.println(maxXor(lower, upper));
  }

  static int maxXor(int lower, int upper) {
    int max = Integer.MIN_VALUE;
    for (int i = lower; i <= upper; i++) {
      for (int j = lower; j <= upper; j++) {
        int curr = i ^ j;
        if (curr > max) {
          max = curr;
        }
      }
    }
    return max;
  }
}
