package algorithms.graphs.kruskals.example;

import algorithms.graphs.kruskals.Kruskals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import structures.graph.UnDirectedGraph;
import structures.graph.generation.GraphGenerator;
import structures.graph.persistence.GraphSaver;

public class KruskalsExampleUse {
  private static Logger logger = LoggerFactory.getLogger(KruskalsExampleUse.class);

  public static void main(String[] ags) throws Exception {
    test(new GraphSaver());
  }

  static void test(GraphSaver graphSaver) throws Exception {
    UnDirectedGraph randomConnectedUnDirectedGraph = new GraphGenerator().makeRandomConnectedGraph();
    logger.info(randomConnectedUnDirectedGraph.toStringWithEdgesSortedInAscendingOrder());
    logger.info("Attempting to findMinimumSpanningTreeForGraph");
    graphSaver.saveGraph("kruskals.generated", randomConnectedUnDirectedGraph);

    Kruskals ks = new Kruskals();
    UnDirectedGraph mst = ks.findMinimumSpanningTreeForGraph(randomConnectedUnDirectedGraph);
    logger.info("Printing mst");
    logger.info(mst.toString());

    graphSaver.saveGraph("kruskals.solved", mst);
  }

}
