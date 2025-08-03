package structures.graph;

import internal.Preconditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UnDirectedGraph {
  private int nodeNum;
  private Edge[] edges;
  private int totalWeight;
  private UnDirectedGraph() {}

  public int getNodeNum() {
    return nodeNum;
  }

  private void setNodeNum(int nodeNum) {
    this.nodeNum = nodeNum;
  }

  public Edge[] getEdges() {
    return edges;
  }

  public List<Edge> getEdgesAsList() {
    return Arrays.asList(getEdges());
  }

  private void setEdges(Edge[] edges) {
    this.edges = edges;
  }

  @Override
  public String toString() {
    return toStringWithEdgesSortedInAscendingOrder();
  }

  public String toStringWithEdgesSortedInAscendingOrder() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("UnDirectedGraph with nodeNum: ").append(nodeNum)
        .append(" ,number of edges: ").append(edges.length)
        .append(", edges: ").append(System.lineSeparator());
    Edge[] copy = new Edge[edges.length];
    System.arraycopy(edges, 0, copy, 0, edges.length);
    Arrays.sort(copy);
    for (int i = 0; i < copy.length; i++) {
      stringBuilder.append(copy[i]).append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }

  private void setTotalWeight(int totalWeight) {
    this.totalWeight = totalWeight;
  }

  public int getTotalWeight() {
    return totalWeight;
  }

  public static class Builder {
    private Integer nodeNum;
    private Edge[] edges;
    private Integer totalWeight;

    public Builder withEdges(Edge[] edges) {
      this.edges = edges;
      return this;
    }

    public Builder withEdges(Collection<Edge> edges) {
      this.edges = edges.toArray(new Edge[edges.size()]);
      return this;
    }

    public Builder withNodeNum(int nodeNum) {
      this.nodeNum = nodeNum;
      return this;
    }

    public UnDirectedGraph build() {
      UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
      unDirectedGraph.setEdges(Preconditions.checkNotNull(edges));
      unDirectedGraph.setNodeNum(Preconditions.checkNotNull(nodeNum));
      if (totalWeight == null) {
        totalWeight = unDirectedGraph.getEdgesAsList().stream().mapToInt(Edge::getLength).sum();
      }
      unDirectedGraph.setTotalWeight(totalWeight);
      return unDirectedGraph;
    }
  }
}
