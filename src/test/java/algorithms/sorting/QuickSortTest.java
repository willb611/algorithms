package algorithms.sorting;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuickSortTest {

  @Test
  public void testSort1ElementArray() {
    int[] arr = new int[]{1};
    int[] res = QuickSort.sort(arr);
    assertArrayEquals(arr, res);
  }

  @Test
  public void testSortOrdered() {
    int[] arr = new int[]{1,3,5,15,1235,12389};
    int[] res = QuickSort.sort(arr);
    assertArrayEquals(arr, res);
  }

  @Test
  public void testSortShortArray() {
    int[] arr = new int[]{5,1,981,5,123};
    int[] res = QuickSort.sort(arr);
    int[] expected = returnSortedCopyOf(arr);
    assertArrayEquals(expected, res);
  }
  @Test
  public void testSortLongArray() {
    int[] arr = new int[]{5,1,981,5,123,9013,5192,321,412,51,5,23,5,6,12,17,86,53};
    int[] res = QuickSort.sort(arr);
    int[] expected = returnSortedCopyOf(arr);
    assertArrayEquals(expected, res);
  }

  private int[] returnSortedCopyOf(int[] original) {
    int[] copy = new int[original.length];
    System.arraycopy(original, 0, copy, 0, original.length);
    Arrays.sort(copy);
    return copy;
  }
}