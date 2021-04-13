package ua.knu.csc.core;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Stack;

public class ConvexHull {
    /* Calculates twice the signed area of the triangle a->b->c.
     * If signed area > 0, then a->b->c is counterclockwise (c to the left of the ray a->b, i.e. left turn).
     * If signed area < 0, then a->b->c is clockwise (c to the right of the ray a->b, i.e. right turn).
     * If signed area = 0, then a->b->c are collinear (a, b and c lie on a single straight line).
     */
    public static int calculateTwiceSignedAreaOfTriangle(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }

    // Computes the convex hull of the specified list of points 'points' using Graham's scan.
    public static ArrayList<Point> applyGrahamScan(ArrayList<Point> points) {
        if (points == null) {
            throw new IllegalArgumentException("The specified list of points is null.");
        }

        if (points.size() < 3) {
            return points;
        }

        Point origin = points.get(0);
        for (Point point : points) {
            if ((point.y < origin.y) || ((point.y == origin.y) && (point.x < origin.x))) {
                origin = point;
            }
        }

        points.sort(new PolarOrderComparator(origin));

        Stack<Point> stack = new Stack<>();

        stack.push(points.get(0)); // or stack.push(origin), because points.get(0) is equal to origin
        stack.push(points.get(1));
        stack.push(points.get(2));

        for (int i = 3; i < points.size(); i++) {
            Point top = stack.pop();

            while (!stack.isEmpty() && (calculateTwiceSignedAreaOfTriangle(stack.peek(), top, points.get(i)) <= 0)) {
                top = stack.pop();
            }

            stack.push(top);
            stack.push(points.get(i));
        }

        return new ArrayList<>(stack);
    }
}
