package sorting;
import java.util.Comparator;


public class StraightInsertionSort<E> implements ISort<E> {
   
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
}
}
