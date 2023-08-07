package ar.edu.itba.ss.cim.models;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final List<Particle> particles;
    private final Coordinates cellCoordinates;
    private final double cellLarge;

    public Cell(Coordinates cellCoordinates, double cellLarge) {
        this.particles = new ArrayList<>();
        this.cellCoordinates = cellCoordinates;
        this.cellLarge = cellLarge;
    }

    public List<Particle> getParticles(){
        return this.particles;
    }

    public void addParticle(Particle particle){
        this.particles.add(particle);
    }

}
