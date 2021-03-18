package ua.knu.csc.entity;

import java.util.*;

// Edge-weighted digraph
public class EdgeWeightedDigraph {
    private final Map<Vertex, List<DirectedEdge>> adjacencyList = new HashMap<>();
    private final Map<Vertex, Integer> indegree = new HashMap<>();

    // Checks if the specified vertex is in the graph.
    private void validateVertex(Vertex vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            throw new NoSuchElementException("The specified vertex " + vertex + " is not in the graph.");
        }
    }

    public void addVertex(Vertex vertex) {
        if (vertex == null) {
            throw new NullPointerException("The specified vertex is null.");
        }
        adjacencyList.putIfAbsent(vertex, new LinkedList<>());
        indegree.putIfAbsent(vertex, 0);
    }

    public void addDirectedEdge(DirectedEdge directedEdge) {
        Vertex from = directedEdge.getFrom();
        Vertex to = directedEdge.getTo();

        validateVertex(from);
        validateVertex(to);

        adjacencyList.get(from).add(directedEdge);

        indegree.put(to, indegree.get(to) + 1);
    }

    // Returns all directed edges in this edge-weighted digraph.
    public Iterable<DirectedEdge> getEdges() {
        List<DirectedEdge> edges = new LinkedList<>();

        for (Map.Entry<Vertex, List<DirectedEdge>> entry : adjacencyList.entrySet()) {
            edges.addAll(entry.getValue());
        }

        return edges;
    }

    // Returns the directed edges incident from vertex.
    public Iterable<DirectedEdge> getVerticesAdjacentFrom(Vertex vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex);
    }

    // Returns the number of directed edges incident to vertex.
    public int getIndegree(Vertex vertex) {
        validateVertex(vertex);
        return indegree.get(vertex);
    }

    // Returns the number of directed edges incident from vertex.
    public int getOutdegree(Vertex vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex).size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Vertex, List<DirectedEdge>> entry : adjacencyList.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ");

            for (DirectedEdge directedEdge : entry.getValue()) {
                stringBuilder.append("[").append(directedEdge).append("] ");
            }

            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

    /* Returns the leftmost directed edge.
     * The leftmost directed edge is a directed edge which vertex to() has the biggest polar angle.
     * The polar angle is calculated with reference to the origin (from().getX(), from.getY()),
     * i.e. calculatePolarAngle(to.getX() - from.getX(), to.getY() - from.getY()).
     */
    public static DirectedEdge getLeftmostDirectedEdge(Iterable<DirectedEdge> adjacentDirectedEdges) {
        if (adjacentDirectedEdges == null) {
            throw new NullPointerException("The specified 'adjacentDirectedEdges' is null.");
        }

        DirectedEdge leftmostDirectedEdge = null;

        Iterator<DirectedEdge> iterator = adjacentDirectedEdges.iterator();
        if (iterator.hasNext()) {
            leftmostDirectedEdge = iterator.next();

            if (leftmostDirectedEdge == null) {
                throw new NullPointerException("The specified 'adjacentDirectedEdges' contain null element(s).");
            }

            int originX = leftmostDirectedEdge.getFrom().getX();
            int originY = leftmostDirectedEdge.getFrom().getY();

            int x = leftmostDirectedEdge.getTo().getX();
            int y = leftmostDirectedEdge.getTo().getY();

            double biggestPolarAngle = Point.calculatePolarAngle(x - originX, y - originY);

            while (iterator.hasNext()) {
                DirectedEdge currentDirectedEdge = iterator.next();

                if (currentDirectedEdge == null) {
                    throw new NullPointerException("The specified 'adjacentDirectedEdges' contain null element(s).");
                }

                if ((currentDirectedEdge.getFrom().getX() != originX) || (currentDirectedEdge.getFrom().getY() != originY)) {
                    throw new IllegalArgumentException("The specified 'adjacentDirectedEdges' are not adjacent directed edges.");
                }

                x = currentDirectedEdge.getTo().getX();
                y = currentDirectedEdge.getTo().getY();

                double currentPolarAngle = Point.calculatePolarAngle(x - originX, y - originY);

                if (currentPolarAngle > biggestPolarAngle) {
                    leftmostDirectedEdge = currentDirectedEdge;
                    biggestPolarAngle = currentPolarAngle;
                }
            }
        }

        return leftmostDirectedEdge;
    }
}
