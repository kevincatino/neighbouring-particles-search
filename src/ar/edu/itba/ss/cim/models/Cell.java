package ar.edu.itba.ss.cim.models;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final List<Particle> particles;
    private final double cellLength;

    public Cell(double cellLength) {
        this.particles = new ArrayList<>();
        this.cellLength = cellLength;
    }

    public List<Particle> getParticles(){
        return this.particles;
    }

    public void addParticle(Particle particle){
        this.particles.add(particle);
    }

    public double getCellLength(){
        return this.cellLength;
    }

}
