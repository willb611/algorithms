package structures.graph.persistence;

import structures.graph.UnDirectedGraph;
import structures.graph.persistence.io.FilePrinter;

import java.io.FileNotFoundException;

public class GraphSaver {
  public void saveGraph(String baseFileName, UnDirectedGraph graph) throws FileNotFoundException {
    FilePrinter.printToFile(GraphSerializer.serialize(graph), baseFileName + ".original");
    FilePrinter.printToFile(new GraphIntoDotFormatConverter().getOutputAsDotFormat(graph), baseFileName + ".dot");
  }
  public void saveGraph(String baseFileName, UnDirectedGraph graph,
                               Integer source, Integer sink) throws FileNotFoundException {
    FilePrinter.printToFile(GraphSerializer.serialize(graph), baseFileName + ".original");
    FilePrinter.printToFile(new GraphIntoDotFormatConverter().getOutputAsDotFormat(graph, source, sink),
        baseFileName + ".dot");
  }
}
