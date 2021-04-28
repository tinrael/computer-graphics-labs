package ua.knu.csc.ui;

import java.util.List;
import java.util.Iterator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    private final Point origin;

    private final List<Point> simplePolygon;
    private final List<Point> convexHull;

    public DrawingPanel(Point origin, List<Point> simplePolygon, List<Point> convexHull) {
        if (origin == null) {
            throw new NullPointerException("The specified parameter 'origin' is null.");
        }

        if (simplePolygon == null) {
            throw new NullPointerException("The specified parameter 'simplePolygon' is null.");
        }

        if (convexHull == null) {
            throw new NullPointerException("The specified parameter 'convexHull' is null.");
        }

        this.origin = origin;
        this.simplePolygon = simplePolygon;
        this.convexHull = convexHull;

        setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    }

    private void drawCoordinateAxes(Graphics2D graphics2D) {
        final int AXIS_LENGTH = 350;

        graphics2D.setColor(Color.GRAY);

        float[] dash = {3f, 3f};
        Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2.0f);
        graphics2D.setStroke(stroke);

        graphics2D.drawLine(origin.x, origin.y, origin.x + AXIS_LENGTH, origin.y);
        graphics2D.drawLine(origin.x, origin.y, origin.x, origin.y - AXIS_LENGTH);
    }

    private void drawOrigin(Graphics2D graphics2D) {
        graphics2D.setColor(Color.ORANGE);
        graphics2D.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        graphics2D.drawLine(origin.x, origin.y, origin.x, origin.y);
    }

    private void drawPoint(Graphics2D graphics2D, Point point, Color color, float width) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int x = origin.x + point.x;
        int y = origin.y - point.y;

        graphics2D.drawLine(x, y, x, y);
    }

    private void drawLine(Graphics2D graphics2D, Point point1, Point point2, Color color, float width) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(width));

        int x1 = origin.x + point1.x;
        int y1 = origin.y - point1.y;

        int x2 = origin.x + point2.x;
        int y2 = origin.y - point2.y;

        graphics2D.drawLine(x1, y1, x2, y2);
    }

    private void drawString(Graphics graphics, Color color, String text, int x, int y) {
        graphics.setColor(color);
        graphics.drawString(text, origin.x + x, origin.y - y);
    }

    private void drawPolygon(Graphics2D graphics2D, Color color, float width, List<Point> polygon) {
        Iterator<Point> iterator = polygon.iterator();

        if (iterator.hasNext()) {
            Point startPoint = iterator.next();

            Point previousPoint = startPoint;

            while (iterator.hasNext()) {
                Point currentPoint = iterator.next();

                drawLine(graphics2D, previousPoint, currentPoint, color, width);

                previousPoint = currentPoint;
            }

            if (polygon.size() > 2) {
                drawLine(graphics2D, previousPoint, startPoint, color, width);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(950,600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCoordinateAxes((Graphics2D) g);
        drawOrigin((Graphics2D) g);

        drawPolygon((Graphics2D) g, Color.ORANGE, 7f, convexHull);
        drawPolygon((Graphics2D) g, Color.BLUE, 2f, simplePolygon);

        for (Point point : simplePolygon) {
            drawPoint((Graphics2D) g, point, Color.RED, 8f);
            drawString(g, Color.BLACK, "(" + point.x + ", " + point.y + ")", point.x + 7, point.y);
        }
    }
}
