package ua.knu.csc.ui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    private final Point origin;

    public DrawingPanel(Point origin) {
        if (origin == null) {
            throw new NullPointerException("The specified origin is null.");
        }
        this.origin = origin;
        setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    }

    private void drawCoordinateAxes(Graphics2D graphics2D) {
        final int AXIS_LENGTH = 300;

        graphics2D.setColor(Color.GRAY);

        float[] dash = {3f, 3f};
        Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 2.0f);
        graphics2D.setStroke(stroke);

        graphics2D.drawLine(origin.x, origin.y, origin.x + AXIS_LENGTH, origin.y);
        graphics2D.drawLine(origin.x, origin.y, origin.x, origin.y - AXIS_LENGTH);
    }

    private void drawPoint(Graphics2D graphics2D, Point point, Color color, float width) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

        int x = origin.x + point.x;
        int y = origin.y - point.y;

        graphics2D.drawLine(x, y, x, y);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCoordinateAxes((Graphics2D) g);
    }
}
