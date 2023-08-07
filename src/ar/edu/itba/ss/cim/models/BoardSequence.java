package ar.edu.itba.ss.cim.models;

import ar.edu.itba.ss.cim.Coordinates;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoardSequence implements Iterable<Board>{
    private List<TemporalCoordinates> temporalCoordinatesList;
    private StaticStats staticStats;

    private final int M;

    private int index;

    public BoardSequence(StaticStats staticStats, List<TemporalCoordinates> temporalCoordinatesList, int M) {
        this.temporalCoordinatesList = temporalCoordinatesList;
        this.staticStats = staticStats;
        this.M = M;
    }

    private Board getNextBoard() {
        TemporalCoordinates tc = temporalCoordinatesList.get(index++);
        Board board = new Board(M,staticStats.getBoardLength(),tc.getTime());
        for (Map.Entry<Integer,Properties> idProp : staticStats.getIdPropertyPairs()) {
            Coordinates coordinates = tc.getCoordinates(idProp.getKey());
            Properties properties = idProp.getValue();
            Particle particle = new Particle(coordinates,properties);
            board.addParticle(particle);
        }
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
