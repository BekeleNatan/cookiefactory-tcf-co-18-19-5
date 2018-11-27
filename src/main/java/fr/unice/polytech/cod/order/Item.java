package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;

public class Item {
	private int quantity;
	private int price;

	public Item(Recipe aRecipe, int aQuantity) {
		throw new UnsupportedOperationException();
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int aQuantity, Stock aStock) {
		throw new UnsupportedOperationException();
	}

	public Recipe getRecipe() {
		throw new UnsupportedOperationException();
	}

	public int getPrice() {
		return this.price;
	}
}