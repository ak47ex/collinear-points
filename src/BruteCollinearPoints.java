import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        lineSegments = new ArrayList<>();
        if (points.length < 4) {
            for (int i = 0; i < points.length - 1; ++i) {
                for (int j = i + 1; j < points.length; ++j) {
                    if (validateAndReturn(points[i]).compareTo(validateAndReturn(points[j])) == 0) throw new IllegalArgumentException();
                }
            }
        }

        for (int i = 0; i < points.length - 3; ++i) {
            for (int j = i + 1; j < points.length - 2; ++j) {
                for (int k = j + 1; k < points.length - 1; ++k) {
                    for (int z = k + 1; z < points.length; ++z) {
                        Point p = validateAndReturn(points[i]);
                        Point q = validateAndReturn(points[j]);
                        Point r = validateAndReturn(points[k]);
                        Point s = validateAndReturn(points[z]);

                        if (p.compareTo(q) == 0) throw new IllegalArgumentException();
                        if (p.compareTo(r) == 0) throw new IllegalArgumentException();
                        if (p.compareTo(s) == 0) throw new IllegalArgumentException();
                        if (q.compareTo(r) == 0) throw new IllegalArgumentException();
                        if (q.compareTo(s) == 0) throw new IllegalArgumentException();
                        if (r.compareTo(s) == 0) throw new IllegalArgumentException();

                        if (isCollinear(p, q, r, s)) {
                            Point[] ordered = {p, q, r, s};
                            Arrays.sort(ordered);
                            lineSegments.add(new LineSegment(ordered[0], ordered[3]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    private boolean isCollinear(Point p, Point q, Point r, Point s) {
        return p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s);
    }

    private Point validateAndReturn(Point p) {
        if (p == null) throw new IllegalArgumentException();
        return p;
    }
}
