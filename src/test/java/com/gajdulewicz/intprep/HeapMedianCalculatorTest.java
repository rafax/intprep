package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

/** Created by gajduler on 7/21/17. */
public class HeapMedianCalculatorTest {
  @Test
  public void putBasic() {
    StreamingMedian.HeapMedianCalculator hmc = new StreamingMedian.HeapMedianCalculator();
    Truth.assertThat(hmc.put(12)).isEqualTo(12.0);
    Truth.assertThat(hmc.put(4)).isEqualTo(8.0);
    Truth.assertThat(hmc.put(5)).isEqualTo(5.0);
    Truth.assertThat(hmc.put(3)).isEqualTo(4.5);
    Truth.assertThat(hmc.put(8)).isEqualTo(5.0);
    Truth.assertThat(hmc.put(7)).isEqualTo(6.0);
  }

  @Test
  public void putIncreasing() {
    StreamingMedian.HeapMedianCalculator hmc = new StreamingMedian.HeapMedianCalculator();
    Truth.assertThat(hmc.put(1)).isEqualTo(1.0);
    Truth.assertThat(hmc.put(2)).isEqualTo(1.5);
    Truth.assertThat(hmc.put(3)).isEqualTo(2.0);
    Truth.assertThat(hmc.put(4)).isEqualTo(2.5);
    Truth.assertThat(hmc.put(5)).isEqualTo(3.0);
    Truth.assertThat(hmc.put(6)).isEqualTo(3.5);
    Truth.assertThat(hmc.put(7)).isEqualTo(4.0);
    Truth.assertThat(hmc.put(8)).isEqualTo(4.5);
    Truth.assertThat(hmc.put(9)).isEqualTo(5.0);
    Truth.assertThat(hmc.put(10)).isEqualTo(5.5);
  }
}
