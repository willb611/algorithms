import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import todo.MinBinaryHeap;

import java.util.Arrays;
import static org.junit.Assume.*;
import static org.junit.Assert.*;
// TODO clean up this code
public class MinBinaryHeapTest 
{
  private MinBinaryHeap<String> heap;
  private MinBinaryHeap<String> heap2;
  private MinBinaryHeap<String> heap3;
  private MinBinaryHeap<Integer> numHeap;
  private MinBinaryHeap<Integer> numHeap2;
  private MinBinaryHeap<Integer> numHeap3;
  private MinBinaryHeap<Integer> numHeap4;
  private MinBinaryHeap<Integer> numHeap5;
  private MinBinaryHeap<Integer> numHeap6;
  @Before
  public void setUp() {
    heap = new MinBinaryHeap<String>();
    heap2 = new MinBinaryHeap<String>();
    heap3 = new MinBinaryHeap<String>();
    numHeap = new MinBinaryHeap<Integer>();
    numHeap2 = new MinBinaryHeap<Integer>();
    numHeap3 = new MinBinaryHeap<Integer>();
    numHeap4 = new MinBinaryHeap<Integer>();
    numHeap5 = new MinBinaryHeap<Integer>();
    numHeap6 = new MinBinaryHeap<Integer>();
  }

  @After
  public void tearDown() {
    heap = null;
    heap2 = null;
    heap3 = null;
    numHeap = null;
    numHeap2 = null;
    numHeap3 = null;
    numHeap4 = null;
    numHeap5 = null;
    numHeap6 = null;
  }

  @Test
  public void testInsertNotNull()
  {
    heap.insert("hello");
    assertEquals(heap.size(), 1);
  }
  @Test
  public void testPollNull()
  {
    assertEquals(0, heap3.size());
    assertNull(heap3.poll());
  }
  @Test
  public void testPollSingle()
  {
    Integer i = Integer.valueOf(1);
    numHeap3.insert(i);
    assertEquals(numHeap3.size(), 1);
    assertEquals(i, numHeap3.poll());
    assertEquals(numHeap3.size(), 0);
  }
  @Test
  public void testPollMultiple()
  {
    assumeTrue(numHeap4.size() == 0);
    numHeap4.insert(1);
    numHeap4.insert(2);
    numHeap4.insert(3);
    numHeap4.insert(4);
    numHeap4.insert(5);
    numHeap4.insert(6);
    for (int i=1;i<=6;i++) {
      assertEquals(Integer.valueOf(i), numHeap4.poll());
    }
    assertEquals(0, numHeap4.size());
  }
  @Test
  public void testOrdering()
  {
    assumeTrue(numHeap5.size() == 0);
    int[] arr = new int[]{ 1, 51, 23, 63, 23 ,51};
    for (int i=0;i<arr.length; i++) {
      numHeap5.insert(arr[i]);
    }
    assertEquals(arr.length, numHeap5.size());
    Arrays.sort(arr);
    for (int i=0;i<arr.length; i++) {
      assertEquals(Integer.valueOf(arr[i]), numHeap5.poll());
    }
  }
  @Test
  public void testPeek()
  {
    assertNull(numHeap6.peek());
    numHeap6.insert(5);
    assertEquals(Integer.valueOf(5), numHeap6.peek());
    assertEquals(1, numHeap6.size());
  }
  @Test
  public void testSize()
  {
    // Size is kind of tested by other methods as well, oh well
    assertEquals(0, heap2.size());
    heap2.insert("ha!");
    assertEquals(1, heap2.size());
  }
  @Test
  public void testInsertRepeatKey()
  {
    numHeap.insert(3);
    numHeap.insert(3);
    assertEquals(numHeap.size(), 2);
  }

  @Test
  public void testInsertNull()
  {
    try {
      heap.insert(null);
    } catch (Exception e) {
      return;
    }
    fail();
  }
}