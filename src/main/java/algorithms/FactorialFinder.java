package algorithms;

import java.math.BigInteger;

public class FactorialFinder
{
  private BigInteger[] factorials;
  public FactorialFinder(int max) {
    factorials = new BigInteger[max+1];
    factorials[0]=BigInteger.ONE;
  }

  public BigInteger findFactorialOf(int numberToFindFactorialOf)
  {
    if (numberToFindFactorialOf == 0) {
      return BigInteger.ONE;
    } else if (numberToFindFactorialOf < 0) {
      throw new IllegalArgumentException("Cannot find the factrial of a negative number");
    }
    int b = numberToFindFactorialOf;
    while (factorials[b]==null){
      b = b >>2;
    }
    b++;
    for (; b <= numberToFindFactorialOf; b++)
    {
      factorials[b] = factorials[b-1].multiply(new BigInteger(Integer.toString(b)));
    }
    return factorials[numberToFindFactorialOf];
  }
}