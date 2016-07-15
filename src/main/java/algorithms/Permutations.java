package algorithms;

public class Permutations {
  public static int nChooseK(int n, int k)  {
    if (n < 1) {
      throw new IllegalArgumentException("N must be greater than 1, given n: " + n);
    }
    if (k < 0) {
      throw new IllegalArgumentException("K must be greater than 0, given k: " + k);
    }
    if (k == 0 || k == n) {
      return 1;
    }
    int res = numerator(n, k);
    res = res / denominator(n, k);
    return res;
  }

  private static int denominator(int n, int k) {
    return factorial(k);
  }

  private static int factorial(int i) {
    int sum = 1;
    while (i > 0) {
      sum *= i;
      i--;
    }
    return sum;
  }

  private static int numerator(int n, int k) {
    int res = 1;
    for (int i = n ; i > n - k; i--) {
      res *= i;
    }
    return res;
  }
}