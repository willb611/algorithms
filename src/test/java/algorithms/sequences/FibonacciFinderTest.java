package algorithms.sequences;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FibonacciFinderTest {
  public static final int nthTermBiggerThanUsedInTest = 100;
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {new CachingFibonacciFinder(nthTermBiggerThanUsedInTest)},
        {new MatricesFibonacciFinder()},
        {new NaiveFibonacciFinder()}
    });
  }

  @Parameterized.Parameter
  public FibonacciFinder fibonacciFinder;

  /**
   * The first few numbers in the sequence are as follows:
   * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
   * 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811.
  */
  @Test
  public void testFindNthTermSmall() {
    assertEquals(BigInteger.valueOf(5), fibonacciFinder.findNthTermInSequence(5));
    assertEquals(BigInteger.valueOf(5), fibonacciFinder.findNthTermInSequence(5));
  }

  @Test
  public void testFindNthTermMedium() {
    assertEquals(BigInteger.valueOf(4181), fibonacciFinder.findNthTermInSequence(19));
    assertEquals(BigInteger.valueOf(4181), fibonacciFinder.findNthTermInSequence(19));
  }

}