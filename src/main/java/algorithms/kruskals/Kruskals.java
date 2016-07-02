package algorithms.kruskals;

import java.util.*;
import java.io.*;
// TODO clean up "solve" method, eliminate single line comments
public class Kruskals
{
  class Edge implements Comparable<Edge> // TODO extracted into own class ?
  {
    public int start, end, val;
    public Edge(int startParam, int endParam, int length) { // TODO should it be length ? Or generefied ?
      start = startParam;
      end = endParam;
      val = length;
    }
    public String toString(){return "start: " + start + " end: " + end + " val: " + val;}
    public int compareTo(Edge o) 
    {
      return this.val - o.val;
    }
  }
  class Node
  {
    int tag;
    public Node(int tagParam) { // TODO also extract this ? 
      tag = tagParam;
    }
  }
  String dataFile = "in.txt";
  int MAX_VALUE = 150;
  int MIN_VALUE = 50;
  int globNodeNum;
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
  int i(String s) throws Exception
  {
    return Integer.parseInt(s);
  }
  static void test() throws Exception
  {
    Kruskals ks = new Kruskals();
    ks.makeRandomData();
    Edge[] data = ks.parseData();
    System.out.println("Printing generated data");
    Arrays.sort(data);
    for(int i=0; i<data.length;i++) {
      System.out.println(data[i]);
    }
    System.out.println("Attempting to solve");
    Edge[] mst = ks.solve(data);
    System.out.println("Printing mst");
    for(int i=0; i<mst.length;i++) {
      System.out.println(mst[i]);
    }
    KruskalsGraphOutput go = new KruskalsGraphOutput(mst);
    String result = go.output();
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
  * Data should be in a text file, see variable dataFile
  * First line contains the number of nodes
  * Rest of the lines contain edges:
  * <p>start Node #, end Node #, length.</p>
  */
  Edge[] parseData() throws Exception
  {
    BufferedReader br = new BufferedReader(new FileReader(dataFile));
    int nodeNum = Integer.parseInt(br.readLine());
    NODE_NUM = nodeNum;
    List<Edge> list = new ArrayList<Edge>();
    while (br.ready()) {
      String[] info = br.readLine().split(" ");
      Edge e = new Edge(i(info[0]),i(info[1]),i(info[2])); // TODO what even
      list.add(e);
    }
    br.close();
    return list.toArray(new Edge[0]);
  }
  int NODE_NUM;
  /**
  * Solves using a list and disjoint set trees
  */
  Edge[] solve(Edge[] arr) {
    Arrays.sort(arr);
    DisjointSet[] forest = new DisjointSet[NODE_NUM + 1];
    for (int i=0;i<forest.length;i++) {
      forest[i] = new DisjointSet(i);
    }
    List<Edge> mst = new ArrayList<Edge>(NODE_NUM-1);
    // Try all edges, in ascending order of edge weight
    for(int i=0;i<arr.length;i++) {
      // if it doesnt form a cycle, add it
      if(forest[arr[i].start].findParent() != forest[arr[i].end].findParent()) {
        // Connect these two nodes.
        forest[arr[i].start].union(forest[arr[i].end].findParent());
        mst.add(arr[i]);
      }
    }
    if (mst.size() != NODE_NUM-1) throw new NullPointerException("Error, need moar edges,  " + mst.size());
    return mst.toArray(new Edge[mst.size()]);
  }
}