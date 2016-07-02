package algorithms.kruskals;

import structures.graph.Graph;
import structures.graph.generation.GraphGenerator;
import structures.graph.serializers.GraphIntoWhateverFormatConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class KruskalsTest {
  static String dataFile = "in.txt";

  static void test() throws Exception {
    Graph graph = GraphGenerator.makeRandomTree();
    System.out.println(graph.toStringWithEdgesSortedInAscendingOrder());
    System.out.println("Attempting to findMinimumSpanningTreeForGraph");

    Kruskals ks = new Kruskals();
    Graph mst = ks.findMinimumSpanningTreeForGraph(graph);
    System.out.println("Printing mst");
    System.out.println(mst);

    GraphIntoWhateverFormatConverter go = new GraphIntoWhateverFormatConverter(mst);
    printToFile(go.getOutputAsGraphFormatOutput(), "kruskals.out.txt");
  }
  static private PrintStream makeFileStream(String fileName) throws FileNotFoundException {
    String outputFileName = fileName;
    File outputFile = new File(outputFileName);
    return new PrintStream(outputFile);
  }
  static private void printToFile(String result, String fileName) throws FileNotFoundException {
    try (PrintStream outputStream = makeFileStream(fileName)) {
      outputStream.print(result);
    }
  }
}
