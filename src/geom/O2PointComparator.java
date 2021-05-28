
package geom;

import java.util.Comparator;

/**
 * @author tiencuong
 */

/*
 * This class is used to return a comparator for Point2D object.
 * The constructor requires one argument which is `isAscending`.
 * If this argument is `true`, then the comparator can only be used
 * for sorting in ascending direction.
 * Otherwise, descending.
 */
public class O2PointComparator implements Comparator<Point2D> {
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
    private Point2D origin = new Point2D();

    public O2PointComparator(boolean isAscending) {
        if (isAscending) this.direction = SortDirection.ASCENDING;
        else this.direction = SortDirection.DESCENDING;
    }

    @Override
    public int compare(Point2D o1, Point2D o2) {
        double o1_dist = this.origin.distanceTo(o1), o2_dist = this.origin.distanceTo(o2);
        if (Math.abs(o1_dist - o2_dist) < 1e-9) return 0;
        else if (o1_dist < o2_dist) return -1 * direction.getConstant();
        else return 1 * direction.getConstant();
    }
}