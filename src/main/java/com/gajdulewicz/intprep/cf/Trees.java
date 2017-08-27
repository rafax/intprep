package com.gajdulewicz.intprep.cf;

import java.util.*;

public class Trees {
    static class Tree<T> {
        Tree(T x) {
            value = x;
        }

        T value;
        Tree<T> left;
        Tree<T> right;
    }

    static boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        if (t == null && s == 0) {
            return true;
        }
        return hasPath(t, s, 0);
    }

    static private boolean hasPath(Tree<Integer> t, int s, int sum) {
        if (t == null) {
            return false;
        }
        if (t.left == null && t.right == null) {
            if (sum + t.value == s) {
                return true;
            }
        }
        return hasPath(t.left, s, sum + t.value) || hasPath(t.right, s, sum + t.value);
    }

    static boolean isTreeSymmetric(Tree<Integer> t) {
        if (t == null) return true;
        if ((t.left == null && t.right != null) || (t.left != null && t.right == null) || (t.left != null && t.right != null && !Objects.equals(t.left.value, t.right.value)))
            return false;
        List<Integer> lOrder = new ArrayList<>();
        List<Integer> rOrder = new ArrayList<>();
        inOrder(t.left, lOrder);
        inOrder(t.right, rOrder);
        Collections.reverse(rOrder);
        return lOrder.equals(rOrder);
    }

    static void inOrder(Tree<Integer> t, List<Integer> order) {
        if (t == null) return;
        inOrder(t.left, order);
        order.add(t.value);
        inOrder(t.right, order);
    }

    static String findProfession(int level, int pos) {
        int levelSize = 2 << (level - 1);
        int flips = 0;
        while (pos - 1 > 0) {
            if (pos > levelSize / 2) {
                flips++;
                pos -= levelSize / 2;
            }
            levelSize /= 2;
        }
        return (flips % 2) == 0 ? "Engineer" : "Doctor";
    }

//    static int treeDiameter(int n, int[][] tree) {
//        Map<Integer, Set<Integer>> edges = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            edges.put(i, new HashSet<>());
//        }
//        for (int[] ints : tree) {
//            edges.get(ints[0]).add(ints[1]);
//            edges.get(ints[1]).add(ints[0]);
//        }
//        Set<Integer> visited = new HashSet<>();
//        Queue<Integer> toVisit = new LinkedList<>();
//        toVisit.add(0);
//        while (!toVisit.isEmpty()) {
//            final Integer next = toVisit.iterator().next();
//            toVisit.remove(next);
//
//        }
//    }
//
//    static int dfsLongestPath(int from, Map<Integer, Set<Integer>> edges) {
//        Stack<Integer> path = new Stack<>();
//        path.add(from);
//        Set<Integer> visited = new HashSet<>();
//        visited.add(from);
//        return dfsLongestPath(path, edges, visited, 1);
//
//
//    }
//
//    private static int dfsLongestPath(Stack<Integer> path, Map<Integer, Set<Integer>> edges, Set<Integer> visited, int longest) {
//        for (Integer n : edges.get(path.peek())) {
//            if(visited.contains(n)){
//
//            }
//        }
//
//    }


}
