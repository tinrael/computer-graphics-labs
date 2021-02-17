package ua.knu.csc.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final int WIDTH = 900;
    private final int HEIGHT = 600;

    private final Point originPoint;
    private final Polygon polygon;

    public MainWindow(String title, Point originPoint, Polygon polygon) throws NullPointerException {
        super(title);

        if (originPoint == null) {
            throw new NullPointerException("The specified origin is null.");
        }

        if (polygon == null) {
            throw new NullPointerException("The specified polygon is null.");
        }

        this.originPoint = originPoint;
        this.polygon = polygon;

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

    private void drawOriginPoint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.RED);
        graphics2D.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int x = originPoint.x;
        int y = originPoint.y;

        graphics2D.drawLine(x, y, x, y);
    }

    private void drawPolygon(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(3f));

        graphics2D.drawPolygon(polygon);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        translateAxes();

        drawCoordinateAxes(g);
        drawOriginPoint(g);
        drawPolygon(g);
    }
}
