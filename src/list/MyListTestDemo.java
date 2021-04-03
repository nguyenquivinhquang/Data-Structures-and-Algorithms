package list;

import geom.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyListTestDemo {
    public static void removeHittedPoints(List<Point2D> list, Point2D testPoint, double radius) {
        Iterator<Point2D> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Point2D temp = it.next();
            i++;
            if (Point2D.distanceAB(testPoint, temp) < radius) {
                it.remove();
            }
        }

    }
    public static void main(String[] args) {
        Point2D[] points = Point2D.generate(20, -10, 20);
        ArrayList<Point2D> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(new Point2D(points[i]));
        }
        System.out.println("The original list :");
        for (Point2D temp : list) {
            System.out.println(temp);
        }
        System.out.println("---------------------");
        removeHittedPoints(list, new Point2D(5,3), 5.0);
        System.out.println("The result list :" + list.size() );
        for (Point2D temp : list) {
            System.out.println(temp);
        }

    }
}
