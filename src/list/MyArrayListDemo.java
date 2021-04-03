package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import geom.*;

public class MyArrayListDemo {
    public static void problem1() {
        List<Integer> list = new SLinkedList<>();
        //Add elements
        for (int idx = 0; idx < 10; idx++) {
            Integer object = idx;
            list.add(object);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-25s", "Before modification:");
        for (Integer integer : list) {
            System.out.printf("%s ", integer);
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

    public static void problem2() {
        Point2D[] points = Point2D.generate(20, -10, 20);
        List<Point2D> list = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Point2D(points[i]));
        }
        System.out.println("The original list :");
        for (Point2D temp : list) {
            System.out.println(temp);
        }
        System.out.println("---------------------");
        MyListTestDemo.removeHittedPoints(list, new Point2D(5, 3), 7.0);
        System.out.println("The result list :" + list.size());
        for (Point2D temp : list) {
            System.out.println(temp);
        }
    }

    public static void main(String[] args) {
        problem2();
    }


}