package algorithms.kruskals;

class DisjointSet {
  private DisjointSet parent;
  private int id;
  public DisjointSet(int givenId) {
    id = givenId;
    parent = this;
  }
  public DisjointSet findParent(){
    if (parent == this) return this;
    else return parent.findParent();
  }
  public void union(DisjointSet other) {
    other.parent = findParent();
  }

  public int getId() {
    return id;
  }
}
