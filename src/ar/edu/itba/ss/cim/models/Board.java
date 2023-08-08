package ar.edu.itba.ss.cim.models;



import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class Board {

    private final int M;
    private final Cell[][] cells;

    private final Set<Particle> particles = new HashSet<>();


    private final double boardLength;
    private final double cellLength;

    private final double interactionRadius;

    public int getTime() {
        return time;
    }

    private final int time;

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Cell[] row :cells) {
            for (Cell cell : row) {
                board.append(cell).append(" ");
            }
            // Remove the trailing space and add a newline
            board.setLength(board.length() - 1);
            board.append("\n");
        }
        // Remove the trailing newline
        board.setLength(board.length() - 1);
        return "Board{" +
                "M=" + M +
                ", boardLength=" + boardLength +
                ", cellLength=" + cellLength +
                ", time=" + time +
                ",\n cells=\n" + board +
                '}';
    }

    public Board(int M, double boardLength, int time, double interactionRadius){
        this.time = time;
        this.M = M;
        this.interactionRadius = interactionRadius;
        this.boardLength = boardLength;
        this.cellLength = boardLength/ (double) M;
        this.cells = new Cell[M][M];

        for(int i=0; i<M; i++){
            double x = cellLength *i;
            for(int j=0; j<M; j++){
                double y = cellLength * j;
                this.cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int i, int j){
        return this.cells[i][j];
    }

    public int getM(){
        return M;
    }

    public Set<Particle> getParticlesAt(int row, int col){
        return this.cells[row][col].getParticles();
    }

    public Set<Particle> getAllParticles(){
        return particles;
    }
    public double getBoardLength() {
        return boardLength;
    }

    public void addParticle(Particle particle){
        double x = particle.getX();
        double y = particle.getY();
        if (x > boardLength || y > boardLength || x < 0 || y < 0) {
            throw new IllegalArgumentException("particle does not fit inside board");
        }
         int row = (int) ( x / cellLength);
         int col= (int) (y / cellLength);
        this.cells[row][col].addParticle(particle);
    }

    public void addParticles(Collection<Particle> particles) {
        for (Particle particle : particles) {
            addParticle(particle);
        }
        this.particles.addAll(particles);
    }

    public void computeNeighbours(Cell cell1, Cell cell2) {
        Set<Particle> cell1Particles = cell1.getParticles();
        Set<Particle> cell2Particles = cell2.getParticles();
        for (Particle particle : cell1Particles) {
            for (Particle otherParticle : cell2Particles) {
                if (!particle.equals(otherParticle) && particle.isWithinInteractionRadius(otherParticle, interactionRadius)) {
                    particle.addNeighbour(otherParticle);
                    otherParticle.addNeighbour(particle);
                }
            }
        }
    }

    public Set<Cell> getNeighbourCells(Cell cell) {
        Set<Cell> neighbourCells = new HashSet<>();

        // Calculate neighbour cells and add to set

        return neighbourCells;
    }

    public Map<Particle,Set<Particle>> getNeighbours() {
        Map<Particle,Set<Particle>> neighbours = new HashMap<>();
        Set<Pair<Cell>> comparedCells = new HashSet<>();
        for (int i=0 ; i< M ; i++) {
            for (int j=0 ; j<M ; j++) {
                Cell cell = cells[i][j];
                Set<Cell> neighbourCells = getNeighbourCells(cell);
                for (Cell otherCell : neighbourCells) {
                    Pair<Cell> pair = Pair.of(cell, otherCell);
                    if(!comparedCells.contains(pair)) {
                        comparedCells.add(pair);
                        computeNeighbours(cell, otherCell);
                    }
                }
            }
        }

        for (Particle particle : particles) {
            neighbours.put(particle,particle.getNeighbours());
        }

        return neighbours;

    }

}
