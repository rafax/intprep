package com.gajdulewicz.intprep;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IceCreamParlor {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int a0 = 0; a0 < t; a0++) {
      int m = in.nextInt();
      int n = in.nextInt();
      int a[] = new int[n];
      for (int a_i = 0; a_i < n; a_i++) {
        a[a_i] = in.nextInt();
      }
      System.out.println(Joiner.on(" ").join(combination(a, m)));
    }
  }

  static List<Integer> combination(int[] a, int m) {
    Map<Integer, Integer> valToPos = new HashMap<>();
    for (int i = 0; i < a.length; i++) {
      if (valToPos.containsKey(m - a[i])) {
        return Lists.newArrayList(valToPos.get(m - a[i]) + 1, i + 1);
      }
      if (!valToPos.containsKey(a[i])) {
        valToPos.put(a[i], i);
      }
    }
    return null;
  }
}
