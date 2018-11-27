package fr.unice.polytech.cod.recipe;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;

import java.util.List;

public class CookieFactory {
	private int minPossibleToppings;
	private int maxPossibleToppings;
	private int minPossibleFlavour;
	private int maxPossibleFlavour;
	private int minPossibleDough;
	private int maxPossibleDough;

	public NormalRecipe createNormalRecipe(double aPrice, CookingType aCookingType, MixType aMixType, List<Ingredient> aListIngredient) {
		throw new UnsupportedOperationException();
	}

	public PersonnalizedRecipe createPersonnalizedRecipe(double aStoreMargin, CookingType aCookingType, MixType aMixType, List<Ingredient> aListIngredient) {
		throw new UnsupportedOperationException();
	}

	public boolean setNumberPossibleToppings(int aMin, int aMax) {
		throw new UnsupportedOperationException();
	}

	public boolean setNumberPossibleFlavours(int aMin, int aMax) {
		throw new UnsupportedOperationException();
	}

	public boolean setNumberPossibleDoughs(int aMin, int aMax) {
		throw new UnsupportedOperationException();
	}
}