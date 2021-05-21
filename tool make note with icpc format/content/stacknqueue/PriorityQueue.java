package stacknqueue;
import heap.Heap;
import java.util.Comparator;
public class PriorityQueue<T> extends Heap<T> {
    public PriorityQueue(){
        super();
    }
    public PriorityQueue(Comparator<? super T> comparator){
        super(comparator);
    }
}
