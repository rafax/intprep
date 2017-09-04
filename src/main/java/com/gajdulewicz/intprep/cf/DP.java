package com.gajdulewicz.intprep.cf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DP {
  static long climbingStairs(int n) {
    long[] mem = new long[Math.max(n + 1, 3)];
    mem[0] = 0;
    mem[1] = 1;
    mem[2] = 2;
    for (int i = 3; i <= n; i++) {
      mem[i] = mem[i - 1] + mem[i - 2];
    }
    return mem[n];
  }

  static int houseRobber(int[] nums) {
    int[] maxByIndex = new int[nums.length];
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i >= 2) {
        maxByIndex[i] =
            i >= 3
                ? Math.max(
                    nums[i] + Math.max(maxByIndex[i - 2], maxByIndex[i - 3]), maxByIndex[i - 1])
                : Math.max(nums[i] + maxByIndex[i - 2], maxByIndex[i - 1]);
      } else {
        maxByIndex[i] = nums[i];
      }
      if (maxByIndex[i] > res) {
        res = maxByIndex[i];
      }
    }
    return res;
  }

  static String[] composeRanges(int[] nums) {
    if (nums.length == 0) return new String[] {};
    ArrayList<String> ranges = new ArrayList<>();
    int start = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1] + 1) {
        if (start == i - 1) {
          ranges.add(Integer.toString(nums[start]));
        } else {
          ranges.add(nums[start] + "->" + nums[i - 1]);
        }
        start = i;
      }
    }
    if (start == nums.length - 1) {
      ranges.add(Integer.toString(nums[start]));
    } else {
      ranges.add(nums[start] + "->" + nums[nums.length - 1]);
    }
    String[] res = new String[ranges.size()];
    for (int i = 0; i < ranges.size(); i++) {
      res[i] = ranges.get(i);
    }
    return res;
  }

  static int mapDecoding(String message) {
    if (message.isEmpty()) return 1;
    if (message.length() == 1) {
      return decode(message).isPresent() ? 1 : 0;
    }
    int modulo = (int) (Math.pow(10, 9) + 7);
    int[] until = new int[message.length()];
    for (int i = 0; i < message.length(); i++) {
      if (i == 0) {
        until[i] = decode(message.substring(0, 1)).isPresent() ? 1 : 0;
        continue;
      }
      long ways;
      String lett1 = message.substring(i, i + 1);
      String lett2 = message.substring(i - 1, i + 1);
      if (i == 1) {
        ways = decode(lett1).isPresent() ? until[0] : 0;
        ways += decode(lett2).isPresent() ? 1 : 0;
      } else {
        ways = decode(lett1).isPresent() ? until[i - 1] : 0;
        ways += decode(lett2).isPresent() ? until[i - 2] : 0;
      }
      if (ways == 0) {
        return 0;
      } else {
        until[i] = (int) (ways % modulo);
      }
    }
    return until[message.length() - 1];
  }

  private static Optional<Character> decode(String in) {
    if (in.startsWith("0")) {
      return Optional.empty();
    }
    try {
      int n = Integer.parseInt(in, 10);
      if (n > 26 || n < 1) {
        return Optional.empty();
      }
      return Optional.of((char) ('A' + n));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  static int maximalSquare(char[][] matrix) {
    int max = 0;
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[0].length; c++) {
        int area = tryTopLeft(matrix, r, c);
        if (area > max) {
          max = area;
        }
      }
    }
    return max;
  }

  private static int tryTopLeft(char[][] matrix, int r, int c) {
    int size;
    if (matrix[r][c] == '1') {
      size = 1;
    } else {
      return 0;
    }
    for (int i = 1; i < matrix.length; i++) {
      if (r + i >= matrix.length || c + i >= matrix[0].length) return size * size;
      for (int j = 0; j < i; j++) {
        if (matrix[r + i][c + j] == '0') return size * size;
        if (matrix[r + j][c + i] == '0') return size * size;
      }
      if (matrix[r + i][c + i] == '1') {
        size = i + 1;
      } else {
        return size * size;
      }
    }
    return size * size;
  }

  static boolean regularExpressionMatching(String s, String p) {
    return match(s, p, 0, 0);
  }

  private static boolean match(String s, String p, int si, int pi) {
    if (pi == p.length()) {
      return si == s.length();
    }
    if (pi == p.length() - 1 || p.charAt(pi + 1) != '*') {
      if (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
        return match(s, p, si + 1, pi + 1);
      } else {
        return false;
      }
    }
    while (si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) {
      if (match(s, p, si, pi + 2)) {
        return true;
      }
      si++;
    }
    return match(s, p, si, pi + 2);
  }

  static int paintHouses(int[][] cost) {
    int[][] minUntil = new int[cost.length][3];
    minUntil[0][0] = cost[0][0];
    minUntil[0][1] = cost[0][1];
    minUntil[0][2] = cost[0][2];
    for (int i = 1; i < cost.length; i++) {
      minUntil[i][0] = Math.min(minUntil[i - 1][1] + cost[i][0], minUntil[i - 1][2] + cost[i][0]);
      minUntil[i][1] = Math.min(minUntil[i - 1][0] + cost[i][1], minUntil[i - 1][2] + cost[i][1]);
      minUntil[i][2] = Math.min(minUntil[i - 1][0] + cost[i][2], minUntil[i - 1][1] + cost[i][2]);
    }
    return java.util.Arrays.stream(minUntil[cost.length - 1]).min().getAsInt();
  }

  static int longestIncreasingSubsequence(int[] sequence) {
    int[] maxSeq = new int[sequence.length];
    maxSeq[0] = 1;
    for (int i = 1; i < sequence.length; i++) {
      boolean found = false;
      for (int j = 0; j < i; j++) {
        if (sequence[j] < sequence[i]) {
          if (maxSeq[j] + 1 > maxSeq[i]) {
            maxSeq[i] = maxSeq[j] + 1;
            found = true;
          }
        }
      }
      if (!found) maxSeq[i] = 1;
    }
    return Arrays.stream(maxSeq).max().getAsInt();
  }


  static boolean kpalindrome(String s, int k) {
    if (k < 0) {
      return false;
    }
    if (s.length() <= 1) {
      return true;
    }

    if (s.charAt(0) == s.charAt(s.length() - 1)) {
      return kpalindrome(s.substring(1, s.length() - 1), k);
    } else {
      return kpalindrome(s.substring(0, s.length() - 1), k - 1) || kpalindrome(s.substring(1, s.length()), k - 1);
    }
  }

//  static boolean kpalindrome(String s, int k) {
//    return kpalindrome(s, reverse(s), s.length(), s.length()) <= 2 * k;
//  }
//
//  private static int kpalindrome(String s, String r, int si, int ri) {
//    if (si == 0) return r.length();
//    if (ri == 0) return s.length();
//    if (s.charAt(si - 1) == r.charAt(ri - 1)) {
//      return kpalindrome(s, r, si - 1, ri - 1);
//    }
//    return 1 + Math.min(kpalindrome(s, r, si - 1, ri), kpalindrome(s, r, si, ri - 1));
//  }
//
//  private static String reverse(String in) {
//    char[] copy = in.toCharArray().clone();
//    for (int i = 0; i < in.length() / 2; i++) {
//      char tmp = in.charAt(i);
//      copy[i] = in.charAt(in.length() - 1 - i);
//      copy[in.length() - 1 - i] = tmp;
//    }
//    return new String(copy);
//  }
}
