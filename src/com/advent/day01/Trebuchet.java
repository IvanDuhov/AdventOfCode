package com.advent.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Trebuchet {

    private static final List<String> digits =
            List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    public static int trebuchet() throws FileNotFoundException {
        String filePath = "resources/day01/input.txt";

        Scanner scanner = new Scanner(new File(filePath));

        int totalSum = 0;

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            totalSum += findSumIfNoTextDigits(line);
        }

        return totalSum;
    }

    private static int findSumIfNoTextDigits(String text) {
        var firstDigit = findFirstDigit(text);
        var lastDigit = findLastDigit(text);

        return firstDigit * 10 + lastDigit;
    }

    private static int findFirstDigit(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                return Integer.parseInt(String.valueOf(text.charAt(i)));
            }
        }

        return 0;
    }

    private static int findLastDigit(String text) {
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {
                return Integer.parseInt(String.valueOf(text.charAt(i)));
            }
        }

        return 0;
    }

    public static int trebuchetV2() throws FileNotFoundException {
        String filePath = "resources/day01/input.txt";

        Scanner scanner = new Scanner(new File(filePath));

        int totalSum = 0;

        while (scanner.hasNext()) {
            var line = scanner.nextLine();

            // Key is the index, value is the actual digit value
            Map<Integer, Integer> textDigitsMap = new HashMap<>();

            for (int i = 0; i < digits.size(); i++) {
                var startsAt = -2;
                while (startsAt != -1) {
                    var number = digits.get(i);
                    var checkFromIndex = startsAt == -2 ? 0 : startsAt + 1;
                    startsAt = line.indexOf(digits.get(i), checkFromIndex);

                    if (startsAt >= 0) {
                        textDigitsMap.put(startsAt, i + 1);
                    }
                }
            }

            // If there are no text digits
            if (textDigitsMap.isEmpty()) {
                totalSum += findSumIfNoTextDigits(line);
                continue;
            }

            Map<Integer, Integer> sortedTextDigits = new TreeMap<Integer, Integer>(textDigitsMap);


            // 1six7396484
            int firstTextDigitIndex = sortedTextDigits.keySet().stream().findFirst().get();
            int firstTextDigitValue = sortedTextDigits.values().stream().findFirst().get();

            int lastTextDigitIndex = -1;
            int lastTextDigitValue = -1;

            if (sortedTextDigits.size() > 1) {
                lastTextDigitIndex = new ArrayList<>(sortedTextDigits.keySet())
                        .get(sortedTextDigits.size() - 1);
                lastTextDigitValue = new ArrayList<>(sortedTextDigits.values())
                        .get(sortedTextDigits.size() - 1);
            } else {
                lastTextDigitIndex = firstTextDigitIndex;
                lastTextDigitValue = firstTextDigitValue;
            }

            int actualFirstDigit = -1;
            int actualLastDigit = -1;

            var potentialFirstDigit = findFirstDigit(line.substring(0, firstTextDigitIndex));

            if (potentialFirstDigit > 0) {
                actualFirstDigit = potentialFirstDigit;
            } else {
                actualFirstDigit = firstTextDigitValue;
            }

            var potentialLastDigit =
                    findLastDigit(line.substring(lastTextDigitIndex, line.length()));

            if (potentialLastDigit > 0) {
                actualLastDigit = potentialLastDigit;
            } else {
                actualLastDigit = lastTextDigitValue;
            }

            totalSum += actualFirstDigit * 10 + actualLastDigit;
        }

        return totalSum;
    }


}
