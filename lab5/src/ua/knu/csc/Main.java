package ua.knu.csc;

import ua.knu.csc.core.QuickHull;

import java.awt.Point;

import java.util.Set;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        Set<Point> points1 = new HashSet<>(); // Convex hull: (0, 0) (0, 3) (3, 1) (4, 4).
        points1.add(new Point(0, 3));
        points1.add(new Point(1, 1));
        points1.add(new Point(2, 2));
        points1.add(new Point(4, 4));
        points1.add(new Point(0, 0));
        points1.add(new Point(1, 2));
        points1.add(new Point(3, 1));
        points1.add(new Point(3, 3));

        Set<Point> points2 = new HashSet<>(); // Convex hull: (-4, 0), (5, 0), (0, -6), (0, 4).
        points2.add(new Point(0, 0));
        points2.add(new Point(0, 4));
        points2.add(new Point(-4, 0));
        points2.add(new Point(5, 0));
        points2.add(new Point(0, -6));
        points2.add(new Point(1, 0));

        Set<Point> points3 = new HashSet<>(); // Convex hull: (6, 7), (7, 11), (11, 10), (8,8)
        points3.add(new Point(6, 7));
        points3.add(new Point(8, 8));
        points3.add(new Point(11, 10));
        points3.add(new Point(7, 11));

        System.out.println(QuickHull.findConvexHull(points1));
        System.out.println(QuickHull.findConvexHull(points2));
        System.out.println(QuickHull.findConvexHull(points3));
    }
}
