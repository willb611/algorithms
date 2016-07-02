package structures.graph;

public class Edge implements Comparable<Edge> {
  final public int start, end, val;

  public Edge(int startParam, int endParam, int length) {
    start = startParam;
    end = endParam;
    val = length;
  }

  public String toString() {
    return "start: " + start + " end: " + end + " val: " + val;
  }

  public int compareTo(Edge other) {
    return this.val - other.val;
  }
}
