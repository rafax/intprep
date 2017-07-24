package com.gajdulewicz.intprep.ds;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
    checkNotNull(value);
    final boolean deleted = deleteValue(value);
    if (deleted) {
      size--;
    }
    return deleted;
  }

  private boolean deleteValue(T value) {
    if (root == null) {
      return false;
    }
    if (value.compareTo(root.getValue()) == 0) {
      if (root.childCount() == 0) {
        root = null;
        return true;
      }
      if (root.childCount() == 1) {
        if (root.getLeft().isPresent()) {
          root = root.getLeft().get();
        } else {
          root = root.getRight().get();
        }
        return true;
      }
      if (root.childCount() == 2) {
        final T lrc = leftmostRightChild(root).getValue();
        root.setValue(lrc);
        return delete(lrc, root.getRight().get(), root);
      }
    }
    if (value.compareTo(root.getValue()) < 0) {
      if (root.getLeft().isPresent()) {
        return delete(value, root.getLeft().get(), root);
      }
      return false;
    } else {
      if (root.getRight().isPresent()) {
        return delete(value, root.getRight().get(), root);
      }
      return false;
    }
  }

  /*
   * Deletes a non-root node
   */
  private static <T extends Comparable<T>> boolean delete(
      T value, BinaryNode<T> searchRoot, BinaryNode<T> rootParent) {
    NodeWithParent<T> nodeWithParent = find(value, searchRoot, rootParent);
    if (nodeWithParent == null) {
      return false;
    }
    if (nodeWithParent.node == searchRoot) {
      nodeWithParent = new NodeWithParent<T>(searchRoot, rootParent);
    }
    if (nodeWithParent.node.childCount() == 0) {
      if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
        nodeWithParent.parent.setLeft(null);
      } else {
        nodeWithParent.parent.setRight(null);
      }
      return true;
    }
    if (nodeWithParent.node.childCount() == 1) {
      final BinaryNode<T> child =
          nodeWithParent
              .node
              .getLeft()
              .map(Optional::of)
              .orElse(nodeWithParent.node.getRight())
              .get();
      if (value.compareTo(nodeWithParent.parent.getValue()) < 0) {
        nodeWithParent.parent.setLeft(child);
      } else {
        nodeWithParent.parent.setRight(child);
      }
      return true;
    }

    // 2 children - replace node with leftmost child of right tree
    final BinaryNode<T> swapWith = leftmostRightChild(nodeWithParent.node);
    nodeWithParent.node.setValue(swapWith.getValue());
    return delete(swapWith.getValue(), nodeWithParent.node.getRight().get(), nodeWithParent.node);
  }

  private static <T extends Comparable<T>> BinaryNode<T> leftmostRightChild(BinaryNode<T> node) {
    checkArgument(node.getRight().isPresent());
    BinaryNode<T> curr = node.getRight().get();
    while (curr.getLeft().isPresent()) {
      curr = curr.getLeft().get();
    }
    return curr;
  }

  public List<List<T>> levelOrder() {
    if (root == null) {
      return Lists.newArrayList();
    }
    List<List<BinaryNode<T>>> levels = Lists.newArrayList();
    levels.add(Lists.newArrayList(root));
    descend(root.getLeft(), levels, 1);
    descend(root.getRight(), levels, 1);
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

  public boolean contains(T value) {
    return find(value, root, null) != null;
  }

  private static <T extends Comparable<T>> NodeWithParent<T> find(
      T value, BinaryNode<T> searchRoot, BinaryNode<T> rootParent) {
    if (searchRoot == null) {
      return null;
    }
    BinaryNode<T> curr = searchRoot, prev = rootParent;
    while (true) {
      if (curr.getValue().compareTo(value) == 0) {
        return new NodeWithParent<>(curr, prev);
      }
      if (value.compareTo(curr.getValue()) < 0) {
        if (!curr.getLeft().isPresent()) {
          return null;
        }
        prev = curr;
        curr = curr.getLeft().get();
        continue;
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
    final BinaryNode<T> node, parent;

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
