package ua.knu.csc;

import ua.knu.csc.core.EvenOddRule;

import ua.knu.csc.ui.MainWindow;

import java.awt.Point;
import java.awt.Polygon;

public class Main {

    public static void main(String[] args) {
        Point originPoint = new Point(50, 500);

        int n = 12;
        int[] x = {60, 60, 125, 200, 325, 360, 420, 420, 550, 390, 200, 200};
        int[] y = {110, 290, 240, 370, 370, 240, 240, 370, 150, 70, 95, 150};

        Polygon polygon = new Polygon(x, y, n);

        // Ordinary cases
        Point point = new Point(270, 330); // INSIDE
        // Point point = new Point(100, 350); // OUTSIDE

        // Special cases
        // Point point = new Point(170, 370); // OUTSIDE
        // Point point = new Point(20, 150); // OUTSIDE
        // Point point = new Point(270, 240); // INSIDE
        // Point point = new Point(100, 150); // INSIDE
        // Point point = new Point(70, 240); // INSIDE
        // Point point = new Point(390, 240); // BOUNDARY
        // Point point = new Point(420, 370); // BOUNDARY

        MainWindow mainWindow = new MainWindow("lab1", originPoint, polygon, point);
        mainWindow.setVisible(true);

        System.out.println(EvenOddRule.pointInPolygon(point, polygon));
    }
}
