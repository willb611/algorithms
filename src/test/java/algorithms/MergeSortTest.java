package algorithms;

import algorithms.MergeSort;
import org.junit.Test;

import static org.junit.Assert.*;
public class MergeSortTest
{
  @Test
  public void testSort1ELementArray()
  {
    int[] arr = new int[]{1};
    int[] res = MergeSort.sort(arr);
    assertArrayEquals(arr, res);
  }

  @Test
  public void testSortOrdered()
  {
    int[] arr = new int[]{1,3,5,15,1235,12389};
    int[] res = MergeSort.sort(arr);
    assertArrayEquals(arr, res);
  }

  @Test
  public void testSortShortArray()
  {
    int[] arr = new int[]{5,1,981,5,123};
    int[] res = MergeSort.sort(arr);
    int[] expected = new int[]{1,5,5,123,981};
    assertArrayEquals(expected, res);
  }
  @Test
  public void testSortLongArray()
  {
    int[] arr = new int[]{5,1,981,5,123,9013,5192,321,412,51,5,23,5,6,12,17,86,53};
    int[] res = MergeSort.sort(arr);
    int[] expected = new int[]{1,5,5,5,5,6,12,17,23,51,53,86,123,321,412,981,5192,9013};
    assertArrayEquals(expected, res);
  }
}