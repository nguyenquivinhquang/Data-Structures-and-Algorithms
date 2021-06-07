package heap;

import java.util.Comparator;

public class HeapSort <T> extends Heap{
    public HeapSort() {

    }
    public HeapSort(Comparator<? super T> comparator){
        super(comparator);
    }
    public void sort(T[] arr) {
        // Dùng maxHeap để sort giảm dần, minHeap để sort tăng dần
        this.heapify(arr);
        for (int i = arr.length; i >= 1; i--) {
            swap(1, i);
            this.size--;
            reheapDown(1);
        }
        for (int i = 1; i <= arr.length; i++)
            System.out.println(elements[i]);
    }

    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>(new  IntComparator()); // <- cái này là maxHeap nha
        Integer[] tmp = new Integer[]{9, 45, 1, 3, 2, 5};
        heapSort.sort(tmp);

    }
}
