package com.gajdulewicz.intprep;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Anagram {
  public static int numberNeeded(String first, String second) {
    Map<Character, Long> firstCount = countChars(first);
    Map<Character, Long> secondCount = countChars(second);
    int total = 0;
    for (Map.Entry<Character, Long> e : firstCount.entrySet()) {
      if (secondCount.containsKey(e.getKey())) {
        total += Math.abs(e.getValue() - secondCount.get(e.getKey()));
      } else {
        total += e.getValue();
      }
    }
    for (Map.Entry<Character, Long> e : secondCount.entrySet()) {
      if (!firstCount.containsKey(e.getKey())) {
        total += e.getValue();
      }
    }
    return total;
  }

  private static Map<Character, Long> countChars(String in) {
    return in.chars()
        .mapToObj(c -> ((char) c))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String a = in.next();
    String b = in.next();
    System.out.println(numberNeeded(a, b));
  }
}
