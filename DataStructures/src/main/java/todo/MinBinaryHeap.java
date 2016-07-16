package todo;

/**
*  <p> Time complexity</p>
* The following is the complexity for standard heap operations on this heap.
*   <table style="border:1px solid #000000;"><tr><td>          Average  </td><td>   Worst case</td></tr>
*   <tr><td>    Search  O(n)     </td><td>      O(n)</td></tr>
*   <tr><td>    Insert  O(log n) </td><td>      O(log n)</td></tr>
*   <tr><td>    Delete  O(log n) </td><td>      O(log n)</td></tr>
*   <tr><td>    Peek    O(1)     </td><td>      O(1) </td></td>
*   <tr><td>    Poll    O(log n) </td><td>      O(log n) </td></td>
* </table>
* An implementation of MinBinaryHeap, using generics and casting
* Follows the JRE library standard of storing using Object arrays.
* <p>This implementation doesn't allow insertion of null objects, and only allows
* objects of the same class.</p>
* @author William Brown william.brown-4@student.manchester.ac.uk
* @version 1
*/
public class MinBinaryHeap<T extends Comparable<T>>
{
    // Saved using 0-indexing.
    private Object[] arr;
    private Object[] arr2;
    private int RESIZE_FACTOR = 2;
    private int size;
    private int nextIndex = 0;
    /**
    * Creates a heap of default size (11)
    */
    public MinBinaryHeap()
    {
        this(11);
    }
    /**
    * Creates a heap of specified size
    * @param i Size of heap to be created (rounded up to a value of 2).
    */
    public MinBinaryHeap(int i)
    {
        // ensure size is enough so that always a power of 2.
        if (i > Integer.MAX_VALUE >> 1) {
            size = Integer.MAX_VALUE;
        }
        else {
            size = Integer.highestOneBit(i << 1) - 1 ;
        }
        arr = new Object[size];
    }
    /**
    * Inserts an object into the heap, and maintains heap property.
    * Doesn't allow null objects to be inserted.
    * Allows repeat keys.
    * @param object The object to insert into the heap.
    * Throws IllegalArgumentException if the given object is null
    */
    public <T extends Comparable<T>> void insert(T  object)
    {
        if (object == null) throw new IllegalArgumentException("Given object cannot be null");
        if (nextIndex == size) {
            size *= RESIZE_FACTOR;
            arr2 = new Object[size];
            System.arraycopy(arr, 0, arr2, 0, arr.length);
            arr = arr2;            
        }
        arr[nextIndex] = (Object) object;
        siftUp(nextIndex++);
    } // insert
    /**
    * Move a leaf up the tree toward root as far as it needs to go.
    * Used during insert operations.
    * @see insert
    */
    @SuppressWarnings("unchecked")
    private boolean siftUp(int index)
    {
        // int currIndex = index + 1;
        boolean changed = false;
        while (index > 0) {
            int parent = ((index+1) >> 1)-1;
            if (((T)arr[index]).compareTo((T)arr[parent]) < 0) {
                swap(index, parent);
                changed = true;
            } else {
                return changed;
            }
            index = parent;
            parent = ((parent+1)>>1)-1;
        }
        return changed;
    }
    /**
    * Move a down the heap towards leaves as far as it needs to go.
    * Used by poll method.
    * @see poll
    */
    @SuppressWarnings("unchecked")
    private boolean siftDown(int index) {
        int queSize = size();
        if (index > queSize) return false;
        // index++;
        int left = ((index+1) << 1)-1;
        if (left < queSize && left > 0) {
            int right = left + 1;
            if (right < queSize) {
                if (((T)arr[right]).compareTo((T)arr[left]) < 0) {
                    // check right
                    if (((T)arr[right]).compareTo((T)arr[index]) < 0) {
                        swap(index, right);
                        siftDown(right);
                        return true;
                    } else return false;
                }
            }
            // check left
            if (((T)arr[left]).compareTo((T)arr[index]) < 0) {
                swap(left, index);
                siftDown(left);
                return true;
            }
        }
        return false;
    }
    
    // assumes a, b are values to be used as index's < size(), and a, b > 0
    // Used internally
    private void swap(int a, int b)
    {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    /**
    * Returns the element at the top of the heap, doesn't modify the heap in any way.
    * @return The element at the top of heap, or null if no elements in heap.
    */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size() != 0) {
            return (T) arr[0];
        } else return null;
    }
    /**`
    * @return the number of elements currently stored in the heap
    */
    public int size()
    {
        return nextIndex;
    }
    void printStuff() {
        System.out.printf("printStuff called, size: %d\n", size());
        for (int i=0;i<size();i++)
            System.out.printf("printStuff, i: %3d, value: %s\n", i, arr[i]);
    }
    /**
    *  Polls the heap, removes and returns the minimum item. 
    *  @return the object at top of heap, or null if no elements exist
    */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T poll()
    {
        int eleNum = size();
        if (eleNum == 0) return null;
        System.out.println("Thing I'm poilling: " + (arr[-1+nextIndex]));
        T object = (T) arr[0];
        arr[0] = arr[--nextIndex];
        System.out.println("After poll of : " + object + " root is: " + arr[0]);
        siftDown(0);
        System.out.println("after siftDown, root: " + arr[0]);
        for (int i=0;i<size();i++) {
            System.out.println(i +": "+ (T)arr[i]);
        }
        return object;
    } // method poll
    /**
    * Returns if there are elements in the heap currently.
    * @return true if the heap has any items, false otherwise.
    */
    public boolean isEmpty()
    {
        return size() == 0;
    }
    /**
    * Merges two different heaps.
    * Currently inefficient as does one-by-one insertions into a new array.
    * @param a Heap a to merge
    * @param b Heap b to merge
    * @return The heap to return as a result of the merge.
    */
    MinBinaryHeap<T> merge(MinBinaryHeap a, MinBinaryHeap b)
    {
        int arrSize = a.size() + b.size();
        MinBinaryHeap<T> result = new MinBinaryHeap<T>(arrSize);
        while (!a.isEmpty() || !b.isEmpty()) {
            if (a.isEmpty()) {
                result.insert(b.poll());
            } else {
                result.insert(a.poll());
            }
        }
        return result;
    } // method Merge
} // class MinBinaryHeap
