package algorithms.kruskals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class KruskalsTest {

  static String dataFile = "in.txt";
  static void test() throws Exception
  {
    GraphGenerator graphGenerator = new GraphGenerator(dataFile);
    graphGenerator.makeRandomTree();
    Graph graph = Graph.parseData(dataFile);
    System.out.println(graph.toStringWithEdgesSortedInAscendingOrder());
    System.out.println("Attempting to findMinimumSpanningTreeForGraph");

    Kruskals ks = new Kruskals();
    Graph mst = ks.findMinimumSpanningTreeForGraph(graph);
    System.out.println("Printing mst");
    System.out.println(mst);

    KruskalsGraphOutput go = new KruskalsGraphOutput(mst);
    String result = go.getOutputAsGraphFormatOutput();
    PrintStream outputStream = makeFileStream("kruskals.out.txt");
    try {
      outputStream.print(result);
    } finally {
      outputStream.close();
    }
  }
  static private PrintStream makeFileStream(String fileName) throws FileNotFoundException {
    String outputFileName = fileName;
    File outputFile = new File(outputFileName);
    return new PrintStream(outputFile);
  }
}
