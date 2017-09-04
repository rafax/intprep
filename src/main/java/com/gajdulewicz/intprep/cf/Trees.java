package com.gajdulewicz.intprep.cf;

import java.util.*;
import java.util.Arrays;

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
    return levels.stream().mapToInt(x -> x.stream().mapToInt(e -> e).max().getAsInt()).toArray();
  }

  static void levelOrder(Tree<Integer> t, int level, List<List<Integer>> levels) {
    if (t == null) return;
    if (level >= levels.size()) {
      levels.add(new ArrayList<>());
    }
    levels.get(level).add(t.value);
    levelOrder(t.left, level + 1, levels);
    levelOrder(t.right, level + 1, levels);
  }

  static int[][] climbingStaircase(int n, int k) {
    ArrayList<int[]> ways = new ArrayList<>();
    climbingStaircase(n, k, new Stack<>(), ways);
    int[][] res = new int[ways.size()][];
    for (int i = 0; i < ways.size(); i++) {
      res[i] = ways.get(i);
    }
    return res;
  }

  private static void climbingStaircase(int n, int k, Stack<Integer> path, ArrayList<int[]> ways) {
    if (n < 0) {
      return;
    }
    if (n == 0) {
      ways.add(path.stream().mapToInt(x -> x).toArray());
      return;
    }
    for (int i = 1; i <= k; i++) {
      Stack<Integer> npath = new Stack<>();
      npath.addAll(path);
      npath.push(i);
      climbingStaircase(n - i, k, npath, ways);
    }
  }

  static int[][] nQueens(int n) {

    List<List<Integer>> all = new ArrayList<>();
    nQueens(n, 0, new ArrayList<>(), all);
    int[][] res = new int[all.size()][];
    for (int i = 0; i < all.size(); i++) {
      res[i] = all.get(i).stream().mapToInt(x -> x + 1).toArray();
    }
    return res;
  }

  private static void nQueens(int n, int col, ArrayList<Integer> pos, List<List<Integer>> all) {
    if (col == n) {
      all.add(pos);
    }
    for (int i = 0; i < n; i++) {
      if (!conflicts(pos, col, i)) {
        final ArrayList<Integer> nPos = new ArrayList<>(pos);
        nPos.add(i);
        nQueens(n, col + 1, nPos, all);
      }
    }
  }

  private static boolean conflicts(ArrayList<Integer> pos, int col, int row) {
    if (pos.isEmpty()) {
      return false;
    }
    for (int i = 0; i < pos.size(); i++) {
      if (pos.get(i) == row) {
        return true;
      }
      if (col - i == row - pos.get(i)) {
        return true;
      }
      if (col - i == pos.get(i) - row) {
        return true;
      }
    }
    return false;
  }

  static int[] findLongestSubarrayBySum(int s, int[] arr) {
    int curr = arr[0];
    int[] max = arr[0] == s ? new int[] {0, 0} : new int[] {-1};
    int left = 0, right = 0;
    while (left < arr.length && right < arr.length) {
      if (curr < s) {
        right++;
        if (right == arr.length) {
          break;
        }
        curr += arr[right];
      } else if (curr == s) {
        if (max.length == 1 || right - left > max[1] - max[0]) {
          max = new int[] {left, right};
        }
        right++;
        if (right == arr.length) {
          break;
        }
        curr += arr[right];
      } else {
        curr -= arr[left];
        left++;
      }
    }
    return max.length > 1 ? Arrays.stream(max).map(x -> x + 1).toArray() : max;
  }

  static int productExceptSelf(int[] nums, int m) {
    int[] suffix = new int[nums.length];
    int[] prefix = new int[nums.length];
    prefix[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      prefix[i] = Math.floorMod(prefix[i - 1] * nums[i - 1], m);
    }
    suffix[nums.length - 1] = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      suffix[i] = Math.floorMod(suffix[i + 1] * nums[i + 1], m);
    }
    int[] res = new int[nums.length];
    res[0] = suffix[0];
    for (int i = 1; i < nums.length; i++) {
      res[i] = Math.floorMod(Math.floorMod(prefix[i], m) * Math.floorMod(suffix[i], m), m);
    }
    int resSumMod = 0;
    for (int i = 0; i < nums.length; i++) {
      resSumMod += res[i];
    }
    return Math.floorMod(resSumMod, m);
  }

  static String minSubstringWithAllChars(String s, String t) {
    if (t.isEmpty()) return "";
    Map<Character, Integer> seen = new HashMap<>();
    Map<Character, Integer> needed = new HashMap<>();
    for (char c : t.toCharArray()) {
      needed.put(c, needed.getOrDefault(c, 0) + 1);
    }
    seen.put(s.charAt(0), 1);
    int left = 0, right = 0;
    String min = "";
    while (left != s.length()) {
      if (needed
          .entrySet()
          .stream()
          .allMatch(
              kv -> seen.containsKey(kv.getKey()) && seen.get(kv.getKey()) >= kv.getValue())) {
        if (min.length() == 0 || right - left + 1 < min.length()) {
          min = s.substring(left, right + 1);
        }
        final char v = s.charAt(left);
        int ccnt = seen.get(v);
        if (ccnt == 1) {
          seen.remove(v);
        } else {
          seen.put(v, ccnt - 1);
        }
        left++;
      } else {
        right++;
        if (right >= s.length()) {
          break;
        }
        char k = s.charAt(right);
        seen.put(k, seen.getOrDefault(k, 0) + 1);
      }
    }
    return min;
  }

  static int kthLargestInBST(Tree<Integer> t, int k) {
    return countingDfs(t, new int[] {k}).get();
  }

  private static Optional<Integer> countingDfs(Tree<Integer> t, int[] k) {
    if (t.left != null) {
      final Optional<Integer> f = countingDfs(t.left, k);
      if (f.isPresent()) return f;
    }
    k[0]--;
    if (k[0] == 0) return Optional.of(t.value);
    if (t.right != null) {
      final Optional<Integer> f = countingDfs(t.right, k);
      if (f.isPresent()) return f;
    }
    return Optional.empty();
  }

  static boolean isSubtree(Tree<Integer> t1, Tree<Integer> t2) {
    if (t1 == null) return t2 == null;
    if (t2 == null) return true;
    if (Objects.equals(t1.value, t2.value) && areEqual(t1, t2)) return true;
    if (t1.left != null) {
      if (isSubtree(t1.left, t2)) return true;
    }
    if (t1.right != null) {
      if (isSubtree(t1.right, t2)) return true;
    }
    return false;
  }

  private static boolean areEqual(Tree<Integer> t1, Tree<Integer> t2) {
    if (!Objects.equals(t1.value, t2.value)) return false;
    if (t1.left == null && t2.left != null) return false;
    if (t1.left != null && t2.left == null) return false;
    if (t1.right == null && t2.right != null) return false;
    if (t1.right != null && t2.right == null) return false;
    if (t1.left != null && t2.left != null && !areEqual(t1.left, t2.left)) return false;
    if (t1.right != null && t2.right != null && !areEqual(t1.right, t2.right)) return false;
    return true;
  }

  static Tree<Integer> restoreBinaryTree(int[] inorder, int[] preorder) {
    if (inorder.length == 0) return null;
    Tree<Integer> root = new Tree<>(preorder[0]);
    if (inorder.length == 1) return root;
    int inroot = -1;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == root.value) {
        inroot = i;
        break;
      }
    }
    root.left =
        restoreBinaryTree(
            Arrays.copyOfRange(inorder, 0, inroot), Arrays.copyOfRange(preorder, 1, inroot + 1));

    if (inroot < inorder.length - 1) {
      root.right =
          restoreBinaryTree(
              Arrays.copyOfRange(inorder, inroot + 1, inorder.length),
              Arrays.copyOfRange(preorder, inroot + 1, inorder.length));
    }
    return root;
  }

  static String[] findSubstrings(String[] words, String[] parts) {
    Map<Character, List<String>> partsByFirst = new HashMap<>();
    for (String part : parts) {
      final char c = part.charAt(0);
      final List<String> p = partsByFirst.getOrDefault(c, new ArrayList<>());
      p.add(part);
      partsByFirst.put(c, p);
    }
    for (Character key : partsByFirst.keySet()) {
      partsByFirst.get(key).sort((a, b) -> -Integer.compare(a.length(), b.length()));
    }
    List<String> res = new ArrayList<>();
    for (String word : words) {
      int maxLength = -1;
      String cand = word;
      for (int i = 0; i < word.length(); i++) {
        final List<String> curr = new ArrayList<>();
        for (String s : partsByFirst.getOrDefault(word.charAt(i), new ArrayList<>())) {
          if (s.length() > maxLength) {
            curr.add(s);
          } else {
            break;
          }

        }
        for (String part : curr) {
          boolean match = true;
          if (i + part.length() > word.length()
              || word.substring(i, i + part.length()).hashCode() != part.hashCode()) {
            continue;
          }
          for (int j = 0; j < part.length(); j++) {
            if (i + j >= word.length() || word.charAt(i + j) != part.charAt(j)) {
              match = false;
              break;
            }
          }
          if (match && part.length() > maxLength) {
            maxLength = part.length();
            cand = word.replaceFirst(part, "[" + part + "]");
            break;
          }
        }
      }
      res.add(cand);
    }
    return res.toArray(words);
  }
}
