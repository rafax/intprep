package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.MineReveal.reveal;

public class MineRevealTest {
  @Test
  public void smallTest() {
    final boolean[][] grid = new boolean[][] {{false, false}, {false, true}};
    Truth.assertThat(reveal(grid, 0, 0)).isEqualTo(1);
  }

  @Test
  public void mediumTest() {
    final boolean[][] grid =
        new boolean[][] {{false, false, false}, {false, false, false}, {false, false, true}};
    Truth.assertThat(reveal(grid, 0, 0)).isEqualTo(8);
  }

  @Test
  public void revealOneTest() {
    final boolean[][] grid =
      new boolean[][] {{false, false, false}, {false, true, false}, {false, false, false}};
    Truth.assertThat(reveal(grid, 0, 0)).isEqualTo(1);
  }
  @Test
  public void revealMineTest() {
    final boolean[][] grid =
      new boolean[][] {{false, false, false}, {false, true, false}, {false, false, false}};
    Truth.assertThat(reveal(grid, 1, 1)).isEqualTo(0);
  }
}
