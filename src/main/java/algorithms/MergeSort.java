package algorithms;

import java.util.Arrays;
public class MergeSort
{
  public static int[] sort(int[] arr)
  {
    if (arr == null) return null;
    int len = arr.length;
    if (len<= 1) return arr;
    int mid = len/2;
    int[] a=new int[mid];
    System.arraycopy(arr,0,a,0,mid);
    a = sort(a);
    int[] b = new int[len-mid];
    System.arraycopy(arr,mid,b,0,len-mid);
    b = sort(b);
    return merge(a, b);
  }
  private static int[] merge(int[] a, int[] b)
  {
    int[] res = new int[a.length + b.length];
    int lp = 0,rp=0;
    for (int count = 0; lp < a.length | rp < b.length; count++)
    {
      if (lp >= a.length)
        res[count] = b[rp++];
      else if (rp >= b.length)
        res[count] = a[lp++];
      else if (a[lp] < b[rp]) {
        res[count] = a[lp++];
      } else {
        res[count] = b[rp++];
      }
    }
    return res;
  }
}