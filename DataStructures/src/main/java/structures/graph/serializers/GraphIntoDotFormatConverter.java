package structures.graph.serializers;

import structures.graph.Edge;
import structures.graph.Graph;

public class GraphIntoDotFormatConverter {
  private String lineSeparator = System.lineSeparator();

  public String getOutputAsDotFormat(Graph graph) {
    StringBuilder sb = new StringBuilder();
    sb.append("graph G {");
    sb.append(lineSeparator);
    appendEdgesToBuilder(graph.getEdges(), sb);
    sb.append("}");
    sb.append(lineSeparator);
    return sb.toString();
  }

  private void appendEdgesToBuilder(Edge[] edges, StringBuilder stringBuilder) {
    for (int i = 0; i < edges.length; i++) {
      Edge edge = edges[i];
      stringBuilder.append(String.format("%s -- %s [label=%d];\n",
          edge.getSource(), edge.getDestination(), edge.getLength()));
    }
  }
}