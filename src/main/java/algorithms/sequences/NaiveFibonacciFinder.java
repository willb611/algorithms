package algorithms.sequences;

import java.math.BigInteger;

public class NaiveFibonacciFinder implements FibonacciFinder {

  public BigInteger findNthTermInSequence(int n) {
    if (n <= 2) {
      return BigInteger.ONE;
    }
    BigInteger fibOfNMinusOne = BigInteger.ONE;
    BigInteger fibOfNMinusTwo = BigInteger.ONE;
    int currentN = 2;
    while (currentN < n) {
      BigInteger fibOfI = fibOfNMinusOne.add(fibOfNMinusTwo);
      fibOfNMinusTwo = fibOfNMinusOne;
      fibOfNMinusOne = fibOfI;
      currentN++;
    }
    return fibOfNMinusOne;
  }
}
