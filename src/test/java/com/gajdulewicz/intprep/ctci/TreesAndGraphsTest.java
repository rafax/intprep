package com.gajdulewicz.intprep.ctci;

import com.gajdulewicz.intprep.ds.BinaryNode;
import com.google.common.collect.Lists;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.truth.Truth;
import org.junit.Test;

import java.util.Random;

import static com.gajdulewicz.intprep.ctci.TreesAndGraphs.*;

public class TreesAndGraphsTest {

  @Test
  public void isBalancedPositiveTest() {
    final BinaryNode<Integer> root = new BinaryNode<>(1);
    final BinaryNode<Integer> l = new BinaryNode<>(1);
    final BinaryNode<Integer> ll = new BinaryNode<>(1);
    final BinaryNode<Integer> lr = new BinaryNode<>(1);
    final BinaryNode<Integer> r = new BinaryNode<>(1);
    final BinaryNode<Integer> rl = new BinaryNode<>(1);
    final BinaryNode<Integer> rr = new BinaryNode<>(1);
    root.setLeft(l);
    l.setLeft(ll);
    l.setRight(lr);
    root.setRight(r);
    r.setRight(rr);
    r.setLeft(rl);
    Truth.assertThat(isBalanced(root)).isTrue();
  }

  @Test
  public void isBalancedNegativeSmallTest() {
    final BinaryNode<Integer> root = new BinaryNode<>(1);
    final BinaryNode<Integer> l = new BinaryNode<>(1);
    final BinaryNode<Integer> ll = new BinaryNode<>(1);
    root.setLeft(l);
    l.setLeft(ll);
    Truth.assertThat(isBalanced(root)).isFalse();
  }

  @Test
  public void isBalancedNegative() {
    final BinaryNode<Integer> root = new BinaryNode<>(1);
    final BinaryNode<Integer> l = new BinaryNode<>(1);
    final BinaryNode<Integer> ll = new BinaryNode<>(1);
    final BinaryNode<Integer> lll = new BinaryNode<>(1);
    final BinaryNode<Integer> r = new BinaryNode<>(1);
    final BinaryNode<Integer> rl = new BinaryNode<>(1);
    final BinaryNode<Integer> rr = new BinaryNode<>(1);
    root.setLeft(l);
    l.setLeft(ll);
    ll.setLeft(lll);
    root.setRight(r);
    r.setRight(rr);
    r.setLeft(rl);
    Truth.assertThat(isBalanced(root)).isFalse();
  }

  @Test
  public void hasPathPositive() {
    Random r = new Random();
    final MutableGraph<Integer> g = GraphBuilder.directed().allowsSelfLoops(false).build();
    final int count = 10000;
    for (int i = 0; i < count; i++) {
      g.addNode(i);
    }
    for (int i = 0; i < count * 10; ) {
      int from = r.nextInt(count);
      int to = r.nextInt(count);
      if (from != to) {
        g.putEdge(from, to);
        i++;
      }
    }
    g.putEdge(1, 10);
    g.putEdge(10, 30);
    g.putEdge(30, 66);
    Truth.assertThat(hasPath(g, 1, 66)).isTrue();
  }

  @Test
  public void hasPathNegative() {
    Random r = new Random();
    final MutableGraph<Integer> g = GraphBuilder.directed().allowsSelfLoops(false).build();
    final int count = 10000;
    for (int i = 0; i < count; i++) {
      g.addNode(i);
    }
    for (int i = 0; i < count * 10; ) {
      int from = r.nextInt(count);
      int to = r.nextInt(count);
      if (from != to) {
        g.putEdge(from, to);
        i++;
      }
    }
    g.addNode(count + 1);
    Truth.assertThat(hasPath(g, 1, count + 1)).isFalse();
  }

  @Test
  public void buildBSTSmallTest() {
    Truth.assertThat(buildBST(new int[] {1, 2, 3}).levelOrder())
        .isEqualTo(Lists.newArrayList(Lists.newArrayList(2), Lists.newArrayList(1, 3)));
  }

  @Test
  public void buildBSTEvenLengthTest() {
    final int[] ints = new int[] {1, 2, 3, 4};
    Truth.assertThat(buildBST(ints).levelOrder())
        .isEqualTo(
            Lists.newArrayList(
                Lists.newArrayList(2), Lists.newArrayList(1, 3), Lists.newArrayList(4)));
  }

  @Test
  public void buildBSTOddLengthFullTreeTest() {
    final int[] ints = new int[] {1, 2, 3, 4, 5, 6, 7};
    Truth.assertThat(buildBST(ints).levelOrder())
        .isEqualTo(
            Lists.newArrayList(
                Lists.newArrayList(4), Lists.newArrayList(2, 6), Lists.newArrayList(1, 3, 5, 7)));
  }

  @Test
  public void levelOrderTest() {
    final BinaryNode<Integer> node = buildBST(new int[] {1, 2, 3, 4, 5, 6, 7});
    Truth.assertThat(levelOrder(node))
        .isEqualTo(
            Lists.newArrayList(
                Lists.newArrayList(4), Lists.newArrayList(2, 6), Lists.newArrayList(1, 3, 5, 7)));
  }
}
