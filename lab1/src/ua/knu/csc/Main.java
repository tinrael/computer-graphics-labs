package ua.knu.csc;

import ua.knu.csc.ui.MainWindow;

import java.awt.Point;
import java.awt.Polygon;

public class Main {

    public static void main(String[] args) {
        Point originPoint = new Point(50, 500);

        int n = 12;
        int[] x = {60, 60, 125, 200, 325, 360, 420, 420, 550, 390, 200, 200};
        int[] y = {110, 290, 240, 370, 370, 240, 240, 370, 190, 70, 95, 150};

        Polygon polygon = new Polygon(x, y, n);
        Point point = new Point(150, 230);

        MainWindow mainWindow = new MainWindow("lab1", originPoint, polygon, point);
        mainWindow.setVisible(true);
    }
}
