package algorithms.graphs.flow.example

import algorithms.graphs.flow.FordFulkerson
import structures.graph.UnDirectedGraph
import structures.graph.generation.GraphGenerator
import structures.graph.persistence.{GraphDeSerializer, GraphSaver}

object FordFulkersonExample extends App {
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
    GraphSaver.saveGraph(prefix, graph, source, sink)
    System.out.println("Source: " + source + ", destination: " + sink)
    val maxFlow = new FordFulkerson().maxFlow(graph, source, sink)
    System.out.println("MaxFlow: " + maxFlow)
  }

}
