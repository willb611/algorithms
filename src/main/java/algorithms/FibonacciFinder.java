package algorithms;

import java.math.BigInteger;

import static algorithms.Preconditions.checkNotNull;

public class FibonacciFinder
{
  private BigInteger[] storedResults;
  public FibonacciFinder(int max) {
    if (max < 0) {
      throw new IllegalArgumentException("Can only find fibonacci numbers of at least 0");
    }
    max = Math.max(2, max);
    storedResults = new BigInteger[max+1];
    storedResults[0] = BigInteger.ONE;
    storedResults[1] = BigInteger.ONE;
    storedResults[2] = BigInteger.ONE;
  }
  public BigInteger solveCachingResults(int n) {
    if (n <= 2) {
      return BigInteger.ONE;
    } else if (n > storedResults.length) {
      throw new IllegalArgumentException("Only saving values up to " + storedResults.length
          + " but asked to solve for nth fibonacci number, n: " + n);
    }
    int startIndex = 2;
    while (storedResults[startIndex] != null && startIndex < n) {
      startIndex++;
    }
    for (int i = startIndex; i <= n; i++) {
      storedResults[i] = storedResults[i-1].add(storedResults[i-2]);
    }
    return storedResults[n];
  }
  // Useful if you calc fib lots of times TODO refactor tests to non-static
  public static BigInteger solveUsingSavedValues(int n) {
    return new FibonacciFinder(n+5).solveCachingResults(n);
  }
  @Deprecated // solveUsingMatrices method is faster, use that instead.
  public static BigInteger solveSimply(int n) {
    if (n <= 2) {
      return BigInteger.ONE;
    }
    BigInteger fibOfNMinusOne = BigInteger.ONE;
    BigInteger fibOfNMinusTwo = BigInteger.ONE;
    int i = 2;
    while (i < n) {
      BigInteger fibOfI = fibOfNMinusOne.add(fibOfNMinusTwo);
      fibOfNMinusTwo = fibOfNMinusOne;
      fibOfNMinusOne = fibOfI;
      i++;
    }
    return fibOfNMinusOne;
  }

  public static int solveUsingMatrices(int n) {
    if (n <= 2) {
      return 1;
    }
    final int[][] T = new int[2][2];
    T[0] = new int[]{0, 1};
    T[1] = new int[]{1, 1};
    final int[] F1 = new int[]{1,1};
    return matrixMul(raiseMatrixToThePower(T, n-2), F1)[1];
  }

  private static int[][] raiseMatrixToThePower(int[][] matrix, int power) {
    checkNotNull(matrix);
    if (power == 1) {
      return matrix;
    } else if (power%2 == 1) {
      return matrixMul(matrix, raiseMatrixToThePower(matrix, power-1));
    } else {
      int[][] X = raiseMatrixToThePower(matrix, power/2);
      return matrixMul(X, X);
    }
  }

  private static int[][] matrixMul(int[][] A, int[][] B) {
    checkNotNull(A);
    checkNotNull(B);
    if (A.length != B.length || A.length != 2
        || A[0].length != 2 || B[0].length != 2) {
      throw new IllegalArgumentException("Must provide two 2x2 matrices to multiply");
    }
    int[][] resultingMatrix = new int[2][2];
    for (int row = 0; row < A.length; row++) {
      for (int col = 0; col < A[0].length; col++) {
        // calculate this thing.
        int res = 0;
        for (int i=0;i<2;i++) {
          res += A[row][i] * B[i][col];
        }
        resultingMatrix[row][col] = res;
      }
    }
    return resultingMatrix;
  }
  private static int[] matrixMul(int[][] A, int[] B) {
    checkNotNull(A);
    checkNotNull(B);
    if (A[0].length != B.length) {
      throw new IllegalArgumentException("Length of two matrices must be the same");
    }
    int[] resultingMatrix = new int[B.length];
    for (int row = 0; row < B.length; row++) {
      int res = 0;
      // col = colnumber in a, rownumber in b
      for (int col = 0; col < A[row].length; col++) {
        res += A[row][col] * B[col];
      }
      resultingMatrix[row] = res;
    }
    return resultingMatrix;
  }
}