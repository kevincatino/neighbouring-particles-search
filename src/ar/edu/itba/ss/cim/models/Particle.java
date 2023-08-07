package ar.edu.itba.ss.cim.models;

import java.util.ArrayList;
import java.util.List;

public class Particle {

    private static int ID = 1;

    private final int id;
    private final Coordinates coordinates;

    private final List<Particle> neighbours;

    private final Properties properties;

    Particle(double x, double y, Properties properties) {
        this.id = ID;
        ID++;
        this.properties = properties;
        this.coordinates = Coordinates.of(x,y);
        this.neighbours = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public double getRadius() {
        return properties.getRadius();
    }

    public double getX(){
        return this.coordinates.getX();
    }

    public double getY(){
        return this.coordinates.getY();
    }

    public boolean isNeighbour(int particleId) {
        return neighbours.stream().anyMatch(neighbour -> neighbour.getId() == particleId);
    }

    public void addNeighbour(Particle particle){
        this.neighbours.add(particle);
    }

    public void clearNeighbour(){
        this.neighbours.clear();
    }

    public List<Particle> getNeighbours() {
        return neighbours;
    }

    public boolean equals(Object o ) {
        if(this == o) {
            return true;
        }
        if (!(o instanceof Particle)) {
            return false;
        }
        Particle aux = (Particle) o;
        return this.id == aux.id;
    }

    @Override
    public String toString(){
        return "id: " + id + ", x: " + getX() + ", y: " + getY() + "radius: " + getRadius();
    }
}
