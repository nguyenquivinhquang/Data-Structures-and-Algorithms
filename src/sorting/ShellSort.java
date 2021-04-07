package sorting;
import java.util.Comparator;

public class ShellSort<E> implements ISort<E> {
   /*
    Sort segement k:
    
    */
    public static void sort_segment(Point2D[] points, int segment_idx, int num_segment){
        int current;
        int walker;
        Point2D temp;
        current = segment_idx + num_segment;
        while(current < points.length){
            temp = points[current];
            walker = current - num_segment;
            while((walker >= 0) && (temp.getX() < points[walker].getX()) ){
                points[walker + num_segment] = points[walker]; //shift to right
                walker -= num_segment;
            }
            points[walker + num_segment] = temp;
            current += num_segment;
        }
    }
    /*
    shell_sort
    -----------
    num_segments: 
         + The first must be 1, for examples: [1,3,7]
    */
    public static void shell_sort(Point2D[] points, int[] num_segment){
        for(int k=num_segment.length - 1; k > 0; k--){
            for(int segment_idx = 0; segment_idx < k; segment_idx++)
                sort_segment(points, segment_idx, k);
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
