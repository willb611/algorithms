package todo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
/* Allows repeat keys, no stability guaranteed
        Average     Worst case
Space   O(n)        O(n)
Search  O(log n)    O(n)
Insert  O(log n)    O(n)
Delete  O(log n)    O(n)
Uses Objects as opposed to an array to do everything.
*/
public class BinarySearchTree <K extends Comparable<K>,V>
{
    public static  final class MyEntry<K, V> {
        MyEntry<K, V> left = null;
        MyEntry<K, V> right = null;
        MyEntry<K,V> parent;
        K key;
        V value;
        public MyEntry(K key, V value, MyEntry<K, V> parent)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
        V value() { return value; }
        K key() { return key; } 
        public String toString()
        {
            return "toSTring: \nk: "+key+"length:"+value+" \nparent: " + (parent==null?"null":("k: " +parent.key()+"v: "+parent.value()))+"\n left: "+(left==null?"null":("k: "+left.key()+", v: "+left.value()))+"\nright:"+(right==null?"null":("k: "+right.key()+", v: "+right.value()))+"\n\n";
        }
    } // class myEntry
    private MyEntry<K, V> root;
    private final Comparator<? super K> comparator;
    public BinarySearchTree()
    {
        root = null;
        comparator = null;
    }
    public BinarySearchTree(Comparator<? super K> comparator)
    {
        this.comparator = comparator;
    }
    // probably throws something if keys aren’t comparable 
    final int compare(Object o1, Object o2)
    {
        if (comparator == null) {
            @SuppressWarnings("unchecked")
            int res = ((Comparable<? super K>)o1).compareTo((K)o2);
            return res;
        } else {
            @SuppressWarnings("unchecked")
            int res = comparator.compare((K)o1, (K)o2);
            return res;
        }
    }
    final boolean equals(Object o1, Object o2)
    {
        return compare(o1, o2) == 0;
    }    
    final boolean equals(MyEntry<K, V> o1, MyEntry<K, V> o2)
    {
        return compare(o1.key(), o2.key()) == 0;
    }
    // Doesn’t allow null keys
    public boolean insert(K key, V value)
    {
        if (key == null) return false;
        if (root == null) {
            root = new MyEntry<K, V>(key, value, null);
        } else {
            if (compare(key, root.key()) <= 0 ) {
                if (root.left == null) root.left = new MyEntry<K, V>(key, value, root);
                else insert(key, value, root.left);
            } else {
                if (root.right == null) root.right = new MyEntry<K, V>(key, value, root);
                else insert(key, value, root.right);
            }
        }
        return true;
    } // method insert    
    
    // Should not be given a null node as parameter
    private void insert(K key, V value, MyEntry<K, V> node)
    {
            if (compare(key, node.key()) <= 0 ) {
                if (node.left == null) node.left = new MyEntry<K, V>(key, value, node);
                else insert(key, value, node.left);
            } else {
                if (node.right == null) node.right = new MyEntry<K, V>(key, value, node);
                else insert(key, value, node.right);
            }
    } // method insert

    /**
    *   Removes a node from the tree if it exists, then fixes tree.
    *   @Return MyEntry that was removed or null if node did not exist.
    *   @See MyEntry
    */
    public MyEntry<K, V> remove(K key)
    {
        // System.out.println("node: %")
        MyEntry<K, V> node = get(key);
        // System.out.printf("got node: %s with key: %s\n", node, key);
        if (node == null) return null;
        MyEntry<K, V> parent = node.parent;
        // if parent is null use root as ptr for parent
        MyEntry<K, V> last = getLast(node.left);
        // Set whatever was above node to point to last.
        if (parent == null) root = last;
        else if (parent.left != null && compare(parent.left, node) == 0) parent.left = last;
        else parent.right = last;

        // wasn't any children from node
        if (last == null) {
            if (parent == null) root = node.right;
            else if (parent.left != null && compare(parent.left, node) == 0) parent.left = node.right;
            else parent.right = node.right;

            node.right.parent = parent;
            // System.out.printf("last is null, parent: \n%s node:\n%s last:\n%s parent.right: %s\n", parent,node, last,parent.right);
        }
        else {
            // Set last's parent to be parent or root
            last.parent = parent;
            
            last.right = node.right;
            // Was only one child in the tree on left side of node.
            // System.out.printf("Was only one child in the tree on left side of node:\n");
        // System.out.printf("returned node: %s with key: %s\n", node, key);
            if (node.left == last) return node; 
            last.parent.right = last.left;
        }
        // System.out.printf("returned node: %s with key: %s\n", node, key);
        return node;
    }
    
