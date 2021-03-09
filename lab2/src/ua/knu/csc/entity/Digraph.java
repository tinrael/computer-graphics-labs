package ua.knu.csc.entity;

import java.util.*;

// directed graph (or digraph)
public class Digraph {
    private final Map<Vertex, List<Vertex>> adjacencyList = new HashMap<>();

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
    }

    public void removeVertex(Vertex vertex) {
        validateVertex(vertex);
        adjacencyList.values().forEach(list -> list.remove(vertex));
        adjacencyList.remove(vertex);
    }

    public void addEdge(Vertex from, Vertex to) {
        validateVertex(from);
        validateVertex(to);
        adjacencyList.get(from).add(to);
    }

    public void removeEdge(Vertex from, Vertex to) {
        validateVertex(from);
        validateVertex(to);
        adjacencyList.get(from).remove(to);
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
