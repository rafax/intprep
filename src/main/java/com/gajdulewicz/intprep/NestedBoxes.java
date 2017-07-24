package com.gajdulewicz.intprep;

import com.google.common.collect.Lists;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.util.List;

public class NestedBoxes {
  private final List<Box> boxes;

  public NestedBoxes(List<Integer> dimensions) {
    this.boxes = Lists.newArrayList();
    for (List<Integer> dim : Lists.partition(dimensions, 2)) {
      this.boxes.add(new Box(dim.get(0), dim.get(1)));
    }
  }

  public int getBoxCount() {
    return boxes.size();
  }

  public int maxContainedBoxes() {
    MutableGraph<Box> graph = GraphBuilder.directed().allowsSelfLoops(false).build();
    for (Box b : this.boxes) {
      graph.addNode(b);
    }
    for (int i = 0; i < boxes.size(); i++) {
      for (int j = i + 1; j < boxes.size(); j++) {
        Box a = boxes.get(i);
        Box b = boxes.get(j);
        if (a.canContain(b)) {
          graph.putEdge(a, b);
        }
        if (b.canContain(a)) {
          graph.putEdge(b, a);
        }
      }
    }
    int chainLength = 0;
    while (!graph.nodes().isEmpty()) {
      List<Box> toRemove = findBoxesWithNoAncestors(graph);
      if (toRemove.isEmpty()) {
        throw new RuntimeException("Nothing to remove but graph contains: " + graph.nodes());
      }
      for (Box box : toRemove) {
        graph.removeNode(box);
      }
      chainLength++;
    }
    return chainLength;
  }

  private static List<Box> findBoxesWithNoAncestors(MutableGraph<Box> graph) {
    List<Box> result = Lists.newArrayList();
    for (Box box : graph.nodes()) {
      if (graph.predecessors(box).isEmpty()) {
        result.add(box);
      }
    }
    return result;
  }

  public static class Box {
    private final int w;
    private final int h;

    Box(int w, int h) {
      this.w = w;
      this.h = h;
    }

    boolean canContain(Box other) {
      return (this.w > other.w && this.h > other.h) || (this.h > other.w && this.w > other.h);
    }

    @Override
    public String toString() {
      return String.format("Box{w: %s, h: %s}", w, h);
    }
  }
}
