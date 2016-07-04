package structures.graph;

public class Edge implements Comparable<Edge> {
  private final int source;
  private final int end;
  private final int length;

  public Edge(int startParam, int endParam, int length) {
    source = startParam;
    end = endParam;
    this.length = length;
  }

  public int getLength() {
    return length;
  }

  public String toString() {
    return "source: " + getSource() + " end: " + getEnd() + " length: " + getLength();
  }

  public int compareTo(Edge other) {
    return this.getLength() - other.getLength();
  }

  public int getSource() {
    return source;
  }

  public int getEnd() {
    return end;
  }
}
