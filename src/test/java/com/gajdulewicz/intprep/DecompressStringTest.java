package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.io.IOException;

public class DecompressStringTest {

  @Test
  public void noChange() {
    Truth.assertThat(DecompressString.decompress("abc")).isEqualTo("abc");
  }

  @Test
  public void simple() {
    Truth.assertThat(DecompressString.decompress("abc3[xy]")).isEqualTo("abcxyxyxy");
  }

  @Test
  public void doubleTest() {
    Truth.assertThat(DecompressString.decompress("3[a]2[bc]")).isEqualTo("aaabcbc");
  }

  @Test
  public void nestedTest() throws IOException {

    Lists.newArrayList(
            new Case<>("3[a2[c]]", "accaccacc"), new Case<>("2[abc]3[cd]ef", "abcabccdcdcdef"))
        .forEach(c -> Truth.assertThat(DecompressString.decompress(c.in())).isEqualTo(c.out()));
  }
}
