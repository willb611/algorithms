package algorithms.sequences;

import java.math.BigInteger;

import static internal.Preconditions.checkThat;

// Useful if you calc fib lots of times
public class CachingFibonacciFinder implements FibonacciFinder {
  private BigInteger[] storedResults;

  public CachingFibonacciFinder() {
    this(100);
  }

  public CachingFibonacciFinder(int max) {
    checkThat(max > 0, "Can only find fibonacci numbers of at least 0");
    max = Math.max(2, max);
    initialize(max);
  }

  private void initialize(int max) {
    storedResults = new BigInteger[max + 1];
    storedResults[0] = BigInteger.ONE;
    storedResults[1] = BigInteger.ONE;
    storedResults[2] = BigInteger.ONE;
  }

  @Override
  public BigInteger findNthTermInSequence(int n) {
    if (n <= 2) {
      return BigInteger.ONE;
    } else if (n > storedResults.length) {
      initialize(n);
      return findNthTermInSequence(n);
    }
    int startIndex = 2;
    while (storedResults[startIndex] != null && startIndex < n) {
      startIndex++;
    }
    for (int i = startIndex; i <= n; i++) {
      storedResults[i] = storedResults[i - 1].add(storedResults[i - 2]);
    }
    return storedResults[n];
  }
}
