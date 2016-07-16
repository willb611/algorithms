package algorithms;

import java.util.List;

import static structures.internal.Preconditions.checkNotNull;

public class BinarySearch {

  public static int find(int needle, List<Integer> haystack) {
    checkNotNull(haystack);
    return find(new Integer(needle), haystack);
  }

  public static <T extends Comparable<T>> int find(T needle, List<T> haystack) {
    checkNotNull(haystack);
    checkNotNull(needle);
    int left = 0;
    int right = haystack.size() - 1;
    int mid = (left + right) / 2;
    while (left <= right) {
      int res = haystack.get(mid).compareTo(needle);
      if (res == 0) {
        return mid;
      } else if (res < 0) {
        left = mid + 1;
        mid = (left + right) / 2;
      } else if (res > 0) {
        right = mid - 1;
        mid = (left + right) / 2;
      }
    }
    return -1;
  }
}