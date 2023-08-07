package ar.edu.itba.ss.cim.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Cell {

    private final Set<Particle> particles = new HashSet<>();


    public Set<Particle> getParticles(){
        return this.particles;
    }

    public void addParticle(Particle particle){
        this.particles.add(particle);
    }

    @Override
    public String toString() {
        String particlesString =  particles.stream().map(p -> String.format("%d",p.getId())).collect(Collectors.joining(","));
        return String.format("[%10s]",particlesString);

    }

}
