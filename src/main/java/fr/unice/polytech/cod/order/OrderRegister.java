package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.stats.Stats;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class OrderRegister {
    List<Order> orders;
    List<Stats> stats;

    public OrderRegister(){

    }

    public Order findOrder(int aOrderId) {
        throw new UnsupportedOperationException();
    }

    public void printPeekHoursStat() {
        throw new UnsupportedOperationException();
    }

    public Order createNewOrder(Stock aStock) {
        throw new UnsupportedOperationException();
    }

    public void computeStats() {
        throw new UnsupportedOperationException();
    }
}