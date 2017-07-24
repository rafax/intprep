package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.Anagram.numberNeeded;

public class AnagramTest {
  @Test
  public void sample() {
    Truth.assertThat(numberNeeded("cde", "abc")).isEqualTo(4);
  }

  @Test
  public void sampleShrink1() {
    Truth.assertThat(numberNeeded("cde", "c")).isEqualTo(2);
  }

  @Test
  public void sampleShrink2() {
    Truth.assertThat(numberNeeded("c", "cdefg")).isEqualTo(4);
  }
}
