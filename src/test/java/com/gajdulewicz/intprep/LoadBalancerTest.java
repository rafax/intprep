package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.function.Supplier;

public class LoadBalancerTest {

  @Test
  public void randTest() {
    final Supplier<Integer> zero = new LoadBalancer(() -> 0.1f).rand(new int[] {2, 0, 3});
    for (int i = 0; i < 10; i++) {
      Truth.assertThat(zero.get()).isEqualTo(0);
    }
    final Supplier<Integer> two = new LoadBalancer(() -> 0.6f).rand(new int[] {2, 0, 3});
    for (int i = 0; i < 10; i++) {
      Truth.assertThat(two.get()).isEqualTo(2);
    }
  }
}
