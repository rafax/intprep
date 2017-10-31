package com.gajdulewicz.intprep;

public class CandyJars {

  public static int averageCandies(int[][] fills, int jars) {
    double total = 0d;
    for (int[] fill : fills) {
      total += (fill[1] - fill[0] + 1) * fill[2];
    }
    return (int) (total / (double) jars);
  }
}
