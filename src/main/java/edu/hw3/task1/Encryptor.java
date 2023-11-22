package edu.hw3.task1;

import java.util.List;

public class Atbash {
    public static String atbash(String text) {
        List<String> letters = List.of(
            "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z"
        );
        List<String> Letters = List.of(
            "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"
        );

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String letter = String.valueOf(text.charAt(i));
            if (letters.contains(letter)) {
                stringBuilder.append(letters.get(25 - letters.indexOf(letter)));
            } else if (Letters.contains(letter)) {
                stringBuilder.append(Letters.get(25 - Letters.indexOf(letter)));
            } else {
                stringBuilder.append(letter);
            }
        }
        return stringBuilder.toString();
    }
}
