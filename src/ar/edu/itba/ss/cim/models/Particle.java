package ar.edu.itba.ss.cim.models;


import ar.edu.itba.ss.cim.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Particle {


    private static int ID = 1;

    private final int id;
    private Coordinates coordinates;

    private final List<Particle> neighbours;

    private final Properties properties;

    Particle(double x, double y, Properties properties) {
        this(Coordinates.of(x,y), properties);
    }

    Particle(Coordinates coordinates, Properties properties) {
        this.id = ID;
        ID++;
        this.properties = properties;
        this.coordinates = coordinates;
        this.neighbours = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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

    public boolean isNeighbourOf(int particleId) {
        return neighbours.stream().anyMatch(neighbour -> neighbour.getId() == particleId);
    }

    public boolean isWithinInteractionRadius(Particle otherParticle, double interactionRadius) {
        double horizontalDistance  = Math.abs(this.coordinates.getX() - otherParticle.getX());
        double verticalDistance = Math.abs(this.coordinates.getY() - otherParticle.getY());
        double totalDistance = Math.sqrt(Math.pow(horizontalDistance, 2) + Math.pow(verticalDistance, 2));

        // Perhaps we should add a boolean to check if it is a point particle?
        totalDistance -= this.properties.getRadius();
        totalDistance -= otherParticle.properties.getRadius();

        return totalDistance <= interactionRadius;
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

        return "{id: " + id + ", x: " + getX() + ", y: " + getY() + " radius: " + getRadius() + "}";
    }

}
