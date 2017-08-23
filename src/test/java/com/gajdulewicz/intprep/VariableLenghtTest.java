package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

public class VariableLenghtTest {

  @Test
  public void testIsSingleRune() {
    for (byte i = 0; i < 127; i++) {
      Truth.assertThat(isSingleRune(i)).isTrue();
    }
    for (byte i = Byte.MIN_VALUE; i < 0; i++) {
      Truth.assertWithMessage(i + ": " + Integer.toBinaryString(i)).that(isSingleRune(i)).isFalse();
    }
  }

  public boolean isSingleRune(byte input) {
    return (input & 1<<7) == 0;
  }
}
