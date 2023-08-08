package ar.edu.itba.ss.cim.models;


import java.util.*;

public class Board {

    private final int M;
    private final Cell[][] cells;

    private final Set<Particle> boardParticles = new HashSet<>();

    private final double boardLength;
    private final double cellLength;


    private final double interactionRadius;

    public int getTime() {
        return time;
    }

    private int time = 0;

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Cell[] row : cells) {
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
                ", interactionRadius=" + interactionRadius +
                ",\n cells=\n" + board +
                '}';
    }

    public Board(int M, double boardLength, double interactionRadius) {
        this.M = M;
        this.interactionRadius = interactionRadius;
        this.boardLength = boardLength;
        this.cellLength = boardLength / (double) M;
        this.cells = new Cell[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    private Cell getCell(int i, int j){

        return this.cells[i][j];
    }

    private void clearCells() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                cells[i][j].clearCell();
            }
        }
    }

    public int getM() {
        return M;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Set<Particle> getParticlesAt(int row, int col) {
        return this.cells[row][col].getParticles();
    }

    public Set<Particle> getAllParticles() {
        return boardParticles;
    }

    public double getBoardLength() {
        return boardLength;
    }

    private void computeParticleCell(Particle particle) {
        double x = particle.getX();
        double y = particle.getY();
        if (x > boardLength || y > boardLength || x < 0 || y < 0) {
            throw new IllegalArgumentException("particle does not fit inside board");
        }
        int row = (int) (y / cellLength);
        int col = (int) (x / cellLength);
        this.cells[row][col].addParticle(particle);
    }

    public void recomputeParticlesCell() {
        clearCells();
        for (Particle particle : boardParticles) {
            computeParticleCell(particle);
        }
    }

    public void addParticle(Particle particle) {
        boardParticles.add(particle);
        computeParticleCell(particle);
    }

    public void addParticles(Collection<Particle> particles) {
        for (Particle particle : particles) {
            addParticle(particle);
        }
        this.boardParticles.addAll(particles);
    }

    public void computeNeighbours(Cell cell1, Cell cell2) {
        Set<Particle> cell1Particles = cell1.getParticles();
        Set<Particle> cell2Particles = cell2.getParticles();
        for (Particle particle : cell1Particles) {
            for (Particle otherParticle : cell2Particles) {
                if (!particle.equals(otherParticle) && particle.isWithinInteractionRadius(otherParticle, interactionRadius, BOUNDARY_CONDITIONS.NOT_PERIODIC)) {
                    particle.addNeighbour(otherParticle);
                    otherParticle.addNeighbour(particle);
                }
            }
        }
    }

    private Collection<Cell> getNeighbourCells(int i, int j) {

        Set<Cell> neighbourCells = new HashSet<>();

        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x >= 0 && x < M && y >= 0 && y < M) {
                    neighbourCells.add(cells[x][y]);
                }
            }
        }
        // Possibly consider wrapped boundaries condition

        return neighbourCells;
    }


    private void clearNeighbours() {
        boardParticles.forEach(Particle::clearNeighbours);
    }


    public Map<Particle, Set<Particle>> getNeighbours(METHOD method, BOUNDARY_CONDITIONS boundaryConditions) {
        clearNeighbours();
        Map<Particle, Set<Particle>> neighbours = new HashMap<>();
        switch(method) {
            case CIM:
                Set<Pair<Cell>> comparedCells = new HashSet<>();
                for (int i = 0; i < M; i++) {
                    for (int j = 0; j < M; j++) {
                        Cell cell = cells[i][j];
                        Collection<Cell> neighbourCells = getNeighbourCells(i, j);
                        for (Cell otherCell : neighbourCells) {
                            Pair<Cell> pair = Pair.of(cell, otherCell);
                            if (!comparedCells.contains(pair)) {
                                comparedCells.add(pair);
                                computeNeighbours(cell, otherCell);
                            }
                        }
                    }
                }
                break;
            case BRUTE_FORCE:
                for (Particle particle : boardParticles) {
                    for (Particle otherParticle : boardParticles) {
                        if (!particle.equals(otherParticle) && particle.isWithinInteractionRadius(otherParticle, interactionRadius, boundaryConditions)) {
                            particle.addNeighbour(otherParticle);
                            otherParticle.addNeighbour(particle);
                        }
                    }
                }
        }


        for (Particle particle : boardParticles) {
            neighbours.put(particle, particle.getNeighbours());
        }

        return neighbours;
    }

    public enum METHOD {
        BRUTE_FORCE,
        CIM
    }

    public enum BOUNDARY_CONDITIONS {
        PERIODIC(){
            @Override
            public boolean isWithinInteractionMethod(Particle particle1, Particle particle2, double interactionRadius) {
                if(super.isWithinInteractionMethod(particle1, particle2, interactionRadius)){
                    return true;
                }

                // VER SI SON VECINAS DE FORMA PERIODICA

                return false;
            }
        },
        NOT_PERIODIC(){
            @Override
            public boolean isWithinInteractionMethod(Particle particle1, Particle particle2, double interactionRadius) {
                return super.isWithinInteractionMethod(particle1, particle2, interactionRadius);
            }
        };


        public boolean isWithinInteractionMethod(Particle particle1, Particle particle2, double interactionRadius){
            double totalDistance = particle1.getDistanceTo(particle2);

            // Perhaps we should add a boolean to check if it is a point particle?
            totalDistance -= particle1.getRadius();
            totalDistance -= particle2.getRadius();

            return totalDistance <= interactionRadius;
        }
    }

}
