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
    private int size = 0;

    public boolean insert(T value) {
        final BinaryNode<T> node = new BinaryNode<>(value);
        final boolean inserted = insert(node);
        if (inserted) {
            size++;
        }
        return inserted;
    }

    private boolean insert(BinaryNode<T> node) {
        if (root == null) {
            root = node;
            return true;
        }
        BinaryNode<T> curr = root;
        while (true) {
            if (node.getValue().compareTo(curr.getValue()) == 0) {
                return false;
            }
            if (node.getValue().compareTo(curr.getValue()) < 0) {
                if (!curr.getLeft().isPresent()) {
                    curr.setLeft(node);
                    return true;
                }
                curr = curr.getLeft().get();
            } else {
                if (!curr.getRight().isPresent()) {
                    curr.setRight(node);
                    return true;
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
        if (nodeWithParent.node.childCount() == 0) {
            if (nodeWithParent.parent == null) {
                root = null;
            } else {
                if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
                    nodeWithParent.parent.setLeft(null);
                } else {
                    nodeWithParent.parent.setRight(null);
                }
            }
            size--;
            return true;
        }
        if (nodeWithParent.node.childCount() == 1) {
            final BinaryNode<T> child = nodeWithParent.node.getLeft()
                    .map(Optional::of).orElse(nodeWithParent.node.getRight()).get();
            if (nodeWithParent.parent == null) {
                root = child;
            } else {
                if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
                    nodeWithParent.parent.setLeft(child);
                } else {
                    nodeWithParent.parent.setRight(child);
                }
            }
            size--;
            return true;
        }
        // 2 children - replace node with leftmost child of right tree
        final BinaryNode<T> swapWith = leftmostRightChild(nodeWithParent.node);
        if (nodeWithParent.parent == null) {
            root = swapWith;
        } else {
            if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
                nodeWithParent.parent.setLeft(swapWith);
            } else {
                nodeWithParent.parent.setRight(swapWith);
            }
        }
        size--;
        return true;
    }

    private BinaryNode<T> leftmostRightChild(BinaryNode<T> node) {
        BinaryNode<T> curr = node.getRight().get();
        while (curr.getLeft().isPresent()) {
            curr = curr.getLeft().get();
        }
        return curr;
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

    public int size() {
        return size;
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
