package ar.edu.itba.ss.cim.utils;

<<<<<<< HEAD
import ar.edu.itba.ss.cim.models.Coordinates;
=======
import ar.edu.itba.ss.cim.Coordinates;
import ar.edu.itba.ss.cim.models.Properties;
import ar.edu.itba.ss.cim.models.StaticStats;
import ar.edu.itba.ss.cim.models.TemporalCoordinates;
>>>>>>> origin/develop

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Fileparser {
    static Map<Integer, List<Coordinates>> parseDynamicFile(String filePath) throws IOException {

        String dynamicFileText = IO.readFile(filePath);

        HashMap<Integer, List<Coordinates>> timeData = new HashMap<>();
=======
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public interface Fileparser {
    static List<TemporalCoordinates> parseDynamicFile(String filePath) throws IOException {

        String dynamicFileText = IO.readFile(filePath);

        List<TemporalCoordinates> timeData = new ArrayList<>();
>>>>>>> origin/develop
        Scanner scanner = new Scanner(dynamicFileText);

        String line;
        Integer currentTime = null;
<<<<<<< HEAD

        while ((scanner.hasNextLine() &&  (line = scanner.nextLine()) != null)) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                timeData.get(currentTime).add(Coordinates.of(x,y));
            } else {
                currentTime = Integer.parseInt(line);
                timeData.put(currentTime, new ArrayList<>());
=======
        TemporalCoordinates tc = null;
        Integer idCounter = null;

        while ((scanner.hasNextLine() &&  (line = scanner.nextLine()) != null)) {

            if (line.contains(" ")) {
                if (idCounter == null || tc == null) {
                    throw new RuntimeException("Invalid dynamic file format");
                }
                String[] parts = line.split(" ");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                tc.addCoordinates(idCounter++,Coordinates.of(x,y));
            } else {
                currentTime = Integer.parseInt(line);
                idCounter = 1;
               tc = new TemporalCoordinates(currentTime);
                timeData.add(currentTime, tc);
>>>>>>> origin/develop
            }
        }

        scanner.close();
        return timeData;
    }

<<<<<<< HEAD
    static Map<Integer, List<Coordinates>> parseStaticFile(String filePath) throws IOException {
=======
    static StaticStats parseStaticFile(String filePath) throws IOException {
>>>>>>> origin/develop

        String staticFileText = IO.readFile(filePath);

        HashMap<Integer, List<Coordinates>> timeData = new HashMap<>();
        Scanner scanner = new Scanner(staticFileText);
<<<<<<< HEAD

        String line;
        Integer currentTime = null;

        while ((scanner.hasNextLine() &&  (line = scanner.nextLine()) != null)) {
            if (line.contains(" ")) {
                String[] parts = line.split(" ");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                timeData.get(currentTime).add(Coordinates.of(x,y));
            } else {
                currentTime = Integer.parseInt(line);
                timeData.put(currentTime, new ArrayList<>());
=======
        Map<Integer, Properties> propertiesMap = new HashMap<>();
        String line;
        int numberOfParticles = 0;
        double length = 0;
        int lineNumber = 0;

        while ((scanner.hasNextLine() &&  (line = scanner.nextLine()) != null)) {
            lineNumber++;

            if (lineNumber == 1) {
                numberOfParticles = Integer.parseInt(line);
            } else if (lineNumber == 2) {
                length = Double.parseDouble(line);
            } else {
                String[] parts = line.split(" ");
                float radius = Float.parseFloat(parts[0]);
                int particleId = lineNumber-2;
                propertiesMap.put(particleId,new Properties(radius, parts[1]));
>>>>>>> origin/develop
            }
        }

        scanner.close();
<<<<<<< HEAD
        return timeData;
=======
        return new StaticStats(numberOfParticles, length, propertiesMap);
>>>>>>> origin/develop
    }
}
