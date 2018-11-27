package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.store.Stock;

import java.util.List;

public interface Menu {

    public List<NormalRecipe> getMenu(Stock aStock);

    public boolean addRecipe(NormalRecipe aRecipe);

    public boolean deleteRecipe(NormalRecipe aRecipe);

    public NormalRecipe getRecipeByName(String aRecipeName);
}