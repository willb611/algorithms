package algorithms.graphs.kruskals;

import structures.DisjointSet;
import structures.graph.Edge;
import structures.graph.Graph;

import java.util.*;

public class Kruskals {
  /**
  * Solves using a list and disjoint set trees
  */
  public Graph findMinimumSpanningTreeForGraph(Graph graph) {
    SortedSet<Edge> sortedEdges = new TreeSet<>(graph.getEdgesAsList());
    final int numberOfEdgesNeededForMst = graph.getNodeNum() - 1;
    DisjointSet[] forest = DisjointSet.makeUnconnectedForest(graph.getNodeNum() + 1);
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
      throw new IllegalArgumentException("Error, provided graph was not connected. Only produced tree " +
          "of size: " + edgesInMst.size());
    }
    Graph.Builder builder = new Graph.Builder();
    builder.withNodeNum(graph.getNodeNum());
    builder.withEdges(edgesInMst);
    return builder.build();
  }
}