package com.gajdulewicz.intprep.ds;

import java.util.Optional;

/**
 * Created by gajduler on 4/4/17.
 */
public class BinaryNode<T extends Comparable<T>> {

    private final T value;
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
}
