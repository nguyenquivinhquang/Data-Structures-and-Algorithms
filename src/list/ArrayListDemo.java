package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import geom.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<Integer> list = new MyArrayList<>();
        int[] arr = new int[10];
        //Add elements

        for (int idx = 0; idx < 10; idx++) {
            list.add(idx);
//            arr[idx] = idx;
        }


        System.out.println(list.contains(3));

        //(1)Print elements - Use Index, travel forward
//        System.out.printf("%-25s", "Before modification:");
//        for (int idx = 0; idx < list.size(); idx++) {
//            System.out.printf("%s ", list.get(idx));
//        }
//        System.out.println();
//        //(2)Remove odd numbers
        ListIterator<Integer> it = list.listIterator();
        // list = {1,2 , 5, 4,5,6,7}
        // {2, 5, 4,5,6,7}

        /*
            it = elements[0];
            it = elements[1];
            it = elements[2];
         */


        //(3) Print after changing
        System.out.printf("%-25s", "After modification:");
        it = list.listIterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.println();
    }



}