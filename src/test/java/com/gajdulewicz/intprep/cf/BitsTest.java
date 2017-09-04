package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Bits.*;

public class BitsTest {

  @Test
  public void insertBitsTest() {
    Truth.assertThat(insertBits(1024, 1, 6, 17)).isEqualTo(1058);
  }

  @Test
  public void number1Test() {
    Truth.assertThat(numberOf1Bits(13)).isEqualTo(3);
  }

  @Test
  public void bitsToFloatTest() {
    Truth.assertThat(bitsToFloat(65539)).isEqualTo(1.3);
    Truth.assertThat(bitsToFloat(1073741824)).isEqualTo(16384.);

  }
}
