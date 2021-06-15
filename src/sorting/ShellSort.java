package sorting;
import java.util.Comparator;

public class ShellSort<E> implements ISort<E> {
    //Sort segement k:
    /* To call this method, 
	int[] num_segment = {1, 3, 7}; shell_sort(list, num_segment);	*/
    private int[] num_segment;  
    public ShellSort(){
        this.num_segment = num_segment;  
    }  
    public static <E> void sortSegment(E[] array, int segment_idx, int num_segment,  
            Comparator<E> comparator){  
        int current, walker;
        E temp;  
        current = segment_idx + num_segment;  
        while(current < array.length){  
            temp = array[current];  
            walker = current - num_segment;  
            while((walker >= 0) &&  comparator.compare(temp, array[walker]) < 0 ){  
                array[walker + num_segment] = array[walker]; //shift to right  
                walker -= num_segment;  
            }  
            array[walker + num_segment] = temp;  
            current += num_segment;  
        }  
    }  
    public void sort(E[] array, Comparator<E> comparator){  
        for(int k= num_segment.length - 1; k > 0; k--){
            int nsegment = num_segment[k];  
            for(int segment_idx = 0; segment_idx < k; segment_idx++)  
                ShellSort.sortSegment(array, segment_idx, nsegment, comparator);  
        }
    }

    // Another shell sort method, shell sort with segments divided / 2 after each itearations

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
