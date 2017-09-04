package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.math.BigDecimal;

import static com.gajdulewicz.intprep.cf.Counting.*;

public class CountingTest {

  @Test
  public void stringPermutationsTest() {
    Truth.assertThat(stringPermutations("CBA"))
        .isEqualTo(new String[] {"ABC", "ACB", "BAC", "BCA", "CAB", "CBA"});
  }

  @Test
  public void pressingButtonsTest() {
    Truth.assertThat(pressingButtons("42"))
        .isEqualTo(new String[] {"ga", "gb", "gc", "ha", "hb", "hc", "ia", "ib", "ic"});
  }

  @Test
  public void lettersTest() {
    Truth.assertThat(letters(8)).containsExactly("t", "u", "v");
  }

  @Test
  public void differentPlaylistsTest() {
    Truth.assertThat(differentPlaylists(5, 3, 6)).isEqualTo(480);
    Truth.assertThat(differentPlaylists(40, 31, 13)).isEqualTo(278003520);
    Truth.assertThat(differentPlaylists(431567435, 19891, 791266063)).isEqualTo(448567060);
  }

  @Test
  public void knightOnBoardProbabilityTest() {
    Truth.assertThat(knightOnBoardProbability(8, 8, 2, 4, 4)).isEqualTo(0.875);
  }
}
