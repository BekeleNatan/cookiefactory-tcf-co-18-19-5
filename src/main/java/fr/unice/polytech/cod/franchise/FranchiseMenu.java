package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.StoreMenu;

import java.util.ArrayList;

public class FranchiseMenu implements Menu {
	public ArrayList<StoreMenu> _completes = new ArrayList<StoreMenu>();
	public ArrayList<Recipe> _offers = new ArrayList<Recipe>();

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