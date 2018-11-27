package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.Recipe;

public interface Menu {
    public boolean addRecipe(Recipe aRecipe);

    public boolean deleteRecipe(Recipe aRecipe);

    public Recipe getRecipeByName(String aRecipeName);
}