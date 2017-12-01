package com.gajdulewicz.intprep.aoc;

public class One {

  public static int inverseCaptcha(String in) {
    int sum = 0;
    final char[] chars = in.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == chars[(i + 1) % in.length()]) {
        sum += chars[i] - '0';
      }
    }
    return sum;
  }

  public static int inverseCaptchaHalfway(String in) {
    int dist = in.length() / 2;
    int sum = 0;
    final char[] chars = in.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == chars[(i + dist) % in.length()]) {
        sum += chars[i] - '0';
      }
    }
    return sum;
  }
}
