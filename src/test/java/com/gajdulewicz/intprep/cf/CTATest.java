package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.CTA.*;

public class CTATest {

  @Test
  public void repeatedDNASequencesTest() {
    Truth.assertThat(repeatedDNASequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"))
        .isEqualTo(new String[] {"AAAAACCCCC", "CCCCCAAAAA"});
  }

  @Test
  public void getSkylineTest() {
    Truth.assertThat(
            getSkyline(
                new double[][] {
                  {1, 4, 4}, {2, 5, 3}, {3, 3, 6}, {5.5, 3, 5}, {10, 2, 2}, {11, 2, 3}
                }))
        .isEqualTo(new double[][] {{1, 4}, {3, 6}, {6, 5}, {8.5, 0}, {10, 2}, {11, 3}, {13, 0}});
  }

  @Test
  public void countInversionsTest() {
    Truth.assertThat(countInversions(new int[] {3, 1, 5, 6, 4})).isEqualTo(3);
    Truth.assertThat(countInversions(new int[] {3, 2, 1})).isEqualTo(3);
  }
}
