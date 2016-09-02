package algorithms.graphs.prims;

import structures.DisjointSet;
import structures.DisjointSetTrackingChildren;
import structures.graph.Edge;
import structures.graph.UnDirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Prims {
  public UnDirectedGraph findMinimumSpanningTreeForGraph(UnDirectedGraph unDirectedGraph) {
    final int numberOfEdgesNeededForMst = unDirectedGraph.getNodeNum() - 1;
    Map<Integer, DisjointSetTrackingChildren> unconnectedForest = getDisjointForestOfSize(unDirectedGraph.getNodeNum());
    List<Edge> edgesInMst = new ArrayList<>(numberOfEdgesNeededForMst);

    DisjointSetTrackingChildren root = unconnectedForest.values().stream().findFirst().get();
    while (!unconnectedForest.isEmpty()) {
      SortedSet<Edge> edgesToConsider = getEdgesConnectedToOneDisjointSet(unDirectedGraph.getEdges(), root.getChildren());
      if (edgesToConsider.isEmpty()) {
        throw new IllegalArgumentException("Given unDirectedGraph was not connected");
      } else {
        Edge edge = edgesToConsider.first();
        if (edge.getSource() == root.getId()) {
          root.union(unconnectedForest.remove(edge.getDestination()));
        } else {
          root.union(unconnectedForest.remove(edge.getSource()));
        }
      }
    }

    UnDirectedGraph.Builder builder = new UnDirectedGraph.Builder();
    builder.withNodeNum(unDirectedGraph.getNodeNum());
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
      if (edge.sourceOrDestinationButNotBothContainedIn(included)) {
        sorted.add(edge);
      }
    }
    return sorted;
  }
}
