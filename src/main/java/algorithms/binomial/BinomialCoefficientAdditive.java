package algorithms.binomial;

import java.util.Arrays;

public class BinomialCoefficientAdditive implements BinomialCoefficient {
  private static final int arraySize = 1000;
  private final long[][] calculatedValues = new long[arraySize][arraySize];

  public BinomialCoefficientAdditive() {
    for (int i = 0; i < calculatedValues.length; i++) {
      Arrays.fill(calculatedValues[i], -1);
      calculatedValues[i][i] = 1;
      calculatedValues[i][0] = 1;
    }
  }



  @Override
  public long nChooseK(int n, int k) {
    if (n == 0) {
      return 0;
    } else if (k > n || n < 0 || k < 0) {
      throw new IllegalArgumentException(String.format("Given n: %s, k: %s", n, k));
    }
    if (calculatedValues[n][k] == -1) {
      long result = nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
      if (result < 0) {
        throw new RuntimeException("Long overflow!");
      }
      calculatedValues[n][k] = result;
      return result;
    } else {
      return calculatedValues[n][k];
    }
  }
}
