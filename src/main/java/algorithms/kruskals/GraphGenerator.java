package algorithms.kruskals;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
  private static String dataFile = "in.txt";
  private int LENGTH_GENERATED_MAX_VALUE = 150;
  private int LENGTH_GENERATED_MIN_VALUE = 50;

  private int NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE = 7;

  public GraphGenerator(String filenameToWriteTo) {
    dataFile = filenameToWriteTo;
  }

  public Graph makeRandomTree() throws Exception {
    Random random = new Random();
    int nodeNum = random.nextInt(10) + 5;
    List<Integer> unconnected = new ArrayList<>();
    List<Integer> connected = new ArrayList<>();
    for (int i = 1; i <= nodeNum; i++) {
      unconnected.add(i);
    }

    PrintStream printStream = new PrintStream(new File(dataFile));
    printStream.println(nodeNum);
    List<Edge> edgesCreated = generateRandomEdges(random, connected, unconnected, nodeNum);
    edgesCreated.addAll(ensureConnected(random, unconnected, connected));

    printEdgesToStream(edgesCreated, printStream);
    printStream.flush();
    printStream.close();
    return new Graph.Builder()
        .withNodeNum(nodeNum)
        .withEdges(edgesCreated.toArray(new Edge[]{}))
        .build();
  }

  private void printEdgesToStream(List<Edge> edges, PrintStream ps) {
    for (Edge edge : edges) {
      printEdgeToStream(ps, edge);
    }
  }

  private List<Edge> generateRandomEdges(Random random, List<Integer> connected, List<Integer> unconnected,
                                         int nodeNum) {
    if (nodeNum <= 2) {
      throw new IllegalArgumentException("Node number must be more than 2");
    }
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE; i++) {
      int start = random.nextInt(nodeNum) + 1;
      int end = random.nextInt(nodeNum) + 1;
      while (start == end) {
        end = random.nextInt(nodeNum) + 1;
      }
      int length = getRandomLength(random);
      Edge edge = new Edge(start, end, length);
      edges.add(edge);
      updateConnectivityWithEdge(connected, unconnected, edge);
    }
    return edges;
  }

  private void updateConnectivityWithEdge(List<Integer> connected, List<Integer> unconnected, Edge edge) {
    if (connected.contains(edge.start) && !connected.contains(edge.end)) {
      unconnected.remove(edge.end);
      connected.add(edge.start);
    } else if (connected.contains(edge.end) && !connected.contains(edge.start)) {
      unconnected.remove(edge.start);
      connected.remove(edge.end);
    }
  }


  private void printEdgeToStream(PrintStream printStream, Edge edge) {
    printStream.printf("%d %d %d\n", edge.start, edge.end, edge.val);
  }
  private List<Edge> ensureConnected(Random random, List<Integer> unconnected, List<Integer> connected) {
    List<Edge> edges = new ArrayList<>();
    while (!unconnected.isEmpty()) {
      Integer nextNode = unconnected.get(random.nextInt(unconnected.size()));
      Integer startNode = connected.get(random.nextInt(connected.size()));
      int length = getRandomLength(random);
      edges.add(new Edge(startNode, nextNode, length));
      unconnected.remove(nextNode);
      connected.add(nextNode);
    }
    return edges;
  }
  private int getRandomLength(Random random) {
    return random.nextInt(LENGTH_GENERATED_MAX_VALUE - LENGTH_GENERATED_MIN_VALUE) + LENGTH_GENERATED_MIN_VALUE;
  }
}
