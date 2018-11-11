package fr.unice.polytech.cod.order;


import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.unitaryTesting.WorkingHours.WorkingHours;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
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
        this.currentState = State.toPay;
        computeFinalPrice();
        store.collectOrder(this);


        if(store==null || items == null || !dateIsCorrect(orderTime) || !customerPhoneNumber.matches("[0-9]*")){
            this.currentState = State.refused;
        };
    }

    private boolean dateIsCorrect(Date orderTime) {
        //TODO Verifier mieux la date
        Date date = new Date();
        WorkingHours wo = this.store.getWorkingHours();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTime);

        Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
        LocalTime convert = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (dayOfTheWeek==0)dayOfTheWeek=7;

        return orderTime.after(date) && wo.isOpenOn(DayOfWeek.of(dayOfTheWeek),convert);
    }

    private void alertClient(String message) {
        this.customer.sendMessage(message);
    }

    private void computeFinalPrice() {
        double taxeRate = this.store.getTaxeRate();
        for (Item item : items){
            this.finalPrice += item.getPrice()*taxeRate;
        }
        if(this.hasDiscount){
            this.finalPrice *= this.discountRate;
        }
        BigDecimal bd = new BigDecimal(this.finalPrice);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        this.finalPrice = bd.doubleValue();
    }

    public boolean makePayement(boolean success, int transaction) {
        this.bankTransactionNumber = 0;
        if(success && this.currentState==State.toPay){
            this.bankTransactionNumber = transaction;
            this.currentState = State.toDo;
            alertClient("payment done, the command is treated");
        }else if (!success){
            alertClient("payment failure, the command is canceled");
            this.currentState = State.paymentProblem;
        }else if (this.currentState!=State.toPay){
            alertClient("payment done but command canceled"); //TODO is that possible ?
        }
        return true;
    }

    public String cancelOrder() {
        if (this.currentState==State.toPay){
            this.currentState = State.refused;
            String message = "La command a été annulé";
            if(!customer.sendMessage("Votre command a été annulé")){
                message += ", mais on a pas pu le prévenir au : "+this.customer.getPhoneNumber();
            }
            return message;
        }

        this.currentState = State.refused;
        String message = "La command a été annulé";

        if(this.customer.payBack(this.bankTransactionNumber)){
            message += ", le client a été remboursé";
            if(!this.customer.sendMessage("Votre command a été annulé, vous allez recevoir un remboursement sous peu")){
                message += ", mais on a pas pu le prévenir au : "+this.customer.getPhoneNumber();
            }
        }else{
            message += ", le client n'a pas été remboursé";
            if(!this.customer.sendMessage("Votre command a été annulé, vous allez être contacter pour le remboursement")){
                message += ", et on a pas pu le prévenir au : "+this.customer.getPhoneNumber();
            }else{
                message += ", son numéro est le : "+this.customer.getPhoneNumber();
            }
        }

        return message;
    }

    public Integer getID() {
        return this.orderId;
    }

    public double getPrice() {
        return this.finalPrice;
    }

    public State getState() {
        return this.currentState;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Object getTransactionNumber() {
        return this.bankTransactionNumber;
    }
}