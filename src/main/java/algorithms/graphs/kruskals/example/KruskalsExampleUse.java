package algorithms.graphs.kruskals.example;

import algorithms.graphs.kruskals.Kruskals;
import structures.graph.UnDirectedGraph;
import structures.graph.generation.GraphGenerator;
import structures.graph.persistence.GraphSaver;

public class KruskalsExampleUse {

  public static void main(String[] ags) throws Exception {
    test();
  }

  static void test() throws Exception {
    UnDirectedGraph randomConnectedUnDirectedGraph = new GraphGenerator().makeRandomConnectedGraph();
    System.out.println(randomConnectedUnDirectedGraph.toStringWithEdgesSortedInAscendingOrder());
    System.out.println("Attempting to findMinimumSpanningTreeForGraph");
    GraphSaver.saveGraph("kruskals.generated", randomConnectedUnDirectedGraph);

    Kruskals ks = new Kruskals();
    UnDirectedGraph mst = ks.findMinimumSpanningTreeForGraph(randomConnectedUnDirectedGraph);
    System.out.println("Printing mst");
    System.out.println(mst);

    GraphSaver.saveGraph("kruskals.solved", mst);
  }

}
