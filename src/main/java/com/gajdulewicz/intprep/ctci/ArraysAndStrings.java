package com.gajdulewicz.intprep.ctci;

import java.util.HashSet;
import java.util.Set;

public class ArraysAndStrings {

  // 1.1
  public static boolean allUnique(String in) {
    Set<Character> seen = new HashSet<>();
    for (char c : in.toCharArray()) {
      if (seen.contains(c)) {
        return false;
      }
      seen.add(c);
    }
    return true;
  }

  // 1.4
  public static void replaceSpaces(char[] in) {
    if (in.length == 0) {
      return;
    }
    int read = in.length - 1;
    int write = in.length - 1;
    int emptySpaceLength = 0;
    while (in[read] == ' ' && read >= 0) {
      read--;
      emptySpaceLength++;
    }
    int spaces = emptySpaceLength / 2;
    while (read >= 0 && spaces > 0) {
      if (in[read] != ' ') {
        in[write--] = in[read--];
      } else {
        in[write--] = '0';
        in[write--] = '2';
        in[write--] = '%';
        read--;
        spaces--;
      }
    }
  }

  // 1.5
  public static String compress(String in) {
    if (in == null || in.isEmpty()) {
      return in;
    }
    StringBuilder sb = new StringBuilder();
    int count = 1;
    char last = in.charAt(0);
    for (int i = 1; i < in.length(); i++) {
      if (in.charAt(i) != last) {
        sb.append(last);
        sb.append(Integer.toString(count));
        last = in.charAt(i);
        count = 1;
      } else {
        count++;
      }
    }
    sb.append(last);
    sb.append(Integer.toString(count));
    if (sb.length() > in.length()) {
      return in;
    }
    return sb.toString();
  }
}
