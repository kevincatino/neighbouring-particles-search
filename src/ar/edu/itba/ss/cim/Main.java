package ar.edu.itba.ss.cim;

<<<<<<< HEAD
import ar.edu.itba.ss.cim.models.Coordinates;
=======
import ar.edu.itba.ss.cim.models.Board;
import ar.edu.itba.ss.cim.models.BoardSequence;
import ar.edu.itba.ss.cim.models.StaticStats;
import ar.edu.itba.ss.cim.models.TemporalCoordinates;
>>>>>>> origin/develop
import ar.edu.itba.ss.cim.utils.Fileparser;
import ar.edu.itba.ss.cim.utils.IO;

import java.io.IOException;
import java.util.List;
<<<<<<< HEAD
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
       String STATIC_FILE_PATH = "static.txt";
       String DYNAMIC_FILE_PATH = "dynamic.txt";
//       String staticFile = IO.readFile(STATIC_FILE_PATH);
       String dynamicFile = IO.readFile(DYNAMIC_FILE_PATH);
       Map<Integer, List<Coordinates>> coordinatesMap = Fileparser.parseDynamicFile(dynamicFile);
       System.out.println(coordinatesMap.get(1));

=======

public class Main {


    public static void main(String[] args) throws IOException {
       String STATIC_FILE_PATH = "static.txt";
       String DYNAMIC_FILE_PATH = "dynamic.txt";
       int M = 4;
       StaticStats staticStats = Fileparser.parseStaticFile(STATIC_FILE_PATH);
       List<TemporalCoordinates> temporalCoordinates = Fileparser.parseDynamicFile(DYNAMIC_FILE_PATH);
        BoardSequence boardSequence = new BoardSequence(staticStats,temporalCoordinates, M);
        for (Board b : boardSequence) {
            System.out.println(b);
        }
>>>>>>> origin/develop
    }
}
