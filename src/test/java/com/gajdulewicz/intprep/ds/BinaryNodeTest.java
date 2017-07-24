package com.gajdulewicz.intprep.ds;

import org.junit.Test;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

/** Created by gajduler on 4/4/17. */
public class BinaryNodeTest {
  @Test
  public void testDefaultChildrenReturnEmpty() {
    final BinaryNode<Integer> n = new BinaryNode<>(1);
    assertThat(n.getLeft()).isEqualTo(Optional.empty());
    assertThat(n.getRight()).isEqualTo(Optional.empty());
  }

  @Test
  public void testChildrenWithValuesReturnPresent() {
    int left = 5, right = 10;
    final BinaryNode<Integer> n = new BinaryNode<>(1);

    n.setLeft(new BinaryNode<>(left));
    n.setRight(new BinaryNode<>(right));

    assertThat(n.getLeft().isPresent()).isTrue();
    n.getLeft().ifPresent(l -> assertThat(l.getValue()).isEqualTo(left));
    assertThat(n.getRight().isPresent()).isTrue();
    n.getRight().ifPresent(r -> assertThat(r.getValue()).isEqualTo(right));
  }
}
