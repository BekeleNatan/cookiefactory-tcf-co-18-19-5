package store;

import franchise.FranchiseMenu;
import order.Order;
import order.OrderRegister;
import store.workinghours.WorkingHours;

import java.util.UUID;


public class Store {


    private String storeName;
    private double taxeRate;
    private double pointsToMoneyRate;  // used to pay with points
    private double moneyToPointsRate; // used to give points to clients who paid

    private WorkingHours workingHours;
    private StoreMenu storeMenu;
    private Stock stock;
    private OrderRegister orderRegister;
    private CashRegister cashRegister;

    public Store(String name, FranchiseMenu franchiseMenu) {
        this.storeName = name;
        this.workingHours = new WorkingHours();
        this.storeMenu = new StoreMenu(franchiseMenu);
        this.taxeRate = 1;
        this.pointsToMoneyRate = 0.1;
        this.moneyToPointsRate= 0.01;
        this.stock = new Stock();
        this.orderRegister = new OrderRegister();
        cashRegister= new CashRegister();
    }

    // taxeRate
    public void setTaxeRate(double aNewRate) {
        this.taxeRate = aNewRate;
    }

    public double getTaxeRate() {
        return this.taxeRate;
    }

    // storeMenu
    public StoreMenu getStoreMenu() {
        return storeMenu;
    }

    // workingHours
    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    // pointsToMoneyRate
    public boolean setPointsToMoneyRate(double aNewPointsToEurosRate) {
        this.pointsToMoneyRate = aNewPointsToEurosRate;
        return true;
    }

    public double getPointsToMoneyRate() { return this.pointsToMoneyRate;}



    // Name
    public String getName() {
        return storeName;
    }

    public boolean setName(String newName) {
        this.storeName = newName;
        return true;
    }

    // Order Register
    public OrderRegister getOrderRegister() {
        return this.orderRegister;
    }

    // Stock
    public Stock getStock() {
        return this.stock;
    }

    public double getMoneyToPointsRate() {
        return moneyToPointsRate;
    }

    public void setMoneyToPointsRate(double moneyToPointsRate) {
        this.moneyToPointsRate = moneyToPointsRate;
    }

    public Order createOrder() {
        return orderRegister.createNewOrder();
    }

    public Order collectOrder(UUID orderId) {
        return orderRegister.findOrder(orderId);
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }
}