package structures.graph.serializers;

import structures.graph.Edge;
import structures.graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class GraphSerializer {
  public static void printToStreamAndClose(Graph graph, PrintStream printStream) {
    printStream.println(graph.getNodeNum());
    printEdgesToStream(graph.getEdges(), printStream);
    printStream.flush();
    printStream.close();
  }

  public static void printToFile(Graph graph, File file) throws FileNotFoundException {
    printToStreamAndClose(graph, new PrintStream(file));
  }

  public static void printToFile(Graph graph, String fileName) throws FileNotFoundException {
    printToFile(graph, new File(fileName));
  }

  private static void printEdgesToStream(Edge[] edges, PrintStream ps) {
    for (Edge edge : edges) {
      printEdgeToStream(ps, edge);
    }
  }

  private static void printEdgesToStream(List<Edge> edges, PrintStream ps) {
    for (Edge edge : edges) {
      printEdgeToStream(ps, edge);
    }
  }

  private static void printEdgeToStream(PrintStream printStream, Edge edge) {
    printStream.printf("%d %d %d\n", edge.getSource(), edge.getDestination(), edge.getLength());
  }
}
