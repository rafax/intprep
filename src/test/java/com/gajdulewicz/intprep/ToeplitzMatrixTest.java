package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.ToeplitzMatrix.isToeplitz;

public class ToeplitzMatrixTest {

  @Test
  public void isToeplitzTest() {
    final int[][] m = {{3, 2, 1}, {2, 3, 2}, {1, 2, 3}};
    Truth.assertThat(isToeplitz(m)).isTrue();
    final int[][] not = {{3, 3, 3}, {2, 3, 3}, {100, 3, 3}};
    Truth.assertThat(isToeplitz(not)).isFalse();
  }
}
