package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.franchise.FranchiseMenu;
import fr.unice.polytech.cod.recipe.Recipe;

public class StoreMenu implements Menu {
	private Recipe _monthlyRecipe;
	public FranchiseMenu _completes;
	public Store _edit_and_show;
	public Recipe _offers;

	public boolean setMonthlyRecipe(Recipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteMonthlyRecipe() {
		throw new UnsupportedOperationException();
	}

	public void getMenu(Stock aStock) {
		throw new UnsupportedOperationException();
	}

	public boolean addRecipe(Recipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteRecipe(Recipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public Recipe getRecipeByName(String aRecipeName) {
		throw new UnsupportedOperationException();
	}
}