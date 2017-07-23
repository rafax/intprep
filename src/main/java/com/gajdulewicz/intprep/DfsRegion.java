package com.gajdulewicz.intprep;

import com.google.common.collect.Sets;

import java.util.*;

public class DfsRegion {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int grid[][] = new int[n][m];
    for (int grid_i = 0; grid_i < n; grid_i++) {
      for (int grid_j = 0; grid_j < m; grid_j++) {
        grid[grid_i][grid_j] = in.nextInt();
      }
    }
    System.out.println(largestRegion(grid));
  }

  public static int largestRegion(int[][] grid) {
    SortedSet<Cell> toMark = buildToMark(grid);
    Map<Integer, Set<Cell>> regionCells = new HashMap<>();
    int region = 0;
    while (!toMark.isEmpty()) {
      final Cell start = toMark.first();
      toMark.remove(start);
      if (grid[start.row][start.col] == 0) {
        continue;
      }
      regionCells.put(++region, Sets.newHashSet(start));
      markRegion(start, toMark, grid, region, regionCells);
    }
    return regionCells.values().stream().mapToInt(Set::size).max().orElseGet(() -> -1);
  }

  private static void markRegion(
      Cell start,
      SortedSet<Cell> toMark,
      int[][] grid,
      int region,
      Map<Integer, Set<Cell>> regionCells) {
    if (grid[start.row][start.col] == 0) {
      return;
    }
    final List<Cell> neighbours = start.neighbours(grid[0].length, grid.length);
    for (Cell neighbour : neighbours) {
      if (!toMark.contains(neighbour)) {
        continue;
      }
      if (grid[neighbour.row][neighbour.col] == 1) {
        toMark.remove(neighbour);
        regionCells.get(region).add(neighbour);
        markRegion(neighbour, toMark, grid, region, regionCells);
      }
    }
  }

  private static SortedSet<Cell> buildToMark(int[][] grid) {
    SortedSet<Cell> toMark = new TreeSet<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        toMark.add(new Cell(j, i));
      }
    }
    return toMark;
  }

  public static class Cell implements Comparable<Cell> {
    public final int col;
    public final int row;

    public Cell(int col, int row) {
      this.col = col;
      this.row = row;
    }

    public List<Cell> neighbours(int width, int height) {
      List<Cell> res = new ArrayList<>();
      if (col > 0) {
        res.add(new Cell(col - 1, row));
        if (row > 0) {
          res.add(new Cell(col - 1, row - 1));
        }
        if (row < height - 1) {
          res.add(new Cell(col - 1, row + 1));
        }
      }
      if (row < height - 1) {
        res.add(new Cell(col, row + 1));
        if (col < width - 1) {
          res.add(new Cell(col + 1, row + 1));
        }
      }
      if (col < width - 1) {
        res.add(new Cell(col + 1, row));
        if (row > 0) {
          res.add(new Cell(col + 1, row - 1));
        }
      }
      if (row > 0) {
        res.add(new Cell(col, row - 1));
      }
      return res;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Cell cell = (Cell) o;

      if (col != cell.col) return false;
      return row == cell.row;
    }

    @Override
    public int hashCode() {
      int result = col;
      result = 31 * result + row;
      return result;
    }

    @Override
    public String toString() {
      return String.format("[c:%s,r:%s]", col, row);
    }

    @Override
    public int compareTo(Cell o) {
      final int colComp = Integer.compare(col, o.col);
      return colComp != 0 ? colComp : Integer.compare(row, o.row);
    }
  }
}
