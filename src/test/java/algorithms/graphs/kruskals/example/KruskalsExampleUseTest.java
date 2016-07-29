package algorithms.graphs.kruskals.example;

import org.junit.Test;
import structures.graph.UnDirectedGraph;
import structures.graph.persistence.GraphSaver;

import java.io.FileNotFoundException;

public class KruskalsExampleUseTest {
  @Test
  public void main() throws Exception {
    KruskalsExampleUse.test(new GraphSaverStub());
  }

  public class GraphSaverStub extends GraphSaver {
    @Override
    public void saveGraph(String baseFileName, UnDirectedGraph graph) throws FileNotFoundException {
    }

    @Override
    public void saveGraph(String baseFileName, UnDirectedGraph graph, Integer source, Integer sink) throws FileNotFoundException {
    }
  }

}