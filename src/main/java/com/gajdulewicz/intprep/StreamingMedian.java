package com.gajdulewicz.intprep;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by gajduler on 7/21/17.
 */
public class StreamingMedian {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        HeapMedianCalculator hmc = new HeapMedianCalculator();
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
            System.out.println(hmc.put(a[a_i]));
        }
    }

    public static class HeapMedianCalculator {

        PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
        PriorityQueue<Integer> right = new PriorityQueue<>();

        public double put(Integer elem) {
            if (left.isEmpty() || elem.compareTo(left.peek()) <= 0) {
                left.add(elem);
            } else {
                right.add(elem);
            }
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            }
            if (left.size() < right.size()) {
                left.add(right.poll());
            }
            if (right.isEmpty()) {
                if (left.isEmpty()) {
                    throw new RuntimeException("Both heaps cannot be empty after put()");
                }
                return (double) left.peek();
            }
            if (left.size() == right.size() + 1) {
                return left.peek();
            }
            return (left.peek() + right.peek()) / (double) 2;
        }
    }
}
