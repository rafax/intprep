package com.gajdulewicz.intprep.cf;

import java.util.*;

public class Strings {

  static final Set<Character> vowels =
      new HashSet<Character>(java.util.Arrays.asList('a', 'e', 'i', 'o', 'u'));

  static String classifyStrings(String s) {
    String vs = s.replace('?', 'a'), cs = s.replace('?', 'z');
    boolean vbad = isBad(vs);
    boolean cbad = isBad(cs);
    if (vbad && cbad) return "bad";
    if (!vbad && !cbad) return "good";
    return "mixed";
  }

  static boolean isBad(String vs) {
    int vcount = 0, ccount = 0;
    for (char c : vs.toCharArray()) {
      if (isVowel(c)) {
        vcount++;
        ccount = 0;
      } else {
        ccount++;
        vcount = 0;
      }
      if (vcount >= 3 || ccount >= 5) return true;
    }
    return false;
  }

  static boolean isVowel(char c) {
    return vowels.contains(c);
  }

  static String[] textJustification(String[] words, int l) {
    List<List<String>> lineWords = new ArrayList<>();
    lineWords.add(new ArrayList<>());
    int lines = 1;
    int lcount = 0;
    for (int i = 0; i < words.length; i++) {
      String w = words[i];
      if (lcount + w.length() + (!lineWords.get(lines - 1).isEmpty() ? 1 : 0) <= l) {
        lcount += w.length() + (!lineWords.get(lines - 1).isEmpty() ? 1 : 0);
        lineWords.get(lines - 1).add(w);
      } else {
        lineWords.add(new ArrayList<>());
        lines++;
        lcount = w.length();
        lineWords.get(lines - 1).add(w);
      }
    }
    String[] res = new String[lineWords.size()];
    for (int i = 0; i < lineWords.size(); i++) {
      res[i] = justify(lineWords.get(i), i == lineWords.size() - 1, l);
    }
    return res;
  }

  private static String justify(List<String> lineWords, boolean last, int l) {
    StringBuilder sb = new StringBuilder();
    if (lineWords.size() == 1 || last) {
      for (int i = 0; i < lineWords.size(); i++) {
        sb.append(lineWords.get(i));
        if (i != lineWords.size() - 1) {
          sb.append(" ");
        }
      }
      int remaining = l - sb.length();
      for (int i = 0; i < remaining; i++) {
        sb.append(" ");
      }
    } else {
      final int wlength = lineWords.stream().mapToInt(String::length).sum();
      int gaps = lineWords.size() - 1;
      int spaces = l - wlength;
      int gapWidth = spaces / gaps;
      int gapOverflow = spaces % gaps;
      for (int ind = 0; ind < lineWords.size(); ind++) {
        sb.append(lineWords.get(ind));
        if (ind != lineWords.size() - 1) {
          for (int i = 0; i < gapWidth; i++) {
            sb.append(" ");
          }
          if (gapOverflow-- > 0) {
            sb.append(" ");
          }
        }
      }
      ;
    }
    return sb.toString();
  }

  static boolean regexMatching(String pattern, String test) {
    if (pattern.startsWith("^")) {
      return test.indexOf(pattern.substring(1)) == 0;
    }
    if (pattern.endsWith("$")) {
      return test.indexOf(
              pattern.substring(0, pattern.length() - 1), test.length() - pattern.length())
          == test.length() - pattern.length() + 1;
    }
    return test.contains(pattern);
  }

  static int longestCommonSubstring(String s, String t) {
    Map<Character, SortedSet<Integer>> sIndices = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      final SortedSet<Integer> l = sIndices.getOrDefault(c, new TreeSet<>());
      l.add(i);
      sIndices.put(c, l);
    }
    Map<Character, List<Integer>> tIndices = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      final List<Integer> l = tIndices.getOrDefault(c, new ArrayList<>());
      l.add(i);
      tIndices.put(c, l);
    }
    Set<Character> common = new HashSet<>(sIndices.keySet());
    int max = 0;
    common.retainAll(tIndices.keySet());
    for (Character c : common) {
      for (Integer si : sIndices.get(c)) {
        if (si + max >= s.length()) {
          continue;
        }
        for (Integer ti : tIndices.get(c)) {
          if (ti + max >= t.length()) {
            break;
          }
          int sic = si;
          int tic = ti;
          int cnt = 0;
          if (si + max + 1 <= s.length()
              && ti + max + 1 <= t.length()
              && s.substring(si, si + max + 1).hashCode()
                  != t.substring(ti, ti + max + 1).hashCode()) {
            continue;
          }
          while (sic < s.length() && tic < t.length() && s.charAt(sic) == t.charAt(tic)) {
            cnt++;
            sic++;
            tic++;
          }
          if (cnt > max) {
            max = cnt;
          }
        }
      }
    }
    return max;
  }
}
