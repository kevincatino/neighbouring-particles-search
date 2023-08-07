package ar.edu.itba.ss.cim;

import ar.edu.itba.ss.cim.models.Coordinates;
import ar.edu.itba.ss.cim.utils.Fileparser;
import ar.edu.itba.ss.cim.utils.IO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
       String STATIC_FILE_PATH = "static.txt";
       String DYNAMIC_FILE_PATH = "dynamic.txt";
//       String staticFile = IO.readFile(STATIC_FILE_PATH);
       String dynamicFile = IO.readFile(DYNAMIC_FILE_PATH);
       Map<Integer, List<Coordinates>> coordinatesMap = Fileparser.parseDynamicFile(dynamicFile);
       System.out.println(coordinatesMap.get(1));

    }
}
