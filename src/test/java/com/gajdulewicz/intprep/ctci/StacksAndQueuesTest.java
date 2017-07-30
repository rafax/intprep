package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Lists;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.Stack;

import static com.gajdulewicz.intprep.ctci.StacksAndQueues.sortDescending;

public class StacksAndQueuesTest {
  @Test
  public void hanoiTest() {
    final Stack<Integer> res = StacksAndQueues.hanoi(10);
    for (int i = 1; i <= 10; i++) {
      Truth.assertThat(res.pop()).isEqualTo(i);
    }
  }

  @Test
  public void sortStackTest() {
    final Stack<Integer> in = new Stack<>();
    Lists.newArrayList(7, 10, 5, 12, 8, 3, 1).forEach(in::push);
    final Stack<Integer> res = sortDescending(in);
    Truth.assertThat(Lists.newArrayList(res.iterator()))
        .isEqualTo(Lists.newArrayList(12, 10, 8, 7, 5, 3, 1));
  }
}
