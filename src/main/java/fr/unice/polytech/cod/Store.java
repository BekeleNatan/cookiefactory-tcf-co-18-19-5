package fr.unice.polytech.cod;

import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.recipe.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Store {

	private Franchise franchise;
	private String name;
	private double taxeRate = 1;
	private int lastId = 0;
	private Recipe recipeOfTheMonth = null;
	private HashMap<Integer,Order> orders = new HashMap();

	public Store(Franchise franchise, String name) {
		this.name = name;
		this.franchise = franchise;
	}

	public List<Recipe> getMenu() {
		List<Recipe> toReturn = new ArrayList<>();
		if (recipeOfTheMonth!=null) {
			toReturn.add(recipeOfTheMonth);
		}
		toReturn.addAll(franchise.getMenu());
		return toReturn;
	}

	public void setRecipeOfTheMonth(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price){
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
	 * 
	 * @param newRate
	 */
	public void setTaxeRate(double newRate) {
		this.taxeRate = newRate;
	}

	public double getTaxeRate() {
		return this.taxeRate;
	}

	/**
	 * 
	 * @param items
	 * @param hasDiscount
	 */
	public boolean takeOrder(List<Item> items, Date orderTime, String phoneNumber, boolean hasDiscount) {
		new Order(this,lastId,items,orderTime, phoneNumber, hasDiscount);
		lastId++;
		return true;
	}

	/**
	 *
	 */
	public void collectOrder(Order order) {
		orders.put(order.getID(),order);
	}

	public void printPeekHoursStat() {
		// TODO - implement Store.printPeekHoursStat
		throw new UnsupportedOperationException();
	}

    public List<Order> getOrders() {
		List<Order> toReturn = new ArrayList<>();
		toReturn.addAll(orders.values());
        return toReturn;
    }
}