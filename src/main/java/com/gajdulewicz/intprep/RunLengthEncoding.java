package com.gajdulewicz.intprep;

import java.util.Arrays;

public class RunLengthEncoding {
  public static boolean isValid(String in) {
    if (in == null || in.isEmpty()) {
      return false;
    }
    final String[] split = in.split(" ");
    if (Arrays.stream(split).anyMatch(c -> c.length() != 8)) {
      return false;
    }
    int continuation = 0;
    for (int i = 0; i < split.length; i++) {
      final String b = split[i];
      if (continuation > 0) {
        if (isSingleByte(b)) {
          return false;
        }
        if (b.startsWith("10")) {
          continuation--;

        } else {
          return false;
        }
      } else {
        if (!isSingleByte(b)) {
          continuation = wordSize(b) - 1;
        }
      }
      i++;
    }
    return true;
  }

  private static int wordSize(String b) {
    for (int i = 0; i < b.toCharArray().length; i++) {
      if (b.charAt(i) != '1') {
        return i;
      }
    }
    return 0;
  }

  private static boolean isSingleByte(String b) {
    return false;
  }
}
