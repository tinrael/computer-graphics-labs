package ua.knu.csc;

import ua.knu.csc.ui.MainWindow;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Point originPoint = new Point(200, 300);

        int n = 4;
        int[] x = {10, 10, 100, 100};
        int[] y = {10, 50, 50, 10};

        Polygon polygon = new Polygon(x, y, n);
        Point point = new Point(100, 50);

        MainWindow mainWindow = new MainWindow("lab1", originPoint, polygon, point);
        mainWindow.setVisible(true);
    }
}
