package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.StoreMenu;

import java.util.ArrayList;
import java.util.List;

public class FranchiseMenu implements Menu {
	public ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	@Override
	public List<NormalRecipe> getMenu(Stock aStock) {
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