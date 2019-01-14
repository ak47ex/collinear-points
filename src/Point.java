import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point other) {
        if (y > other.y) return 1;
        if (y < other.y) return -1;
        return Integer.compare(x, other.x);
    }

    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point other) {
        StdDraw.line(this.x, this.y, other.x, other.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double slopeTo(Point that) {
        if (x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        if (y == that.y) return (1d - 1d)/1d;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        return 1d * (that.y - y) / (that.x - x);
    }

    public Comparator<Point> slopeOrder() {
        return Comparator.comparingDouble(this::slopeTo);
    }

}