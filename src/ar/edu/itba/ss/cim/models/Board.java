package ar.edu.itba.ss.cim.models;

public class Board {

    private final int M;
    private final double L;
    private final double cellsLength;
    private final Cell[][] cells;

    public Board(int M, int L){
        this.M = M;
        this.L = L;
        this.cellsLength = L/ (double) M;
        this.cells = new Cell[M][M];

        for(int i=0; i<M; i++){
            double x = cellsLength*i;
            for(int j=0; j<M; j++){
                double y = cellsLength * j;
                this.cells[i][j] = new Cell(Coordinates.of(x,y), cellsLength);
            }
        }
    }

    public Cell getCell(int i, int j){
        return this.cells[i][j];
    }

    public int getM(){
        return M;
    }

    public double getL() {
        return L;
    }

    public void addParticle(Particle particle){
        // int row =
        // int col=
        // this.cells[row][col].addParticle(particle);
    }

}
