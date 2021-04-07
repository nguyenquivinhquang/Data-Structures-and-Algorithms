package sorting;

import java.util.Comparator;

public class ShellSort<E> implements ISort<E> {
    private int[] num_segments;
    public ShellSort(int[] num_segments) {
        this.num_segments = num_segments; }
    @Override
    public void sort(E arr[], Comparator<E> comparator) {
        int n = arr.length;
        int num_seg_idx = this.num_segments.length - 1, k = this.num_segments[num_seg_idx];
        while (num_seg_idx >= 0 && k >= 1) {
            int segment = 1;
            int seg_length = (int)Math.floor((double)n / (double)k);
            while (segment <= k) {
                sortSegment(arr, (segment - 1)*(n / k), Math.min(segment*seg_length, n) - 1, comparator);
                segment += 1;
            }
            if (num_seg_idx == 0) break;
            --num_seg_idx;
            k = this.num_segments[num_seg_idx];
        }
    }
    private void sortSegment(E arr[], int l, int r, Comparator<E> comparator) {
        if (r - l + 1 <= 1) return;
        
        int current, walker, n = r - l + 1;
        E temp;
        current = 1;
        while(current < n) {
            temp = arr[current + l];
            walker = current + l - 1;
            while((walker >= l) && comparator.compare(temp, arr[walker]) < 0) {
                arr[walker + 1] = arr[walker]; //shift to right
                walker -= 1;
            }
            arr[walker + 1] = temp;
            current += 1;
        }
    }
    // Another shell sort method, shell sort with segments divided / 2 after each itearations
    @Override
    public void sortSegmentMethod2(E[] array, Comparator<E> comparator) {
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
