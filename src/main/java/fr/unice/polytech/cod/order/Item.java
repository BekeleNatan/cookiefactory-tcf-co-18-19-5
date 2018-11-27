package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;

public class Item {
	private int _quantity;
	private int _price;
	public Order _is_composed_of;

	public Item(Recipe aRecipe, int aQuantity) {
		throw new UnsupportedOperationException();
	}

	public int getQuantity() {
		return this._quantity;
	}

	public void setQuantity(int aQuantity, Stock aStock) {
		throw new UnsupportedOperationException();
	}

	public Recipe getRecipe() {
		throw new UnsupportedOperationException();
	}

	public int getPrice() {
		return this._price;
	}
}