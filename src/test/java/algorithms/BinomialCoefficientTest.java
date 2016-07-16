package algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinomialCoefficientTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeN() {
    BinomialCoefficient.nChooseK(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroN() {
    BinomialCoefficient.nChooseK(0, 2);
  }

  @Test
  public void testLowestBoundaryStillValid() {
    int expected = 1;
    int actual = BinomialCoefficient.nChooseK(1, 1);
    assertEquals(expected, actual);

    actual = BinomialCoefficient.nChooseK(1, 0);
    assertEquals(expected, actual);
  }

  @Test
  public void testJustAboveLowerBoundary() {
    int expected = 2;
    int actual = BinomialCoefficient.nChooseK(2, 1);
    assertEquals(expected, actual);

    expected = 1;
    actual = BinomialCoefficient.nChooseK(2, 2);
    assertEquals(expected, actual);
  }

  @Test
  public void betweenBoundaries() {
    int expected = 455;
    int actual = BinomialCoefficient.nChooseK(15, 3);
    assertEquals(expected, actual);
  }
}