package ua.knu.csc.core;

import java.awt.Point;
import java.util.Comparator;

public class PolarOrderComparator implements Comparator<Point> {
    private final Point origin;

    public PolarOrderComparator() {
        origin = new Point(0, 0);
    }

    public PolarOrderComparator(Point origin) {
        this.origin = origin;
    }

    /* Compares points 'p1' and 'p2' relative to polar angle (between 0 and 2pi) they make with the point 'origin',
     * breaking ties by the distance to the point 'origin'.
     * Formally, the point 'p1' is less than the point 'p2' if and only if either
     * 1) the polar angle the point 'p1' makes with respect to the point 'origin' is less than the polar angle of the point 'p2'
     * 2) or if polar angles are equal and Math.abs(p1.x) < Math.abs(p2.x)
     * 3) or if polar angles are equal, p1.x == 0, p2.x == 0 and Math.abs(p1.y) < Math.abs(p2.y).
     */
    @Override
    public int compare(Point p1, Point p2) {
        int x1 = p1.x - origin.x;
        int y1 = p1.y - origin.y;

        int x2 = p2.x - origin.x;
        int y2 = p2.y - origin.y;

        if ((y1 >= 0) && (y2 < 0)) { // p1 above; p2 below
            return -1;
        } else if ((y2 >= 0) && (y1 < 0)) { // p1 below; p2 above
            return 1;
        } else if ((y1 == 0) && (y2 == 0)) { // 3-collinear and horizontal
            if ((x1 >= 0) && (x2 < 0)) {
                return -1;
            } else if ((x2 >= 0) && (x1 < 0)) {
                return 1;
            } else {
                return Integer.compare(Math.abs(x1), Math.abs(x2));
            }
        } else { // both p1 and p2 above or below
            if ((x1 == 0) && (x2 == 0)) { // 3-collinear and vertical
                return Integer.compare(Math.abs(y1), Math.abs(y2));
            } else {
                int twiceSignedAreaOfTriangle = ConvexHull.calculateTwiceSignedAreaOfTriangle(origin, p1, p2);
                if (twiceSignedAreaOfTriangle == 0) { // 3-collinear
                    return Integer.compare(Math.abs(x1), Math.abs(x2));
                } else {
                    return -twiceSignedAreaOfTriangle;
                }
            }
        }
    }
}
