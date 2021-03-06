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
}
