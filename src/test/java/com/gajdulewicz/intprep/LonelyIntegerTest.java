package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.LonelyInteger.lonelyinteger;

public class LonelyIntegerTest {
  @Test
  public void lonelyintegerTest() {
    Truth.assertThat(lonelyinteger(new int[] {1, 2, 3, 2, 1})).isEqualTo(3);
  }
}
