package ua.knu.csc;

import ua.knu.csc.core.ConvexHull;
import ua.knu.csc.ui.DrawingPanel;

import java.util.List;
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

        List<Point> simplePolygon = new ArrayList<>();

        simplePolygon.add(new Point(60, 110));

        List<Point> convexHull = ConvexHull.findConvexHullOfSimplePolygon(simplePolygon);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(origin, simplePolygon, convexHull);
            }
        });
    }

    private static void createAndShowGUI(Point origin, List<Point> simplePolygon, List<Point> convexHull) {
        JFrame mainWindow = new JFrame("lab8");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.add(new DrawingPanel(origin, simplePolygon, convexHull));

        mainWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setLocation(screenSize.width / 2 - mainWindow.getSize().width / 2, screenSize.height / 2 - mainWindow.getSize().height / 2);

        mainWindow.setVisible(true);
    }
}
