package sorting;

import java.util.Comparator;

public class ShellSort<E> implements ISort<E> {

    @Override
    public void sort(E[] array, Comparator<E> comparator) {
        int n = array.length;
        int interval = n;
        while ((interval /= 2) > 0) {
            for(int i = interval, j; i < n; i++){
                E temp = array[i];
                for(j = i; j >= interval && comparator.compare(temp, array[j - interval]) < 0; j -= interval){
                    array[j] = array[j - interval];
                }
                array[j] = temp;
            }
        }

    }
}
