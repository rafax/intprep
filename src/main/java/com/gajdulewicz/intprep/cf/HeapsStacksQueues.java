package com.gajdulewicz.intprep.cf;

import com.google.common.collect.Lists;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.paint.Color.color;

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

    static int[] nextLarger(int[] a) {
        int[] res = new int[a.length];
        Stack<Integer> s = new Stack<>();
        int i = a.length - 1;
        while (i >= 0) {
            if (s.isEmpty()) {
                res[i] = -1;
                s.push(a[i]);
                i--;
            } else if (s.peek() > a[i]) {
                res[i] = s.peek();
                s.push(a[i]);
                i--;
            } else {
                s.pop();
            }
        }
        return res;
    }

    static int[] minimumOnStack(String[] operations) {
        Stack<Integer> s = new Stack<>();
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (String op : operations) {
            if (Objects.equals(op, "pop")) {
                final Integer pop = s.pop();
                q.remove(pop);
            } else if (Objects.equals(op, "min")) {
                res.add(q.peek());
            } else {
                String[] el = op.split(" ");
                int n = Integer.parseInt(el[1]);
                s.push(n);
                q.add(n);
            }
        }
        return res.stream().mapToInt(x -> x).toArray();
    }

    static int countClouds(char[][] skyMap) {
        Set<Cell> cells = new HashSet<>();
        int color = 0;
        for (int i = 0; i < skyMap.length; i++) {
            for (int j = 0; j < skyMap[0].length; j++) {
                cells.add(new Cell(i, j));
            }
        }
        while (!cells.isEmpty()) {
            Cell c = cells.iterator().next();
            cells.remove(c);
            if (skyMap[c.row][c.col] == '1') {
                color++;
                Queue<Cell> toColor = new LinkedList<>();
                toColor.addAll(c.neighbours(skyMap.length, skyMap[0].length));
                while (!toColor.isEmpty()) {
                    Cell n = toColor.poll();
                    final boolean contained = cells.remove(n);
                    if (contained) {
                        if (skyMap[n.row][n.col] == '1') {
                            toColor.addAll(n.neighbours(skyMap.length, skyMap[0].length));
                        }
                    }
                }
            }
        }
        return color;
    }

    static class Cell {
        final int row;
        final int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (row != cell.row) return false;
            return col == cell.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        public List<Cell> neighbours(int h, int w) {
            List<Optional<Cell>> c = new ArrayList<>();
            c.add(row < h - 1 ? Optional.of(new Cell(row + 1, col)) : Optional.empty());
            c.add(row > 0 ? Optional.of(new Cell(row - 1, col)) : Optional.empty());
            c.add(col < w - 1 ? Optional.of(new Cell(row, col + 1)) : Optional.empty());
            c.add(col > 0 ? Optional.of(new Cell(row, col - 1)) : Optional.empty());
            return c.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

}



