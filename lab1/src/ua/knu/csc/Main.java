package ua.knu.csc;

import ua.knu.csc.ui.MainWindow;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        int[] x = {50, 150, 150, 50};
        int[] y = {50, 50, 100, 100};
        int n = 4;
        Polygon polygon = new Polygon(x, y, n);

        MainWindow mainWindow = new MainWindow("lab1", polygon);
        mainWindow.setVisible(true);
    }
}
