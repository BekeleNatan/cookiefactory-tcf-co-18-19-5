package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.order.states.OrderState;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.workinghours.WorkingHours;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int _orderId;
    private Date _orderTime;
    private OrderState _currentState;
    private boolean _hasDiscount;
    private String _bankTransactionNumber;
    private double _price;
    public OrderRegister _recieves;
    public ArrayList<Item> _is_composed_of = new ArrayList<Item>();
    public Customer _is_made_by;
    public OrderState _unnamed_OrderState_;

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