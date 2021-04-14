package ua.knu.csc;

import ua.knu.csc.core.ConvexHull;
import ua.knu.csc.ui.DrawingPanel;

import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        Point origin = new Point(50, 500);

        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(60, 110));
        points.add(new Point(150, 40));
        points.add(new Point(300, 210));
        points.add(new Point(40, 300));
        points.add(new Point(50, 50));
        points.add(new Point(85, 90));
        points.add(new Point(170, 210));
        points.add(new Point(150, 180));
        points.add(new Point(130, 290));
        points.add(new Point(10, 200));
        points.add(new Point(300, 175));

        ArrayList<Point> convexHullPoints = ConvexHull.applyGrahamScan(points);

        System.out.print("[");
        int size = convexHullPoints.size();
        for (int i = 0; i < size; i++) {
            System.out.print("(" + convexHullPoints.get(i).x + ", " + convexHullPoints.get(i).y + ")");

            if (i != (size - 1)) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(origin, points, convexHullPoints);
            }
        });
    }

    private static void createAndShowGUI(Point origin, ArrayList<Point> points, ArrayList<Point> convexHullPoints) {
        JFrame mainWindow = new JFrame("lab6");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.add(new DrawingPanel(origin, points, convexHullPoints));

        mainWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setLocation(screenSize.width / 2 - mainWindow.getSize().width / 2, screenSize.height / 2 - mainWindow.getSize().height / 2);

        mainWindow.setVisible(true);
    }
}
