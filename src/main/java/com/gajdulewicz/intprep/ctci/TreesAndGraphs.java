package com.gajdulewicz.intprep.ctci;

import com.gajdulewicz.intprep.ds.BinaryNode;
import com.google.common.collect.Lists;
import com.google.common.graph.Graph;

import java.util.*;

public class TreesAndGraphs {
  //4.1
  public static boolean isBalanced(BinaryNode<Integer> root) {
    if (root == null) {
      return true;
    }
    final Integer lh = root.getLeft().map(TreesAndGraphs::height).orElse(0);
    final Integer rh = root.getRight().map(TreesAndGraphs::height).orElse(0);
    if (Math.abs(lh - rh) > 1) {
      return false;
    }
    return root.getLeft().map(TreesAndGraphs::isBalanced).orElse(true)
        && root.getRight().map(TreesAndGraphs::isBalanced).orElse(true);
  }

  private static int height(BinaryNode<Integer> node) {
    if (node.childCount() == 0) {
      return 1;
    }
    final Integer heightL = node.getLeft().map(TreesAndGraphs::height).orElse(0);
    final Integer heightR = node.getRight().map(TreesAndGraphs::height).orElse(0);
    return Math.max(heightL, heightR) + 1;
  }
  //4.2
  public static boolean hasPath(Graph<Integer> g, int from, int to) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> toVisit = new LinkedList<>();
    toVisit.add(from);
    while (!toVisit.isEmpty()) {
      int curr = toVisit.poll();
      if (visited.contains(curr)) {
        continue;
      }
      visited.add(curr);
      if (g.successors(curr).contains(to)) {
        return true;
      } else {
        toVisit.addAll(g.successors(curr));
      }
    }
    return false;
  }
  //4.3
  public static BinaryNode<Integer> buildBST(int[] in) {
    return buildMinBst(in, 0, in.length - 1);
  }

  private static BinaryNode<Integer> buildMinBst(int[] in, int start, int end) {
    if (start < 0 || end >= in.length || end < start) {
      return null;
    }
    int mid = (start + end) / 2;
    final BinaryNode<Integer> root = new BinaryNode<>(in[mid]);
    if (start != end) {
      root.setLeft(buildMinBst(in, start, mid - 1));
      root.setRight(buildMinBst(in, mid + 1, end));
    }
    return root;
  }

  //4.4
  public static List<List<Integer>> levelOrder(BinaryNode<Integer> node) {
    final ArrayList<List<Integer>> levels = Lists.newArrayList();
    levels.add(Lists.newArrayList(node.getValue()));
    node.getLeft().ifPresent(n -> descend(n, levels, 1));
    node.getRight().ifPresent(n -> descend(n, levels, 1));
    return levels;
  }

  private static void descend(
      BinaryNode<Integer> node, ArrayList<List<Integer>> levels, int level) {
    if (node == null) {
      return;
    }
    if (levels.size() <= level) {
      levels.add(Lists.newArrayList());
    }
    levels.get(level).add(node.getValue());
    node.getLeft().ifPresent(n -> descend(n, levels, level + 1));
    node.getRight().ifPresent(n -> descend(n, levels, level + 1));
  }

  //4.5
  public static boolean isBst(BinaryNode<Integer> node) {
    return isBstHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstHelper(BinaryNode<Integer> n, int min, int max) {
    if (n == null) {
      return true;
    }
    int v = n.getValue();
    if (v >= max || v <= min) {
      return false;
    }
    return n.getLeft().map(nd -> isBstHelper(nd, min, v)).orElse(true)
        && n.getRight().map(nd -> isBstHelper(nd, v, max)).orElse(true);
  }
}
