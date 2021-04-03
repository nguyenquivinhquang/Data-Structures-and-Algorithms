package sorting;

import java.util.Comparator;

public interface ISort<E> {
    public void sort(E[] array, Comparator<E> comparator);
}