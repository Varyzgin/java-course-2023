package edu.hw3.task6;

public record Stock(String name, double price) implements Comparable<Stock> {
    @Override
    public int compareTo(Stock other) {
        int priceComparison = Double.compare(other.price, this.price);
        if (priceComparison != 0) {
            return priceComparison;
        }
        return this.name.compareTo(other.name);
    }
}
