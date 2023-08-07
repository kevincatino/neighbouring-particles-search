package ar.edu.itba.ss.cim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Coordinates {
    private double x;
    private double y;

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
