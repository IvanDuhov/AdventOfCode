package com.advent.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet {
    
    public static int trebuchet() throws FileNotFoundException {
        String filePath = "resources/day01/input.txt";

        Scanner scanner = new Scanner(new File(filePath));

        int totalSum = 0;

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            int currentLineSum = 0;

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    currentLineSum += Integer.parseInt(String.valueOf(line.charAt(i))) * 10;
                    break;
                }
            }

            for (int i = line.length() - 1; i >= 0; i--) {
                if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    currentLineSum += Integer.parseInt(String.valueOf(line.charAt(i)));
                    break;
                }
            }

            totalSum += currentLineSum;
        }

        return totalSum;
    }


}
