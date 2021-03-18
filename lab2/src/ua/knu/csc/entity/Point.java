package ua.knu.csc.entity;

public class Point {

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
