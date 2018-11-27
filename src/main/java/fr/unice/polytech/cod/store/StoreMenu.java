package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.franchise.FranchiseMenu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.List;

public class StoreMenu implements Menu {
	private NormalRecipe monthlyRecipe;
	public FranchiseMenu franchiseMenu;

	public boolean setMonthlyRecipe(NormalRecipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteMonthlyRecipe() {
		throw new UnsupportedOperationException();
	}

	public List<Recipe> getMenu(Stock aStock) {
		throw new UnsupportedOperationException();
	}

	public boolean addRecipe(NormalRecipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteRecipe(NormalRecipe aRecipe) {
		throw new UnsupportedOperationException();
	}

	public NormalRecipe getRecipeByName(String aRecipeName) {
		throw new UnsupportedOperationException();
	}
}