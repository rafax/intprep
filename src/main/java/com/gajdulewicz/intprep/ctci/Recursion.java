package com.gajdulewicz.intprep.ctci;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recursion {

  // 9.1
  public static long ways(int steps, long[] memo) {
    if (steps < 0) {
      return 0;
    }
    if (steps == 0) {
      return 1;
    }
    if (memo[steps] == 0) {
      memo[steps] = ways(steps - 1, memo) + ways(steps - 2, memo) + ways(steps - 3, memo);
    }
    return memo[steps];
  }

  // 9.2
  public static long gridWays(boolean[][] grid) {
    long[][] ways = new long[grid.length][grid[0].length];
    fill(ways, grid);
    return ways[0][0];
  }

  private static void fill(long[][] ways, boolean[][] grid) {
    for (int row = grid.length - 1; row >= 0; row--) {
      for (int col = grid[0].length - 1; col >= 0; col--) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
          ways[row][col] = 1;
          continue;
        }
        if (!grid[row][col]) {
          continue;
        }
        long res = 0;
        if (!isOutOfGrid(row + 1, col, grid)) {
          res += ways[row + 1][col];
        }
        if (!isOutOfGrid(row, col + 1, grid)) {
          res += ways[row][col + 1];
        }
        ways[row][col] = res;
      }
    }
  }

  private static boolean isOutOfGrid(int row, int col, boolean[][] grid) {
    return row < 0 || row >= grid.length || col < 0 || col >= grid[0].length;
  }

  // 9.4
  public static <T> List<Set<T>> subsets(List<T> in) {
    List<Set<T>> res = Lists.newArrayList();
    for (long i = 0; i < Math.pow(2, in.size()); i++) {
      final String include = Strings.padStart(Long.toBinaryString(i), in.size(), '0');
      Set<T> x = new HashSet<>();
      for (int j = 0; j < include.length(); j++) {
        if (include.charAt(j) == '1') {
          x.add(in.get(j));
        }
      }
      res.add(x);
    }
    return res;
  }

  // 9.5
  public static List<String> permutations(String in) {
    if (in.length() == 1) {
      return Lists.newArrayList(in);
    }
    final char head = in.charAt(0);
    final List<String> subPermutations = permutations(in.substring(1));
    List<String> res = Lists.newArrayList();
    for (String sp : subPermutations) {
      for (int i = 0; i < sp.length() + 1; i++) {
        StringBuilder sb = new StringBuilder();
        if (i > 0) {
          sb.append(sp.substring(0, i));
        }
        sb.append(head);
        if (i < sb.length()) {
          sb.append(sp.substring(i));
        }
        res.add(sb.toString());
      }
    }
    return res;
  }

  public static List<String> parenPairs(int n) {
    if (n == 1) return Lists.newArrayList("()");
    Set<String> res = new HashSet<>();
    final List<String> strings = parenPairs(n - 1);
    for (String s : strings) {
      res.add("()" + s);
      for (int i = 1; i < s.length() + 1; i++) {
        if (s.charAt(i - 1) != '(') {
          continue;
        }
        StringBuilder sb = new StringBuilder();
        if (i > 0) {
          sb.append(s.substring(0, i));
        }
        sb.append("()");
        if (i < sb.length()) {
          sb.append(s.substring(i));
        }
        res.add(sb.toString());
      }
    }
    return Lists.newArrayList(res);
  }
}
