package sorting;
import java.util.Comparator;
public class StraightInsertionSort<E> implements ISort<E> {
    /* Method: sort
    Objective: Sort an array of arbitrary type according to a comparator:
    Computational complexity: O(n^2)
        + Traversing through the array takes O(n), and in each iteration,
        we compare the current element with the elements after it in the array,
        which takes at most O(n) for each iteration. Thus, O(n^2). */
    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int current, walker;
        E temp; current = 1;
        while(current < array.length) {
            temp = array[current];
            walker = current - 1;
            while((walker >= 0) && comparator.compare(temp, array[walker]) < 0) {
                array[walker + 1] = array[walker]; //shift to right
                walker -= 1;
            }
            array[walker + 1] = temp;
            current += 1;
        }
}}
