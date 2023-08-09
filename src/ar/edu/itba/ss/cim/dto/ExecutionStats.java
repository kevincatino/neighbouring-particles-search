package ar.edu.itba.ss.cim.dto;

public class ExecutionStats {
    public int getParticles() {
        return particles;
    }

    public double getBruteForce() {
        return bruteForce;
    }

    public double getCim() {
        return cim;
    }

    private final int particles;
    private final long bruteForce;
    private final long cim;

    public ExecutionStats(int particles, long bruteForce, long cim) {
        this.particles = particles;
        this.bruteForce = bruteForce;
        this.cim = cim;
    }
}
