Default of heap is min heap. To change to max heap, declare like the line below

> HeapSort<Integer> heapSort = new HeapSort<>(new  IntComparator()); 

  
  >
    class IntComparator implements Comparator<Integer>{ // int comparator for maxheap

      @Override
      public int compare(Integer o1, Integer o2) {
          return o2 - o1; // <- want min heap, change return o1-o2
      }
    }
