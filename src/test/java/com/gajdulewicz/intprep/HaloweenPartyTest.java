package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

public class HaloweenPartyTest {

  @Test
  public void haloweenPartyTest() {
    Truth.assertThat(HaloweenParty.pieces(5)).isEqualTo(6);
    Truth.assertThat(HaloweenParty.pieces(6)).isEqualTo(9);
    Truth.assertThat(HaloweenParty.pieces(7)).isEqualTo(12);
    Truth.assertThat(HaloweenParty.pieces(8)).isEqualTo(16);
  }
}
