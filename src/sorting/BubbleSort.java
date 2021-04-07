/*
 * Bubble sort is the worst since for each of the `n - 1` iterations
 * it always iterates through the array `n - i - 1` times where `i` is the
 * `i-th` iteration, which is more than all other algorithms since other
 * algorithms has a stop condition for the inner loop but Bubble sort does not.
 */
package sorting;
import java.util.Comparator;
public class BubbleSort<E> implements ISort<E> {
    @Override
    public void sort(E arr[], Comparator<E> comparator) { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (comparator.compare(arr[j], arr[j + 1]) > 0) { 
                    E temp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = temp; 
                } 
    } 
}
