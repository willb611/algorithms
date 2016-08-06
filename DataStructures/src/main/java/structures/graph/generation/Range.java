package structures.graph.generation;

public class Range {
  private final int min, max;

  public Range(int min, int max) {
    if (min == max) {
      throw new IllegalArgumentException("Max not equal min");
    } else if (max < min) {
      this.min = max;
      this.max = min;
    } else {
      this.min = min;
      this.max = max;
    }
  }
  public Range() {
    this(0, 100);
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }
}
