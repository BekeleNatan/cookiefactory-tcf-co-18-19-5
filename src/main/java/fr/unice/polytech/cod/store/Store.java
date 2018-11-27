package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.order.OrderRegister;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;


public class Store {
    private String storeName;
    private double taxeRate;
    private double pointsToMoneyRate;

    private WorkingHours workingHours;
    private StoreMenu storeMenu;
    private Stock stock;
    private OrderRegister orderRegister;

    public Store(String name) {
        this.storeName = name;
        this.workingHours = new WorkingHours();
        this.storeMenu = new StoreMenu();
        this.taxeRate = 1;
        this.pointsToMoneyRate = 1;
        this.stock = new Stock();
        this.orderRegister = new OrderRegister();
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

    public double getPointsToMoneyRate() {
        return this.pointsToMoneyRate;
    }

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

}