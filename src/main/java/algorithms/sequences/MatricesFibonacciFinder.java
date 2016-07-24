package algorithms.sequences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

import static internal.Preconditions.checkNotNull;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class MatricesFibonacciFinder implements FibonacciFinder {
  private Logger logger = LoggerFactory.getLogger(MatricesFibonacciFinder.class);

  @Override
  public BigInteger findNthTermInSequence(int n) {
    logger.debug("[findNthTermInSequence] Given n: {}");
    if (n <= 2) {
      return ONE;
    }
    final BigInteger[][] T = new BigInteger[2][2];
    T[0] = new BigInteger[]{ONE, ONE};
    T[1] = new BigInteger[]{ONE, ZERO};
    BigInteger result = raiseMatrixToThePower(T, n - 1)[0][0];
    logger.debug("[findNthTermInSequence] For n: {}, returning result: {}", n, result);
    return result;
  }

  private BigInteger[][] raiseMatrixToThePower(BigInteger[][] matrix, int power) {
    logger.debug("[raiseMatrixToThePower] Given power: {}", power);
    checkNotNull(matrix);
    if (power == 1) {
      return matrix;
    } else if (power % 2 == 1) {
      return matrixMul(matrix, raiseMatrixToThePower(matrix, power - 1));
    } else {
      BigInteger[][] matrixRaisedToHalfPow = raiseMatrixToThePower(matrix, power / 2);
      return matrixMul(matrixRaisedToHalfPow, matrixRaisedToHalfPow);
    }
  }

  private BigInteger[][] matrixMul(BigInteger[][] A, BigInteger[][] B) {
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
    BigInteger[][] resultingMatrix = new BigInteger[2][2];
    for (int row = 0; row < A.length; row++) {
      for (int col = 0; col < A[0].length; col++) {
        // calculate this thing.
        BigInteger result = ZERO;
        for (int i = 0; i < 2; i++) {
          BigInteger intermediate = A[row][i].multiply(B[i][col]);
          result = result.add(intermediate);
          logger.trace("[matrixMul] Got intermediate: {}, and result: {}", intermediate, result);
        }
        resultingMatrix[row][col] = result;
      }
    }
    return resultingMatrix;
  }
}
