package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.ZigZag.zigZagIterate;

public class ZigZagTest {
  @Test
  public void zigZagIterateTest() {
    Truth.assertThat(
            zigZagIterate(
                new int[][] {{1, 3, 4, 10}, {2, 5, 9, 11}, {6, 8, 12, 15}, {7, 13, 14, 16}}))
        .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        .inOrder();
    Truth.assertThat(zigZagIterate(new int[][] {{1, 3, 4}, {2, 5, 8}, {6, 7, 9}}))
        .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9)
        .inOrder();
    Truth.assertThat(zigZagIterate(new int[][] {{1, 3}, {2, 4}}))
        .containsExactly(1, 2, 3, 4)
        .inOrder();
    Truth.assertThat(zigZagIterate(new int[][] {{1}})).containsExactly(1).inOrder();
  }
}
