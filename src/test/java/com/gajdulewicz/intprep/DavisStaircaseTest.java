package com.gajdulewicz.intprep;

import com.google.common.collect.Maps;
import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.DavisStaircase.ways;

/** Created by gajduler on 7/23/17. */
public class DavisStaircaseTest {

  @Test
  public void test1() {
    Truth.assertThat(ways(1, Maps.newHashMap())).isEqualTo(1);
  }

  @Test
  public void test2() {
    Truth.assertThat(ways(2, Maps.newHashMap())).isEqualTo(2);
  }

  @Test
  public void test3() {
    Truth.assertThat(ways(3, Maps.newHashMap())).isEqualTo(4);
  }
}
