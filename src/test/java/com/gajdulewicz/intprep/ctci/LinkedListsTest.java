package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.gajdulewicz.intprep.ctci.LinkedLists.kthLast;
import static com.gajdulewicz.intprep.ctci.LinkedLists.partition;
import static com.gajdulewicz.intprep.ctci.LinkedLists.removeDuplicates;

public class LinkedListsTest {

  @Test
  public void linkedListIsSane() {
    LinkedLists.Node<Integer> head = new LinkedLists.Node<>(0);
    for (int i = 1; i <= 10; i++) {
      head.add(i);
    }
    Truth.assertThat(Lists.newArrayList(head.elements()))
        .isEqualTo(IntStream.rangeClosed(0, 10).boxed().collect(Collectors.toList()));
  }

  @Test
  public void removeDuplicatesTest() {
    LinkedLists.Node<Integer> head = new LinkedLists.Node<>(0);
    Lists.newArrayList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 0).forEach(head::add);
    removeDuplicates(head);
    Truth.assertThat(Lists.newArrayList(head.elements()))
        .isEqualTo(Lists.newArrayList(0, 1, 2, 3, 4, 5));
  }

  @Test
  public void kthLastTest() {
    LinkedLists.Node<Integer> head = new LinkedLists.Node<>(0);
    Lists.newArrayList(1, 2, 3, 4, 5).forEach(head::add);
    for (int i = 1; i <= 6; i++) {
      Truth.assertThat(kthLast(head, i).elem).isEqualTo(6 - i);
    }
  }

  @Test
  public void partitionTest() {
    LinkedLists.Node<Integer> head = new LinkedLists.Node<>(4);
    Lists.newArrayList(1, 5, 3, 2, 7, 6).forEach(head::add);
    final LinkedLists.Node<Integer> res = partition(head, 4);
    Truth.assertThat(Lists.newArrayList(res.elements()))
        .isEqualTo(Lists.newArrayList(4, 1, 3, 2, 5, 7, 6));
  }
}
