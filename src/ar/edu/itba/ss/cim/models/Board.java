package ar.edu.itba.ss.cim.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Board {

    private final int M;
    private final double L;
    private final Cell[][] cells;

    public Board(int M, int L){
        this.M = M;
        this.L = L;
        this.cells = new Cell[M][M];

        double cellsLength = L/ (double) M;
        IntStream.range(0, M)
                .forEach(i -> IntStream.range(0, M)
                        .forEach(j -> this.cells[i][j] = new Cell(cellsLength)));

    }

    public void addParticle(Particle particle){
        int row = (int) ((particle.getX() * M) / L);
        int col = (int) ((particle.getY() * M) / L);
        this.cells[row][col].addParticle(particle);
    }

    public Cell getCell(int row, int col){
        return this.cells[row][col];
    }

    public int getM(){
        return M;
    }

    public double getL() {
        return L;
    }

    public double getCellLength(int row, int col) {
        return cells[row][col].getCellLength();
    }

    public List<Particle> getParticlesAt(int row, int col){
        return this.cells[row][col].getParticles();
    }

    public List<Particle> getAllParticles(){
        List<Particle> particles = new ArrayList<>();
        IntStream.range(0, M)
                .forEach(i -> IntStream.range(0, M)
                        .forEach(j -> particles.addAll(cells[i][j].getParticles())));
        return particles;
    }
}
