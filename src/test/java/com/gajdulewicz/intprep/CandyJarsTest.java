package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.CandyJars.averageCandies;

public class CandyJarsTest {

  @Test
  public void averageCandiesTest() {
    Truth.assertThat(averageCandies(new int[][] {{1, 2, 100}, {2, 5, 100}, {3, 4, 100}}, 5))
        .isEqualTo(160);
  }
}
