package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.NSubstring.longestNSubstring;

public class NSubstringTest {

  @Test
  public void simpleTest() {
    Truth.assertThat(longestNSubstring("abcbc", 2)).isEqualTo("bcbc");
    Truth.assertThat(longestNSubstring("abcbc", 3)).isEqualTo("abcbc");
  }

    @Test
    public void longerTest() {
        Truth.assertThat(longestNSubstring("dabbcccabcd", 2)).isEqualTo("bbccc");
        Truth.assertThat(longestNSubstring("dabbcccabcd", 3)).isEqualTo("abbcccabc");
    }
}
