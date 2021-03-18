package ua.knu.csc.entity;

public class Vertex {
    private final String label;

    private int x;
    private int y;

    public Vertex(String label, int x, int y) {
        if (label == null) {
            throw new IllegalArgumentException("The specified label is null.");
        }
        this.label = label;
        
        this.x = x;
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return label;
    }
}
