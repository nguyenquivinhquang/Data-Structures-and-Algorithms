package geom;

import java.awt.*;
import java.util.Random;

public class Point2D extends GeomObject {
    private double x, y;
    public static int POINT_HALF_SIZE = 2;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Point2D(Point2D old) {
        this(old.getX(), old.getY());
    }

    public Point2D clone() {
        return new Point2D(this.x, this.y);
    }

    @Override
    public String toString() {
        return String.format("P(%6.2f, %6.2f)", this.x, this.y);
    }

    public static Point2D[] generate(int n, double min, double max) {
        Point2D[] arr = new Point2D[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            double range = max - min + 1;
            double x = min + (range) * r.nextDouble();
            double y = min + (range) * r.nextDouble();
            arr[i] = new Point2D(x, y);
        }
        return arr;
    }

    public static double distanceAB(Point2D A, Point2D B) {
        double x_d = A.getX() - B.getX();
        double y_d = A.getY() - B.getY();
        x_d *= x_d;
        y_d *= y_d;
        return Math.sqrt(x_d + y_d);
    }

    public double distanceAB(Point2D B) {
        return distanceAB(this, B);
    }



    /*     answer here      */
    public static Point2D[] linear(int N, double a, double b, double xMin, double xMax) {
        Point2D[] arr = new Point2D[N];
        double step = (xMax - xMin) / (N - 1); //xMax inclusive
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            double y = a * x + b;
            arr[idx] = new Point2D(x, y);
            x += step;
        }
        return arr;
    }
    @Override
    public void draw(Graphics g, SpaceMapping mapper) {

        Point2D point = mapper.logic2Device(this.getX(), this.getY());

        int x = (int) point.getX() - POINT_HALF_SIZE;
        int y = (int) point.getY() - POINT_HALF_SIZE;

        g.setColor(this.faceColor);
        System.out.println(x);
        g.fillRect(x, y, 2 * POINT_HALF_SIZE + 1, 2 * POINT_HALF_SIZE + 1);
        g.fillOval(x, y, POINT_HALF_SIZE, POINT_HALF_SIZE);
    }

}
