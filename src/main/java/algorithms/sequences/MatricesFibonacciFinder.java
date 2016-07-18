package algorithms.sequences;

import java.math.BigInteger;

import static internal.Preconditions.checkNotNull;

public class MatricesFibonacciFinder implements FibonacciFinder {

  @Override
  public BigInteger findNthTermInSequence(int n) {
    if (n <= 2) {
      return BigInteger.ONE;
    }
    final int[][] T = new int[2][2];
    T[0] = new int[]{1, 1};
    T[1] = new int[]{1, 0};
    int result = raiseMatrixToThePower(T, n - 1)[0][0];
    return new BigInteger(Integer.toString(result));
  }

  private static int[][] raiseMatrixToThePower(int[][] matrix, int power) {
    checkNotNull(matrix);
    if (power == 1) {
      return matrix;
    } else if (power % 2 == 1) {
      return matrixMul(matrix, raiseMatrixToThePower(matrix, power - 1));
    } else {
      int[][] matrixRaisedToHalfPow = raiseMatrixToThePower(matrix, power / 2);
      return matrixMul(matrixRaisedToHalfPow, matrixRaisedToHalfPow);
    }
  }

  private static int[][] matrixMul(int[][] A, int[][] B) {
    checkNotNull(A);
    checkNotNull(B);
    if (A.length != B.length
        || A.length != 2
        || A[0].length != A[1].length
        || B[0].length != B[1].length
        || A[0].length != 2
        || B[0].length != 2) {
      throw new IllegalArgumentException("Must provide two 2x2 matrices to multiply");
    }
    int[][] resultingMatrix = new int[2][2];
    for (int row = 0; row < A.length; row++) {
      for (int col = 0; col < A[0].length; col++) {
        // calculate this thing.
        int res = 0;
        for (int i = 0; i < 2; i++) {
          res += A[row][i] * B[i][col];
        }
        resultingMatrix[row][col] = res;
      }
    }
    return resultingMatrix;
  }
}
