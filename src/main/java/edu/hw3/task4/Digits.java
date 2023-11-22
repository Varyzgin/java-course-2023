package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public class Digits {
    public static final int MAX_VALUE = 3999;
    @SuppressWarnings("MagicNumber")
    private static final Map<Integer, String> ROMAN_DICTIONARY = new LinkedHashMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    private Digits() {
    }

    public static String convertToRoman(int num) throws IllegalArgumentException {
        int arab = num;
        if (arab <= 0 || arab > MAX_VALUE) {
            throw new IllegalArgumentException("Number out of range (1-3999)");
        }

        StringBuilder roman = new StringBuilder();

        for (Map.Entry<Integer, String> entry : ROMAN_DICTIONARY.entrySet()) {
            int value = entry.getKey();
            while (arab >= value) {
                roman.append(entry.getValue());
                arab -= value;
            }
        }

        return roman.toString();
    }
}

/* import java.util.List;

public class Digits {
    public static final int MAX_VALUE = 3999;

    private Digits() {
    }

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(Integer num) throws Exception {
        if (num > MAX_VALUE || num < 0) {
            throw new Exception("Too big number");
        }
        int arab = num;

        List<String> romanDictionary = List.of("I", "V", "X", "L", "C", "D", "M");
        StringBuilder roman = new StringBuilder();

        int power = 3;

        int digit = arab % 10;
        arab /= 10;
        if (arab > 0) {
            roman.insert(0, switch (digit) {
                case 1 -> romanDictionary.get(0);
                case 2 -> romanDictionary.get(0) + romanDictionary.get(0);
                case 3 -> romanDictionary.get(0) + romanDictionary.get(0) + romanDictionary.get(0);
                case 4 -> romanDictionary.get(0) + romanDictionary.get(1);
                case 5 -> romanDictionary.get(1);
                case 6 -> romanDictionary.get(1) + romanDictionary.get(0);
                case 7 -> romanDictionary.get(1) + romanDictionary.get(0) + romanDictionary.get(0);
                case 8 ->
                    romanDictionary.get(1) + romanDictionary.get(0) + romanDictionary.get(0) + romanDictionary.get(0);
                case 9 -> romanDictionary.get(0) + romanDictionary.get(2);
                default -> throw new IllegalStateException("Unexpected value: " + digit);
            });
        }
        digit = arab % 10;
        arab /= 10;
        if (arab > 0) {
            roman.insert(0, switch (digit) {
                case 1 -> romanDictionary.get(2);
                case 2 -> romanDictionary.get(2) + romanDictionary.get(2);
                case 3 -> romanDictionary.get(2) + romanDictionary.get(2) + romanDictionary.get(2);
                case 4 -> romanDictionary.get(2) + romanDictionary.get(3);
                case 5 -> romanDictionary.get(3);
                case 6 -> romanDictionary.get(3) + romanDictionary.get(2);
                case 7 -> romanDictionary.get(3) + romanDictionary.get(2) + romanDictionary.get(2);
                case 8 ->
                    romanDictionary.get(3) + romanDictionary.get(2) + romanDictionary.get(2) + romanDictionary.get(2);
                case 9 -> romanDictionary.get(2) + romanDictionary.get(4);
                default -> throw new IllegalStateException("Unexpected value: " + digit);
            });
        }
        digit = arab % 10;
        arab /= 10;
        if (digit > 0 && arab > 0) {
            roman.insert(0, switch (digit) {
                case 1 -> romanDictionary.get(4);
                case 2 -> romanDictionary.get(4) + romanDictionary.get(4);
                case 3 -> romanDictionary.get(4) + romanDictionary.get(4) + romanDictionary.get(4);
                case 4 -> romanDictionary.get(4) + romanDictionary.get(5);
                case 5 -> romanDictionary.get(5);
                case 6 -> romanDictionary.get(5) + romanDictionary.get(4);
                case 7 -> romanDictionary.get(5) + romanDictionary.get(4) + romanDictionary.get(4);
                case 8 ->
                    romanDictionary.get(5) + romanDictionary.get(4) + romanDictionary.get(4) + romanDictionary.get(4);
                case 9 -> romanDictionary.get(4) + romanDictionary.get(6);
                default -> throw new IllegalStateException("Unexpected value: " + digit);
            });
        }
        digit = arab % 10;
        if (digit > 0 && arab > 0) {
            roman.insert(0, switch (digit) {
                case 1 -> romanDictionary.get(6);
                case 2 -> romanDictionary.get(6) + romanDictionary.get(6);
                case 3 -> romanDictionary.get(6) + romanDictionary.get(6) + romanDictionary.get(6);
                default -> throw new IllegalStateException("Unexpected value: " + digit);
            });
        }

        return roman.toString();
    }
} */
