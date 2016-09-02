package structures;

import org.junit.Assert;
import org.junit.Test;

public class DisjointSetTest {
  @Test
  public void findParent() throws Exception {
    DisjointSet disjointSet = new DisjointSet(1);
    Assert.assertEquals("new disjoint sets should be their own parent",
        disjointSet, disjointSet.findParent());
  }

  @Test
  public void union() throws Exception {
    DisjointSet a = new DisjointSet(1);
    DisjointSet b = new DisjointSet(2);
    a.union(b);

    Assert.assertEquals("Union with another disjoint set should cause the parent of the other" +
        " to become the parent of this",
        a, b.findParent());
  }

  @Test
  public void makeUnconnectedForest() throws Exception {
    int size = 15;
    DisjointSet[] arr = DisjointSet.makeUnconnectedForest(size);
    Assert.assertEquals(size, arr.length);
  }

  @Test
  public void makeUnconnectedForest_other() throws Exception {
    int size = 5;
    DisjointSet[] arr = DisjointSet.makeUnconnectedForest(size);
    Assert.assertEquals(size, arr.length);
  }
}