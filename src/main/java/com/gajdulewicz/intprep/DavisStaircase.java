package com.gajdulewicz.intprep;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Scanner;

/** Created by gajduler on 7/23/17. */
public class DavisStaircase {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int s = in.nextInt();
    for (int a0 = 0; a0 < s; a0++) {
      int n = in.nextInt();
      System.out.println(ways(n, Maps.newHashMap()));
    }
  }

  static int ways(int n, Map<Integer, Integer> memo) {
    if (n < 0) {
      return 0;
    }
    if (n == 0 || n == 1) return 1;
    if (!memo.containsKey(n)) {
      int w = ways(n - 1, memo) + ways(n - 2, memo) + ways(n - 3, memo);
      memo.put(n, w);
    }
    return memo.get(n);
  }
}
