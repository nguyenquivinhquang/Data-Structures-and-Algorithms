
package geom;

import java.util.Comparator;

public class M2PointComparator implements Comparator<Point2D> {
    enum SortDirection {
        ASCENDING(1),
        DESCENDING(-1);

        private int constant;

        SortDirection(int constant) {
            this.constant = constant;
        }

        int getConstant() {
            return this.constant;
        }
    }

    private SortDirection direction;
    private Point2D origin;

    public M2PointComparator(Point2D[] pointList, boolean isAscending) {
        if (isAscending) this.direction = SortDirection.ASCENDING;
        else this.direction = SortDirection.DESCENDING;

        this.origin = getCenterOfMass(pointList);
    }

    private Point2D getCenterOfMass(Point2D[] a) {
        int n = a.length;
        double total_x = 0, total_y = 0;
        for (Point2D t : a) {
            total_x += t.getX();
            total_y += t.getY();
        }

        return new Point2D(total_x / (double) n, total_y / (double) n);
    }

    @Override
    public int compare(Point2D o1, Point2D o2) {
        double o1_dist = this.origin.distanceAB(o1), o2_dist = this.origin.distanceAB(o2);
        if (Math.abs(o1_dist - o2_dist) < 1e-9) return 0;
        else if (o1_dist < o2_dist) return -1 * direction.getConstant();
        else return 1 * direction.getConstant();
    }
}