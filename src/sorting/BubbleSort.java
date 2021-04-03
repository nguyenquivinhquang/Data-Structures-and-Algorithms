package sorting;

import java.util.Comparator;
/*
Method: Bubble sort
Objective: Sort the array in descending or ascending
---
1/ Space complexity: O(n)
Reasons:
    + To store the array
Computational complexity:
    + Average case O(n ^ 2)
*/
public class BubbleSort <E> implements ISort<E>  {
    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int n = array.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j + 1], array[j]) < 0) {
                    E temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
    }
}
