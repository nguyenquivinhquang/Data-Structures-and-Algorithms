package geom;

import java.util.Comparator;

public class PointComparator implements Comparator<Point2D> {
    enum sortTyoe {
        ASC(1),
        DES(-1);

        private int constant;

        sortTyoe(int constant) {
            this.constant = constant;
        }

        int getConstant() {
            return this.constant;
        }
    }

    private sortTyoe direction;

    public PointComparator(boolean isAscending) {
        if (isAscending) this.direction = sortTyoe.ASC;
        else this.direction = sortTyoe.DES;
    }

    @Override
    public int compare(Point2D o1, Point2D o2) {
        if (Math.abs(o1.getX() - o2.getX()) < 1e-7) return 0;
        else if (o1.getX() < o2.getX()) return -1 * direction.getConstant();
        else return 1 * direction.getConstant();
    }
}
