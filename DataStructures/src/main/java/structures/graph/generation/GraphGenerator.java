package structures.graph.generation;

import structures.graph.Edge;
import structures.graph.UnDirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {
  private static final int DEFAULT_LENGTH_GENERATED_MAX_VALUE = 150;
  private static final int DEFAULT_LENGTH_GENERATED_MIN_VALUE = 50;

  private static final int DEFAULT_NODE_NUMBER_MAX_VALUE = 15;
  private static final int DEFAULT_NODE_NUMBER_MIN_VALUE = 5;

  private static final int NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE = 7;

  final private Range lengthGeneratedRange;
  final private Range nodeNumberGeneratedRange;

  private final Random random = new Random();

  public GraphGenerator(Range rangeOfEdgeLengthToGenerate,
                        Range rangeOfNodeNumberToGenerate) {
    if (rangeOfNodeNumberToGenerate.getMin() <= 1) {
      throw new IllegalArgumentException("Range for node number must have min greater than 1. Given: "
          + rangeOfNodeNumberToGenerate.getMin());
    }
    lengthGeneratedRange = rangeOfEdgeLengthToGenerate;
    nodeNumberGeneratedRange = rangeOfNodeNumberToGenerate;
  }
  public GraphGenerator() {
    this(new Range(DEFAULT_LENGTH_GENERATED_MIN_VALUE, DEFAULT_LENGTH_GENERATED_MAX_VALUE),
        new Range(DEFAULT_NODE_NUMBER_MIN_VALUE, DEFAULT_NODE_NUMBER_MAX_VALUE));
  }

  public UnDirectedGraph makeRandomConnectedGraph() throws Exception {
    int nodeNum = getNumberOfNodes(random);
    List<Integer> unconnected = new ArrayList<>();
    List<Integer> connected = new ArrayList<>();
    for (int i = 1; i <= nodeNum; i++) {
      unconnected.add(i);
    }
    // Connect 1 node
    int root = getRandomNode(random, nodeNum);
    connected.add(root);
    unconnected.remove(Integer.valueOf(root));

    List<Edge> edgesCreated = generateRandomEdgesAndUpdateConnectedLists(random, connected, unconnected, nodeNum);
    edgesCreated.addAll(ensureConnected(random, unconnected, connected));

    return new UnDirectedGraph.Builder()
        .withNodeNum(nodeNum)
        .withEdges(edgesCreated.toArray(new Edge[]{}))
        .build();
  }

  private List<Edge> generateRandomEdgesAndUpdateConnectedLists(Random random,
                                                                List<Integer> connected, List<Integer> unconnected,
                                                                int numberOfNodesInGraph) {
    if (numberOfNodesInGraph <= 2) {
      throw new IllegalArgumentException("Node number must be more than 2");
    }
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_EDGES_JOINING_RANDOM_NODES_TO_CREATE; i++) {
      int start = getRandomNode(random, numberOfNodesInGraph);
      int end = getRandomNode(random, numberOfNodesInGraph);
      while (start == end) {
        end = getRandomNode(random, numberOfNodesInGraph);
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
      unconnected.remove(Integer.valueOf(edge.getDestination()));
      connected.add(edge.getSource());
    } else if (connected.contains(edge.getDestination()) && !connected.contains(edge.getSource())) {
      unconnected.remove(Integer.valueOf(edge.getSource()));
      connected.add(edge.getDestination());
    }
  }


  private List<Edge> ensureConnected(Random random,
                                     List<Integer> unconnected, List<Integer> connected) {
    if (connected == null || connected.size() <= 0) {
      throw new IllegalArgumentException("Must be given some connected nodes");
    }
    List<Edge> edges = new ArrayList<>();
    while (!unconnected.isEmpty()) {
      Integer nextNodeToBeConnected = unconnected.get(random.nextInt(unconnected.size()));
      Integer alreadyConnectedNode = connected.get(random.nextInt(connected.size()));
      int length = getRandomLength(random);
      edges.add(new Edge(alreadyConnectedNode, nextNodeToBeConnected, length));
      unconnected.remove(nextNodeToBeConnected);
      connected.add(nextNodeToBeConnected);
    }
    return edges;
  }

  private int getRandomLength(Random random) {
    return getRandomInRange(random, lengthGeneratedRange);
  }
  private int getNumberOfNodes(Random random) {
    return getRandomInRange(random, nodeNumberGeneratedRange);
  }

  public int getRandomInRange(Random random, Range range) {
    return random.nextInt(range.getMax() - range.getMin()) + range.getMin();
  }

  public int getRandomNode(int maximumNodeNumber) {
    return getRandomNode(random, maximumNodeNumber);
  }
  public int getRandomNode(Random random, int maximumNodeNumber) {
      return random.nextInt(maximumNodeNumber) + 1;
  }
}
