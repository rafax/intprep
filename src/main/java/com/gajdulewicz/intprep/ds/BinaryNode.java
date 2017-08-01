package com.gajdulewicz.intprep.ds;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Created by gajduler on 4/4/17. */
public class BinaryNode<T extends Comparable<T>> {

  private T value;
  private BinaryNode<T> left, right;

  public BinaryNode(T value) {
    this.value = value;
  }

  public Optional<BinaryNode<T>> getLeft() {
    return Optional.ofNullable(left);
  }

  public void setLeft(BinaryNode<T> left) {
    this.left = left;
  }

  public Optional<BinaryNode<T>> getRight() {
    return Optional.ofNullable(right);
  }

  public void setRight(BinaryNode<T> right) {
    this.right = right;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public int childCount() {
    return (left != null ? 1 : 0) + (right != null ? 1 : 0);
  }

  public List<List<T>> levelOrder() {
    List<List<BinaryNode<T>>> levels = Lists.newArrayList();
    levels.add(Lists.newArrayList(this));
    descend(this.getLeft(), levels, 1);
    descend(this.getRight(), levels, 1);
    return levels
        .stream()
        .map(level -> level.stream().map(BinaryNode::getValue).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private void descend(Optional<BinaryNode<T>> node, List<List<BinaryNode<T>>> levels, int level) {
    if (!node.isPresent()) {
      return;
    }
    if (levels.size() <= level) {
      levels.add(Lists.newArrayList());
    }
    levels.get(level).add(node.get());
    descend(node.get().getLeft(), levels, level + 1);
    descend(node.get().getRight(), levels, level + 1);
  }

  @Override
  public String toString() {
    return "BinaryNode{" +
            "value=" + value +
            ", left=" + left +
            ", right=" + right +
            '}';
  }
}
