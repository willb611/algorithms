package algorithms.graphs.kruskals.example;

import algorithms.graphs.kruskals.Kruskals;
import structures.graph.Graph;
import structures.graph.generation.GraphGenerator;
import structures.graph.serializers.GraphIntoDotFormatConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class KruskalsExampleUse {

  public static void main(String[] ags) throws Exception {
    test();
  }

  static void test() throws Exception {
    Graph randomConnectedGraph = new GraphGenerator().makeRandomConnectedGraph();
    System.out.println(randomConnectedGraph.toStringWithEdgesSortedInAscendingOrder());
    System.out.println("Attempting to findMinimumSpanningTreeForGraph");
    GraphIntoDotFormatConverter dotFormatConverter = new GraphIntoDotFormatConverter();
    printToFile(dotFormatConverter.getOutputAsDotFormat(randomConnectedGraph), "generated.out.dot");

    Kruskals ks = new Kruskals();
    Graph mst = ks.findMinimumSpanningTreeForGraph(randomConnectedGraph);
    System.out.println("Printing mst");
    System.out.println(mst);

    printToFile(dotFormatConverter.getOutputAsDotFormat(mst), "kruskals.out.dot");
  }

  static private PrintStream makeFileStream(String fileName) throws FileNotFoundException {
    File outputFile = new File(fileName);
    return new PrintStream(outputFile);
  }

  static private void printToFile(String result, String fileName) throws FileNotFoundException {
    try (PrintStream outputStream = makeFileStream(fileName)) {
      outputStream.print(result);
    }
  }
}
