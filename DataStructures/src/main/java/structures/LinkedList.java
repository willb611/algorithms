package structures;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> {
  private Node<T> start = null;
  private int elementCount = 0;

  public Iterator<T> iterator() {
    return new MyIterator(this);
  }

  public boolean contains(T needle) {
    Node<T> pointer = start;
    while (pointer != null) {
      if (pointer.data.equals(needle)) return true;
      pointer = pointer.next;
    }
    return false;
  }

  public T poll() {
    Node<T> firstElement = first();
    if (firstElement == null) {
      return null;
    }
    start = firstElement.next;
    elementCount--;
    return firstElement.data;
  }

  public T peek() {
    Node<T> res = first();
    if (res == null) {
      return null;
    }
    return first().data;
  }

  private Node<T> first() {
    return start;
  }

  private Node<T> last() {
    if (start == null) {
      return null;
    } else {
      Node<T> curr = start;
      Node<T> follower = start;
      while (curr != null) {
        follower = curr;
        curr = curr.next;
      }
      return follower;
    }
  }

  public void insert(T data) {
    if (data == null) {
      throw new NullPointerException("Data cannot be null!");
    }
    Node<T> newNode = new Node<>(data);
    if (start == null) {
      start = newNode;
    } else {
      last().next = newNode;
    }
    elementCount++;
  }

  public int size() {
    return elementCount;
  }

  private class Node <T extends Comparable<T>> {
    public Node<T> next = null;
    public T data;

    public Node(T data) {
      this.data = data;
    }
  }

  private class MyIterator implements Iterator<T> {
    Node<T> curr = null;
    LinkedList<T> backing = null;
    boolean haveStarted = false;

    public MyIterator(LinkedList<T> backingList){
      backing = backingList;
    }

    public boolean hasNext() {
      return !haveStarted || (curr != null && curr.next != null);
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }

    public T next() {
      if (curr == null) {
        if (!haveStarted) { 
          curr = start;
          haveStarted = true;
          return curr == null ? null : curr.data;
        } else return null;
      }
      curr = curr.next;
      return curr == null ? null : curr.data;
    }
  }
}