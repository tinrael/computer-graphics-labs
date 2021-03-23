package ua.knu.csc.core;

import java.util.Iterator;
import java.awt.Point;

public class QuickHull {
    public enum PointAndLineLocationType {
        LEFT,
        RIGHT,
        BEYOND,
        BEHIND,
        BETWEEN,
        ORIGIN,
        DESTINATION
    }

    // Returns the leftmost point (minimum x-coordinate).
    public static Point getLeftmostPoint(Iterable<Point> points) {
        if (points == null) {
            throw new NullPointerException("The specified 'points' argument is null.");
        }

        Point leftmostPoint = null;

        Iterator<Point> iterator = points.iterator();

        if (iterator.hasNext()) {
            leftmostPoint = iterator.next();

            while (iterator.hasNext()) {
                Point currentPoint = iterator.next();

                if (currentPoint.x < leftmostPoint.x) {
                    leftmostPoint = currentPoint;
                }
            }
        }

        return leftmostPoint;
    }

    public static double calculateVectorLength(Point p) {
        return Math.sqrt(p.x * p.x + p.y * p.y);
    }

    // Determines the location of the point 'p2' concerning the oriented from 'p0' to 'p1' straight line segment.
    public static PointAndLineLocationType classify(Point p0, Point p1, Point p2) {
        Point a = new Point(p1.x - p0.x, p1.y - p0.y); // p1 - p0 (vector subtraction)
        Point b = new Point(p2.x - p0.x, p2.y - p0.y); // p2 - p0 (vector subtraction)

        int sign = a.x * b.y - b.x * a.y;

        if (sign > 0) {
            return PointAndLineLocationType.LEFT;
        }

        if (sign < 0) {
            return PointAndLineLocationType.RIGHT;
        }

        if ((a.x * b.x < 0) || (a.y * b.y < 0)) { // if vectors 'a' and 'b' are point in the opposite directions
            return PointAndLineLocationType.BEHIND;
        }

        if (calculateVectorLength(a) < calculateVectorLength(b)) {
            return PointAndLineLocationType.BEYOND;
        }

        if ((p0.x == p2.x) && (p0.y == p2.y)) { // p0 == p2
            return PointAndLineLocationType.ORIGIN;
        }

        if ((p1.x == p2.x) && (p1.y == p2.y)) { // p1 == p2
            return PointAndLineLocationType.DESTINATION;
        }

        return PointAndLineLocationType.BETWEEN;
    }

    // Calculates twice the area of a triangle.
    public static int calculateTwiceAreaOfTriangle(Point p1, Point p2, Point p3) {
        return ((p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y));
    }

    // From the given 'points', finds the farthest point from the line segment PQ.
    public static Point findFarthestPointFromLineSegment(Iterable<Point> points, Point p, Point q) {
        if (points == null) {
            throw new NullPointerException("The specified 'points' argument is null.");
        }

        Point farthestPoint = null;

        Iterator<Point> iterator = points.iterator();

        if (iterator.hasNext()) {
            farthestPoint = iterator.next();

            int twiceAreaOfTriangle = calculateTwiceAreaOfTriangle(p, q, farthestPoint);

            while (iterator.hasNext()) {
                Point currentPoint = iterator.next();

                int currentTwiceAreaOfTriangle = calculateTwiceAreaOfTriangle(p, q, currentPoint);

                if (currentTwiceAreaOfTriangle > twiceAreaOfTriangle) {
                    farthestPoint = currentPoint;
                    twiceAreaOfTriangle = currentTwiceAreaOfTriangle;
                }
            }
        }

        return farthestPoint;
    }
}
