package com.advent.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeCondrum {

    static Pattern pattern = Pattern.compile("(\\d+)\\s+(\\w+)");

    public static int cubeCondrum() throws FileNotFoundException {
        String filePath = "resources/day02/input.txt";

        Scanner scanner = new Scanner(new File(filePath));

        Map<String, Integer> maxCubesCount = new HashMap<>();
        maxCubesCount.put("red", 12);
        maxCubesCount.put("green", 13);
        maxCubesCount.put("blue", 14);

        int possibleGamesSum = 0;

        int lineCounter = 0;
        while (scanner.hasNext()) {
            lineCounter++;
            var line = scanner.nextLine();
            boolean impossibleGame = false;

            var setsOfCubes = line.substring(line.indexOf(":")).split(";");

            for (var set : setsOfCubes) {
                Matcher matcher = pattern.matcher(set);

                while (matcher.find()) {
                    int count = Integer.parseInt(matcher.group(1));
                    String color = matcher.group(2);

                    if (count > maxCubesCount.get(color)) {
                        impossibleGame = true;
                        break;
                    }
                }

                if (impossibleGame) {
                    break;
                }
            }

            if (!impossibleGame) {
                possibleGamesSum += lineCounter;
            }
        }

        return possibleGamesSum;
    }

    public static int cubeCondrumV2() throws FileNotFoundException {
        String filePath = "resources/day02/input.txt";

        Scanner scanner = new Scanner(new File(filePath));

        int totalPowerOfCubes = 0;

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            var setsOfCubes = line.substring(line.indexOf(":")).split(";");

            Map<String, Integer> maxCubes = new HashMap<>();

            for (var set : setsOfCubes) {
                Matcher matcher = pattern.matcher(set);

                while (matcher.find()) {
                    Integer count = Integer.parseInt(matcher.group(1));
                    String color = matcher.group(2);

                    if (maxCubes.containsKey(color)) {
                        if (maxCubes.get(color) < count) {
                            maxCubes.put(color, count);
                        }
                    } else {
                        maxCubes.put(color, count);
                    }
                }
            }

            int gamePower = 1;
            for (var cubes : maxCubes.values()) {
                gamePower *= cubes;
            }

            totalPowerOfCubes += gamePower;
        }

        return totalPowerOfCubes;
    }

}
