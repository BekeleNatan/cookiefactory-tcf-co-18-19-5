package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stock {
	public HashMap<Ingredient,Integer> ingredients = new HashMap<Ingredient, Integer>();

	public void addIngredient(Ingredient aIngredient, int quantity) {
		throw new UnsupportedOperationException();
	}

	public void removeIngredient(Ingredient aIngredient, int quantity) {
		throw new UnsupportedOperationException();
	}

	public Ingredient getIngredientByName(String aNomIngredient) {
		throw new UnsupportedOperationException();
	}

	public List<Ingredient> getIngredientsByType(IngredientType aIngredientType) {
		throw new UnsupportedOperationException();
	}
}