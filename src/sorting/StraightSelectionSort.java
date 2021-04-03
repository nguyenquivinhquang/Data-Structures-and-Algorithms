
package sorting;

import java.util.Comparator;

/*
Method: StraightInsertionSort
Objective: Sort the array in descending or ascending
---
1/ Space complexity: O(n)
Reasons:
    + To store the array
Computational complexity: O(n^2),
    + At iteration of the first loop, it will find the i th small element. To find that element, this algorithm will use another loop from i + 1 to n to find that small element.
    + complexity is n * (n - 1) = n ^ 2
*/
public class StraightSelectionSort<E> implements ISort<E> {
    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int n = array.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (comparator.compare(array[j], array[min_idx]) < 0)
                    min_idx = j;

            // Swap the found minimum element with the firs element
            E temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }
}