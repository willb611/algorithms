package structures.graph.generation;

import structures.graph.Edge;
import structures.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
  private static int LENGTH_GENERATED_MAX_VALUE = 150;
  private static int LENGTH_GENERATED_MIN_VALUE = 50;

  private static int NODE_NUMBER_MAX_VALUE = 15;
  private static int NODE_NUMBER_MIN_VALUE = 5;

  private static int NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE = 7;

  public static Graph makeRandomConnectedGraph() throws Exception {
    Random random = new Random();
    int nodeNum = getNumberOfNodes(random);
    List<Integer> unconnected = new ArrayList<>();
    List<Integer> connected = new ArrayList<>();
    for (int i = 1; i <= nodeNum; i++) {
      unconnected.add(i);
    }

    List<Edge> edgesCreated = generateRandomEdges(random, connected, unconnected, nodeNum);
    edgesCreated.addAll(ensureConnected(random, unconnected, connected));

    return new Graph.Builder()
        .withNodeNum(nodeNum)
        .withEdges(edgesCreated.toArray(new Edge[]{}))
        .build();
  }

  private static int getNumberOfNodes(Random random) {
    return random.nextInt(NODE_NUMBER_MAX_VALUE - NODE_NUMBER_MIN_VALUE) + NODE_NUMBER_MIN_VALUE;
  }

  private static List<Edge> generateRandomEdges(Random random,
                                                List<Integer> connected, List<Integer> unconnected,
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

  private static void updateConnectivityWithEdge(List<Integer> connected, List<Integer> unconnected,
                                                 Edge edge) {
    if (connected.contains(edge.getSource()) && !connected.contains(edge.getEnd())) {
      unconnected.remove(edge.getEnd());
      connected.add(edge.getSource());
    } else if (connected.contains(edge.getEnd()) && !connected.contains(edge.getSource())) {
      unconnected.remove(edge.getSource());
      connected.remove(edge.getEnd());
    }
  }


  private static List<Edge> ensureConnected(Random random,
                                            List<Integer> unconnected, List<Integer> connected) {
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

  private static int getRandomLength(Random random) {
    return random.nextInt(LENGTH_GENERATED_MAX_VALUE - LENGTH_GENERATED_MIN_VALUE)
        + LENGTH_GENERATED_MIN_VALUE;
  }
}
