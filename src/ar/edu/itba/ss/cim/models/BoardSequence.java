package ar.edu.itba.ss.cim.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoardSequence implements Iterable<Board>{
    private final List<TemporalCoordinates> temporalCoordinatesList;


    private final Set<Particle> particles = new HashSet<>();

    private int index;


    private final Board board;

    public BoardSequence(StaticStats staticStats, List<TemporalCoordinates> temporalCoordinatesList, int M, double interactionRadius) {
        this.temporalCoordinatesList = temporalCoordinatesList;
        board = new Board(M, staticStats.getBoardLength(), interactionRadius);

        Coordinates coordinates = Coordinates.of(0,0);
        for (Map.Entry<Integer,Properties> idProp : staticStats.getIdPropertyPairs()) {
            Properties properties = idProp.getValue();
            Particle particle = new Particle(coordinates,properties);
            particles.add(particle);
        }
        board.addParticles(particles);
    }

    private Board getNextBoard() {
        TemporalCoordinates tc = temporalCoordinatesList.get(index++);
        board.setTime(tc.getTime());
        for (Particle particle : particles) {
            int id = particle.getId();
            Coordinates coordinates = tc.getCoordinates(id);
            particle.setCoordinates(coordinates);
        }
        board.recomputeParticlesCell();
        return board;
    }

    @Override
    public Iterator<Board> iterator() {
        return new Iterator<Board>() {
            @Override
            public boolean hasNext() {
                return index < temporalCoordinatesList.size();
            }

            @Override
            public Board next() {
                return getNextBoard();
            }
        };
    }
}
