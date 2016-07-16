package structures;

import java.util.Set;
import java.util.TreeSet;

public class DisjointSetTrackingChildren extends DisjointSet {
  private Set<DisjointSet> children = new TreeSet<>();

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
    DisjointSetTrackingChildren disjointSet = (DisjointSetTrackingChildren) super.findParent();
    if (disjointSet == this) {
      children.add(other);
    } else {
      disjointSet.children.add(this);
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
