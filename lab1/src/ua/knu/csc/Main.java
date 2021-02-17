package ua.knu.csc;

import ua.knu.csc.ui.MainWindow;

import java.awt.Point;
import java.awt.Polygon;

public class Main {

    public static void main(String[] args) {
        Point originPoint = new Point(50, 450);

        int n = 12;
        int[] x = {10, 10, 25, 50, 80, 95, 130, 130, 170, 110, 50, 50};
        int[] y = {60, 120, 90, 150, 150, 80, 80, 170, 80, 30, 45, 75};

        Polygon polygon = new Polygon(x, y, n);
        Point point = new Point(0, 20);

        MainWindow mainWindow = new MainWindow("lab1", originPoint, polygon, point);
        mainWindow.setVisible(true);
    }
}
