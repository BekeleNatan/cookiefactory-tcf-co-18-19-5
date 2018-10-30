package fr.unice.polytech.cod.order;

import com.sun.deploy.Environment;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.WorkingHours.WorkingHours;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
        currentState = State.toPay;
        computeFinalPrice();
        store.collectOrder(this);


        if(store==null || items == null || !dateIsCorrect(orderTime) || !customerPhoneNumber.matches("[0-9]*")){
            this.currentState = State.refused;
        };
    }

    private boolean dateIsCorrect(Date orderTime) {
        Date date = new Date();
        WorkingHours wo = store.getWorkingHours();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTime);

        Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
        LocalTime convert = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (dayOfTheWeek==0)dayOfTheWeek=7;

        return orderTime.after(date) && wo.isOpenOn(DayOfWeek.of(dayOfTheWeek),convert);
    }

    private void alertClient(String message) {
        customer.sendMessage(message);
    }

    private void computeFinalPrice() {
        double taxeRate = store.getTaxeRate();
        for (Item item : items){
            finalPrice += item.getPrice()*taxeRate;
        }
        if(hasDiscount){
            finalPrice *= discountRate;
        }
        BigDecimal bd = new BigDecimal(finalPrice);
        bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
        finalPrice = bd.doubleValue();
    }

    public boolean makePayement(boolean success) {
        if(success && currentState==State.toPay){
            currentState = State.toDo;
            alertClient("payment done, the command is treated");
        }else if (!success){
            alertClient("payment failure, the command is canceled");
            currentState = State.paymentProblem;
        }else if (currentState!=State.toPay){
            alertClient("payment done but command canceled"); //TODO is that possible ?
        }
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

    public State getState() {
        return currentState;
    }

    public Customer getCustomer() {
        return customer;
    }
}