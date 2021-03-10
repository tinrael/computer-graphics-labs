package ua.knu.csc.entity;

public class DirectedEdge {
    private final Vertex from;
    private final Vertex to;

    private int weight;

    public DirectedEdge(Vertex from, Vertex to, int weight) {
        if (from == null) {
            throw new NullPointerException("The specified vertex 'from' is null.");
        }

        if (to == null) {
            throw new NullPointerException("The specified vertex 'to' is null.");
        }

        this.from = from;
        this.to = to;

        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return from + " -> " + to + ", weight: " + weight;
    }
}
