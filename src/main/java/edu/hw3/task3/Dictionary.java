package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;

public class Dictionary {
    private Dictionary() {
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
}
