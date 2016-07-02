package algorithms.kruskals;

public class KruskalsGraphOutput {
  Edge[] graph;
  String lineSeparator = System.lineSeparator();
  public KruskalsGraphOutput(Edge[] data) {
    graph = data;
  }
  String output() {
    StringBuilder sb = new StringBuilder();
    sb.append("graph G {");
    sb.append(lineSeparator);
    for (int i=0; i < graph.length; i++) {
      Edge e = graph[i];
      sb.append(String.format("%s -- %s [label=%d];\n",e.start, e.end, e.val));
    }
    sb.append("}");
    sb.append(lineSeparator);
    return sb.toString();
  }
}