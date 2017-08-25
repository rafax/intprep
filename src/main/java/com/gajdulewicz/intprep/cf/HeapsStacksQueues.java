package com.gajdulewicz.intprep.cf;

import java.util.*;

public class HeapsStacksQueues {

    static int kthLargestElement(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparing((Integer x) -> x).reversed());
        for (int num : nums) {
            heap.add(num);
        }
        for (int i = 0; i < k - 1; i++) {
            heap.remove();
        }
        return heap.peek();
    }

    static String simplifyPath(String path) {
        while (path.contains("//")) {
            path = path.replace("//", "/");
        }
        while (path.contains("/./")) {
            path = path.replace("/./", "/");
        }
        while (path.contains("//")) {
            path = path.replace("//", "/");
        }
        LinkedList<String> elems = new LinkedList<>();
        for (String s : path.split("/")) {
            if (Objects.equals(s, "..")) {
                if (!elems.isEmpty()) {
                    elems.removeLast();
                }
            } else {
                elems.add(s);
            }
        }
        String joined = String.join("/", elems);
        if (joined.isEmpty() || joined.charAt(0) != '/') {
            joined = "/" + joined;
        }
        return joined;
    }

    static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        while (left < s.length()) {
            if (Character.isDigit(s.charAt(left))) {
                int numStart = left;
                int lparen = left;
                while (Character.isDigit(s.charAt(lparen))) {
                    lparen++;
                }
                int times = Integer.parseInt(s.substring(numStart, lparen));
                int curr = lparen + 1;
                int skip = 1;
                while (true) {
                    if (s.charAt(curr) == '[') {
                        skip++;
                    }
                    if (s.charAt(curr) == ']') {
                        skip--;
                        if (skip == 0) {
                            break;
                        }
                    }
                    curr++;

                }
                String repeat = s.substring(lparen + 1, curr);
                for (int i = 0; i < times; i++) {
                    sb.append(repeat);
                }
                left = curr + 1;
            } else {
                sb.append(s.charAt(left++));
            }
        }
        if (sb.toString().contains("[")) {
            return decodeString(sb.toString());
        }
        return sb.toString();
    }


}
