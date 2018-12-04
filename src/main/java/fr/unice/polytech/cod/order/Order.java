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
    private Customer customer;
    private double price;
    private double remainToPay;
    private double limitWithoutPayementOrder = 100;
    private PaymentInfos paymentInfos = new PaymentInfos();

    public String bankTransactionNumber = null;
    public ArrayList<Item> items = new ArrayList<Item>();
    public boolean payed = false;

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

    public double getRemainToPay(){
        return remainToPay;
    }

    public void setPrice(double new_price){
        price = new_price;
        remainToPay = new_price;
    }

    public void setRemainToPay(double new_to_pay){
        remainToPay = new_to_pay;
    }

    public boolean paymentConditionOk() {
        if(price<limitWithoutPayementOrder || payed){
            return true;
        }else{
            return false;
        }
    }

    public double getPrice() {
        return price;
    }
}