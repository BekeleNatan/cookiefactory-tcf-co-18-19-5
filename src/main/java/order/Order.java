package order;

import order.states.OnCreation;
import order.states.OrderState;
import recipe.Recipe;
import store.Stock;
import store.workinghours.WorkingHours;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private static double remainingPaymentThreshold = 0.01;
    private static double limitForCashPayment = 100;

    private UUID orderId;
    private Date collectTime = null;
    private OrderState currentState;
    private Customer customer;
    private double price;
    private double remainToPay;

    private PaymentInfos paymentInfos = new PaymentInfos();

    public String bankTransactionNumber = null;
    public List<Item> items = new ArrayList<>();


    private boolean isPayed = false;

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

    public void deductPayedAmount(double payedAmount){
        remainToPay = remainToPay - payedAmount;
        if(price - remainToPay < remainingPaymentThreshold)
            this.setPayed(true);
    }

    public boolean paymentConditionOk() {
        if(price< limitForCashPayment || this.isPayed()){
            return true;
        }else{
            return false;
        }
    }

    public double getPrice() {
        return price;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}