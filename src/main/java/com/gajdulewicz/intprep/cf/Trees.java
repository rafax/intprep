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

  private static boolean hasPath(Tree<Integer> t, int s, int sum) {
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
    if ((t.left == null && t.right != null)
        || (t.left != null && t.right == null)
        || (t.left != null && t.right != null && !Objects.equals(t.left.value, t.right.value)))
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

  static int treeDiameter(int n, int[][] tree) {
    if (tree.length == 0) return 0;
    Map<Integer, Set<Integer>> edges = new HashMap<>();
    for (int i = 0; i < n; i++) {
      edges.put(i, new HashSet<>());
    }
    for (int[] ints : tree) {
      edges.get(ints[0]).add(ints[1]);
      edges.get(ints[1]).add(ints[0]);
    }
    int[] c = dfsLongestPath(0, edges);
    int[] maxC = dfsLongestPath(c[1], edges);
    return maxC[0] == Integer.MIN_VALUE ? 0 : maxC[0];
  }

  static int[] dfsLongestPath(int from, Map<Integer, Set<Integer>> edges) {
    Stack<Integer> path = new Stack<>();
    Set<Integer> visited = new HashSet<>();
    int max = Integer.MIN_VALUE;
    int last = -1;
    Stack<Integer> toVisit = new Stack<>();
    toVisit.add(from);
    while (!toVisit.isEmpty()) {
      final Integer c = toVisit.pop();
      if (!visited.contains(c)) {
        for (Integer integer : edges.get(c)) {
          if (!visited.contains(integer)) {
            toVisit.add(integer);
          }
        }
        while (!path.isEmpty() && !edges.get(c).contains(path.peek())) {
          path.pop();
        }
        path.push(c);
        visited.add(c);
        if (!path.isEmpty() && path.size() - 1 > max) {
          max = path.size() - 1;
          last = path.peek();
        }
      }
    }
    return new int[] {max, last};
  }

  static int[] traverseTree(Tree<Integer> t) {
    if (t == null) return new int[] {};
    ArrayList<Integer> res = new ArrayList<>();
    Queue<Tree<Integer>> toVisit = new LinkedList<>();
    Set<Tree<Integer>> visited = new HashSet<>();
    toVisit.add(t);
    while (!toVisit.isEmpty()) {
      final Tree<Integer> n = toVisit.poll();
      visited.add(n);
      res.add(n.value);
      if (n.left != null && !visited.contains(n.left)) toVisit.add(n.left);
      if (n.right != null && !visited.contains(n.right)) toVisit.add(n.right);
    }
    return res.stream().mapToInt(c -> c).toArray();
  }

  static int[] largestValuesInTreeRows(Tree<Integer> t) {
    List<List<Integer>> levels = new ArrayList<>();
    levelOrder(t, 0, levels);
    return levels.stream().mapToInt(x->x.stream().mapToInt(e->e).max().getAsInt()).toArray();
  }

  static void levelOrder(Tree<Integer> t, int level, List<List<Integer>> levels) {
    if(t==null)return;
    if (level >= levels.size()) {
      levels.add(new ArrayList<>());
    }
    levels.get(level).add(t.value);
    levelOrder(t.left, level+1, levels);
    levelOrder(t.right,level+1,levels);
  }
}
