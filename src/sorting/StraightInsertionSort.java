
package sorting;

import java.util.Comparator;

/*
Method: StraightInsertionSort
Objective: Sort the array in descending or ascending
---
1/ Space complexity: O(n)
Reasons:
    + To store the array
Computational complexity:
    + Best case: O(n), Average case O(n)
    + At the current index i, this algo will find the i predecessor. If exist the predecessor that i is smaller that predecessor, it will continue go find the predecessor of predecessor.
*/
public class StraightInsertionSort<E> implements ISort<E> {
    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int n = array.length;

        for (int i = 1; i < n ; i++) {
            for (int j = i - 1; j >= 0 && comparator.compare(array[j + 1], array[j ]) < 0; j-- ) {
                E temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }

        }
    }
}