package fr.unice.polytech.cod.recipe;

public class CookieFactory {
	private int _minPossibleToppings;
	private int _maxPossibleToppings;
	private int _minPossibleFlavour;
	private int _maxPossibleFlavour;
	private int _minPossibleDough;
	private int _maxPossibleDough;
	public Recipe _creates;

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