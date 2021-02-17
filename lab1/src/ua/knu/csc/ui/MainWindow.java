package ua.knu.csc.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final int WIDTH = 900;
    private final int HEIGHT = 600;

    private final Point originPoint;

    private final Polygon polygon;
    private final Point point;

    public MainWindow(String title, Point originPoint, Polygon polygon, Point point) throws NullPointerException {
        super(title);

        if (originPoint == null) {
            throw new NullPointerException("The specified origin is null.");
        }

        if (polygon == null) {
            throw new NullPointerException("The specified polygon is null.");
        }

        if (point == null) {
            throw new NullPointerException("The specified point is null.");
        }

        this.originPoint = originPoint;
        this.polygon = polygon;
        this.point = point;

        translateAxes();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
    }

    private void translateAxes() {
        int n = polygon.npoints;
        for (int i = 0; i < n; i++) {
            polygon.xpoints[i] = originPoint.x + polygon.xpoints[i];
            polygon.ypoints[i] = originPoint.y - polygon.ypoints[i];
        }

        point.x = originPoint.x + point.x;
        point.y = originPoint.y - point.y;
    }

    private void drawCoordinateAxes(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        float[] dash = {3f, 3f};
        Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2.0f);

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(stroke);

        int x = originPoint.x;
        int y = originPoint.y;

        final int AXIS_LENGTH = 200;

        graphics2D.drawLine(x, y, x + AXIS_LENGTH, y);
        graphics2D.drawLine(x, y, x, y - AXIS_LENGTH);
    }

    private void drawPolygon(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(3f));

        graphics2D.drawPolygon(polygon);
    }

    private void drawPoint(Graphics graphics, Point point, Color color, float width) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int x = point.x;
        int y = point.y;

        graphics2D.drawLine(x, y, x, y);
    }

    private void drawRay(Graphics graphics, Point point) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.GRAY);
        graphics2D.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int x = point.x;
        int y = point.y;

        graphics2D.drawLine(x, y, Toolkit.getDefaultToolkit().getScreenSize().width, y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawCoordinateAxes(g);
        drawPoint(g, originPoint, Color.RED, 5f);

        drawPolygon(g);
        drawRay(g, point);
        drawPoint(g, point, Color.ORANGE, 7f);
    }
}
