package edu.hw3.task2;

import java.util.ArrayList;

public class Interpreter {
    private Interpreter() {
    }

    public static ArrayList<String> clusterize(String text) throws Exception {
        ArrayList<String> cluster = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int open = 0;
        int close = 0;

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (letter == '(') {
                open++;
                stringBuilder.append(letter);
            } else if (letter == ')') {
                close++;
                stringBuilder.append(letter);
                if (close == open) {
                    cluster.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            } else {
                throw new Exception("Incorrect input");
            }
        }
        return cluster;
    }
}
