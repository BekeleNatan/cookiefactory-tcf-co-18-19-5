package fr.unice.polytech.cod.recipe;

import fr.unice.polytech.cod.franchise.FranchiseMenu;
import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import java.util.List;

public abstract class Recipe {
	private int price;
	private CookingType cooking;
	private MixType mix;
	public List<Ingredient> ingredients;

	public List<Ingredient> getIngredients() {
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		throw new UnsupportedOperationException();
	}

	public MixType getMixType() {
		throw new UnsupportedOperationException();
	}

	public CookingType getCookingType() {
		throw new UnsupportedOperationException();
	}
}