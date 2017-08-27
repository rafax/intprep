package com.gajdulewicz.intprep;

import java.util.Arrays;

public class DecompressString {

  public static String decompress(String in) {
    return decompressRecurse(in.toCharArray());
  }

  private static String decompressRecurse(char[] in) {
    StringBuilder sb = new StringBuilder();
    int from = 0;
    while (from < in.length) {
      if (isDigit(in[from])) {
        StringBuilder timesBuilder = new StringBuilder();
        int end = from + 1;
        timesBuilder.append(in[from]);
        while (in[end] != '[') {
          timesBuilder.append(in[end++]);
        }
        int times = Integer.parseInt(timesBuilder.toString());
        // end is [
        int start = end + 1;
        from = end + 1;
        int opens = 0;
        while (in[from] != ']' || opens-- > 0) {
          if (in[from] == '[') {
            opens++;
          }
          from++;
        }
        String expanded = decompressRecurse(Arrays.copyOfRange(in, start, from));
        for (int i = 0; i < times; i++) {
          sb.append(expanded);
        }

      } else {
        sb.append(in[from]);
      }
      from++;
    }
    return sb.toString();
  }

  private static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }
}
