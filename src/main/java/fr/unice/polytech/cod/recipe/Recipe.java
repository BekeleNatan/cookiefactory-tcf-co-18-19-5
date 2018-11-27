package fr.unice.polytech.cod.recipe;

import java.util.ArrayList;

public abstract class Recipe {
	private int _price;
	private CookingType _cooking;
	private MixType _mix;
	public ArrayList<Item> _is_composed_of = new ArrayList<Item>();
	public Store _creates;
	public FranchiseMenu _offers;
	public StoreMenu _offers;
	public Dough _unnamed_Dough_;
	public Topping _unnamed_Topping_;
	public Flavour _unnamed_Flavour_;
	public Flavour _is_made_of;
	public ArrayList<Ingredient> _is_made_of_ = new ArrayList<Ingredient>();

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