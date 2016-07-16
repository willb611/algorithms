package algorithms;

import algorithms.BinarySearch;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assume.*;
import static org.junit.Assert.*;
public class BinarySearchTest {
  // TODO rename these varibles, inline them ? 
  // empty
  List<Integer> list1;
  // 1 element
  List<Integer> list2;
  List<Integer> list3;
  List<Integer> list4;
  List<Integer> list5;
  @Before
  public void setUp()
  {
    list1 = new ArrayList<Integer>();
    list2 = new ArrayList<Integer>();
    list3 = new ArrayList<Integer>();
    list4 = new ArrayList<Integer>();
    list5 = new ArrayList<Integer>();
    list2.add(5);
    for (int i=3; i<50; i<<=1) {
      list3.add(i);
    }
    int[] tmp = new int[]{1,3,15,73,612,6912,23000};
    for (int i=0;i<tmp.length;i++) {
      list4.add(tmp[i]);
    }
    // 18 elements
    tmp = new int[]{1,5,5,5,5,6,12,17,23,51,53,86,123,321,412,981,5192,9013};
    for (int i=0;i<tmp.length;i++) {
      list5.add(tmp[i]);
    }
  }
  @After
  public void tearDown()
  {
    list1.clear();
    list1 = null;
    list2.clear();
    list2 = null;
    list3.clear();
    list3 = null;
    list4.clear();
    list4 = null;
    list5.clear();
    list5 = null;
  }
  @Test
  public void testFindEmptyList()
  {
    assertEquals(-1, BinarySearch.find(15, list1));
  }
  @Test
  public void testFindSingleElementList()
  {
    assumeTrue(list2.contains(new Integer(5)));
    assertEquals(0, BinarySearch.find(5, list2));
  }
  @Test
  public void testFindNonExistent()
  {
    // use populated array
    assertEquals(-1, BinarySearch.find(62, list3));
  }
  @Test
  public void testFindFarLeft()
  {
    assumeTrue(list3.contains(new Integer(3)));
    assertEquals(0, BinarySearch.find(3, list3));
  }

  @Test
  public void testFindFarRight()
  {
    assumeTrue(list4.size() > 1);
    assumeTrue(list4.contains(new Integer(23000)));
    assertEquals(list4.size()-1, BinarySearch.find(23000, list4));
  }

  @Test
  public void testFind1LeftMid()
  {
    assumeTrue(list5.size()%2 == 0);
    assertEquals(8, BinarySearch.find(23, list5));
  }
  @Test
  public void testFind1RightMid()
  {
    assumeTrue(list5.size()%2 == 0);
    assertEquals(9, BinarySearch.find(51, list5));
  }
  @Test
  public void testFindMid()
  {
    assumeTrue(list4.size() %2==1);
    assertEquals(3, BinarySearch.find(73, list4));
  }
}