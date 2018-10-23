package fr.unice.polytech.cod.order;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private Date orderTime;
    private State currentState;
    private boolean hasDiscount;
    private String bankTransactionNumber;
    private int finalPrice;

    /**
     * @param items
     * @param customer
     * @param hasDiscount
     */
    public Order(List<Item> items, Customer customer, boolean hasDiscount) {
        // TODO - implement Order.Order
        throw new UnsupportedOperationException();
    }

    private void computeFinalPrice() {
        // TODO - implement Order.computeFinalPrice
        throw new UnsupportedOperationException();
    }

    public boolean makePayement() {
        // TODO - implement Order.makePayement
        throw new UnsupportedOperationException();
    }

    public void cancelOrder() {
        // TODO - implement Order.cancelOrder
        throw new UnsupportedOperationException();
    }

}