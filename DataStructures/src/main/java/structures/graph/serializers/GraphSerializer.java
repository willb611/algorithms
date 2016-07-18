package structures.graph.serializers;

import structures.graph.Edge;
import structures.graph.UnDirectedGraph;


public class GraphSerializer {

  public static String serialize(UnDirectedGraph unDirectedGraph) {
    String sep = System.lineSeparator();
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(unDirectedGraph.getNodeNum()).append(sep);
    serializeEdges(stringBuilder, unDirectedGraph.getEdges());
    return stringBuilder.toString();
  }

  private static void serializeEdges(StringBuilder stringBuilder, Edge[] edges) {
    for (Edge edge : edges) {
      stringBuilder.append(String.format("%d %d %d%n",
          edge.getSource(), edge.getDestination(), edge.getLength()));
    }
  }
}
