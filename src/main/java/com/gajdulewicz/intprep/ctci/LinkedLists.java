package com.gajdulewicz.intprep.ctci;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Stream;

public class LinkedLists {

  static class Node<T> {
    final T elem;
    Node<T> next;

    public Node(T elem) {
      this.elem = elem;
    }

    public void add(T elem) {
      Node<T> tail = new Node<>(elem);
      Node<T> curr = this;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = tail;
    }

    public Iterator<T> elements() {
      final Iterator<Node<T>> nodes = nodes();
      return new Iterator<T>() {
        @Override
        public boolean hasNext() {
          return nodes.hasNext();
        }

        @Override
        public T next() {
          return nodes.next().elem;
        }
      };
    }

    public Iterator<Node<T>> nodes() {
      return new Iterator<Node<T>>() {
        Node<T> curr = Node.this;

        @Override
        public boolean hasNext() {
          return curr != null;
        }

        @Override
        public Node<T> next() {
          Node<T> res = curr;
          curr = curr.next;
          return res;
        }
      };
    }

    @Override
    public String toString() {
      return "Node{" + "elem=" + elem + ", next=" + next + '}';
    }
  }

  // 2.1
  public static void removeDuplicates(Node<Integer> head) {
    if (head == null) {
      return;
    }
    final Iterator<Node<Integer>> nodes = head.nodes();
    Node<Integer> prev = nodes.next(), curr = null;
    HashSet<Integer> seen = Sets.newHashSet(prev.elem);
    while (nodes.hasNext()) {
      curr = nodes.next();
      if (seen.contains(curr.elem)) {
        prev.next = curr.next;
      } else {
        seen.add(curr.elem);
        prev = curr;
      }
    }
  }

  // 2.2
  static <T> Node<T> kthLast(Node<T> head, int k) {
    if (head == null) {
      return null;
    }
    Node<T> left = head, right = head;
    for (int i = 0; i < k - 1; i++) {
      if (right.next == null) {
        return null;
      }
      right = right.next;
    }
    while (right.next != null) {
      right = right.next;
      left = left.next;
    }
    return left;
  }

  // 2.2
  static Node<Integer> partition(Node<Integer> head, int pivot) {
    if (head == null) {
      return null;
    }
    Node<Integer> lte = null, gt = null, curr = head;
    while (curr != null) {
      if (curr.elem <= pivot) {
        if (lte == null) {
          lte = new Node<>(curr.elem);
        } else {
          lte.add(curr.elem);
        }
      } else {
        if (gt == null) {
          gt = new Node<>(curr.elem);
        } else {
          gt.add(curr.elem);
        }
      }
      curr = curr.next;
    }
    final Node<Integer> last = Iterators.getLast(lte.nodes());
    last.next = gt;
    return lte;
  }
}
