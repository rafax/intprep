package com.gajdulewicz.intprep;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by gajduler on 7/21/17.
 */
public class Fibonacci {
    private static int fibonacci(int n) {
        return fibonacciHelper(n, new HashMap<>());
    }

    public static int fibonacciHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        if (!memo.containsKey(n)) {
            memo.put(n, fibonacciHelper(n - 1, memo) + fibonacciHelper(n - 2, memo));
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
