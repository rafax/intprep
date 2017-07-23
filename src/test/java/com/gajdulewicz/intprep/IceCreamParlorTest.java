package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.IceCreamParlor.combination;

public class IceCreamParlorTest {

  @Test
  public void sample1() {
    Truth.assertThat(combination(new int[] {1, 4, 5, 3, 2}, 4)).isEqualTo(Lists.newArrayList(1, 4));
  }

  @Test
  public void sample2() {
    Truth.assertThat(combination(new int[] {2, 2, 4, 3}, 4)).isEqualTo(Lists.newArrayList(1, 2));
  }
}
