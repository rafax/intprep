package com.gajdulewicz.intprep;

import java.util.Arrays;
import java.util.function.Supplier;

public class LoadBalancer {

  private Supplier<Float> random;

  public LoadBalancer(Supplier<Float> random) {
    this.random = random;
  }

  public Supplier<Integer> rand(int[] weights) {
    int sum = Arrays.stream(weights).sum();
    return () -> {
      float r = random.get() * sum;
      int s = 0;
      for (int i = 0; i < weights.length - 1; i++) {
        if (r <= s + weights[i]) {
          return i;
        }
        s += weights[i];
      }
      return weights.length - 1;
    };
  }
}
