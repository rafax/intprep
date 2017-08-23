package com.gajdulewicz.intprep;


import com.google.common.collect.Lists;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.Random;

@State(Scope.Thread)
public class NestedBoxesBenchmark {
  NestedBoxes nestedBoxes;
  @Param({"10", "100", "1000"})
  private int length;
  private List<Integer> values;

  public static void main(String[] args) throws Exception {
    Options opt = new OptionsBuilder()
      .include(NestedBoxesBenchmark.class.getSimpleName())
      .warmupIterations(10)
      .measurementIterations(5)
      .threads(1)
      .forks(1)
      .build();

    new Runner(opt).run();
  }

  @Setup
  public void setUp() throws Exception {
    values = Lists.newArrayList();
    Random r = new Random(123213);
    for (int i = 0; i < length; i++) {
      values.add(r.nextInt());
    }
    nestedBoxes = new NestedBoxes(values);
  }

  @Benchmark
  public int timeStandard() {
    int value = nestedBoxes.maxContainedBoxes();
    return value;
  }

  @Benchmark
  public int timeGreedy() {
    int value = nestedBoxes.maxContainedBoxesGreedy();
    return value;
  }
}
