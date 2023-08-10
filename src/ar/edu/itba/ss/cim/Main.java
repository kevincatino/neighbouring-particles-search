package ar.edu.itba.ss.cim;


import ar.edu.itba.ss.cim.dto.ExecutionStats;
import ar.edu.itba.ss.cim.helper.FileNamesWrapper;
import ar.edu.itba.ss.cim.models.Board;
import ar.edu.itba.ss.cim.models.BoardSequence;
import ar.edu.itba.ss.cim.models.ExecutionStatsWrapper;
import ar.edu.itba.ss.cim.models.Pair;
import ar.edu.itba.ss.cim.models.Particle;
import ar.edu.itba.ss.cim.models.StaticStats;
import ar.edu.itba.ss.cim.models.TemporalCoordinates;
import ar.edu.itba.ss.cim.utils.Fileparser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        int timeValues = 3;
        Integer[] numberOfParticles = new Integer[]{3000,1000,500,100};
        double interactionRadius = 2;
        double boardLength = 10;

        Integer M = null;
        ExecutionStatsWrapper stats = new ExecutionStatsWrapper();
        for (int particlesNumber : numberOfParticles) {


            FileNamesWrapper fileNameWrapper = Fileparser.generateInputData(particlesNumber, boardLength, interactionRadius, timeValues);

            String STATIC_FILE_PATH = fileNameWrapper.StaticFileName;
            String DYNAMIC_FILE_PATH = fileNameWrapper.DynamicFileName;
            StaticStats staticStats = Fileparser.parseStaticFile(STATIC_FILE_PATH);
            List<TemporalCoordinates> temporalCoordinates = Fileparser.parseDynamicFile(DYNAMIC_FILE_PATH);
            BoardSequence boardSequence = new BoardSequence(staticStats, temporalCoordinates, M, interactionRadius, Board.BoundaryConditions.NOT_PERIODIC);

            for (Board b : boardSequence) {
                long start = System.currentTimeMillis();
                b.getNeighbours(Board.Method.BRUTE_FORCE);
                long end = System.currentTimeMillis();
                long bruteForceComputationTime = end - start;
                System.out.printf("Brute force Computation time: %d ms\n", bruteForceComputationTime);

                start = System.currentTimeMillis();
                b.getNeighbours(Board.Method.CIM);
                end = System.currentTimeMillis();
                long cimComputationTime = end - start;
                System.out.printf("CIM Computation time: %d ms\n", cimComputationTime);

                stats.addStats(particlesNumber, bruteForceComputationTime, cimComputationTime);
            }

            boardSequence.writeToFile("sequence" + fileNameWrapper.getId() + ".json");

            Particle.resetIdCounter();
        }

        stats.writeToFile("stats.json");


    }
}
