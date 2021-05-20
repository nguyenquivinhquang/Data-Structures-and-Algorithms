
package tree;

import java.util.Iterator;
import java.util.List;


public interface IBinarySearchTree<T> {
    public void add(T item);
    public void remove(Object key);
    public T search(Object key);
    public int size();
    public List<T> ascendingList();
    public List<T> descendingList();
}
