package structures.graph.serializers;

import structures.graph.UnDirectedGraph;
import structures.graph.serializers.io.FilePrinter;

import java.io.FileNotFoundException;

public class GraphSaver {
  public static void saveGraph(String baseFileName, UnDirectedGraph graph) throws FileNotFoundException {
    FilePrinter.printToFile(GraphSerializer.serialize(graph), baseFileName + ".original");
    FilePrinter.printToFile(new GraphIntoDotFormatConverter().getOutputAsDotFormat(graph), baseFileName + ".dot");
  }
}
