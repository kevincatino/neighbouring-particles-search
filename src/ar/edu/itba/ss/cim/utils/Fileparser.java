package ar.edu.itba.ss.cim.utils;

import ar.edu.itba.ss.cim.models.Coordinates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Fileparser {
    static Map<Integer, List<Coordinates>> parseDynamicFile(String filePath) throws IOException {

        String dynamicFileText = IO.readFile(filePath);

        HashMap<Integer, List<Coordinates>> timeData = new HashMap<>();
        Scanner scanner = new Scanner(dynamicFileText);

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
            }
        }

        scanner.close();
        return timeData;
    }

    static Map<Integer, List<Coordinates>> parseStaticFile(String filePath) throws IOException {

        String staticFileText = IO.readFile(filePath);

        HashMap<Integer, List<Coordinates>> timeData = new HashMap<>();
        Scanner scanner = new Scanner(staticFileText);

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
            }
        }

        scanner.close();
        return timeData;
    }
}
