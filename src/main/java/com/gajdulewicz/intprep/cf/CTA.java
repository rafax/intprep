package com.gajdulewicz.intprep.cf;

import java.util.*;
import java.util.stream.Collectors;

public class CTA {

  static String[] repeatedDNASequences(String s) {
    TreeMap<String, Integer> counts = new TreeMap<>();
    for (int i = 0; i <= s.length() - 10; i++) {
      String ss = s.substring(i, i + 10);
      counts.put(ss, counts.getOrDefault(ss, 0) + 1);
    }

    return counts
        .entrySet()
        .stream()
        .filter(e -> e.getValue() > 1)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList())
        .toArray(new String[] {});
  }

  static double[][] getSkyline(double[][] buildings) {
    List<double[]> ans = new ArrayList<>();
    if (buildings.length == 0) return new double[][] {};
    List<double[]> heights = new ArrayList<>();
    for (double[] b : buildings) {
      heights.add(new double[] {b[0], b[2]});
      heights.add(new double[] {b[0] + b[1], -b[2]});
    }
    heights.sort(
            (a, b) -> (a[0] == b[0] ? Double.compare(b[1], a[1]) : Double.compare(a[0], b[0])));
    PriorityQueue<Double> pq = new PriorityQueue<>(heights.size(), (a, b) -> Double.compare(b, a));
    pq.offer(0d);
    double pre = 0d;
    for (double[] h : heights) {
      if (h[1] > 0) pq.offer(h[1]);
      else if (h[1] < 0) pq.remove(-h[1]);
      if (pq.peek() != pre) {
        ans.add(new double[]{h[0], pq.peek()});
        pre = pq.peek();
      }
    }
    double[][] res = new double[ans.size()][];
    int i = 0;
    for (double[] d : ans) {
      res[i++] = d;
    }
    return res;
  }
}
