package com.gajdulewicz.intprep;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnitQuickcheck.class)
public class QueueTwoStacksPropertyTest {

  @Property(trials = 10)
  public void testEnqueueDequeueRandomized(List<Integer> elems) {
    QueueTwoStacks.MyQueue<Integer> q = new QueueTwoStacks.MyQueue<>();
    Queue<Integer> oracle = new LinkedList<>();
    for (Integer elem : elems) {
      q.enqueue(elem);
      oracle.offer(elem);
    }
    while (!oracle.isEmpty()) {
      assertThat(q.peek()).isEqualTo(oracle.peek());
      final Integer ex = oracle.poll();
      final Integer act = q.dequeue();
      assertThat(act).isEqualTo(ex);
    }
  }
}
