package ar.edu.itba.ss.cim.models;

import ar.edu.itba.ss.cim.Coordinates;

import java.util.Arrays;

public class Board {

    private final int M;
    private final double boardLength;
    private final double cellLength;

    public int getTime() {
        return time;
    }

    private final int time;
    private final Cell[][] cells;

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

    public Board(int M, double boardLength, int time){
        this.time = time;
        this.M = M;
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

}
