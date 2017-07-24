package com.gajdulewicz.intprep;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrayLeft {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int a[] = new int[n];
    for (int a_i = 0; a_i < n; a_i++) {
      a[a_i] = in.nextInt();
    }
    LinkedList<Integer> rotate = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      rotate.add(a[i]);
    }
    for (int i = 0; i < k; i++) {
      Integer move = rotate.remove();
      rotate.addLast(move);
    }
    System.out.println(
        String.join(" ", rotate.stream().map(Object::toString).collect(Collectors.toList())));
  }
}
