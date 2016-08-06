package algorithms;

public class BinomialCoefficientAdditive {
  private long[][] vals = new long[Integer.MAX_VALUE][Integer.MAX_VALUE];
  public long nChooseK(int n, int k) {
    if (vals[n][k] == -1) {
      long res = nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
      if (res < 0) {
        throw new RuntimeException("Long overflow!");
      }
      vals[n][k] = res;
      return res;
    } else {
      return vals[n][k];
    }
  }
}
