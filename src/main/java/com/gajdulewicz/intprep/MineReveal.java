package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MineReveal {

  public static int reveal(boolean[][] grid, int row, int col) {
    Cell[][] cells = new Cell[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        cells[i][j] = new Cell(i, j, grid[i][j]);
      }
    }
    return reveal(cells, row, col);
  }

  public static int reveal(Cell[][] grid, int row, int col) {
    final Cell cell =
        get(grid, row, col).orElseThrow(() -> new RuntimeException("Invalid position"));
    if (cell.isRevealed() || cell.hasMine) {
      return 0;
    }
    cell.reveal();
    final List<Cell> neighbours = neighbours(grid, cell.row, cell.col);
    int nMines = neighbours.stream().map(c -> c.hasMine ? 1 : 0).mapToInt(c -> c).sum();
    if (nMines > 0) {
      return 1;
    }
    return 1
        + neighbours
            .stream()
            .filter(c -> !c.isRevealed())
            .map(c -> reveal(grid, c.row, c.col))
            .mapToInt(c -> c)
            .sum();
  }

  private static List<Cell> neighbours(Cell[][] grid, int row, int col) {
    return Lists.newArrayList(
            get(grid, row - 1, col),
            get(grid, row - 1, col + 1),
            get(grid, row, col + 1),
            get(grid, row + 1, col + 1),
            get(grid, row + 1, col),
            get(grid, row + 1, col - 1),
            get(grid, row, col - 1),
            get(grid, row - 1, col - 1))
        .stream()
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  private static Optional<Cell> get(Cell[][] grid, int row, int col) {
    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
      return Optional.of(grid[row][col]);
    }
    return Optional.empty();
  }

  public static class Cell {
    private final int row;
    private final int col;
    private final boolean hasMine;
    private boolean revealed;

    public Cell(int row, int col, boolean hasMine) {
      this.row = row;
      this.col = col;
      this.hasMine = hasMine;
    }

    public void reveal() {
      this.revealed = true;
    }

    public boolean isRevealed() {
      return this.revealed;
    }

    @Override
    public String toString() {
      return "Cell{"
          + "row="
          + row
          + ", col="
          + col
          + ", hasMine="
          + hasMine
          + ", revealed="
          + revealed
          + '}';
    }
  }
}
