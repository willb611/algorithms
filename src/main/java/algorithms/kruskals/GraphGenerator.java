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
  public GraphGenerator(String filenameToWriteTo) {
    dataFile = filenameToWriteTo;
  }

  public void makeRandomTree() throws Exception
  {
    Random r = new Random();
    int nodeNum = r.nextInt(10)+5;
    List<Node> unconnected = new ArrayList<>();
    List<Node> connected = new ArrayList<>();
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
    while (!unconnected.isEmpty()) {
      Node nextNode = unconnected.get(r.nextInt(unconnected.size()));
      Node startNode = connected.get(r.nextInt(connected.size()));
      int length = r.nextInt(LENGTH_GENERATED_MAX_VALUE - LENGTH_GENERATED_MIN_VALUE) + LENGTH_GENERATED_MIN_VALUE;
      ps.printf("%d %d %d\n", startNode.tag, nextNode.tag, length);
      unconnected.remove(nextNode);
      connected.add(nextNode);
    }
    // Minimum spanning tree should be formed by this point.

    ps.flush();
    ps.close();
  }
}
