package edu.hw3.task8;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final T[] elements;
    private int currentIndex;

    public BackwardIterator(T[] elements) {
        this.elements = elements;
        this.currentIndex = elements.length - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex > 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the collection.");
        }
        return elements[currentIndex--];
    }
}
