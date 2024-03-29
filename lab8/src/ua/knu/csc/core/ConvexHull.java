package ua.knu.csc.core;

import java.util.Queue;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

import java.awt.Point;

public class ConvexHull {
    public enum Part {
        UPPER,
        LOWER
    }

    /* Calculates twice the signed area of the triangle a->b->c.
     * If signed area > 0, then a->b->c is counterclockwise (c to the left of the ray a->b, i.e. left turn).
     * If signed area < 0, then a->b->c is clockwise (c to the right of the ray a->b, i.e. right turn).
     * If signed area = 0, then a->b->c are collinear (a, b and c lie on a single straight line).
     */
    public static int calculateTwiceSignedAreaOfTriangle(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
    }

    /* This function assumes the specified simple polygon 'simplePolygon' to be clockwise-ordered.
     *
     * TODO: This function also assumes that simplePolygon.get(0) is the leftmost point.
     *  A possible solution to remove this restriction is to use a circular list for a simple polygon 'simplePolygon'.
     */
    public static List<Point> findConvexHullOfSimplePolygon(List<Point> simplePolygon) {
        if (simplePolygon == null) {
            throw new NullPointerException("The specified parameter 'simplePolygon' is null.");
        }

        if (simplePolygon.isEmpty()) {
            return new LinkedList<>();
        }

        ListIterator<Point> listIterator = simplePolygon.listIterator();

        Point leftmostPoint = listIterator.next();
        int leftmostPointIndex = listIterator.previousIndex();

        Point rightmostPoint = leftmostPoint;
        int rightmostPointIndex = leftmostPointIndex;

        while (listIterator.hasNext()) {
            Point currentPoint = listIterator.next();

            if (currentPoint.x < leftmostPoint.x) {
                leftmostPoint = currentPoint;
                leftmostPointIndex = listIterator.previousIndex();
            } else if (currentPoint.x > rightmostPoint.x) {
                rightmostPoint = currentPoint;
                rightmostPointIndex = listIterator.previousIndex();
            }
        }

        simplePolygon.add(leftmostPoint);

        // upper-hull
        List<Point> upperHull = applyLeeAlgorithm(simplePolygon.subList(leftmostPointIndex, rightmostPointIndex + 1), Part.UPPER);

        // lower-hull
        List<Point> lowerHull = applyLeeAlgorithm(simplePolygon.subList(rightmostPointIndex, simplePolygon.size()), Part.LOWER);

        lowerHull.remove(0); // to remove the rightmost point as it is already in the upper-hull
        lowerHull.remove(lowerHull.size() - 1); // to remove the leftmost point as it is already in the upper-hull

        upperHull.addAll(lowerHull); // the convex hull of a simple polygon

        // remove previously added point 'leftmostPoint' to save the list 'simplePolygon' unchanged
        simplePolygon.remove(simplePolygon.size() - 1);

        return upperHull;
    }

    /* The parameter 'part' specify which part of a simple polygon passed as the parameter 'points'.
     * This function assumes the specified part of a simple polygon (i.e. the parameter 'points') to be clockwise-ordered.
     * This function also assumes that points.get(0) is the leftmost point and that points.get(points.size() - 1) is the rightmost point.
     */
    public static List<Point> applyLeeAlgorithm(List<Point> points, Part part) {
        if (points == null) {
            throw new NullPointerException("The specified list of points 'points' is null.");
        }

        if (points.isEmpty()) {
            throw new IllegalArgumentException("The specified list of points 'points' is empty.");
        }

        Queue<Point> queue = new LinkedList<>(points); // P
        Stack<Point> stack = new Stack<>(); // Q

        Point p1 = queue.poll(); // the leftmost point
        Point q0 = switch (part) {
            case UPPER -> new Point(p1.x, p1.y - 1);
            case LOWER -> new Point(p1.x, p1.y + 1);
            default -> throw new IllegalArgumentException("The specified part 'part' is illegal.");
        };

        stack.push(q0);
        stack.push(p1);

        Point u = q0;

        Point qM = points.get(points.size() - 1); // q_{M} (the rightmost point)

        Point previousToV = p1; // the point that immediately precedes the point v (the current point)
        while (!queue.isEmpty()) {
            Point v = queue.poll(); // the current point

            Point q2 = stack.pop(); // q_{i}
            Point q1 = stack.peek(); // q_{i-1}
            stack.push(q2);

            if (calculateTwiceSignedAreaOfTriangle(q1, q2, v) < 0) { // regions 1, 3, 4
                if (calculateTwiceSignedAreaOfTriangle(u, q2, v) < 0) { // regions 3, 4
                    if (calculateTwiceSignedAreaOfTriangle(qM, q2, v) < 0) { // region 3
                        stack.push(v);
                        u = previousToV;
                    } else { // region 4
                        while (!queue.isEmpty() && calculateTwiceSignedAreaOfTriangle(qM, q2, queue.peek()) >= 0) {
                            v = queue.poll();
                        }
                    }
                } else { // region 1
                    while (calculateTwiceSignedAreaOfTriangle(q2, q1, queue.peek()) >= 0) {
                        v = queue.poll();
                    }
                }
            } else { // region 2
                while (calculateTwiceSignedAreaOfTriangle(q1, q2, v) > 0) {
                    stack.pop(); // to remove the current q_{i}

                    q2 = stack.pop(); // to access a new q_{i}
                    q1 = stack.peek(); // to access a new q_{i-1}
                    stack.push(q2);
                }
                stack.push(v);
                u = previousToV;
            }

            previousToV = v;
        }

        List<Point> convexHull = new LinkedList<>(stack);
        convexHull.remove(0); // to remove q0
        convexHull.add(qM);

        return convexHull;
    }
}
