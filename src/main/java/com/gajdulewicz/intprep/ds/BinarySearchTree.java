package com.gajdulewicz.intprep.ds;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by gajduler on 4/4/17.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

    public void insert(T value) {
        final BinaryNode<T> node = new BinaryNode<>(value);
        if (root == null) {
            root = node;
            return;
        }
        BinaryNode<T> curr = root;
        while (true) {
            if (value.compareTo(curr.getValue()) == 0) {
                return;
            }
            if (value.compareTo(curr.getValue()) < 0) {
                if (!curr.getLeft().isPresent()) {
                    curr.setLeft(node);
                    return;
                }
                curr = curr.getLeft().get();
            } else {
                if (!curr.getRight().isPresent()) {
                    curr.setRight(node);
                    return;
                }
                curr = curr.getRight().get();
            }
        }
    }

    public boolean delete(T value) {
        final NodeWithParent<T> nodeWithParent = find(value);
        if (nodeWithParent == null) {
            return false;
        }
        if (nodeWithParent.parent == null) {
            root = null;
            return true;
        }
        if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
            nodeWithParent.parent.setLeft(null);
        } else {
            nodeWithParent.parent.setRight(null);
        }
        return true;
    }

    public List<List<T>> levelOrder() {
        List<List<BinaryNode<T>>> levels = Lists.newArrayList();
        levels.add(Lists.newArrayList(root));
        descend(root.getLeft(), levels, 1);
        descend(root.getRight(), levels, 1);
        return levels.stream().map(level -> level.stream().map(BinaryNode::getValue).collect(Collectors.toList())).collect(Collectors.toList());
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

    public boolean contains(T value) {
        return find(value) != null;
    }

    private NodeWithParent<T> find(T value) {
        if (root == null) {
            return null;
        }
        BinaryNode<T> curr = this.root, prev = null;
        while (true) {
            if (curr.getValue().compareTo(value) == 0) {
                return new NodeWithParent<>(curr, prev);
            }
            if (value.compareTo(curr.getValue()) < 0) {
                if (!curr.getLeft().isPresent()) {
                    return null;
                }
                curr = curr.getLeft().get();
            }
            if (value.compareTo(curr.getValue()) > 0) {
                if (!curr.getRight().isPresent()) {
                    return null;
                }
                prev = curr;
                curr = curr.getRight().get();
            }
        }
    }

    private static class NodeWithParent<T extends Comparable<T>> {
        private final BinaryNode<T> node, parent;

        private NodeWithParent(BinaryNode<T> node, BinaryNode<T> parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    @Override
    public String toString() {
        return Joiner.on("\n").join(levelOrder());
    }
}
