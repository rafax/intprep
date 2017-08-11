package com.gajdulewicz.intprep;

public class ToeplitzMatrix {

  static boolean isToeplitz(int[][] m) {
    for (int row = 0; row < m.length; row++) {
      if (!checkDiagonal(row, 0, m)) {
        return false;
      }
    }
    for (int col = 1; col < m[0].length; col++) {
      if (!checkDiagonal(0, col, m)) {
        return false;
      }
    }
    return true;
  }

  private static boolean checkDiagonal(int row, int col, int[][] m) {
    int elem = m[row++][col++];
    while (row < m.length && col < m.length) {
      if (elem != m[row++][col++]) {
        return false;
      }
    }
    return true;
  }
}