    public MyEntry<K, V> get(K key)
    {
        return get(key, root);
    }
    
    // allows null nodes
    public MyEntry<K, V> get(K key, MyEntry<K, V> node)
    {
        if (node == null) return null;
        int res = compare(key, node.key());
        if (res == 0)  return node; 
        else if (res < 0) return get(key, node.left);
        else return get(key, node.right);
    }
    
    public int size() { return(size(root)); }
    private int size(MyEntry<K, V> node)
    {
        if (node == null) return 0;
        else return size(node.left) + 1 + size(node.right);
    }
    
    public Iterator<MyEntry<K, V>> iterator()
    {
        Iterator<MyEntry<K,V>> it = new AscIterator(root, this);
        return it;
    }

    @Override
    public String toString()
    {
        StringBuffer st = new StringBuffer("BinarySearchTree toString:\n");
        Iterator<MyEntry<K, V>> it = iterator();
        while (it.hasNext()) {
        // while(true) {
        // for (MyEntry<K,V> thing: it) {
            // System.out.printf("So far: %s\n", st.toString());
            MyEntry<K, V> thing = it.next();
            st.append(thing.key());
            st.append(": ");
            st.append(thing.value());
            st.append("\n");
        }
        it = null;
        return st.toString();
    }

    // Get last item in this tree.
    public MyEntry<K, V> getLast(MyEntry<K, V> node)
    {
        if (node == null) return null;
        else {
            MyEntry<K,V> last = node;
            while (last.right != null) last = last.right;
            return last;
        }
    }

     // Get first item in this tree.
    public MyEntry<K, V> getFirst(MyEntry<K, V> node)
    {
        MyEntry<K, V> first;
        if (node == null) return null;
        else {
            first = node;
            while (first.left != null) first = first.left;
            return first;
        }
    }
    // Doesn’t guarantee ConcurrentModificationException will be thrown ever
    class AscIterator implements Iterator<MyEntry<K, V>>
    {
    private final Comparator<? super K> comparator = null;
        private MyEntry<K, V> last, current, root;
        private boolean isStarted = false;
        private BinarySearchTree<K,V> father;
        public AscIterator(MyEntry<K,V> rootPassed, BinarySearchTree<K,V> iAmYourFather)
        {
            this.root = rootPassed;
            last = getLast(rootPassed);
            current = getFirst(rootPassed);
            father = iAmYourFather;
        }
        @Override
        public boolean hasNext()
        {
            if (current == null) return false;
            if (!isStarted) return true;
            if (current == last) {
                if (current.right == null) return true;
                else throw new ConcurrentModificationException("Tree was modified during iteration");
            } else { 
            if (successor(current) == null) return false; else return true; }
        } // method hasNext

        // Returns successor to this node, null if this is null or no successor.
        private MyEntry<K, V> successor(MyEntry<K, V> node) 
        {
            if (node == null) return null;
            if (node.right != null) {
                MyEntry<K, V> child = node.right;
                while (child.left != null) child = child.left;
                return child;
            } else { // go up
                MyEntry<K, V> p = node.parent;
                if (p == null) return null; // at root
                else { // not root, but need to go up and right.
                    MyEntry<K, V> child = node;
                    while (p != null && p.right != null && father.equals(p.right, child)) {
                        child = p;
                        p = p.parent;
                        // System.out.println("looping in  awhile smu1ehrere");
                        // System.out.println("child: " + child + " parent: " + p);
                    } // while advance up tree
                    if (p == null) return null;
                    else if (p == node) return null;
                    else return p;
                } // else - not root, go right + up
            } // else - need to look up
        } // method successor

        // Find next node for in-order traversal (LNR), advances iterator.
        @Override
        public MyEntry<K, V> next()
        {
            if (!isStarted) isStarted = true;
            MyEntry<K, V> toRet = current;
            current = successor(current);
            return toRet;
        } // method next

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    } // class AscIterator
} // class bst
