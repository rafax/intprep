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
        ans.add(new double[] {h[0], pq.peek()});
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

  static int countInversions(int[] a) {
    int[] aux = a.clone();
    return (int)(countInversions(a, 0, a.length - 1, aux) % 1000000007);
  }

  private static long countInversions(int[] arr, int lo, int hi, int[] aux) {
    if (lo >= hi) return 0;

    int mid = lo + (hi - lo) / 2;

    long count = 0;
    count += countInversions(aux, lo, mid, arr);
    count += countInversions(aux, mid + 1, hi, arr);
    count += merge(arr, lo, mid, hi, aux);

    return count;
  }

  private static long merge(int[] arr, int lo, int mid, int hi, int[] aux) {
    long count = 0;
    int i = lo, j = mid + 1, k = lo;
    while (i <= mid || j <= hi) {
      if (i > mid) {
        arr[k++] = aux[j++];
      } else if (j > hi) {
        arr[k++] = aux[i++];
      } else if (aux[i] <= aux[j]) {
        arr[k++] = aux[i++];
      } else {
        arr[k++] = aux[j++];
        count += mid + 1 - i;
      }
    }

    return count;
  }
}
