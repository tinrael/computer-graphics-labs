package ua.knu.csc.core;

import java.awt.Point;
import java.awt.Polygon;

public class EvenOddRule {
    public enum PointAndLineLocationType {
        LEFT,
        RIGHT,
        BEYOND,
        BEHIND,
        BETWEEN,
        ORIGIN,
        DESTINATION
    }

    public enum EdgeType {
        TOUCHING,
        CROSSING,
        INESSENTIAL
    }

    public enum PointInPolygonLocationType {
        INSIDE,
        OUTSIDE,
        BOUNDARY
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

    public static EdgeType getEdgeType(Point p0, Point p1, Point p2) {
        switch (classify(p0, p1, p2)) {
            case LEFT:
                return ((p0.y < p2.y) && (p2.y <= p1.y)) ? EdgeType.CROSSING : EdgeType.INESSENTIAL;
            case RIGHT:
                return ((p1.y < p2.y) && (p2.y <= p0.y)) ? EdgeType.CROSSING : EdgeType.INESSENTIAL;
            case BETWEEN:
            case ORIGIN:
            case DESTINATION:
                return EdgeType.TOUCHING;
            default:
                return EdgeType.INESSENTIAL;
        }
    }

    public static PointInPolygonLocationType pointInPolygon(Point a, Polygon p) {
        boolean isInsidePolygon = false;

        int i = p.npoints - 1;
        for (int j = 0; j < p.npoints; j++) {
            Point p0 = new Point(p.xpoints[i], p.ypoints[i]);
            Point p1 = new Point(p.xpoints[j], p.ypoints[j]);

            switch (getEdgeType(p0, p1, a)) {
                case TOUCHING:
                    return PointInPolygonLocationType.BOUNDARY;
                case CROSSING:
                    isInsidePolygon = !isInsidePolygon;
            }

            i = j;
        }

        return (isInsidePolygon  ? PointInPolygonLocationType.INSIDE : PointInPolygonLocationType.OUTSIDE);
    }
}
