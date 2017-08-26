package com.gajdulewicz.intprep.cf;

import com.google.common.truth.Truth;
import org.junit.Test;

import static com.gajdulewicz.intprep.cf.Graphs.feedingTime;
import static com.gajdulewicz.intprep.cf.Graphs.singlePointOfFailure;
import static com.gajdulewicz.intprep.cf.HeapsStacksQueues.hasDeadlock;

public class GraphsTest {

  @Test
  public void hasDeadlockTest() {
    Truth.assertThat(hasDeadlock(new int[][]{{1}, {2}, {3, 4}, {4}, {0}})).isTrue();
    Truth.assertThat(hasDeadlock(new int[][]{{1, 2, 3}, {2, 3}, {3}, {}})).isFalse();
  }

  @Test
  public void singlePointOfFailureTest() {
    Truth.assertThat(singlePointOfFailure(new int[][]{{0, 1, 1, 0, 0, 0, 0},
      {1, 0, 1, 0, 0, 0, 0},
      {1, 1, 0, 0, 0, 0, 1},
      {0, 0, 0, 0, 1, 1, 1},
      {0, 0, 0, 1, 0, 1, 0},
      {0, 0, 0, 1, 1, 0, 0},
      {0, 0, 1, 1, 0, 0, 0}})).isEqualTo(2);
    Truth.assertThat(singlePointOfFailure(new int[][]{{0, 1, 1, 1, 0, 0}, {1, 0, 1, 0, 0, 0}, {1, 1, 0, 0, 0, 0}, {1, 0, 0, 0, 1, 1}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}})).isEqualTo(3);
    Truth.assertThat(singlePointOfFailure(new int[][]{{0, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}})).isEqualTo(4);
    Truth.assertThat(singlePointOfFailure(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}})).isEqualTo(0);
  }

  @Test
  public void feedingTimeTest() {
    Truth.assertThat(feedingTime(new String[][]{{"Tiger", "Lima", "Frog"}, {"Tiger", "Zebra", "Lion"}, {"Tiger", "Rabbit"}, {"Emu", "Zebra", "Rabbit"}})).isEqualTo(3);
    Truth.assertThat(feedingTime(new String[][]{{"Coati", "Ram"},
      {"Chameleon", "Buffalo", "Coati"},
      {"Elk", "Coyote", "Jerboa"},
      {"Coyote", "Elk"},
      {"Gorilla", "Chameleon"},
      {"Fawn", "Alpaca", "Coyote"},
      {"Raccoon", "Bear", "Coyote", "Walrus"},
      {"ocelot", "Coyote", "Giraffe"}})).isEqualTo(5);


  }

}