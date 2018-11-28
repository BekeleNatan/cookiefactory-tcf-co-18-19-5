package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.CookieType;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class Menu {
    private List<Recipe> recipes;

    public Menu(){
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe aRecipe) {
        recipes.add(aRecipe);
    }

    public void removeRecipe(Recipe aRecipe) {
        recipes.remove(aRecipe);
    }

    public List<Recipe> getRecipes(){
        return this.recipes;
    }

    public Optional<Recipe> getRecipeByName(String aRecipeName){
        Recipe returnRecipe = null;
        for(Recipe r : recipes){
            if(r.getCookieType().equals(CookieType.NormalRecipe)){
                if(((NormalRecipe)r).getName().equals(aRecipeName)){
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();
    }
}