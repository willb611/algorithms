package structures.graph.persistence;

import structures.graph.UnDirectedGraph;
import structures.graph.persistence.io.FilePrinter;

import java.io.FileNotFoundException;

public class GraphSaver {
  public static void saveGraph(String baseFileName, UnDirectedGraph graph) throws FileNotFoundException {
    FilePrinter.printToFile(GraphSerializer.serialize(graph), baseFileName + ".original");
    FilePrinter.printToFile(new GraphIntoDotFormatConverter().getOutputAsDotFormat(graph), baseFileName + ".dot");
  }
}
