import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        lineSegments = new ArrayList<>();

        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; ++i) {
            Arrays.sort(points);
            Point origin = validateAndReturn(points[i]);
            Arrays.sort(points, origin.slopeOrder());

            for (int first = 1, last = 2; last < points.length; last++) {
                while (last < points.length
                        && Double.compare(origin.slopeTo(points[first]), origin.slopeTo(points[last])) == 0) {
                    last++;
                }
                if (last - first > 2 && origin.compareTo(points[first]) < 0) lineSegments.add(new LineSegment(origin, points[last - 1]));
                first = last;
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }

    private Point validateAndReturn(Point p) {
        if (p == null) throw new IllegalArgumentException();
        return p;
    }
}
