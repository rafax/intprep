package com.gajdulewicz.intprep.alg;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GraphTraversal {

  public static class Node<T> {
    public final T elem;
    private LinkedList<Node<T>> adjacent = new LinkedList<>();

    public Node(T elem) {
      this.elem = elem;
    }

    public void add(Node<T> n) {
      adjacent.add(n);
    }

    public boolean hasAdjacent() {
      return !adjacent.isEmpty();
    }

    public Stream<NodePath<T>> bfsStream() {
      return StreamSupport.stream(
          Spliterators.spliteratorUnknownSize(bfs(), Spliterator.DISTINCT), false);
    }

    public Iterator<NodePath<T>> bfs() {
      HashSet<T> visited = new HashSet<>();
      LinkedList<NodePath<T>> toVisit = new LinkedList<>();
      toVisit.add(new NodePath<>(this, ImmutableList.of()));
      return new Iterator<NodePath<T>>() {
        NodePath<T> next = null;

        @Override
        public boolean hasNext() {
          while (!toVisit.isEmpty()) {
            final NodePath<T> n = toVisit.remove();
            if (visited.contains(n.node.elem)) {
              continue;
            }
            visited.add(n.node.elem);
            for (Node<T> a : n.node.adjacent) {
              toVisit.add(
                  new NodePath<>(
                      a,
                      ImmutableList.copyOf(Iterables.concat(n.path, Lists.newArrayList(a.elem)))));
            }
            next = n;
            return true;
          }
          return false;
        }

        @Override
        public NodePath<T> next() {
          return next;
        }
      };
    }

    public static class NodePath<T> {
      public final Node<T> node;
      public final ImmutableList<T> path;

      public NodePath(Node<T> node, ImmutableList<T> path) {
        this.node = node;
        this.path = path;
      }
    }
  }
}
