package algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
// TODO remove all the comments, extract some methods, etc...
public class Dijkstra 
{
  public static void main(String[] args) throws Exception {
    Dijkstra d = new Dijkstra("dijkstra.in");
  }

  private int nodeNumber;
  private Map<Integer, int[]> edges = new HashMap<>();
  private int[] dist;
  private int[] parent;

  private void initializeEdges(int nodeNumber) {
    for (Integer i = 0; i < nodeNumber; i++) {
      int[] tmp = new int[nodeNumber];
      Arrays.fill(tmp, -1);
      edges.put(i, tmp);
    }
  }

  // Use A as the source... the first one from input
  public Dijkstra(String inputFile) throws Exception {
    FileReader fr = new FileReader(inputFile);
    BufferedReader input = new BufferedReader(fr);
    nodeNumber = Integer.parseInt(input.readLine());
    initializeEdges(nodeNumber);
    for (Integer currSource=0; currSource < nodeNumber; currSource++)
    {
      int[] arOfEdgesLeavingThisNode = edges.get(currSource);
      int totalEdgesFromNode = Integer.parseInt(input.readLine().trim());
      String[] lineInputAfterSplit = input.readLine().split("\\s+");
      int currentSourceNode = 0;
      // Loop through all of the given edges
      for (int j = 0; j < totalEdgesFromNode * 2; j += 2) {
        while (currentSourceNode + 'A' < lineInputAfterSplit[j].charAt(0)) {
          currentSourceNode++;
        }
        arOfEdgesLeavingThisNode[currentSourceNode] = Integer.parseInt(lineInputAfterSplit[j+1]);
        currentSourceNode++;
      }
    }
    // Read all input.
    debugInput();
    dist = new int[nodeNumber];
    parent = new int[nodeNumber];
    
    // findMinimumSpanningTreeForGraph the thing
    solve(0);
    System.out.println("Solved, printing results");
    for (int i = 0; i < nodeNumber; i++) {
      System.out.println("Min distance to " + i + " from source is: " + sourceToNode(i));
    }
  } // main

  private void debugInput() {
    for (Integer currNode = 0; currNode < edges.size(); currNode++) {
      System.out.println("Edges leaving node " + currNode + " are as follows:");
      int[] stuff = edges.get(currNode);
      for (int target = 0; target < stuff.length; target++)
        System.out.println("To node " + target + ": "+ stuff[target]);
    }
  }

  // use s as the source node... this is assumed, & if you use something
  // else the program may break... I'm not sure
  private void solve(int s) {
    for (int i=0; i< nodeNumber; i++ ) {
      dist[i] = Integer.MAX_VALUE; // so that all connections overwrite this
      parent[i] = -1; // to mark that it has no parent as of yet...
    }
    dist[s] = 0;

    ArrayList<Integer> nodesLeft = new ArrayList<>();
    nodesLeft.add(0);

    // While nodes need to be updated
    while (!nodesLeft.isEmpty()) {
      Integer v = nodesLeft.get(0);
      nodesLeft.remove(v);

      /* Loop through all the edges leaving from this node 
      If its quicker to go to their via this node thne update that node,
      re-add that node to check all edges leading from that node */
      int[] leavingV = edges.get(v);
      for (Integer u = 0; u < leavingV.length; u++) {
        if(leavingV[u] == -1) continue; // 
        Integer d = dist[v] + leavingV[u];
        if (d < dist[u]) {
          nodesLeft.remove(u);
          dist[u] = d;
          nodesLeft.add(u);
          parent[u] = v;
        } // if
      } // for
    } // while nodesleft

  } // method findMinimumSpanningTreeForGraph

  // Returns the shortest route to a node...
  public int sourceToNode(int currNode) {
    return dist[currNode];
  }
}