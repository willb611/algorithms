package algorithms.kruskals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static algorithms.internal.Preconditions.checkNotNull;
import static java.lang.Integer.parseInt;

public class Graph {
  private int nodeNum;
  private Edge[] edges;
  private Graph() {}

  private static Queue<String> getLines(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    Queue<String> lines = new ArrayDeque<>();
    try {
      while (br.ready()) {
        lines.add(br.readLine());
      }
    } finally {
      br.close();
    }
    return lines;
  }

  /**
  * Data should be in a text file, see variable dataFile
  * First line contains the number of nodes
  * Rest of the lines contain edges:
  * <p>start Node #, end Node #, length.</p>
  */
  static Graph parseData(String dataFile) throws Exception
  {
    Queue<String> lines = getLines(dataFile);
    int nodeNum = parseInt(lines.poll());
    List<Edge> list = new ArrayList<>();
    while (!lines.isEmpty()) {
      String[] info = lines.poll().split(" ");
      int startNodeNum = parseInt(info[0]);
      int endNodeNum = parseInt(info[1]);
      int length = parseInt(info[2]);
      Edge e = new Edge(startNodeNum, endNodeNum, length);
      list.add(e);
    }
    Edge[] edges = list.toArray(new Edge[0]);
    return new Builder().withEdges(edges).withNodeNum(nodeNum).build();
  }


  public int getNodeNum() {
    return nodeNum;
  }

  private void setNodeNum(int nodeNum) {
    this.nodeNum = nodeNum;
  }

  public Edge[] getEdges() {
    return edges;
  }

  private void setEdges(Edge[] edges) {
    this.edges = edges;
  }
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Graph with nodeNum: ").append(nodeNum).append(", edges: ").append(System.lineSeparator());
    Edge[] copy = new Edge[edges.length];
    System.arraycopy(edges, 0, copy, 0, edges.length);
    Arrays.sort(copy);
    for(int i=0; i < copy.length;i++) {
      stringBuilder.append(copy[i]).append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }
  public static class Builder {
    Integer nodeNum;
    Edge[] edges;
    public Builder withEdges(Edge[] edges) {
      this.edges = edges;
      return this;
    }
    public Builder withNodeNum(int nodeNum) {
      this.nodeNum = nodeNum;
      return this;
    }
    public Graph build() {
      Graph graph = new Graph();
      graph.setEdges(checkNotNull(edges));
      graph.setNodeNum(checkNotNull(nodeNum));
      return graph;
    }

  }
}