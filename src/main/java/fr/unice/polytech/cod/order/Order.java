package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.Store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    Store store;
    private Date orderTime;
    private State currentState;
    private boolean hasDiscount;
    private List<Item> items = new ArrayList<>();
    private Customer customer;
    private double finalPrice = 0;

    private int bankTransactionNumber;
    private static double discountRate = 0.9;

    public Order(Store store, int orderId, List<Item> items, Date orderTime, String customerPhoneNumber, boolean hasDiscount) {
        this.store = store;
        this.orderTime = orderTime;
        this.orderId = orderId;
        this.items = items;
        this.customer = new Customer(customerPhoneNumber);
        this.hasDiscount = hasDiscount;
        currentState = State.toPay;
        computeFinalPrice();
        if(makePayement()){
            currentState = State.toDo;
            store.collectOrder(this);
        }else{
            alertClient("the payment has not be done, the command is canceled");
            currentState = State.paymentProblem;
        }
    }

    private void alertClient(String message) {
        //TODO figure out what to do in this case
    }

    private void computeFinalPrice() {
        double taxeRate = store.getTaxeRate();
        for (Item item : items){
            finalPrice += item.getPrice()*taxeRate;
        }
        if(hasDiscount){
            finalPrice *= discountRate;
        }
    }

    public boolean makePayement() {
        // TODO figure out what to do
        bankTransactionNumber = 0;
        return true;
    }

    public void cancelOrder() {
        // TODO - implement Order.cancelOrder
        throw new UnsupportedOperationException();
    }

    public Integer getID() {
        return orderId;
    }

    public double getPrice() {
        return finalPrice;
    }
}