Default of heap is min heap. To change to max heap, declare like the line below

>  Heap<Integer> heap = new Heap<>(new IntComparator());

  
  >
    class IntComparator implements Comparator<Integer>{ // int comparator for maxheap

      @Override
      public int compare(Integer o1, Integer o2) {
          return o2 - o1; // <- want minheap, change return o1-o2
      }
    }
