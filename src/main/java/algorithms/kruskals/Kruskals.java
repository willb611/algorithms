package algorithms.kruskals;

import structures.DisjointSet;
import structures.graph.Edge;
import structures.graph.Graph;

import java.util.*;

public class Kruskals {
  /**
  * Solves using a list and disjoint set trees
  */
  public Graph findMinimumSpanningTreeForGraph(Graph graph) {
    SortedSet<Edge> sortedEdges = new TreeSet<>(Arrays.asList(graph.getEdges()));
    final int numberOfEdgesNeededForMst = graph.getNodeNum() - 1;
    DisjointSet[] forest = DisjointSet.makeUnconnectedForest(graph.getNodeNum() + 1);
    List<Edge> mst = new ArrayList<>(numberOfEdgesNeededForMst);

    for (Edge edge : sortedEdges) {
      DisjointSet source = forest[edge.start];
      DisjointSet target = forest[edge.end];
      if (source.findParent() != target.findParent()) {
        source.union(target);
        mst.add(edge);
      }
    }
    if (mst.size() != numberOfEdgesNeededForMst) {
      throw new IllegalArgumentException("Error, provided graph was not connected. Only produced tree " +
          "of size: " + mst.size());
    }
    Graph.Builder builder = new Graph.Builder();
    builder.withNodeNum(graph.getNodeNum());
    builder.withEdges(mst.toArray(new Edge[mst.size()]));
    return builder.build();
  }
}