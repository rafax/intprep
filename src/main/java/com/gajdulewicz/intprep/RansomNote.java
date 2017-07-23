package com.gajdulewicz.intprep;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RansomNote {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int m = in.nextInt();
    int n = in.nextInt();
    List<String> magazine = new ArrayList<>();
    for (int magazine_i = 0; magazine_i < m; magazine_i++) {
      magazine.add(in.next());
    }
    List<String> ransom = new ArrayList<>();
    for (int ransom_i = 0; ransom_i < n; ransom_i++) {
      ransom.add(in.next());
    }
    System.out.println(canCreateNote(magazine, ransom) ? "Yes" : "No");
  }

  public static boolean canCreateNote(List<String> magazine, List<String> ransom) {
    Map<String, Long> magCount =
        magazine
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Map<String, Long> ransomCount =
        ransom.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    for (Map.Entry<String, Long> entry : ransomCount.entrySet()) {
      if (!magCount.containsKey(entry.getKey())
          || magCount.get(entry.getKey()) < entry.getValue()) {
        return false;
      }
    }

    return true;
  }
}
