package structures.graph.generation;

import org.junit.Test;
import structures.graph.UnDirectedGraph;

import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GraphGeneratorTest {
  @Test
  public void makeRandomConnectedGraph() throws Exception {
    GraphGenerator graphGenerator = new GraphGenerator();
    UnDirectedGraph graph = graphGenerator.makeRandomConnectedGraph();
    assertNotNull(graph);
  }

  @Test
  public void getRandomInRange() throws Exception {
    int max = 5;
    int min = 2;
    Random random = new Random();
    Range range = new Range(2, 5);
    GraphGenerator graphGenerator = new GraphGenerator();
    for (int i = 0; i < 100; i++) {
      int val = graphGenerator.getRandomInRange(random, range);
      assertTrue(String.format("Val: %s must be less than max: %s", val, max),
          val <= max);
      assertTrue(String.format("Val: %s must be greater than min: %s", val, max),
          val >= min);
    }
  }
}