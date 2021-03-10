package ua.knu.csc;

import ua.knu.csc.entity.DirectedEdge;
import ua.knu.csc.entity.EdgeWeightedDigraph;
import ua.knu.csc.entity.Vertex;

import ua.knu.csc.ui.DrawingPanel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        Point origin = new Point(50, 500);

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();

        Vertex v1 = new Vertex("v1", 20, 20);
        Vertex v2 = new Vertex("v2", 20, 220);
        Vertex v3 = new Vertex("v3", 420 ,20);
        Vertex v4 = new Vertex("v3", 420, 220);

        edgeWeightedDigraph.addVertex(v1);
        edgeWeightedDigraph.addVertex(v2);
        edgeWeightedDigraph.addVertex(v3);
        edgeWeightedDigraph.addVertex(v4);

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v3, 5));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v2, 5));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v2, v3, 5));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v3, v4, 5));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v4, v2, 5));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(origin, edgeWeightedDigraph);
            }
        });
    }

    private static void createAndShowGUI(Point origin, EdgeWeightedDigraph edgeWeightedDigraph) {
        JFrame mainWindow = new JFrame("lab2");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainWindow.add(new DrawingPanel(origin, edgeWeightedDigraph));

        mainWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setLocation(screenSize.width / 2 - mainWindow.getSize().width / 2, screenSize.height / 2 - mainWindow.getSize().height / 2);

        mainWindow.setVisible(true);
    }
}
