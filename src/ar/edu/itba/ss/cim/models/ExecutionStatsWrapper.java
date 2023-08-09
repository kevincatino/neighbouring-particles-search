package ar.edu.itba.ss.cim.models;

import ar.edu.itba.ss.cim.dto.BoardSequenceDto;
import ar.edu.itba.ss.cim.dto.ExecutionStats;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class ExecutionStatsWrapper {
    private final Collection<ExecutionStats> stats = new HashSet<>();

    public void writeToFile(String path) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), stats);

    }

    public void addStats(int numberOfParticles, long bruteForceTime, long cimTime) {
        stats.add(new ExecutionStats(numberOfParticles, bruteForceTime, cimTime));
    }
}
