package algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;


public class FibonacciFinderTest {
  /**
   * The first few numbers in the sequence are as follows:
   * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
   * 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811.
  */
  @Test
  public void testSolve1Small() {
    assertEquals(BigInteger.valueOf(5), FibonacciFinder.solveUsingSavedValues(5));
  }

  @Test
  public void testSolve2Small() {
    assertEquals(BigInteger.valueOf(5), FibonacciFinder.solveSimply(5));
  }

  @Test
  public void testSolve3Small() {
    assertEquals(5, FibonacciFinder.solveUsingMatrices(5));
  }

  @Test
  public void testSolveUsingStoredMedium() {
    assertEquals(BigInteger.valueOf(4181), FibonacciFinder.solveUsingSavedValues(19));
    assertEquals(BigInteger.valueOf(4181), FibonacciFinder.solveUsingSavedValues(19));
  }

  @Test
  public void testSolveSimplyMedium() {
    assertEquals(BigInteger.valueOf(4181), FibonacciFinder.solveSimply(19));
  }

  @Test
  public void testSolveUsingMatricesMedium() {
    assertEquals(4181, FibonacciFinder.solveUsingMatrices(19));
  }
}