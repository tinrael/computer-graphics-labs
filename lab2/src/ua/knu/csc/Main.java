package ua.knu.csc;

import ua.knu.csc.ui.MainWindow;
import java.awt.Point;

public class Main {

    public static void main(String[] args) {
        Point originPoint = new Point(50, 500);

        Point point = new Point(150, 150);

        MainWindow mainWindow = new MainWindow("lab1", originPoint, point);
        mainWindow.setVisible(true);
    }
}
