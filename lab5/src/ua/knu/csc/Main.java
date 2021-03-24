package ua.knu.csc;

import ua.knu.csc.core.QuickHull;
import ua.knu.csc.ui.DrawingPanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        Point origin = new Point(50, 500);

        Set<Point> points = new HashSet<>();
        points.add(new Point(60, 110));
        points.add(new Point(150, 40));
        points.add(new Point(300, 210));
        points.add(new Point(40, 300));
        points.add(new Point(50, 50));
        points.add(new Point(85, 90));
        points.add(new Point(170, 210));
        points.add(new Point(150, 180));

        ArrayList<Point> convexHullPoints = QuickHull.findConvexHull(points);
        System.out.println(convexHullPoints);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(origin, points);
            }
        });
    }

    private static void createAndShowGUI(Point origin, Set<Point> points) {
        JFrame mainWindow = new JFrame("lab5");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.add(new DrawingPanel(origin, points));

        mainWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setLocation(screenSize.width / 2 - mainWindow.getSize().width / 2, screenSize.height / 2 - mainWindow.getSize().height / 2);

        mainWindow.setVisible(true);
    }
}
