package com.gajdulewicz.intprep.cf;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    Map<Long, List<String>> pbh = new HashMap<>();
    for (String part : parts) {
      final long h = hash(part);
      final List<String> p = pbh.getOrDefault(h, new ArrayList<>());
      p.add(part);
      pbh.put(h, p);
    }
    List<String> res = new ArrayList<>();
    for (String word : words) {
      res.add(findReplacement(pbh, word));
    }
    return res.toArray(words);
  }

  private static String findReplacement(Map<Long, List<String>> pbh, String word) {
    for (int sl = 5; sl >= 1; sl--) {
      for (int i = 0; i < word.length(); i++) {
        if (i + sl > word.length()) break;
        final String sub = word.substring(i, i + sl);
        final List<String> parts = pbh.get(hash(sub));
        if (parts == null) continue;
        for (String part : parts) {
          if (part != null && Objects.equals(part, sub))
            return word.replaceFirst(part, "[" + part + "]");
        }
      }
    }
    return word;
  }

  static long hash(String s) {
    long h = 0;
    if (s.length() > 0) {
      char val[] = s.toCharArray();
      h = 5381;
      for (int i = 0; i < val.length; i++) {
        h = 33 * h + val[i];
      }
    }
    return h;
  }

  static long digitTreeSum(Tree<Integer> t) {
    return digitTreeSum(t, new ArrayList<>());
  }

  private static long digitTreeSum(Tree<Integer> t, List<String> curr) {
    if (t == null) return 0;
    if (t.left == null && t.right == null) {
      return Long.parseLong(String.join("", curr) + t.value, 10);
    }
    return digitTreeSum(t.left, copyOf(curr, t.value))
        + digitTreeSum(t.right, copyOf(curr, t.value));
  }

  public static ArrayList<String> copyOf(List<String> s, Integer n) {
    final ArrayList<String> nu = new ArrayList<>(s);
    nu.add(Integer.toString(n));
    return nu;
  }

  static int longestPath(String fileSystem) {
    final String[] elems = fileSystem.split("\\R");
    int max = 0;
    Stack<String> nested = new Stack<>();
    for (String elem : elems) {

      int i = count(elem);
      String trimmed = elem.replace("\t", "");
      while (!nested.isEmpty() && i <= nested.size() - 1) {
        nested.pop();
      }
      if (elem.contains(".")) {
        final String path = (nested.isEmpty() ? "" : (String.join("/", nested) + "/")) + trimmed;
        int c = path.length();
        if (c > max) {
          max = c;
        }
      } else {
        nested.add(trimmed);
      }
    }

    return max;
  }

  private static int count(String elem) {
    for (int i = 0; i < elem.length(); i++) {
      if (!Objects.equals(Character.toString(elem.charAt(i)), "\t")) return i;
    }
    return elem.length();
  }

  static int[] graphDistances(int[][] g, int s) {
    int[] dist = new int[g.length];
    List<Integer> toVisit = new ArrayList<>();
    for (int i = 0; i < g.length; i++) {
      toVisit.add(i);
      dist[i] = Integer.MAX_VALUE;
    }
    dist[s] = 0;
    while (!toVisit.isEmpty()) {
      int u = minDist(toVisit, dist);
      toVisit.remove(Integer.valueOf(u));
      for (int v = 0; v < g[u].length; v++) {
        if (g[u][v] == -1) continue;
        int alt = dist[u] + g[u][v];
        if (alt < dist[v]) {
          dist[v] = alt;
        }
      }
    }
    return dist;
  }

  private static int minDist(List<Integer> toVisit, int[] dist) {
    int min = Integer.MAX_VALUE;
    int res = -1;
    for (Integer i : toVisit) {
      if (dist[i] < min) {
        min = dist[i];
        res = i;
      }
    }
    return res;
  }

  static int[] mostFrequentSum(Tree<Integer> t) {
    if (t == null || t.value == null) return new int[] {};

    Map<Integer, Integer> sumCount = new HashMap<>();
    mfs(t, sumCount);
    final List<Map.Entry<Integer, Integer>> sorted =
        sumCount
            .entrySet()
            .stream()
            .sorted(Comparator.comparingInt(a -> -a.getValue()))
            .collect(Collectors.toList());
    TreeMap<Integer, List<Integer>> elemsByCount = new TreeMap<>();
    for (Map.Entry<Integer, Integer> entry : sorted) {
      final List<Integer> v = elemsByCount.getOrDefault(entry.getValue(), new ArrayList<>());
      v.add(entry.getKey());
      elemsByCount.put(entry.getValue(), v);
    }
    return elemsByCount.lastEntry().getValue().stream().mapToInt(a -> a).sorted().toArray();
  }

  private static int mfs(Tree<Integer> t, Map<Integer, Integer> sumCount) {
    if (t == null || t.value == null) return 0;
    int ltree = mfs(t.left, sumCount);
    int rtree = mfs(t.right, sumCount);
    int stree = t.value + ltree + rtree;
    sumCount.put(stree, sumCount.getOrDefault(stree, 0) + 1);
    return stree;
  }

  static int networkWires(int nodes, int[][] wires) {
    PriorityQueue<int[]> e = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    e.addAll(Arrays.asList(wires));
    final PartitionedSet p = new PartitionedSet(nodes);
    List<Integer> used = new ArrayList<>();
    while (!e.isEmpty() && p.partitions().size() != 1) {
      final int[] edge = e.poll();
      if (p.find(edge[0]) != p.find(edge[1])) {
        System.out.println("Unioning by " + Arrays.toString(edge));
        p.union(edge[0], edge[1]);
        used.add(edge[2]);
        System.out.println("\t" + p.partitions());
      }
    }
    System.out.println(p.partitions());
    return used.stream().mapToInt(a -> a).sum();
  }

  public static class PartitionedSet {
    final Elem[] elems;

    public PartitionedSet(int n) {
      this.elems = new Elem[n];
      for (int i = 0; i < n; i++) {
        elems[i] = new Elem(i, i);
      }
    }

    Set<Set<Integer>> partitions() {
      Map<Integer, Set<Integer>> p = new HashMap<>();
      for (Elem elem : elems) {
        final Set<Integer> v = p.getOrDefault(elem.parent, new HashSet<>());
        v.add(elem.value);
        p.put(elem.parent, v);
      }
      return new HashSet<>(p.values());
    }

    int find(int e) {
      return elems[e].parent;
    }

    void union(int a, int b) {
      Elem op, np;
      if (elems[elems[a].parent].rank >= elems[elems[b].parent].rank) {
        op = elems[elems[b].parent];
        np = elems[elems[a].parent];
      } else {
        op = elems[elems[a].parent];
        np = elems[elems[b].parent];
      }
      for (Elem elem : elems) {
        if (elem.parent == op.value) {
          elem.parent = np.value;
        }
      }
    }

    static class Elem {
      final int value;
      int parent;
      int rank;

      Elem(int value, int parent) {
        this.value = value;
        this.parent = parent;
        this.rank = 0;
      }
    }
  }
}
