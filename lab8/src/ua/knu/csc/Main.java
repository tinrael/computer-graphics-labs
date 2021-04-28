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

        simplePolygon.add(new Point(140, 160));
        simplePolygon.add(new Point(140, 380));
        simplePolygon.add(new Point(280, 460));
        simplePolygon.add(new Point(360, 300));
        simplePolygon.add(new Point(480, 360));
        simplePolygon.add(new Point(380, 400));
        simplePolygon.add(new Point(560, 460));
        simplePolygon.add(new Point(560, 320));
        simplePolygon.add(new Point(640, 320));
        simplePolygon.add(new Point(640, 400));
        simplePolygon.add(new Point(740, 460));
        simplePolygon.add(new Point(800, 220));
        simplePolygon.add(new Point(600, 260));
        simplePolygon.add(new Point(540, 180));
        simplePolygon.add(new Point(500, 260));
        simplePolygon.add(new Point(400, 180));
        simplePolygon.add(new Point(540, 120));
        simplePolygon.add(new Point(500, 80));
        simplePolygon.add(new Point(320, 80));
        simplePolygon.add(new Point(320, 240));

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
