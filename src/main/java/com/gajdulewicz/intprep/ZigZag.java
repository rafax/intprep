package com.gajdulewicz.intprep;

import java.util.ArrayList;
import java.util.List;

public class ZigZag {

  static List<Integer> zigZagIterate(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    boolean downLeft = true;
    int r = 0, c = 0;

    while (!(r == matrix.length - 1 && c == matrix[0].length - 1)) {
      res.add(matrix[r][c]);
      if (downLeft) {
        if (c == 0) {
          if (r < matrix.length - 1) {
            r++;
            downLeft = false;
          } else {
            c++;
            downLeft = false;
          }
        } else {
          if (r < matrix.length - 1) {
            r++;
            c--;
          } else {
            c++;
            downLeft = false;
          }
        }
      } else {
        if (r == 0) {
          if (c < matrix[0].length - 1) {
            c++;
            downLeft = true;
          } else {
            r++;
            downLeft = true;
          }
        } else {
          if (c < matrix[0].length - 1) {
            r--;
            c++;
          } else {
            r++;
            downLeft = true;
          }
        }
      }
    }
    res.add(matrix[r][c]);
    return res;
  }
}
