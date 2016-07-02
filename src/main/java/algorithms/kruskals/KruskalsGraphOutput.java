package algorithms.kruskals;

public class KruskalsGraphOutput {
  Graph graph;
  String lineSeparator = System.lineSeparator();
  public KruskalsGraphOutput(Graph graph ) {
    this.graph = graph;
  }
  public String outputToGivenFile() {
    StringBuilder sb = new StringBuilder();
    sb.append("graph G {");
    sb.append(lineSeparator);
    Edge[] arr = graph.getEdges();
    for (int i=0; i < arr.length; i++) {
      Edge e = arr[i];
      sb.append(String.format("%s -- %s [label=%d];\n",e.start, e.end, e.val));
    }
    sb.append("}");
    sb.append(lineSeparator);
    return sb.toString();
  }
}