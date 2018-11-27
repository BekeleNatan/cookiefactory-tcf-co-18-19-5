package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.Recipe;
import java.util.List;

abstract public class Menu {
    private List<Recipe> recipes;

    public boolean addRecipe(Recipe aRecipe) {
        recipes.add(aRecipe);
        return true;
    }

    public boolean deleteRecipe(Recipe aRecipe) {
        recipes.remove(aRecipe);
        return true;
    }
}