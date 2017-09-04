package com.gajdulewicz.intprep.cf;

import java.util.*;

public class Arrays {

  static int firstDuplicate(int[] a) {
    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < a.length; i++) {
      int e = a[i];
      if (seen.contains(e)) {
        return e;
      } else {
        seen.add(e);
      }
    }
    return -1;
  }

  static char firstNotRepeatingCharacter(String s) {
    int[] counts = new int[26];
    java.util.Arrays.fill(counts, -2);
    for (int i = 0; i < s.length(); i++) {
      final int c = s.charAt(i) - 'a';
      if (counts[c] == -1) {
        continue;
      }
      if (counts[c] == -2) {
        counts[c] = i;
      } else {
        counts[c] = -1;
      }
    }
    char first = '_';
    int index = Integer.MAX_VALUE;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] >= 0 && counts[i] < index) {
        first = (char) (((char) i) + 'a');
        index = counts[i];
      }
    }
    return first;
  }

  static int[][] rotateImage(int[][] a) {
    transpose(a);
    for (int[] ints : a) {
      for (int i = 0; i < ints.length / 2; i++) {
        int temp = ints[i];
        ints[i] = ints[ints.length - i - 1];
        ints[ints.length - i - 1] = temp;
      }
    }

    return a;
  }

  static void transpose(int[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = i + 1; j < a[0].length; j++) {
        if (i == j) {
          continue;
        }
        int tmp = a[i][j];
        a[i][j] = a[j][i];
        a[j][i] = tmp;
      }
    }
  }

  static boolean sudoku2(char[][] grid) {
    for (int i = 0; i < 9; i++) {
      if (!sudokuGood(grid[i])) {
        return false;
      }
    }
    for (int i = 0; i < 9; i++) {
      char[] a = new char[9];
      for (int j = 0; j < 9; j++) {
        a[j] = grid[j][i];
      }
      if (!sudokuGood(a)) {
        return false;
      }
    }
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        char[] a = new char[9];
        for (int x = 0; x < 3; x++) {
          for (int y = 0; y < 3; y++) {
            a[x * 3 + y] = grid[i + x][j + y];
          }
        }
        if (!sudokuGood(a)) {
          return false;
        }
      }
    }
    return true;
  }

  static boolean sudokuGood(char[] grid) {
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : grid) {
      if (c != '.') {
        counts.put(c, counts.getOrDefault(c, 0) + 1);
      }
    }
    return counts.values().stream().allMatch(e -> e == 1);
  }

  static boolean isCryptSolution(String[] crypt, char[][] solution) {
    Optional<Long> a = translate(crypt[0], solution);
    Optional<Long> b = translate(crypt[1], solution);
    Optional<Long> z = translate(crypt[2], solution);
    if (a.isPresent() && b.isPresent() && z.isPresent()) {
      return a.get() + b.get() == z.get();
    }
    return false;
  }

  static Optional<Long> translate(String in, char[][] solution) {
    char[] res = new char[in.length()];
    for (int i = 0; i < in.length(); i++) {
      for (int j = 0; j < solution.length; j++) {
        if (solution[j][0] == in.charAt(i)) {
          res[i] = solution[j][1];
          break;
        }
      }
    }
    if (res[0] == '0' && res.length > 1) return Optional.empty();
    return Optional.of(Long.parseLong(new String(res)));
  }
}
