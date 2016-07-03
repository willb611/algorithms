import org.junit.Before;
import org.junit.Test;
import todo.LinkedList;

import static org.junit.Assert.*;

public class LinkedListTest {
  LinkedList<String> stringList;
  LinkedList<Integer> integerLinkedList;

  @Before
  public void setUp() {
    stringList = new LinkedList<>();
    integerLinkedList = new LinkedList<>();
  }

  @Test
  public void testInsertNotNull() {
    stringList.insert("hello");
    assertEquals(stringList.size(), 1);
  }

  @Test
  public void testPollNull() {
    assertEquals(0, stringList.size());
    assertNull(stringList.poll());
  }

  @Test
  public void testPollSingle()  {
    Integer i = 1;
    integerLinkedList.insert(i);
    assertEquals(integerLinkedList.size(), 1);
    assertEquals(i, integerLinkedList.poll());
    assertEquals(integerLinkedList.size(), 0);
  }

  @Test
  public void testPollMultiple() {
    for (int i = 1; i <= 6; i++) {
      integerLinkedList.insert(i);
    }
    for (int i = 1; i <= 6; i++) {
      assertEquals(Integer.valueOf(i), integerLinkedList.poll());
    }
    assertEquals(0, integerLinkedList.size());
  }

  @Test
  public void testPeek() {
    LinkedList<Integer> list = new LinkedList<>();
    list.insert(5);
    assertEquals(Integer.valueOf(5), list.peek());
    assertEquals(1, list.size());
  }

  @Test
  public void testSize() {
    // Size is kind of tested by other methods as well, oh well
    assertEquals(0, stringList.size());
    stringList.insert("ha!");
    assertEquals(1, stringList.size());
  }

  @Test
  public void testInsertRepeatKey() {
    integerLinkedList.insert(3);
    integerLinkedList.insert(3);
    assertEquals(integerLinkedList.size(), 2);
  }

  @Test(expected = NullPointerException.class)
  public void testInsertNull() {
    stringList.insert(null);
  }
}