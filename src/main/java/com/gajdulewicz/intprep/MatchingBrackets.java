package com.gajdulewicz.intprep;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/** Created by gajduler on 7/21/17. */
public class MatchingBrackets {

  private static final Map<Character, Character> pairs;

  static {
    pairs = new HashMap<>();
    pairs.put('{', '}');
    pairs.put('(', ')');
    pairs.put('[', ']');
  }

  public static boolean isBalanced(String expression) {
    final char[] chars = expression.toCharArray();
    Stack<Character> seen = new Stack<>();
    for (int i = 0; i < chars.length; i++) {
      if (pairs.keySet().contains(chars[i])) {
        seen.push(chars[i]);
      } else {
        if (seen.isEmpty()) return false;
        if (pairs.get(seen.peek()) == chars[i]) {
          seen.pop();
        } else {
          return false;
        }
      }
    }
    return seen.empty();
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int a0 = 0; a0 < t; a0++) {
      String expression = in.next();
      System.out.println((isBalanced(expression)) ? "YES" : "NO");
    }
  }
}
