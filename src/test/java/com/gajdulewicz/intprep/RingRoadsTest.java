package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.RingRoads.ringRoads;

public class RingRoadsTest {
  @Test
  public void ringRoadsFirstTest() {
    assertWithinTolerance(
        ringRoads(10, 20, new double[] {180d}, new double[][] {{0d, 0d}, {60d, 300d}}),
        new double[] {104.24778d, 72.83185d});
  }

  @Test
  public void ringRoadsSecondTest() {
    assertWithinTolerance(
        ringRoads(
            5,
            30,
            new double[] {220.28249},
            new double[][] {
              {92.230979, 114.401906},
              {339.48124, 308.256335},
              {235.46681, 183.183047},
              {205.322956, 49.294439},
              {337.458142, 102.005834},
              {218.294861, 238.36292},
              {301.342953, 327.157906},
              {55.648601, 58.345595},
              {180.520251, 80.37913},
              {15.794063, 85.984333}
            }),
        new double[] {
          91.613547,
          81.465051,
          45.750305,
          115.8346,
          97.155017,
          34.640344,
          88.033697,
          124.156977,
          101.723138,
          108.889295
        });
  }

  public static void assertWithinTolerance(double[] actual, double[] expected) {
    Truth.assertThat(actual.length).isEqualTo(expected.length);
    for (int i = 0; i < actual.length; i++) {
      Truth.assertThat(actual[i]).isWithin(0.00001).of(expected[i]);
    }
  }
}
