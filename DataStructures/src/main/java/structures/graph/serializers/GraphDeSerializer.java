package structures.graph.serializers;

import structures.graph.Edge;
import structures.graph.Graph;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Integer.parseInt;

/**
 * Data should be in a text file
 * First line contains the # of nodes
 * Rest of the lines contain edges:
 * <p>source Node #, end Node #, length.</p>
 */
public class GraphDeSerializer {
  public static Graph fromFileName(String fileName) throws IOException {
    return fromFile(new File(fileName));
  }

  public static Graph fromFile(File file) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      return fromBufferedReader(bufferedReader);
    }
  }

  public static Graph fromBufferedReader(BufferedReader bufferedReader) throws IOException {
    return fromLines(getLinesFromBufferedReader(bufferedReader));
  }

  private static Graph fromLines(Queue<String> lines) {
    int nodeNum = parseInt(lines.poll());
    List<Edge> list = new ArrayList<>();
    while (!lines.isEmpty()) {
      String[] info = lines.poll().split(" ");
      int startNodeNum = parseInt(info[0]);
      int endNodeNum = parseInt(info[1]);
      int length = parseInt(info[2]);
      Edge e = new Edge(startNodeNum, endNodeNum, length);
      list.add(e);
    }
    Edge[] edges = list.toArray(new Edge[0]);
    return new Graph.Builder().withEdges(edges).withNodeNum(nodeNum).build();
  }

  private static Queue<String> getLinesFromBufferedReader(BufferedReader bufferedReader) throws IOException {
    Queue<String> lines = new ArrayDeque<>();
    try {
      while (bufferedReader.ready()) {
        lines.add(bufferedReader.readLine());
      }
    } finally {
      bufferedReader.close();
    }
    return lines;
  }
}
