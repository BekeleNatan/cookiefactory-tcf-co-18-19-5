package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;
import java.util.ArrayList;
import java.util.List;

public class FranchiseMenu extends Menu {
    private ArrayList<Recipe> recipes;

    FranchiseMenu() {
        recipes = new ArrayList<>();
    }

    public List<Recipe> getMenu() {
        return recipes;
    }

    public NormalRecipe getRecipeByName(String aRecipeName) {
        for()
    }
}