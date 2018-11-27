package fr.unice.polytech.cod.recipe.ingredients;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;

import java.util.ArrayList;

public class Ingredient {
	private String name;
	private IngredientType type;
	private double pricePerUnit;
	private double margin;

	public int addNbUnitsThatCanBeDone(int aNbUnitsToAdd) {
		throw new UnsupportedOperationException();
	}

	public int getUnitsOfCookies() {
		throw new UnsupportedOperationException();
	}
}