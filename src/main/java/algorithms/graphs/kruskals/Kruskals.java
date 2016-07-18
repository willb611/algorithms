package algorithms.graphs.kruskals;

import structures.DisjointSet;
import structures.graph.Edge;
import structures.graph.UnDirectedGraph;

import java.util.*;

public class Kruskals {
  /**
  * Solves using a list and disjoint set trees
  */
  public UnDirectedGraph findMinimumSpanningTreeForGraph(UnDirectedGraph unDirectedGraph) {
    SortedSet<Edge> sortedEdges = new TreeSet<>(unDirectedGraph.getEdgesAsList());
    final int numberOfEdgesNeededForMst = unDirectedGraph.getNodeNum() - 1;
    DisjointSet[] forest = DisjointSet.makeUnconnectedForest(unDirectedGraph.getNodeNum() + 1);
    List<Edge> edgesInMst = new ArrayList<>(numberOfEdgesNeededForMst);

    for (Edge edge : sortedEdges) {
      DisjointSet source = forest[edge.getSource()];
      DisjointSet target = forest[edge.getDestination()];
      if (source.findParent() != target.findParent()) {
        source.union(target);
        edgesInMst.add(edge);
      }
    }
    if (edgesInMst.size() != numberOfEdgesNeededForMst) {
      throw new IllegalArgumentException("Error, provided unDirectedGraph was not connected. Only produced tree " +
          "of size: " + edgesInMst.size());
    }
    UnDirectedGraph.Builder builder = new UnDirectedGraph.Builder();
    builder.withNodeNum(unDirectedGraph.getNodeNum());
    builder.withEdges(edgesInMst);
    return builder.build();
  }
}