package edu.hw3.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Something {
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

    public static HashMap<Object, Integer> freqDict(List<Object> array) {
        HashMap<Object, Integer> dictionary = new HashMap<>();

        for (Object word : array) {
            if (dictionary.containsKey(word)) {
                dictionary.put(word, dictionary.get(word) + 1);
            } else {
                dictionary.put(word, 1);
            }
        }

        return dictionary;
    }

    public static String convertToRoman(int arab) throws Exception {
        if (arab > 4000) {
            throw new Exception("Too big number");
        }
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (arab >= values[i]) {
                arab -= values[i];
                result.append(romanSymbols[i]);
            }
        }

        return result.toString();
    }

    public static List<Person> parseContacts(List<String> list, String type) throws Exception {
        List<Person> contacts = new ArrayList<>();
        if (list == null) {
            return null;
        }
        for (var name : list) {
            String[] symbols = name.split(" ");
            if (symbols.length > 3) {
                throw new Exception("Too much names");
            } else if (symbols.length == 1) {
                contacts.add(new Person("", symbols[0]));
            } else {
                contacts.add(new Person(symbols[0], symbols[1]));
            }
        }
        Comparator<Person> surnameComparator;
        surnameComparator = switch (type) {
            case "ASC" -> Comparator.comparing(Person::getSurname);
            case "DESC" -> Collections.reverseOrder(Comparator.comparing(Person::getSurname));
            default -> throw new Exception("Incorrect sort type");
        };
        Collections.sort(contacts, surnameComparator);
        return contacts;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(atbash(
            "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler"));
        System.out.println(clusterize("((())())(()(()()))"));
        System.out.println(freqDict(List.of(1, 1, 2, 2)));
        System.out.println(convertToRoman(74));
        System.out.println(parseContacts(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss", "Mister Who"), "DESC"));
    }
}
