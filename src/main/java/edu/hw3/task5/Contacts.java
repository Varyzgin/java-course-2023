package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contacts {
    public static final int MAX_NAMES = 3;

    private Contacts() {
    }

    public static List<Person> parseContacts(List<String> list, String type) throws Exception {
        List<Person> contacts = new ArrayList<>();
        if (list == null) {
            return null;
        }
        for (var name : list) {
            String[] symbols = name.split(" ");
            if (symbols.length > MAX_NAMES) {
                throw new Exception("Too much names");
            } else if (symbols.length == 1) {
                contacts.add(new Person("", symbols[0]));
            } else {
                contacts.add(new Person(symbols[0], symbols[1]));
            }
        }
        Comparator<Person> surnameComparator;
        surnameComparator = switch (type) {
            case "ASC" -> Comparator.comparing(Person::surname);
            case "DESC" -> Collections.reverseOrder(Comparator.comparing(Person::surname));
            default -> throw new Exception("Incorrect sort type");
        };
        contacts.sort(surnameComparator);
        return contacts;
    }
}
