package edu.hw3.task6;

import java.util.PriorityQueue;

public class Market implements StockMarket {
    PriorityQueue<Stock> list;

    public Market() {
        list = new PriorityQueue<>();
    }

    @Override
    public void add(Stock stock) {
        list.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        list.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return list.peek();
    }
}
