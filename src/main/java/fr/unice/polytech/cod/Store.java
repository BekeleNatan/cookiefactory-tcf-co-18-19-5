package fr.unice.polytech.cod;

import fr.unice.polytech.cod.unitaryTesting.WorkingHours.WorkingHours;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.recipe.*;

import java.util.*;

public class Store {

    // initialized in the constructor
    private String name;
    private Menu menu;
    private double taxeRate;


    private int orderIdCounter = 0;
    private Recipe recipeOfTheMonth = null;
    private List<Order> orders = new ArrayList<>();
    private WorkingHours workingHours = new WorkingHours();


    public Store(String name, double taxeRate) {
        this.name = name;
        this.menu = new Menu();
        this.taxeRate = taxeRate;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public void setRecipeOfTheMonth(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price) {
        Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix, price);
        recipeOfTheMonth = recipe;
    }

    /**
     *
     */
    public void removeRecipeOfTheMonth() {
        recipeOfTheMonth = null;
    }

    /**
     * @param newRate
     */
    public void setTaxeRate(double newRate) {
        this.taxeRate = newRate;
    }

    public double getTaxeRate() {
        return this.taxeRate;
    }

    /**
     * @param itemsList
     * @param hasDiscount
     */
    public Order takeOrder(List<Item> itemsList, Date orderTime, String phoneNumber, boolean hasDiscount) {
        Order order = new Order(this, orderIdCounter, itemsList, orderTime, phoneNumber, hasDiscount);
        orderIdCounter++;
        return order;
    }

    /**
     *
     */
    public void collectOrder(Order order) {
        orders.add(order);
    }

    public void printPeekHoursStat() {
        // TODO - implement Store.printPeekHoursStat
        throw new UnsupportedOperationException();
    }

    public List<Order> getOrders() {
        List<Order> toReturn = new ArrayList<>(orders);
        return toReturn;
    }

    public Recipe getRecipeOfTheMonth() {
        return recipeOfTheMonth;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void addRecipteToMenu(Recipe recipe){
        menu.addRecipe(recipe);
    }

    public String getName() {
        return name;
    }
}