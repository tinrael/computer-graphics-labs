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

    public static double calculatePolarAngle(int x, int y) {
        if ((x == 0) && (y == 0)) {
            return -1.0;
        }

        if (x == 0) {
            return ((y > 0) ? 90.0 : 270.0);
        }

        double theta = Math.atan(Integer.valueOf(y).doubleValue() / Integer.valueOf(x).doubleValue());
        theta *= 360.0 / (2.0 * Math.PI);

        if (x > 0) {
            return ((y >= 0) ? theta : (360.0 + theta));
        } else {
            return (180.0 + theta);
        }
    }
}
