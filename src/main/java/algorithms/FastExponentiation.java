package algorithms;

public class FastExponentiation {
	public static void main(String[] args) {
    new FastExponentiation().doTest();
	}
  private void doTest() {
    res(4, 2);
    res(9, 2);
    res(12, 2);
    res(2, 3);
  }

  private void res(int T, int n) {
    int result = expon(T, n);
    System.out.printf("Raising %d to %d got result %d%n", T, n, result);
  }

  public int expon(int T, int n) {
    if (T < 0) {
      return (int) Math.round(dexpon(1 / T, -n));
    } else if (n == 0) {
      return 1;
    } else if (n == 1) {
      return T;
    } else if (isOdd(n)) {
      return T * expon(T, n - 1);
    } else {
      return expon(T * T, n / 2);
    }
  }

  public double dexpon(double d, int n) {
    if (d < 0) {
      return dexpon(1 / d, -n);
    } else if (n == 0) {
      return 1;
    } else if (n == 1) {
      return d;
    } else if (isOdd(n)) {
      return d * dexpon(d, n - 1);
    } else {
      return dexpon(d * d, n / 2);
    }
  }

  private boolean isOdd(int number) {
    return number % 2 == 1;
  }
}