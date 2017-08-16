package com.gajdulewicz.intprep;

import java.util.HashMap;
import java.util.Map;

public class NSubstring {

  static String longestNSubstring(String in, int n) {
    int end = 0, start = 0;
    Map<Character, Integer> cnt = new HashMap<>();
    String max = "";
    while (end < in.length()) {
      cnt.put(in.charAt(end), cnt.getOrDefault(in.charAt(end), 0) + 1);
      if (cnt.size() > n) {
        while (start < end) {
          final char key = in.charAt(start);
          int strCnt = cnt.get(key);
          start++;
          if (strCnt == 1) {
            cnt.remove(key);
            break;
          } else {
            cnt.put(key, strCnt - 1);
          }
        }
      }
      final String curr = in.substring(start, end + 1);
      if (curr.length() > max.length()) {
        max = curr;
      }
      end++;
    }
    return max;
  }
}
