package com.gajdulewicz.intprep.cf;

import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Graphs {

  static boolean hasDeadlock(int[][] connections) {
    for (int i = 0; i < connections.length; i++) {
      Set<Integer> path = new HashSet<>();
      path.add(i);
      final boolean has = hasDeadlock(connections, path, i);
      if (has) return true;
    }
    return false;
  }

  private static boolean hasDeadlock(int[][] connections, Set<Integer> path, int curr) {
    for (int i : connections[curr]) {
      if (path.contains(i)) {
        return true;
      }
      path.add(i);
      final boolean has = hasDeadlock(connections, path, i);
      if (has) return true;
      path.remove(i);
    }
    return false;
  }

  static int singlePointOfFailure(int[][] connections) {
    boolean[][] twoConnected = new boolean[connections.length][connections.length];
    InsertOrderedSet<Integer> path = new InsertOrderedSet<>();
    dfs(0, path, null, twoConnected, connections);
    int spofs = 0;
    for (int i = 0; i < connections.length; i++) {
      for (int j = 0; j < connections.length; j++) {
        if (i != j && connections[i][j] == 1 && !twoConnected[i][j]) {
          spofs++;
        }
      }
    }
//    Arrays.stream(twoConnected).forEach(t -> System.out.println(Arrays.toString(t)));
    return spofs / 2;
  }

  private static void dfs(int n, InsertOrderedSet<Integer> path, Integer parent, boolean[][] twoConnected, int[][] connections) {
    path.addLast(n);
//    System.out.println(String.format("n: %s path: %s parent: %s", n, path, parent));
    for (int i = 0; i < connections[n].length; i++) {
      if (connections[n][i] == 1 && !twoConnected[n][i]) {
        if (path.contains(i)) {
          if (parent != null && parent != i) {
            List<Integer> twoConnComp = path.subArray(i);
            for (int j = 0; j < twoConnComp.size(); j++) {
              for (int k = j + 1; k < twoConnComp.size(); k++) {
                int a = twoConnComp.get(j);
                int b = twoConnComp.get(k);
                twoConnected[a][b] = true;
                twoConnected[b][a] = true;
              }
            }
          }
        } else {
          dfs(i, path, n, twoConnected, connections);
        }
      }
    }
    path.removeLast();
  }

  static int feedingTime(String[][] classes) {
    boolean[][] canComeTogether = new boolean[classes.length][classes.length];
    for (int i = 0; i < classes.length; i++) {
      Arrays.fill(canComeTogether[i], true);
    }
    Map<Integer, Set<String>> classAnimals = new HashMap<>();
    for (int i = 0; i < classes.length; i++) {
      classAnimals.put(i, new HashSet<>(Arrays.stream(classes[i]).collect(Collectors.toList())));
    }
    for (int i = 0; i < classes.length; i++) {
      for (int j = i + 1; j < classes.length; j++) {
        final Set<String> inter = new HashSet<>(classAnimals.get(i));
        inter.retainAll(classAnimals.get(j));
        if (!inter.isEmpty()) {
          canComeTogether[i][j] = false;
          canComeTogether[j][i] = false;
        }
      }
    }
    Arrays.stream(canComeTogether).forEach(c -> System.out.println(Arrays.toString(c)));
    Set<Integer> toProcess = new HashSet<>();
    for (int i = 0; i < classes.length; i++) {
      toProcess.add(i);
    }
    int groups = 0;
    while (!toProcess.isEmpty()) {
      final Integer c = toProcess.iterator().next();
      toProcess.remove(c);
      Set<Integer> s = new HashSet<>();
      s.add(c);
      buildGroup(s, canComeTogether);
      for (Integer integer : s) {
        toProcess.remove(integer);
      }
      groups++;

    }
    return groups;
  }

  private static void buildGroup(Set<Integer> s, final boolean[][] canComeTogether) {
    Integer toAdd = null;
    for (Integer integer : s) {
      if (toAdd != null) break;
      for (int i = 0; i < canComeTogether[integer].length; i++) {
        if (!s.contains(i)) {
          final int cand = i;
          if (canComeTogether[integer][cand] && s.stream().allMatch(c -> canComeTogether[c][cand])) {
            toAdd = cand;
            break;
          }
        }
      }
    }
    if (toAdd != null) {
      s.add(toAdd);
      buildGroup(s, canComeTogether);

    }


  }

  static class InsertOrderedSet<T> {

    private final HashMap<T, Integer> elemIndex;
    private final ArrayList<T> order;

    public InsertOrderedSet() {

      order = new ArrayList<>();
      elemIndex = new HashMap<>();
    }

    public void addLast(T elem) {
      elemIndex.put(elem, order.size());
      order.add(order.size(), elem);
    }

    public void removeLast() {
      final T rem = order.remove(order.size() - 1);
      elemIndex.remove(rem);
    }

    public boolean contains(T elem) {
      return elemIndex.containsKey(elem);
    }

    public List<T> subArray(T elem) {
      final Integer eIndex = elemIndex.get(elem);
      return order.subList(eIndex, order.size());
    }
  }

}
