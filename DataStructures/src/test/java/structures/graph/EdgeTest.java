package structures.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EdgeTest {
  @Test
  public void sourceOrDestinationButNotBothContainedIn_containsBoth() throws Exception {
    Edge e = new Edge(1, 2, 3);
    List<Integer> collectionWithBoth = Arrays.asList(new Integer[]{1,2});
    Assert.assertFalse(e.sourceOrDestinationButNotBothContainedIn(collectionWithBoth));
  }

  @Test
  public void sourceOrDestinationButNotBothContainedIn_containsNone() {
    Edge e = new Edge(1, 2, 3);
    List<Integer> integers = Arrays.asList(new Integer[]{});
    Assert.assertFalse(e.sourceOrDestinationButNotBothContainedIn(integers));
  }

  @Test
  public void sourceOrDestinationButNotBothContainedIn_containsSource() {
    Edge e = new Edge(1, 2, 3);
    List<Integer> integers = Arrays.asList(new Integer[]{1});
    Assert.assertTrue(e.sourceOrDestinationButNotBothContainedIn(integers));
  }

  @Test
  public void sourceOrDestinationButNotBothContainedIn_containsDestination() {
    Edge e = new Edge(1, 2, 3);
    List<Integer> integers = Arrays.asList(new Integer[]{2});
    Assert.assertTrue(e.sourceOrDestinationButNotBothContainedIn(integers));
  }

  @Test
  public void sourceOrDestinationButNotBothContainedIn_containsLength() {
    Edge e = new Edge(1, 2, 3);
    List<Integer> integers = Arrays.asList(new Integer[]{3});
    Assert.assertFalse(e.sourceOrDestinationButNotBothContainedIn(integers));
  }
}