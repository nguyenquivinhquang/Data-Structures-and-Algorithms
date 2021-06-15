/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geom;
import java.util.Comparator;


public class PointComparator implements Comparator<Point2D>{
    public PointComparator(boolean isAscending) {
    }

    @Override
    public int compare(Point2D o1, Point2D o2) {
        int t = (int) (Math.abs(o1.distanceTo(new Point2D(0,0))) - Math.abs(o2.distanceTo(new Point2D(0,0))));
        return t ;
    }

}