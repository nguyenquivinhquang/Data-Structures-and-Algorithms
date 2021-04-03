package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import geom.*;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //Add elements
        for (int idx = 0; idx < 10; idx++) {
            list.add(idx);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-25s", "Before modification:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();
        //(2)Remove odd numbers
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            int item = it.next();
            if (item % 2 != 0) it.remove();
            else it.set(item * 10);
        }
        //(3) Print after changing
        System.out.printf("%-25s", "After modification:");
        it = list.listIterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.println();
    }



}