package ua.knu.csc.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
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
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
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

        drawOriginPoint(g);
        drawPolygon(g);
    }
}
