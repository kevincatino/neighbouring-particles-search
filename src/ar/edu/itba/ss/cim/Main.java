package ar.edu.itba.ss.cim;


import ar.edu.itba.ss.cim.models.Board;
import ar.edu.itba.ss.cim.models.BoardSequence;
import ar.edu.itba.ss.cim.models.Coordinates;
import ar.edu.itba.ss.cim.models.StaticStats;
import ar.edu.itba.ss.cim.models.TemporalCoordinates;
import ar.edu.itba.ss.cim.utils.Fileparser;

import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
       String STATIC_FILE_PATH = "static.txt";
       String DYNAMIC_FILE_PATH = "dynamic.txt";
       int M = 4;
       double interactionRadius = 5;
       StaticStats staticStats = Fileparser.parseStaticFile(STATIC_FILE_PATH);
       List<TemporalCoordinates> temporalCoordinates = Fileparser.parseDynamicFile(DYNAMIC_FILE_PATH);
        BoardSequence boardSequence = new BoardSequence(staticStats,temporalCoordinates, M, interactionRadius);

        for (Board b : boardSequence) {
            System.out.println(b);
            System.out.println("Neighbours");
            long start = System.currentTimeMillis();
            System.out.println(b.getNeighbours(Board.METHOD.CIM, Board.BOUNDARY_CONDITIONS.PERIODIC));
            long end = System.currentTimeMillis();
            System.out.printf("Computation time: %d ms\n",(end-start));
        }
    }
}
