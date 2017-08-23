package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.MaxXor.maxXor;

public class MaxXorTest {
  @Test
  public void maxXorTest() {
    Truth.assertThat(maxXor(10, 15)).isEqualTo(7);
  }
}
