package ua.knu.csc.core;

import java.awt.Point;

public class ConvexHull {
    /* Calculates twice the signed area of the triangle a->b->c.
     * If signed area > 0, then a->b->c is counterclockwise (c to the left of the ray a->b, i.e. left turn).
     * If signed area < 0, then a->b->c is clockwise (c to the right of the ray a->b, i.e. right turn).
     * If signed area = 0, then a->b->c are collinear (a, b and c lie on a single straight line).
     */
    public static int calculateTwiceSignedAreaOfTriangle(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }
}
