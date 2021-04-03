package geom;

import java.awt.*;

public class Graph extends GeomObject {
    public enum GraphMode {LINE, SCATTER}

    ;
    protected Viewport viewport = null;
    private Point2D[] points = null;
    public GraphMode mode = GraphMode.LINE;
    public Viewport getViewport() {
        return viewport;
    }
    public Graph(Point2D[] points, double xMin, double xMax, double yMin,
                 double yMax) {
        this.viewport = new Viewport(xMin, xMax, yMin, yMax);
        this.points = points;
    }

    public Graph(Point2D[] points, double xMin, double xMax, double yMin,
                 double yMax, Color color) {
        this.viewport = new Viewport(xMin, xMax, yMin, yMax);
        this.points = points;
        this.edgeColor = color;
    }

    private void copyPoints(Point2D[] points) {
        this.points = points;
        //update viewport
        this.viewport = new Viewport(points[0].getX(), points[0].getX(), points[0].getY(), points[0].getY());
        for (int idx = 0; idx < points.length; idx++)
            this.viewport.addPoint(points[idx]);

    }

    public Graph(Point2D[] points) {
        copyPoints(points);
    }

    public Graph(Point2D[] points, Color color) {
        copyPoints(points);
        this.edgeColor = color;
    }

    @Override
    public void draw(Graphics g, SpaceMapping mapper) {
        Graphics2D g2 = (Graphics2D) g;
        mapper.updateLogViewPort(this.viewport);

        if (this.mode == GraphMode.LINE) {
            if (this.points == null) return;

            int[] x = new int[this.points.length];
            int[] y = new int[this.points.length];

            for (int idx = 0; idx < this.points.length; idx++) {
                Point2D devPoint = mapper.logic2Device(this.points[idx]);
                x[idx] = (int) devPoint.getX();
                y[idx] = (int) devPoint.getY();
            }

            g2.setColor(this.edgeColor);
            Stroke style = new BasicStroke(this.line_weight);
            g2.setStroke(style);

            for (int i = 0; i < x.length; i++)
                System.out.println(x[i] + " " + y[i]);
            g2.drawPolyline(x, y, x.length);
        } else if (this.mode == GraphMode.SCATTER) {
            for (int idx = 0; idx < this.points.length; idx++) {
                this.points[idx].draw(g, mapper);
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return;
    }

    public static Graph sin(double a, double xMin, double xMax, int N) {
        Point2D[] points = new Point2D[N];
        double step = (xMax - xMin) / (N);
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            double y = a * Math.sin(2 * Math.PI * (x - xMin) / (xMax - xMin));

            points[idx] = new Point2D(x, y);
            x += step;
        }

        return new Graph(points);
    }

    public static Graph quadratic(double a, double b, double c, double xMin, double xMax, int N) {
        Point2D[] points = new Point2D[N];
        double step = (xMax - xMin) / (N);
        double x = xMin;
        for (int idx = 0; idx < N; idx++) {
            double y = a * x * x + b * x + c;
            points[idx] = new Point2D(x, y);
            x += step;
        }
        return new Graph(points);
    }
    public  static  Graph linear() {
        Point2D[] points = new Point2D[2];
        points[0] = new Point2D(-8, 50);
        points[1] = new Point2D(-0.81, -15);
        return new Graph(points);
    }
}

