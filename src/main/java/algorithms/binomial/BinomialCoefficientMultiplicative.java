package algorithms.binomial;

public class BinomialCoefficientMultiplicative implements BinomialCoefficient{

  @Override
  public long nChooseK(int n, int k)  {
    if (n < 0) {
      throw new IllegalArgumentException("N must be greater than 0, given n: " + n);
    } else if (k < 0) {
      throw new IllegalArgumentException("K must be greater than 0, given k: " + k);
    }
    if (k > n) {
      return 0;
    } else if (n == 0) {
      return 0;
    }
    if (k == 0 || k == n) {
      return 1;
    }
    long res = numerator(n, k);
    res = res / denominator(n, k);
    return res;
  }

  private static long denominator(int n, int k) {
    return factorial(k);
  }

  private static long factorial(int i) {
    long sum = 1;
    for (int copy = i; copy > 0; copy--) {
      sum *= copy;
    }
    return sum;
  }

  private static long numerator(int n, int k) {
    long res = 1;
    for (int i = n ; i > n - k; i--) {
      res *= i;
    }
    return res;
  }
}