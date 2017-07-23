package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.List;

import static com.gajdulewicz.intprep.DfsRegion.largestRegion;

public class DfsRegionTest {

  @Test
  public void sample() {
    int[][] grid =
        new int[][] {
          new int[] {1, 1, 0, 0},
          new int[] {0, 1, 1, 0},
          new int[] {0, 0, 1, 0},
          new int[] {1, 0, 0, 0},
        };

    Truth.assertThat(largestRegion(grid)).isEqualTo(5);
  }

  @Test
  public void sampleNonSquare() {
    int[][] grid =
        new int[][] {
          new int[] {0, 0, 1, 1},
          new int[] {0, 0, 1, 0},
          new int[] {0, 1, 1, 0},
          new int[] {0, 1, 0, 0},
          new int[] {1, 1, 0, 0},
        };

    Truth.assertThat(largestRegion(grid)).isEqualTo(8);
  }

  @Test
  public void cellNeighbours() {
    final List<DfsRegion.Cell> neighbours = new DfsRegion.Cell(1, 1).neighbours(3, 3);
    Truth.assertThat(neighbours).hasSize(8);
  }

  @Test
  public void cellNeighboursCorner() {
    final List<DfsRegion.Cell> neighbours = new DfsRegion.Cell(0, 0).neighbours(3, 3);
    Truth.assertThat(neighbours).hasSize(3);
  }
}
