package algorithms.kruskals;

import java.util.*;
import java.io.*;
// TODO clean up "solve" method, eliminate single line comments
public class Kruskals
{
  static String dataFile = "in.txt";
  int MAX_VALUE = 150;
  int MIN_VALUE = 50;
  // Builds a random tree
  void makeRandomData() throws Exception
  {
    Random r = new Random();
    int nodeNum = r.nextInt(10)+5;
    List<Node> unconnected = new ArrayList<Node>();
    List<Node> connected = new ArrayList<Node>();
    PrintStream ps = new PrintStream(new File(dataFile));
    ps.println(nodeNum);
    
    for (int i = 1; i <= nodeNum; i++) {
      Node newNode = new Node(i);
      for (int j=0;j<r.nextInt(3)+1;j++) {
        unconnected.add(newNode);
      }
    }
    System.out.println("Made " + unconnected.size() + " nodes!");
    // Add an edge so that each node is connected
    Node root = unconnected.get(r.nextInt(unconnected.size()));
    unconnected.remove(root);
    connected.add(root);
    while(unconnected.size() >0) {
      Node nextNode = unconnected.get(r.nextInt(unconnected.size()));
      Node startNode = connected.get(r.nextInt(connected.size()));
      int length = r.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
      ps.printf("%d %d %d\n", startNode.tag, nextNode.tag, length);
      unconnected.remove(nextNode);
      connected.add(nextNode);
    }
    // Minimum spanning tree should be formed by this point.

    ps.flush();
    ps.close();
  }
  static void test() throws Exception
  {
    Kruskals ks = new Kruskals();
    ks.makeRandomData();
    Graph graph = Graph.parseData(dataFile);
    System.out.println(graph);
    System.out.println("Attempting to solve");

    Graph mst = ks.solve(graph);
    System.out.println("Printing mst");
    System.out.println(mst);

    KruskalsGraphOutput go = new KruskalsGraphOutput(mst);
    String result = go.outputToGivenFile();
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

  /**
  * Solves using a list and disjoint set trees
  */
  Graph solve(Graph graph) {
    Edge[] arr = graph.getEdges();
    Arrays.sort(arr);
    DisjointSet[] forest = new DisjointSet[graph.getNodeNum() + 1];
    for (int i=0;i<forest.length;i++) {
      forest[i] = new DisjointSet(i);
    }
    List<Edge> mst = new ArrayList<>(graph.getNodeNum() - 1);
    // Try all edges, in ascending order of edge weight
    for(int i=0;i<arr.length;i++) {
      // if it doesnt form a cycle, add it
      if(forest[arr[i].start].findParent() != forest[arr[i].end].findParent()) {
        // Connect these two nodes.
        forest[arr[i].start].union(forest[arr[i].end].findParent());
        mst.add(arr[i]);
      }
    }
    if (mst.size() != graph.getNodeNum() - 1) {
      throw new NullPointerException("Error, provided graph was not connected,  " + mst.size());
    }
    Graph resultingTree = new Graph();
    resultingTree.setNodeNum(graph.getNodeNum());
    resultingTree.setEdges(mst.toArray(new Edge[mst.size()]));
    return resultingTree;
  }
}