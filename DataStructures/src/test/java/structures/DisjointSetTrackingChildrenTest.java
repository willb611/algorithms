package structures;

import org.junit.Assert;
import org.junit.Test;

public class DisjointSetTrackingChildrenTest {
  @Test
  public void union_whenSameType() throws Exception {
    DisjointSetTrackingChildren a = new DisjointSetTrackingChildren(1);
    DisjointSetTrackingChildren aRelative = new DisjointSetTrackingChildren(2);
    aRelative.union(a);

    DisjointSetTrackingChildren b = new DisjointSetTrackingChildren(3);
    b.union(a);
    Assert.assertTrue(a.getChildren().contains(aRelative)
                      || aRelative.getChildren().contains(a));
    Assert.assertTrue(b.getChildren().contains(a)
        || a.getChildren().contains(b));
  }

  @Test
  public void union_whenBaseType_shouldNotThrowException() {
    DisjointSet baseType = new DisjointSet(1);
    DisjointSetTrackingChildren extended = new DisjointSetTrackingChildren(1);
    DisjointSetTrackingChildren otherExtended = new DisjointSetTrackingChildren(2);
    otherExtended.union(extended);
    extended.union(otherExtended);

    baseType.union(otherExtended);
    otherExtended.union(baseType);
    baseType.union(extended);
  }

}