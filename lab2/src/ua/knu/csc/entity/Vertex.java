package ua.knu.csc.entity;

public class Vertex {
    private final String label;

    public Vertex(String label) {
        if (label == null) {
            throw new IllegalArgumentException("The specified label is null.");
        }
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
