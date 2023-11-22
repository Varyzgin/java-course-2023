package edu.hw3.task7;

import java.util.Comparator;

public class Comparators {
    private Comparators() {
    }

    public static Comparator<String> nullHandlingComparator = Comparator.nullsFirst(Comparator.naturalOrder());
}
