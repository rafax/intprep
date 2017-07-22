package com.gajdulewicz.intprep;

import com.google.common.truth.Truth;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gajduler on 7/21/17.
 */
public class MyQueueTest {
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

}