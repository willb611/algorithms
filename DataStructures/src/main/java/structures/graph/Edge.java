package structures.graph;

import java.util.Collection;
import java.util.List;

public class Edge implements Comparable<Edge> {
  private final int source;
  private final int destination;
  private final int length;

  public Edge(int startParam, int endParam, int length) {
    source = startParam;
    destination = endParam;
    this.length = length;
  }

  public int getLength() {
    return length;
  }

  @Override
  public String toString() {
    return "source: " + getSource() + " destination: " + getDestination() + " length: " + getLength();
  }

  public int compareTo(Edge other) {
    return this.getLength() - other.getLength();
  }

  public int getSource() {
    return source;
  }

  public int getDestination() {
    return destination;
  }

  public boolean sourceOrDestinationButNotBothContainedIn(Collection<Integer> integers) {
    if (integers.contains(getSource())) {
      return !integers.contains(getDestination());
    } else {
      return integers.contains(getDestination());
    }
  }
}
