package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static com.gajdulewicz.intprep.QueueTwoStacks.process;

/** Created by gajduler on 7/21/17. */
public class QueueTwoStacksTest {
  @Test
  public void enqueue() throws Exception {
    QueueTwoStacks.MyQueue<Integer> q = new QueueTwoStacks.MyQueue<>();
    q.enqueue(1);
    Truth.assertThat(q.peek()).isEqualTo(1);
    q.enqueue(2);
    Truth.assertThat(q.peek()).isEqualTo(1);
    Truth.assertThat(q.dequeue()).isEqualTo(1);
    Truth.assertThat(q.peek()).isEqualTo(2);
    Truth.assertThat(q.dequeue()).isEqualTo(2);
  }

  @Test
  public void testCase2() throws Exception {
    final InputStream input = getClass().getResourceAsStream("/qts/c2.txt");
    final Scanner scanner = new Scanner(getClass().getResourceAsStream("/qts/c2_sol.txt"));
    final List<Integer> processed = process(input);
    int i = 0;
    while (scanner.hasNextLine()) {
      Truth.assertThat(scanner.nextLine()).isEqualTo(processed.get(i++).toString());
    }
  }
}
