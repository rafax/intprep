package com.gajdulewicz.intprep.ctci;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.ctci.Recursion.gridWays;
import static com.gajdulewicz.intprep.ctci.Recursion.ways;

public class RecursionTest {

  @Test
  public void stepsTest() {
    Truth.assertThat(ways(4, new long[5])).isEqualTo(7);
    Truth.assertThat(ways(35, new long[36])).isEqualTo(1132436852);
  }

  @Test
  public void gridWaysTest() {
    Truth.assertThat(gridWays(new boolean[][] {{true, true}, {true, true}})).isEqualTo(2);
    Truth.assertThat(
            gridWays(
                new boolean[][] {{true, true, true}, {false, false, true}, {true, true, true}}))
        .isEqualTo(1);
    Truth.assertThat(
            gridWays(new boolean[][] {{true, true, true}, {true, true, true}, {true, true, true}}))
        .isEqualTo(6);
  }
}
