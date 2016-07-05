package algorithms.sorting;

public class QuickSort {

  public static int[] sort(int[] original) {
    int[] copy = new int[original.length];
    System.arraycopy(original, 0, copy, 0, original.length);
    qs(copy, 0, copy.length);
    return copy;
  }

  private static void qs(int[] arr, int start, int endExclusive) {
    if (start + 1 >= endExclusive) {
      return;
    } else {
      int mid = (start + endExclusive) / 2;
      int pivot = arr[mid];
      int[] left = new int[endExclusive - start + 1];
      int leftCount = 0;
      int[] right = new int[endExclusive - start + 1];
      int rightCount = 0;
      for (int i = start; i < endExclusive; i++) {
        int element = arr[i];
        if (i == mid) {
          continue;
        } else if ((element <= pivot && i < mid)
            || element < pivot) {
          left[leftCount++] = element;
        } else {
          right[rightCount++] = element;
        }
      }
      System.arraycopy(left, 0, arr, start, leftCount);
      arr[start + leftCount] = pivot;
      System.arraycopy(right, 0, arr, start  + leftCount + 1, rightCount);

      qs(arr, start, start + leftCount);
      qs(arr, start + leftCount + 1, endExclusive);
    }
  }
}
