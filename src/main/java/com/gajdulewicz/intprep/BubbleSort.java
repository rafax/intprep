package com.gajdulewicz.intprep;

import java.util.Scanner;

public class BubbleSort {
    static int bubbleSort(int[] a) {
        int n = a.length;
        int numberOfSwaps = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    numberOfSwaps++;
                }
            }
        }
        return numberOfSwaps;
    }

    private static void swap(int[] a, int j, int i) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        final int swaps = bubbleSort(arr);
        System.out.println(String.format("Array is sorted in %s swaps.", swaps));
        System.out.println(String.format("First Element: %s", arr[0]));
        System.out.println(String.format("Last Element: %s", arr[arr.length - 1]));
    }
}
