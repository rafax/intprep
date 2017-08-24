package com.gajdulewicz.intprep.cf;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

public class HashTables {

  static String[][] groupingDishes(String[][] dishes) {
    Map<String, SortedSet<String>> iDishes = new TreeMap<>();
    for (String[] dishIngredients : dishes) {
      String dish = dishIngredients[0];
      for (int i = 1; i < dishIngredients.length; i++) {
        final SortedSet<String> d = iDishes.getOrDefault(dishIngredients[i], new TreeSet<>());
        d.add(dish);
        iDishes.put(dishIngredients[i], d);
      }
    }
    String[] a = new String[] {};
    return iDishes
        .entrySet()
        .stream()
        .filter(d -> d.getValue().size() >= 2)
        .map(
            e -> {
              final ArrayList<String> l = new ArrayList<>();
              l.add(e.getKey());
              l.addAll(e.getValue());
              return l.toArray(a);
            })
        .collect(Collectors.toList())
        .toArray(new String[][] {});
  }

  static boolean areFollowingPatterns(String[] strings, String[] patterns) {
    Map<String, String> stringToPattern = new HashMap<>();
    Map<String, String> patternToString = new HashMap<>();
    for (int i = 0; i < strings.length; i++) {
      final String s = strings[i];
      final String p = patterns[i];
      if (stringToPattern.containsKey(s)) {
        if (!Objects.equals(stringToPattern.get(s), p)) {
          return false;
        }
      } else {
        stringToPattern.put(s, p);
      }
      if (patternToString.containsKey(p)) {
        if (!Objects.equals(patternToString.get(p), s)) {
          return false;
        }
      } else {
        patternToString.put(p, s);
      }
    }
    return true;
  }

  static boolean containsCloseNums(int[] nums, int k) {
    HashMap<Integer, Integer> lastIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      if (lastIndex.containsKey(n)) {
        int li = lastIndex.get(n);
        if (i - li <= k) {
          return true;
        } else {
          lastIndex.put(n, i);
        }
      } else {
        lastIndex.put(n, i);
      }
    }
    return false;
  }

  static int possibleSums(int[] faces, int[] quantity) {
    Set<Integer> sums = new HashSet<>();
    List<List<Integer>> all = new ArrayList<>();
    for (int i = 0; i < quantity.length; i++) {
      List<Integer> qSums = new ArrayList<>();
      for (int j = 0; j <= quantity[i]; j++) {
        qSums.add(j * faces[i]);
      }
      all.add(qSums);
    }
    while (all.size() > 1) {
      List<Integer> pairs = new ArrayList<>();
      final List<Integer> left = all.remove(0);
      final List<Integer> right = all.remove(0);
      for (Integer l : left) {
        for (Integer r : right) {
          pairs.add(l + r);
        }
      }
      all.add(0, pairs);
    }
    sums.addAll(all.get(0));
    sums.remove(0);
    return sums.size();
  }

  static boolean isBitSet(int val, int n) {
    return ((val >> n) & 1) == 1;
  }

  static String swapLexOrder(String str, int[][] pairs) {
    Map<Integer, Set<Integer>> components = getComponents(pairs);
    char[] res = new char[str.length()];
    for (Set<Integer> component : new HashSet<>(components.values())) {
      List<Character> letters = new ArrayList<>();
      for (Integer ind : component) {
        letters.add(str.charAt(ind - 1));
      }
      letters.sort((a, b) -> -Character.compare(a, b));
      final List<Integer> indices = new ArrayList<>(component);
      indices.sort(Integer::compareTo);
      final List<Character> sortedLetters = new ArrayList<>(letters);
      for (int i = 0; i < sortedLetters.size(); i++) {
        res[indices.get(i) - 1] = sortedLetters.get(i);
      }
    }
    for (int i = 0; i < str.length(); i++) {
      if (res[i] == 0) {
        res[i] = str.charAt(i);
      }
    }
    return new String(res);
  }

  private static Map<Integer, Set<Integer>> getComponents(int[][] pairs) {
    Map<Integer, Set<Integer>> components = new HashMap<>();
    for (int i = 0; i < pairs.length; i++) {
      int l = pairs[i][0], r = pairs[i][1];
      if (components.containsKey(l)) {
        final Set<Integer> c = components.get(l);
        if (components.containsKey(r)) {
          c.addAll(components.get(r));
        }
        c.add(r);
        c.forEach(k -> components.put(k, c));
      } else if (components.containsKey(r)) {
        final Set<Integer> c = components.get(r);
        if (components.containsKey(l)) {
          c.addAll(components.get(l));
        }
        c.add(l);
        components.put(l, c);
        components.put(r, c);
      } else {
        final Set<Integer> n = new HashSet<>();
        n.add(l);
        n.add(r);
        components.put(l, n);
        components.put(r, n);
      }
    }
    return components;
  }
}
