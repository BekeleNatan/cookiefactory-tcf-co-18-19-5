package fr.unice.polytech.cod.recipe.ingredients;

import fr.unice.polytech.cod.recipe.Recipe;

import java.util.ArrayList;

public class Ingredient {
	private String _name;
	private IngredientType _type;
	private int _nbUnitsThatCanBeDone;
	private double _pricePerUnit;
	private double _margin;
	public Stock _stores;
	public ArrayList<Recipe> _is_made_of_ = new ArrayList<Recipe>();

	public int addNbUnitsThatCanBeDone(int aNbUnitsToAdd) {
		throw new UnsupportedOperationException();
	}

	public int getUnitsOfCookies() {
		throw new UnsupportedOperationException();
	}
}