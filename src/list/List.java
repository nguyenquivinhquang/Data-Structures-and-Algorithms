package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> {
    //Group-1: read list’s properties
    public int size();

    public boolean isEmpty();

    //Group-2: add elements
    public boolean add(E e);

    public void add(int index, E element);

    //Group-3: remove elements
    public E remove(int index);

    public boolean remove(Object o);

    public void clear();

    //Group-4: set and get elements with indices
    public E get(int index);

    public E set(int index, E element);

    //Grpup-5: map an object to its index + check object existing?
    public int indexOf(Object o);

    public int lastIndexOf(Object o);

    public boolean contains(Object o);

    //Group-6: travel on lists
    public Iterator<E> iterator();

    public ListIterator<E> listIterator();

    public ListIterator<E> listIterator(int index);

    //Supplementary functionalities
    public Object[] toArray();

    public <T> T[] toArray(T[] a);

    public boolean containsAll(Collection<?> c);

    public boolean addAll(Collection<? extends E> c);

    public boolean addAll(int index, Collection<? extends E> c);

    public boolean removeAll(Collection<?> c);

    public boolean retainAll(Collection<?> c);

    public List<E> subList(int fromIndex, int toIndex);
}
