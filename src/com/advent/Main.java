package com.advent;

import com.advent.day01.Trebuchet;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        var answer = Trebuchet.trebuchet();
        System.out.println(answer);
    }
}
