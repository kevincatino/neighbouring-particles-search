package ar.edu.itba.ss.cim;


import ar.edu.itba.ss.cim.dto.ExecutionStats;
import ar.edu.itba.ss.cim.helper.FileNamesWrapper;
import ar.edu.itba.ss.cim.models.Board;
import ar.edu.itba.ss.cim.models.BoardSequence;
import ar.edu.itba.ss.cim.models.ExecutionStatsWrapper;
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
        int numberOfSimulations = 2;
        int M = 4;
        int timeValues = 3;
        int numberOfParticles = 5;
        double interactionRadius = 5;
        double boardLength = 10;
        ExecutionStatsWrapper stats = new ExecutionStatsWrapper();
        for (int i = 0; i < numberOfSimulations; i++) {
            String STATIC_FILE_PATH = "static.txt";
            String DYNAMIC_FILE_PATH = "dynamic.txt";
            FileNamesWrapper fileNameWrapper = Fileparser.generateInputData(numberOfParticles, M, boardLength, interactionRadius, timeValues);
            StaticStats staticStats = Fileparser.parseStaticFile(STATIC_FILE_PATH);
            List<TemporalCoordinates> temporalCoordinates = Fileparser.parseDynamicFile(DYNAMIC_FILE_PATH);
            BoardSequence boardSequence = new BoardSequence(staticStats, temporalCoordinates, M, interactionRadius, Board.BoundaryConditions.PERIODIC);

            for (Board b : boardSequence) {
                System.out.println(b);
                System.out.println("CIM Neighbours");
                long start = System.currentTimeMillis();
                System.out.println(b.getNeighbours(Board.Method.CIM));
                long end = System.currentTimeMillis();
                long cimComputationTime = end-start;
                System.out.printf("CIM Computation time: %d ms\n", cimComputationTime);

                System.out.println("Brute force Neighbours");
                start = System.currentTimeMillis();
                System.out.println(b.getNeighbours(Board.Method.BRUTE_FORCE));
                end = System.currentTimeMillis();
                long bruteForceComputationTime = end-start;
                stats.addStats(numberOfParticles,bruteForceComputationTime,cimComputationTime);
                System.out.printf("Brute force Computation time: %d ms\n", bruteForceComputationTime);
            }

            boardSequence.writeToFile("sequence" + fileNameWrapper.TimeStamp +".json");

            Particle.resetIdCounter();
        }


    }
}
