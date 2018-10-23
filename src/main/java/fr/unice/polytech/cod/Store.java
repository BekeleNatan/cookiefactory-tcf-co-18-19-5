package fr.unice.polytech.cod;

import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.List;

public class Store {

	Franchise Stores;
	private int taxeRate;
	Recipe recipeOfTheMonth;

	/**
	 * 
	 * @param workingHours
	 */
	public void addWorkingHours(WorkingHour workingHours) {
		// TODO - implement Store.addWorkingHours
		throw new UnsupportedOperationException();
	}

	public List<Recipe> getMenu() {
		// TODO - implement Store.getMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param recipe
	 */
	public void addToMenu(Recipe recipe) {
		// TODO - implement Store.addToMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param recipe
	 */
	public void removeFromMenu(Recipe recipe) {
		// TODO - implement Store.removeFromMenu
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newRate
	 */
	public void setTaxeRate(int newRate) {
		this.taxeRate = newRate;
	}

	public int getTaxeRate() {
		return this.taxeRate;
	}

	/**
	 * 
	 * @param items
	 * @param hasDiscount
	 */
	public boolean takeOrder(List<Item> items, boolean hasDiscount) {
		// TODO - implement Store.takeOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param orderId
	 */
	public void collectOrder(int orderId) {
		// TODO - implement Store.collectOrder
		throw new UnsupportedOperationException();
	}

	public void printPeekHoursStat() {
		// TODO - implement Store.printPeekHoursStat
		throw new UnsupportedOperationException();
	}

}