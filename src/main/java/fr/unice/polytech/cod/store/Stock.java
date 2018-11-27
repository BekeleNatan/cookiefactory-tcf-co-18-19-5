package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;

import java.util.ArrayList;
import java.util.List;

public class Stock {
	public Store _manages;
	public ArrayList<Ingredient> _stores = new ArrayList<Ingredient>();

	public void addIngredient(Ingredient aIngredient) {
		throw new UnsupportedOperationException();
	}

	public void deleteIngredient(Ingredient aIngredient) {
		throw new UnsupportedOperationException();
	}

	public Ingredient getIngredientByName(String aNomIngredient) {
		throw new UnsupportedOperationException();
	}

	public List<Ingredient> getIngredientsByType(IngredientType aIngredientType) {
		throw new UnsupportedOperationException();
	}
}