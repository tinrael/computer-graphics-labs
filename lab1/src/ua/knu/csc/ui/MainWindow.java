package ua.knu.csc.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final Polygon polygon;

    public MainWindow(String title, Polygon polygon) {
        super(title);

        this.polygon = polygon;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getSize().width / 2, screenSize.height / 2 - getSize().height / 2);
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
        drawPolygon(g);
    }
}
