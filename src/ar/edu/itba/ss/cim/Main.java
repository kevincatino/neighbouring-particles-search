package ar.edu.itba.ss.cim;


import ar.edu.itba.ss.cim.models.Board;
import ar.edu.itba.ss.cim.models.BoardSequence;
import ar.edu.itba.ss.cim.models.StaticStats;
import ar.edu.itba.ss.cim.models.TemporalCoordinates;
import ar.edu.itba.ss.cim.utils.Fileparser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
       String STATIC_FILE_PATH = "static.txt";
       String DYNAMIC_FILE_PATH = "dynamic.txt";
       Fileparser.generateInputData(5,5,2,3);
       int M = 4;
       double interactionRadius = 5;
       StaticStats staticStats = Fileparser.parseStaticFile(STATIC_FILE_PATH);
       List<TemporalCoordinates> temporalCoordinates = Fileparser.parseDynamicFile(DYNAMIC_FILE_PATH);
        BoardSequence boardSequence = new BoardSequence(staticStats,temporalCoordinates, M, interactionRadius, Board.BoundaryConditions.PERIODIC);

        for (Board b : boardSequence) {
            System.out.println(b);
            System.out.println("Neighbours");
            long start = System.currentTimeMillis();
            System.out.println(b.getNeighbours(Board.Method.CIM));

            long end = System.currentTimeMillis();
            System.out.printf("Computation time: %d ms\n",(end-start));
        }

        boardSequence.writeToFile("people.json");

    }
}
