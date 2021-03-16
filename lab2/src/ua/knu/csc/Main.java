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

        Vertex v1 = new Vertex("v1", 180, 90);
        Vertex v2 = new Vertex("v2", 345, 141);
        Vertex v3 = new Vertex("v3", 108 ,195);
        Vertex v4 = new Vertex("v4", 216, 222);
        Vertex v5 = new Vertex("v5", 327, 291);
        Vertex v6 = new Vertex("v6", 252, 315);
        Vertex v7 = new Vertex("v7", 309, 354);
        Vertex v8 = new Vertex("v8", 63, 393);
        Vertex v9 = new Vertex("v9", 228, 462);

        edgeWeightedDigraph.addVertex(v1);
        edgeWeightedDigraph.addVertex(v2);
        edgeWeightedDigraph.addVertex(v3);
        edgeWeightedDigraph.addVertex(v4);
        edgeWeightedDigraph.addVertex(v5);
        edgeWeightedDigraph.addVertex(v6);
        edgeWeightedDigraph.addVertex(v7);
        edgeWeightedDigraph.addVertex(v8);
        edgeWeightedDigraph.addVertex(v9);

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v3, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v4, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v5, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v1, v2, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v2, v5, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v3, v8, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v3, v6, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v3, v4, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v4, v6, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v4, v5, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v5, v6, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v5, v7, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v6, v9, 0));
        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v6, v7, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v7, v9, 0));

        edgeWeightedDigraph.addDirectedEdge(new DirectedEdge(v8, v9, 0));

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
