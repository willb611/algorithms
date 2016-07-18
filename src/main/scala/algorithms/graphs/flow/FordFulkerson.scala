package algorithms.graphs.flow

import internal.Preconditions
import structures.graph.UnDirectedGraph

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class FordFulkerson {
  private val NO_PREVIOUS_NODE_IN_FLOW = -1

  def maxFlow(graph: UnDirectedGraph, source: Int, destination: Int): Int = {
    Preconditions.checkThat(source <= graph.getNodeNum)
    Preconditions.checkThat(destination <= graph.getNodeNum)
    Preconditions.checkThat(source != destination)
    val capacity = getInitialCapacity(graph)
    var result = 0
    var capacityToGain = Int.MaxValue
    while (capacityToGain > 0) {
      capacityToGain = findCapacityOfAugmentingPath(graph, source, destination, capacity)
      result += capacityToGain
    }
    result
  }

  private def findCapacityOfAugmentingPath(graph: UnDirectedGraph, source: Int,
                                           destination: Int, capacity: Array[Array[Int]]): Int = {
    val visited = new Array[Boolean](graph.getNodeNum + 1)
    val from = Array.fill[Int](graph.getNodeNum + 1)(NO_PREVIOUS_NODE_IN_FLOW)
    visited(source) = true
    val queue = new mutable.Queue[Int]
    queue += source
    while (queue.nonEmpty) {
      val where: Int = queue.dequeue
      for (adjacent: Int <- getAdjacent(graph, where)
          if !visited(adjacent)
          if capacity(where)(adjacent) > 0) {
        queue += adjacent
        visited(adjacent) = true
        from(adjacent) = where
        if (destination == adjacent) {
          queue.clear()
        }
      }
    }
    val pathCapacity = getAugmentedPathCapacity(capacity, from, destination)
    updateResidualNetworkCapacity(capacity, from, destination, pathCapacity)
    pathCapacity
  }

  def getAdjacent(graph: UnDirectedGraph, where: Int): Array[Int] = {
    var edges = ArrayBuffer.empty[Int]
    for (edge <- graph.getEdges) {
      if (edge.getDestination == where
          && edge.getSource != where) {
        edges += edge.getSource
      } else if (edge.getSource == where
          && edge.getDestination != where) {
        edges += edge.getDestination
      }
    }
    edges.toArray
  }

  private def getInitialCapacity(graph: UnDirectedGraph): Array[Array[Int]] = {
    val capacity: Array[Array[Int]] = Array.fill[Int](graph.getNodeNum + 1, graph.getNodeNum + 1)(0)
    for (edge <- graph.getEdges) {
      val existing = capacity(edge.getSource)(edge.getDestination)
      // model multiple edges from a -> b as a single edge of combined weight
      val newCapacity = existing + edge.getLength
      capacity(edge.getSource)(edge.getDestination) = newCapacity
      capacity(edge.getDestination)(edge.getSource) = newCapacity
    }
    capacity
  }

  private def getAugmentedPathCapacity(capacity: Array[Array[Int]],
                                      from: Array[Int],
                                      destination: Int): Int = {
    var pathCapacity = Int.MaxValue
    var where = destination
    var prev = from(where)
    while (prev != NO_PREVIOUS_NODE_IN_FLOW) {
      val capacityFromThisEdge = capacity(prev)(where)
      pathCapacity = StrictMath.min(pathCapacity, capacityFromThisEdge)
      where = prev
      prev = from(where)
    }
    if (where == destination) { // destination was not reachable
      pathCapacity = 0
    }
    pathCapacity
  }

  private def updateResidualNetworkCapacity(capacity: Array[Array[Int]],
                             from: Array[Int],
                             destination: Int,
                             pathCapacity: Int) = {
    var where = destination
    while (from(where) != NO_PREVIOUS_NODE_IN_FLOW) {
      val prev = from(where)
      capacity(prev)(where) -= pathCapacity
      capacity(where)(prev) += pathCapacity
      where = prev
    }
  }
}
