package ua.knu.csc.entity;

import java.util.*;

// directed graph (or digraph)
public class Digraph {
    private final Map<Vertex, List<Vertex>> adjacencyList = new HashMap<>();

    private final Map<Vertex, Integer> indegree = new HashMap<>();

    // checks if the specified vertex is in the graph
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

    public void removeVertex(Vertex vertex) {
        validateVertex(vertex);

        adjacencyList.get(vertex).forEach(to -> indegree.put(to, indegree.get(to) - 1));

        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex);

        indegree.remove(vertex);
    }

    public void addEdge(Vertex from, Vertex to) {
        validateVertex(from);
        validateVertex(to);

        adjacencyList.get(from).add(to);

        indegree.put(to, indegree.get(to) + 1);
    }

    public void removeEdge(Vertex from, Vertex to) {
        validateVertex(from);
        validateVertex(to);

        adjacencyList.get(from).remove(to);

        indegree.put(to, indegree.get(to) - 1);
    }

    // Returns the vertices adjacent from the specified vertex in this digraph.
    public Iterable<Vertex> getVerticesAdjacentFrom(Vertex vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex);
    }

    // The indegree of a vertex is the number of edges pointing to it.
    public int getIndegree(Vertex vertex) {
        validateVertex(vertex);
        return indegree.get(vertex);
    }

    // The outdegree of a vertex is the number of edges pointing from it.
    public int getOutdegree(Vertex vertex) {
        validateVertex(vertex);
        return adjacencyList.get(vertex).size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Vertex, List<Vertex>> entry : adjacencyList.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ");

            for (Vertex vertex : entry.getValue()) {
                stringBuilder.append("[").append(vertex).append("] ");
            }

            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }
}
