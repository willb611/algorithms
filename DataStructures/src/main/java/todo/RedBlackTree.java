package todo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
/* 
Allows repeat keys, though no stability guaranteed

        Average     Worst case
Space   O(n)        O(n)
Search  O(log n)    O(log n)
Insert  O(log n)    O(log n)
Delete  O(log n)    O(log n)

In addition to the requirements imposed on a binary search tree the following must be satisfied by a red–black tree:[5]

    1. A node is either red or black.
    2. The root is black. (This rule is sometimes omitted. Since the root can always be changed from red to black, but not necessarily vice-versa, this rule has little effect on analysis.)
    3. All leaves (NIL) are black. (All leaves are same color as the root.)
    4. Every red node must have two black child nodes.
    5. Every path from a given node to any of its descendant leaves contains the same number of black nodes.

*/
public class RedBlackTree <K ,V>
{
    public static enum Color {
            BLACK, RED
    } // enum Color
    public static  final class MyEntry<K, V> {
        MyEntry<K, V> left = null;
        MyEntry<K, V> right = null;
        MyEntry<K,V> parent;
        K key;
        V value;
        Color color;    
        public MyEntry(K key, V value, MyEntry<K, V> parent)
        {
            this(key, value, parent, Color.RED);
        }
        public MyEntry(K key, V value, MyEntry<K, V> parent, Color c)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
            color = c;
        }
        V value() { return value; }
        K key() { return key; }
    } // class myEntry
    MyEntry<K, V> root;
    private final Comparator<? super K> comparator;
    public RedBlackTree()
    {
        root = null;
        comparator = null;
    }
    public RedBlackTree(Comparator<? super K> comparator)
    {
        this.comparator = comparator;
    }
    // probably throws something if keys aren’t comparable
    final int compare(Object k1, Object k2)
    {
        return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2)
                                    : comparator.compare((K)k1, (K)k2);
    }
    final boolean equals(Object k1, Object k2)
    {
        return compare(k1, k2) == 0;
    }

    private MyEntry<K, V> grandparent(MyEntry<K, V> node)
    {
        if (node != null && (node.parent) != null) return node.parent.parent;
        else return null;
    }
    
    private MyEntry<K, V> uncle(MyEntry<K, V> node)
    {
        MyEntry<K, V> grandparent = grandparent(node);
        if (grandparent == null) return null;
        if (node.parent == grandparent.left) return grandparent.right;
        else return grandparent.left;
    }


void insert_1(MyEntry<K,V> node)
{
    if (node.parent == null) node.color = Color.BLACK;
    else insert_2(node);
}
void insert_2(MyEntry<K,V> node)
{
 if (node.parent.color == Color.BLACK)  return;
 else insert_3(node);
}

void insert_3(MyEntry<K,V> node)
{
 MyEntry<K,V> u = uncle(node), g;
 
 if ((u != null) && (u.color == Color.RED)) {
  node.parent.color = Color.BLACK;
  u.color = Color.BLACK;
  g = grandparent(node);
  g.color = Color.RED;
  insert_1(g);
 } else {
  insert_4(node);
}
} // method insert_3

void rotateLeft(MyEntry<K,V> node)
{
    MyEntry<K, V> g = grandparent(node);
    MyEntry<K, V> savedParent = g.left;
    MyEntry<K, V> savedLeftN = node.left;
    g.left = node;
    node.left = savedParent;
    savedParent.right = savedLeftN;
    // Don’t forget the parents!
    node.parent = g;
    savedParent.parent = node;
    savedLeftN.parent = node;
}

void rotateRight(MyEntry<K, V> node)
{    
MyEntry<K, V> g = grandparent(node);
    if (g== null) {
        System.out.println("uh oh");
        System.exit(1);
    }
    MyEntry<K, V> savedParent = g.right;
    MyEntry<K, V> savedRightN = node.right;
    
    g.right = node;
    node.right = savedParent;
    savedParent = savedRightN;
    
    // Don’t forget the parents!
    node.parent = g;
    savedParent.parent = node;
    savedRightN.parent = node;
}

