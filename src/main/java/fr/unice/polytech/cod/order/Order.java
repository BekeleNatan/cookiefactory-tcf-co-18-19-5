package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.order.states.OnCreation;
import fr.unice.polytech.cod.order.states.OrderState;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private Date collectTime = null;
    private OrderState currentState;
    private String bankTransactionNumber = null;
    public ArrayList<Item> items = new ArrayList<Item>();
    private Customer customer;

    public Order() {
        orderId = UUID.randomUUID();
        customer = new Customer();
        currentState = new OnCreation(this);
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public boolean addItem(Recipe aRecipe, int aQuantity, Stock stock) {
        return currentState.addItem(aRecipe, aQuantity, stock);
    }

    public boolean addInfos(Date aDate, String aPhoneNumber, WorkingHours aWh) {
        return currentState.addInfos(aDate, aPhoneNumber, aWh);
    }

    public boolean collect() {
        return currentState.collect();
    }

    public JSONObject showOrder(){
        return currentState.showOrder();
    }

    public boolean changeState(){
        return currentState.nextState();
    }

    public void setCurrentState(OrderState orderState) {
        currentState = orderState;
    }

    public void setCollectTime(Date date) {
        collectTime = date;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public Customer getCustomer(){
        return customer;
    }

}