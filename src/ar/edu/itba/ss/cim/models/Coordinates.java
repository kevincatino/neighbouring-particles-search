package ar.edu.itba.ss.cim.models;

public class Coordinates {
    private final double x;
    private final double y;

    private Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(double x, double y) {
        return new Coordinates(x, y);
    }

    public String toString() {
        return String.format("{%f,%f}", x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
