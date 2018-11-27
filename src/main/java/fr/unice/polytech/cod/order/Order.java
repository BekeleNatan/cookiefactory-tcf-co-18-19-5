package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.order.states.OrderState;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.workinghours.WorkingHours;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderId;
    private Date collectTime;
    private OrderState currentState;
    private boolean hasDiscount;
    private String bankTransactionNumber;
    private double price;
    public ArrayList<Item> items = new ArrayList<Item>();
    public Customer customer;

    public Order(Stock aStock, WorkingHours aWorkingHours) {
        throw new UnsupportedOperationException();
    }

    public boolean addItem(Recipe aRecipe, int aQuantity) {
        throw new UnsupportedOperationException();
    }

    public void addInfos(Date aDate, String aPhoneNumber, WorkingHours aWh) {
        throw new UnsupportedOperationException();
    }

    public boolean collect() {
        throw new UnsupportedOperationException();
    }
}