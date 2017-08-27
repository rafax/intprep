package com.gajdulewicz.intprep.cf;

import com.google.common.collect.Sets;

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
        Map<Integer, Set<Integer>> conflicts = new HashMap<>();
        Map<Integer, Set<String>> classAnimals = new HashMap<>();
        for (int i = 0; i < classes.length; i++) {
            classAnimals.put(i, new HashSet<>(Arrays.stream(classes[i]).collect(Collectors.toList())));
        }
        for (int i = 0; i < classes.length; i++) {
            final HashSet<Integer> icon = new HashSet<>();
            for (int j = 0; j < classes.length; j++) {
                if (i != j && !canComeTogether(classAnimals.get(i), classAnimals.get(j))) {
                    icon.add(j);
                }
            }
            conflicts.put(i, icon);
        }
        for (int k = 1; k <= 5; k++) {
            if (canKColor(0, k, new int[classes.length], conflicts))
                return k;
        }
        return -1;
    }

    static boolean canKColor(int i, int k, int[] c, Map<Integer, Set<Integer>> conflicts) {
        if (i == c.length) {
            return true;
        }
        for (int j = 1; j <= k; j++) {
            boolean ok = true;
            for (Integer x : conflicts.get(i)) {
                if (c[x] == j) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                c[i] = j;
                if (canKColor(i + 1, k, c, conflicts)) return true;
            }
        }
        return false;
    }


    static boolean canComeTogether(Set<String> a, Set<String> b) {
        final HashSet<String> x = new HashSet<>(a);
        x.retainAll(b);
        return x.isEmpty();
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
