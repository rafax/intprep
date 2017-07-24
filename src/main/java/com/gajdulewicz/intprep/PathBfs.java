package com.gajdulewicz.intprep;

import com.gajdulewicz.intprep.alg.GraphTraversal;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class PathBfs {
  public static void main(String[] args) {
    findPaths(System.in)
        .forEach(
            l ->
                System.out.println(
                    String.join(
                        " ", l.stream().map(Object::toString).collect(Collectors.toList()))));
  }

  public static List<List<Integer>> findPaths(InputStream in) {
    Scanner s = new Scanner(in);
    List<List<Integer>> result = new ArrayList<>();
    int queries = s.nextInt();
    for (int q = 0; q < queries; q++) {
      int nodeCount = s.nextInt();
      int edgeCount = s.nextInt();
      List<GraphTraversal.Node<Integer>> nodes = new ArrayList<>();
      for (int i = 0; i < nodeCount; i++) {
        GraphTraversal.Node<Integer> n = new GraphTraversal.Node<>(i + 1);
        nodes.add(n);
      }
      for (int i = 0; i < edgeCount; i++) {
        int from = s.nextInt();
        int to = s.nextInt();
        nodes.get(from - 1).add(nodes.get(to - 1));
        nodes.get(to - 1).add(nodes.get(from - 1));
      }
      int startNode = s.nextInt();
      result.add(paths(nodes.get(startNode - 1), nodes));
    }
    return result;
  }

  public static List<Integer> paths(
      GraphTraversal.Node<Integer> from, List<GraphTraversal.Node<Integer>> graph) {
    List<Integer> res = new ArrayList<>();
    Map<Integer, Integer> paths = new HashMap<>();
    from.bfsStream().forEach(np -> paths.put(np.node.elem, np.path.size()));
    for (GraphTraversal.Node<Integer> n : graph) {
      if (!Objects.equals(n.elem, from.elem)) {
        res.add(Optional.ofNullable(paths.get(n.elem)).map(e -> e * 6).orElse(-1));
      }
    }
    return res;
  }
}
