package structures.graph.generation;

import structures.graph.Edge;
import structures.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
  private static int DEFAULT_LENGTH_GENERATED_MAX_VALUE = 150;
  private static int DEFAULT_LENGTH_GENERATED_MIN_VALUE = 50;

  private static int DEFAULT_NODE_NUMBER_MAX_VALUE = 15;
  private static int DEFAULT_NODE_NUMBER_MIN_VALUE = 5;

  private static int NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE = 7;

  final private int lengthGeneratedMaxValue;
  final private int lengthGeneratedMinValue;

  final private int nodeNumMax;
  final private int nodeNumMin;

  public GraphGenerator(int maxLengthGenerated, int minLengthGenerated,
                        int maxNodeNum, int minNodeNum) {
    lengthGeneratedMaxValue = maxLengthGenerated;
    lengthGeneratedMinValue = minLengthGenerated;
    nodeNumMax = maxNodeNum;
    nodeNumMin = minNodeNum;
  }
  public GraphGenerator() {
    this(DEFAULT_LENGTH_GENERATED_MAX_VALUE, DEFAULT_LENGTH_GENERATED_MIN_VALUE,
        DEFAULT_NODE_NUMBER_MAX_VALUE, DEFAULT_NODE_NUMBER_MIN_VALUE);
  }

  public Graph makeRandomConnectedGraph() throws Exception {
    Random random = new Random();
    int nodeNum = getNumberOfNodes(random);
    List<Integer> unconnected = new ArrayList<>();
    List<Integer> connected = new ArrayList<>();
    for (int i = 1; i <= nodeNum; i++) {
      unconnected.add(i);
    }
    // Connect 1 node
    int root = getRandomNode(random, nodeNum);
    connected.add(root);
    unconnected.remove(new Integer(root));

    List<Edge> edgesCreated = generateRandomEdgesAndUpdateConnectedLists(random, connected, unconnected, nodeNum);
    edgesCreated.addAll(ensureConnected(random, unconnected, connected));

    return new Graph.Builder()
        .withNodeNum(nodeNum)
        .withEdges(edgesCreated.toArray(new Edge[]{}))
        .build();
  }

  private int getNumberOfNodes(Random random) {
    return random.nextInt(nodeNumMax - nodeNumMin) + nodeNumMin;
  }

  private List<Edge> generateRandomEdgesAndUpdateConnectedLists(Random random,
                                                                List<Integer> connected, List<Integer> unconnected,
                                                                int nodeNum) {
    if (nodeNum <= 2) {
      throw new IllegalArgumentException("Node number must be more than 2");
    }
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE; i++) {
      int start = getRandomNode(random, nodeNum);
      int end = getRandomNode(random, nodeNum);
      while (start == end) {
        end = getRandomNode(random, nodeNum);
      }
      int length = getRandomLength(random);
      Edge edge = new Edge(start, end, length);
      edges.add(edge);
      updateConnectivityWithEdge(connected, unconnected, edge);
    }
    return edges;
  }

  private void updateConnectivityWithEdge(List<Integer> connected, List<Integer> unconnected,
                                                 Edge edge) {
    if (connected.contains(edge.getSource()) && !connected.contains(edge.getDestination())) {
      unconnected.remove(new Integer(edge.getDestination()));
      connected.add(edge.getSource());
    } else if (connected.contains(edge.getDestination()) && !connected.contains(edge.getSource())) {
      unconnected.remove(new Integer(edge.getSource()));
      connected.remove(new Integer(edge.getDestination()));
    }
  }


  private List<Edge> ensureConnected(Random random,
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

  private int getRandomLength(Random random) {
    return random.nextInt(lengthGeneratedMaxValue - lengthGeneratedMinValue)
        + lengthGeneratedMinValue;
  }

  private int getRandomNode(Random random, int nodeNum) {
      return random.nextInt(nodeNum) + 1;
  }
}