void insert_4(MyEntry<K, V> node)
{
 MyEntry<K, V> g = grandparent(node);
 
 if ((node == node.parent.right) && (node.parent == g.left)) {
   rotateLeft(node.parent);
   insert_5(node.left);
 } else if ((node == node.parent.left) && (node.parent == g.right)) {
  rotateRight(node.parent);  
   insert_5(node.right);
 }
}
void insert_5(MyEntry<K, V> node)
{
MyEntry<K, V> g = grandparent(node);
 node.parent.color = Color.BLACK;
 g.color = Color.RED;
 if (node == node.parent.left)
  rotateRight(g);
 else
  rotateLeft(g);
}




    // Doesn’t allow null keys
    public boolean insert(K key, V value)
    {
        if (key == null) return false;
        if (root == null) {
            root = new MyEntry<K, V>(key, value, null);
            insert_1(root);
        } else {
            insert(key, value, root);
        }
        return true;
        /*
        if (key == null) return false;
        if (root == null) {
            root = new MyEntry<K, V>(key, value, null);
    insert_1(root);
        } else {
            if (compare(key, root.key()) <= 0 ) {
                if (root.left == null) {
                     root.left = new MyEntry<K, V>(key, value, root);
                     insert_1(root.left);
                 }
                else insert(key, value, root.left);
            } else {
                if (root.right == null) {
                    root.right = new MyEntry<K, V>(key, value, root);
                    insert_1(root.right);
                }
                else insert(key, value, root.right);
            }
        }
        return true;*/
    } // method insert 

    // Should not be given a null node as parameter
    private void insert(K key, V value, MyEntry<K, V> node)
    {
            if (compare(key, node.key()) <= 0 ) {
                if (node.left == null) {
                    node.left = new MyEntry<K, V>(key, value, node);
                    insert_1(node.left);
                }
                else insert(key, value, node.left);
            } else {
                if (node.right == null){
                     node.right = new MyEntry<K, V>(key, value, node);
                     insert_1(node.right);
                 }
                else insert(key, value, node.right);
            }
    } // method insert
    
    public V get(K key)
    {
        return get(key, root);
    }
    
    // allows null nodes
    public V get(K key, MyEntry<K, V> node)
    {
        if (node == null) return null;
        int res = compare(key, node.key());
        if (res == 0) return node.value();
        else if (res < 0) return get(key, node.left);
        else return get(key, node.right);
    }
    
    public int size()
    { return(size(root));    }
    private int size(MyEntry<K, V> node)
    {
        if (node == null) return 0;
        else return size(node.left) + 1 + size(node.right);
    }
    
    public Iterator<MyEntry<K, V>> iterator()
    {
        return new AscIterator(root);
    }

    @Override
    public String toString()
    {
        StringBuffer st = new StringBuffer("RedBlackTree toString:\n");
        Iterator<MyEntry<K, V>> it = iterator();
        while (it.hasNext()) {
            MyEntry<K, V> thing = it.next();
            st.append(thing.key());
            st.append(": ");
            st.append(thing.value());
            st.append("\n");
        }
        return st.toString();
    }

    // Doesn’t guarantee ConcurrentModificationException will be thrown ever
    public class AscIterator implements Iterator<MyEntry<K, V>>
    {
        MyEntry<K, V> last, current, root;
        boolean isStarted = false;
        public AscIterator(MyEntry<K,V> root)
        {
            this.root = root;
            last = getLast();
            current = getFirst();
        }
        public MyEntry<K, V> getLast()
        {
            if (root == null) return null;
            else {
                MyEntry<K,V> last = root;
                while (last.right != null) last = last.right;
                return last;
            }
        }
        public MyEntry<K, V> getFirst()
        {
            MyEntry<K, V> first;
            if (root == null) return null;
            else {
                first = root;
                while (first.left != null) first = first.left;
                return first;
            }
        }
        public boolean hasNext()
        {
            if (current == null) return false;
            if (!isStarted) return true;
            if (current == last) {
                if (current.right == null) return true;
                else throw new ConcurrentModificationException("Tree was modified during iteration");
            } else if (successor(current) == null) return false;
            else return true;
        } // method hasNext

        // Returns successor to this node, null if this is null or no successor.
        private MyEntry<K, V> successor(MyEntry<K, V> node)
        {
            if (node == null) return null;
            if (node.right != null) {
                MyEntry<K, V> child = node.right;
                while (child.left != null) child = child.left;
                return child;
            }
            else {
                MyEntry<K, V> p = node.parent;
                if (p == null) return null; // at root
                else { // not root, but need to go up and right.
                    MyEntry<K, V> child = node;
                    while (p != null && p.right == child) {
                        child = p;
                        p = p.parent;
                    } // while advance up tree
                    if (p == null) return null;
                    else return p;
                } // else - not root, go right + up
            } // else - need to look up
        } // method successor

        // Find next node for in-order traversal (LNR), advances iterator.
        public MyEntry<K, V> next()
        {
            if (!isStarted) isStarted = true;
            MyEntry<K, V> toRet = current;
            current = successor(current);
            return toRet;
        } // method next

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    } // class AscIterator
} // class bst


