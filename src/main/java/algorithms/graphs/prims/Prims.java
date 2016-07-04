package algorithms.graphs.prims;

import structures.DisjointSet;
import structures.DisjointSetTrackingChildren;
import structures.graph.Edge;
import structures.graph.Graph;

import java.util.*;
import java.util.stream.Collectors;

public class Prims {
  public Graph findMinimumSpanningTreeForGraph(Graph graph) {
    final int numberOfEdgesNeededForMst = graph.getNodeNum() - 1;
    Map<Integer, DisjointSetTrackingChildren> unconnectedForest = getDisjointForestOfSize(graph.getNodeNum());
    List<Edge> edgesInMst = new ArrayList<>(numberOfEdgesNeededForMst);

    DisjointSetTrackingChildren root = unconnectedForest.values().stream().findFirst().get();
    while (!unconnectedForest.isEmpty()) {
      SortedSet<Edge> edgesToConsider = getEdgesConnectedToOneDisjointSet(graph.getEdges(), root.getChildren());
      if (edgesToConsider.isEmpty()) {
        throw new IllegalArgumentException("Given graph was not connected");
      } else {
        Edge edge = edgesToConsider.first();
        if (edge.getSource() == root.getId()) {
          root.union(unconnectedForest.remove(edge.getEnd()));
        } else {
          root.union(unconnectedForest.remove(edge.getSource()));
        }
      }
    }

    Graph.Builder builder = new Graph.Builder();
    builder.withNodeNum(graph.getNodeNum());
    builder.withEdges(edgesInMst);
    return builder.build();
  }

  private Map<Integer, DisjointSetTrackingChildren> getDisjointForestOfSize(int size) {
    Map<Integer, DisjointSetTrackingChildren> disjointSetForest = new HashMap<>();
    for (int i = 0; i < size; i++) {
      disjointSetForest.put(i, new DisjointSetTrackingChildren(i));
    }
    return disjointSetForest;
  }

  private SortedSet<Edge> getEdgesConnectedToOneDisjointSet(Edge[] edges, Collection<DisjointSet> included) {
    List<Integer> integerList = included.stream().map(DisjointSet::getId).collect(Collectors.toList());
    return getEdgesConnectedToOneIncludedNode(edges, integerList);
  }

  private SortedSet<Edge> getEdgesConnectedToOneIncludedNode(Edge[] edges, Collection<Integer> included) {
    SortedSet<Edge> sorted = new TreeSet<>();
    for (Edge edge : edges) {
      if (included.contains(edge.getSource())) {
        if (included.contains(edge.getEnd())) {
          // adding would form a cycle,
        } else {
          sorted.add(edge);
        }
      } else if (included.contains(edge.getEnd())) {
        sorted.add(edge);
      }
    }
    return sorted;
  }
}
