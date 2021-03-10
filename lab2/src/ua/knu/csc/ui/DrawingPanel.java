package ua.knu.csc.ui;

import ua.knu.csc.entity.DirectedEdge;
import ua.knu.csc.entity.EdgeWeightedDigraph;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    private final Point origin;

    private final EdgeWeightedDigraph edgeWeightedDigraph;

    public DrawingPanel(Point origin, EdgeWeightedDigraph edgeWeightedDigraph) {
        if (origin == null) {
            throw new NullPointerException("The specified origin is null.");
        }

        if (edgeWeightedDigraph == null) {
            throw new NullPointerException("The specified 'edgeWeightedDigraph' is null.");
        }

        this.origin = origin;
        this.edgeWeightedDigraph = edgeWeightedDigraph;

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

    private void drawEdgeWeightedDigraph(Graphics2D graphics2D) {
        for (DirectedEdge directedEdge : edgeWeightedDigraph.getEdges()) {
            Point from = new Point(directedEdge.getFrom().getX(), directedEdge.getFrom().getY());
            Point to = new Point(directedEdge.getTo().getX(), directedEdge.getTo().getY());

            drawLine(graphics2D, from, to, Color.BLUE, 3f);

            drawPoint(graphics2D, from, Color.RED, 7f);
            drawPoint(graphics2D, to, Color.RED, 7f);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900,600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCoordinateAxes((Graphics2D) g);
        drawOrigin((Graphics2D) g);

        drawEdgeWeightedDigraph((Graphics2D) g);
    }
}
