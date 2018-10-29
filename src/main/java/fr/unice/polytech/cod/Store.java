package fr.unice.polytech.cod;

import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.recipe.*;

import java.util.ArrayList;
import java.util.List;

public class Store {

	private Franchise franchise;
	private String name;
	private int taxeRate;
	private Recipe recipeOfTheMonth = null;

	public Store(Franchise franchise, String name) {
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

	public void setRecipeOfTheMonth(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix){
		Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix);
		recipeOfTheMonth = recipe;
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
		return true;
	}

	/**
	 * 
	 * @param orderId
	 */
	public void collectOrder(int orderId) {

	}

	public void printPeekHoursStat() {
		// TODO - implement Store.printPeekHoursStat
		throw new UnsupportedOperationException();
	}

}