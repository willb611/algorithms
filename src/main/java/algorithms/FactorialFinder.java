package algorithms;

import java.math.BigInteger;

public class FactorialFinder {
  private BigInteger[] factorials;

  public FactorialFinder(int max) {
    factorials = new BigInteger[max + 1];
    factorials[0] = BigInteger.ONE;
  }

  public BigInteger findFactorialOf(int numberToFindFactorialOf) {
    if (numberToFindFactorialOf == 0) {
      return BigInteger.ONE;
    } else if (numberToFindFactorialOf < 0) {
      throw new IllegalArgumentException("Cannot find the factrial of a negative number");
    }
    int index = numberToFindFactorialOf;
    while (factorials[index] == null) {
      index = index >> 2;
    }
    index++;
    for (; index <= numberToFindFactorialOf; index++) {
      factorials[index] = factorials[index - 1].multiply(new BigInteger(Integer.toString(index)));
    }
    return factorials[numberToFindFactorialOf];
  }
}