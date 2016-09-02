package structures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DisjointSetTrackingChildren extends DisjointSet {
  private static Logger logger = LoggerFactory.getLogger(DisjointSetTrackingChildren.class);

  private Set<DisjointSet> children = new HashSet<>();

  public DisjointSetTrackingChildren(int givenId) {
    super(givenId);
  }

  public Set<DisjointSet> getChildren() {
    return children;
  }

  public static DisjointSetTrackingChildren[] makeUnconnectedForest(int size) {
    DisjointSetTrackingChildren[] forest = new DisjointSetTrackingChildren[size];
    for (int i = 0; i < size; i++) {
      forest[i] = new DisjointSetTrackingChildren(i);
    }
    return forest;
  }

  @Override
  public void union(DisjointSet other) throws ClassCastException {
    super.union(other);
    DisjointSet parent = findParent();
    if (parent instanceof DisjointSetTrackingChildren) {
      DisjointSetTrackingChildren parentAsThisType = (DisjointSetTrackingChildren)parent;
      if (parent == this) {
        children.add(other);
      } else {
        parentAsThisType.children.add(this);
      }
    } else {
      logger.warn("[union] Tried to union DisjointSetTrackingChildren with disjointSet");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DisjointSetTrackingChildren)) return false;

    DisjointSetTrackingChildren that = (DisjointSetTrackingChildren) o;

    return children.equals(that.children) && getId() == that.getId();
  }

  @Override
  public int hashCode() {
    return children.hashCode() * 3 + getId() * 5;
  }


}