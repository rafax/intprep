package com.gajdulewicz.intprep.ctci;

import java.util.Stack;

public class StacksAndQueues {

  // 3.4
  public static Stack<Integer> hanoi(int n) {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    Stack<Integer> s3 = new Stack<>();
    for (int i = n; i > 0; i--) {
      s1.push(i);
    }
    moveStacks(n, s1, s2, s3);
    return s3;
  }

  private static void moveStacks(
      int n, Stack<Integer> from, Stack<Integer> scratch, Stack<Integer> to) {
    if (n <= 0) {
      return;
    }
    moveStacks(n - 1, from, to, scratch);
    to.push(from.pop());
    moveStacks(n - 1, scratch, from, to);
  }

  // 3.6
  public static Stack<Integer> sortDescending(Stack<Integer> in) {
    if (in == null || in.isEmpty()) {
      return null;
    }
    Stack<Integer> tmp = new Stack<>();
    while (!in.isEmpty()) {
      if (tmp.isEmpty()) {
        tmp.push(in.pop());
      }
      if (in.peek() < tmp.peek()) {
        int tmpElem = in.pop();
        while (tmpElem < tmp.peek()) {
          in.push(tmp.pop());
        }
        tmp.push(tmpElem);
        while (!in.isEmpty() && in.peek() >= tmp.peek()) {
          tmp.push(in.pop());
        }
      } else {
        tmp.push(in.pop());
      }
    }
    while (!tmp.isEmpty()) {
      in.push(tmp.pop());
    }
    return in;
  }
}
