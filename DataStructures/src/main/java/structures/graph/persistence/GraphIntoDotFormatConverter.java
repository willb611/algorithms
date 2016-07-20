package structures.graph.persistence;

import structures.graph.Edge;
import structures.graph.UnDirectedGraph;

public class GraphIntoDotFormatConverter {
  private String lineSeparator = System.lineSeparator();

  public String getOutputAsDotFormat(UnDirectedGraph unDirectedGraph) {
    return getOutputAsDotFormat(unDirectedGraph, null, null);
  }

  public String getOutputAsDotFormat(UnDirectedGraph unDirectedGraph,
                                                        Integer source, Integer sink) {
    StringBuilder sb = new StringBuilder();
    sb.append("Graph G {");
    sb.append(lineSeparator);
    appendEdgesToBuilder(unDirectedGraph.getEdges(), sb, source, sink);
    sb.append("}");
    sb.append(lineSeparator);
    return sb.toString();
  }

  private void appendEdgesToBuilder(Edge[] edges, StringBuilder stringBuilder,
                                    Integer graphSource, Integer graphSInk) {
    for (int i = 0; i < edges.length; i++) {
      Edge edge = edges[i];
      String startLabel = getLabelFor(edge.getSource(), graphSource, graphSInk);
      String endLabel = getLabelFor(edge.getDestination(), graphSource, graphSInk);
      stringBuilder.append(String.format("%s -- %s [label=%d];\n",
          startLabel, endLabel, edge.getLength()));
    }
  }
  private String getLabelFor(int i, Integer graphSource, Integer graphSink) {
    if (i == graphSink) {
      return "Snk" + i + "";
    } else if (i == graphSource) {
      return "Src" + i + "";
    } else {
      return String.valueOf(i);
    }
  }
}