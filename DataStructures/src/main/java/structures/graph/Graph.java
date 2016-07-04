package structures.graph;

import structures.internal.Preconditions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Graph {
  private int nodeNum;
  private Edge[] edges;
  private int totalWeight;
  private Graph() {}

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

  public String toStringWithEdgesSortedInAscendingOrder() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Graph with nodeNum: ").append(nodeNum)
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
    Integer nodeNum;
    Edge[] edges;
    Integer totalWeight;

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

    public Graph build() {
      Graph graph = new Graph();
      graph.setEdges(Preconditions.checkNotNull(edges));
      graph.setNodeNum(Preconditions.checkNotNull(nodeNum));
      if (totalWeight == null) {
        totalWeight = graph.getEdgesAsList().stream().mapToInt(Edge::getLength).sum();
      }
      graph.setTotalWeight(totalWeight);
      return graph;
    }
  }
}
