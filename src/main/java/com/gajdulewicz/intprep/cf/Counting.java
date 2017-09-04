package com.gajdulewicz.intprep.cf;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Counting {

  static String[] pressingButtons(String buttons) {
    if (buttons.isEmpty()) return new String[] {};
    List<String> res = new ArrayList<>();
    res.addAll(letters(buttons.charAt(0) - '0'));
    for (int i = 1; i < buttons.toCharArray().length; i++) {
      char c = buttons.charAt(i);
      List<String> nres = new ArrayList<>();
      final List<String> letters = letters(c - '0');
      for (String re : res) {
        for (String l : letters) {
          nres.add(re + l);
        }
      }
      res.clear();
      res.addAll(nres);
    }
    String[] r = new String[res.size()];
    for (int i = 0; i < res.size(); i++) {
      r[i] = res.get(i);
    }
    return r;
  }

  static List<String> letters(int key) {
    String[][] ret =
        new String[][] {
          {},
          {},
          {"a", "b", "c"},
          {"d", "e", "f"},
          {"g", "h", "i"},
          {"j", "k", "l"},
          {"m", "n", "o"},
          {"p", "q", "r", "s"},
          {"t", "u", "v"},
          {"w", "x", "y", "z"},
        };
    return java.util.Arrays.stream(ret[key]).collect(Collectors.toList());
  }

  static String[] stringPermutations(String s) {
    final TreeSet<String> all = new TreeSet<>();
    LinkedList<String> c = new LinkedList<>();
    for (char c1 : s.toCharArray()) {
      c.add(Character.toString(c1));
    }
    perm(new StringBuilder(), c, all);
    return all.toArray(new String[] {});
  }

  private static void perm(StringBuilder s, LinkedList<String> chars, TreeSet<String> all) {
    String c = chars.pop();
    for (int i = 0; i <= s.length(); i++) {
      final StringBuilder copy = new StringBuilder(s);
      copy.insert(i, c);
      if (chars.isEmpty()) {
        all.add(copy.toString());
      } else {
        perm(copy, new LinkedList<>(chars), all);
      }
    }
  }

  static int differentPlaylists(int n, int k, int l) {
    int m = 1000000007;
    long total = n;
    Map<Integer, Integer> cnt = new HashMap<>();
    for (int i = 1; i < Math.min(k, l); i++) {
      final int max = Math.max(n - i, n - k);
      cnt.put(max, cnt.getOrDefault(max, 0) + 1);
      total = (total * max) % m;
    }
    if (l - k > 0) {
      final long rest =
          BigInteger.valueOf(n - k)
              .modPow(BigInteger.valueOf(l - k), BigInteger.valueOf(m))
              .mod(BigInteger.valueOf(m))
              .longValueExact();
      total = (total * rest) % m;
    }
    return (int) (total % 1000000007);
  }

  static double knightOnBoardProbability(int n, int m, int steps, int x, int y) {
    BigDecimal maxSteps = BigDecimal.valueOf(8).pow(steps);
    Map<List<Integer>, BigInteger> cnt = new HashMap<>();
    BigInteger vsteps = countValid(n, m, steps, x, y, cnt);
    return new BigDecimal(vsteps).divide(maxSteps).doubleValue();
  }

  private static BigInteger countValid(
      int n, int m, int steps, int x, int y, Map<List<Integer>, BigInteger> cnt) {
    if (steps == 0) return inBoard(n, m, x, y) ? BigInteger.ONE : BigInteger.ONE;
    List<Integer> k = buildKey(n, m, steps, x, y);
    if (!cnt.containsKey(k)) {
      BigInteger total = BigInteger.ZERO;
      for (int[] ints : validJumps(n, m, x, y)) {
        int nx = ints[0], ny = ints[1];
        total = total.add(countValid(n, m, steps - 1, nx, ny, cnt));
      }
      cnt.put(k, total);
    }
    return cnt.get(k);
  }

  private static List<Integer> buildKey(int n, int m, int steps, int x, int y) {
    List<Integer> k = new ArrayList<>();
    k.add(n);
    k.add(m);
    k.add(steps);
    k.add(x);
    k.add(y);
    return k;
  }

  static boolean inBoard(int n, int m, int row, int col) {
    return row >= 0 && row < n && col >= 0 && col < m;
  }

  static List<int[]> validJumps(int n, int m, int row, int col) {

    final int[][] ints = {
      {row - 2, col + 1},
      {row - 1, col + 2},
      {row + 1, col + 2},
      {row + 2, col + 1},
      {row + 2, col - 1},
      {row + 1, col - 2},
      {row - 1, col - 2},
      {row - 2, col - 1}
    };
    return Arrays.stream(ints).filter(x -> inBoard(n, m, x[0], x[1])).collect(Collectors.toList());
  }
}
