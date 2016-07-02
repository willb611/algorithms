package algorithms.kruskals;

class Edge implements Comparable<Edge>
{
  public int start, end, val;
  public Edge(int startParam, int endParam, int length) {
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
