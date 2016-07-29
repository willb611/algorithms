package algorithms.graphs.flow.example

import algorithms.graphs.flow.FordFulkerson
import org.slf4j.LoggerFactory
import structures.graph.UnDirectedGraph
import structures.graph.generation.GraphGenerator
import structures.graph.persistence.GraphSaver

object FordFulkersonExample extends App {
  var logger = LoggerFactory.getLogger(this.getClass);
  var prefix = "fordFulkerson"
  runExample()

  def getGraph(generator: GraphGenerator): UnDirectedGraph = {
//    GraphDeSerializer.fromFileName(prefix + ".original")
    generator.makeRandomConnectedGraph()
  }

  def runExample(): Unit = {
    val generator = new GraphGenerator(15, 1, 5, 3)
    val graph = getGraph(generator)
    val source = generator.getRandomNode(graph.getNodeNum)
    var sink = generator.getRandomNode(graph.getNodeNum)
    while (sink == source) {
      sink = generator.getRandomNode(graph.getNodeNum)
    }
    new GraphSaver().saveGraph(prefix, graph, source, sink)
    logger.info("Source: " + source + ", destination: " + sink)
    val maxFlow = new FordFulkerson().maxFlow(graph, source, sink)
    logger.info("MaxFlow: " + maxFlow)
  }

}
