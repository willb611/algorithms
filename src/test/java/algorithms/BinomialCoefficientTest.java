package algorithms;

import algorithms.binomial.BinomialCoefficient;
import algorithms.binomial.BinomialCoefficientAdditive;
import algorithms.binomial.BinomialCoefficientMultiplicative;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BinomialCoefficientTest {
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {new BinomialCoefficientMultiplicative()},
        {new BinomialCoefficientAdditive()}
    });
  }

  @Parameterized.Parameter
  public BinomialCoefficient coefficientFinder;

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeN() {
    coefficientFinder.nChooseK(-1, 2);
  }

  @Test
  public void testZeroN() {
    long expected = 0;
    long actual = coefficientFinder.nChooseK(0, 2);
    assertEquals(expected, actual);
  }

  @Test
  public void testLowestBoundaryStillValid() {
    long expected = 1;
    long actual = coefficientFinder.nChooseK(1, 1);
    assertEquals(expected, actual);

    actual = coefficientFinder.nChooseK(1, 0);
    assertEquals(expected, actual);
  }

  @Test
  public void testJustAboveLowerBoundary() {
    long expected = 2;
    long actual = coefficientFinder.nChooseK(2, 1);
    assertEquals(expected, actual);

    expected = 1;
    actual = coefficientFinder.nChooseK(2, 2);
    assertEquals(expected, actual);
  }

  @Test
  public void betweenBoundaries() {
    int expected = 455;
    long actual = coefficientFinder.nChooseK(15, 3);
    assertEquals(expected, actual);
  }
}