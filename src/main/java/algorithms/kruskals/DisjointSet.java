package algorithms.kruskals;

class DisjointSet {
  DisjointSet parent;
  int id;
  public DisjointSet(int givenID) {
    id = givenID;
    parent = this;
  }
  DisjointSet findParent(){
    if (parent == this) return this;
    else return parent.findParent();
  }
  void union(DisjointSet other)
  {
    other.parent = findParent();
  }
}
