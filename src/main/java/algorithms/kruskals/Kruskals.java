package algorithms.kruskals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kruskals {
  /**
  * Solves using a list and disjoint set trees
  */
  public Graph findMinimumSpanningTreeForGraph(Graph graph) {
    Edge[] arr = graph.getEdges();
    Arrays.sort(arr);
    DisjointSet[] forest = DisjointSet.makeUnconnectedForest(graph.getNodeNum() + 1);
    List<Edge> mst = new ArrayList<>(graph.getNodeNum() - 1);
    // Try all edges, in ascending order of edge weight
    for (Edge edge : arr) {
      DisjointSet source = forest[edge.start];
      DisjointSet target = forest[edge.end];
      if (source.findParent() != target.findParent()) {
        source.union(target.findParent());
        mst.add(edge);
      }
    }
    if (mst.size() != graph.getNodeNum() - 1) {
      throw new IllegalArgumentException("Error, provided graph was not connected. Only produced tree " +
          "of size: " + mst.size());
    }
    Graph.Builder builder = new Graph.Builder();
    builder.withNodeNum(graph.getNodeNum());
    builder.withEdges(mst.toArray(new Edge[mst.size()]));
    return builder.build();
  }
}