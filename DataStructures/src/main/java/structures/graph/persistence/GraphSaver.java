package structures.graph.persistence;

import structures.graph.UnDirectedGraph;
import structures.graph.persistence.images.DotFormatIntoPngConverter;
import structures.graph.persistence.io.FilePrinter;

import java.io.FileNotFoundException;

public class GraphSaver {
  public static void saveGraph(String baseFileName, UnDirectedGraph graph) throws FileNotFoundException {
    FilePrinter.printToFile(GraphSerializer.serialize(graph), baseFileName + ".original");
    FilePrinter.printToFile(new GraphIntoDotFormatConverter().getOutputAsDotFormat(graph), baseFileName + ".dot");
    try {
      DotFormatIntoPngConverter.createPngForFileWithPrefix(baseFileName);
    } catch (Exception e) {
      System.err.println("Error creating png from dot file due to exception: " + e.getLocalizedMessage());
      e.printStackTrace();
    }
  }
}
