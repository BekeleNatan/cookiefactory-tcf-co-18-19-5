package fr.unice.polytech.cod;

import recipe.CookieType;
import recipe.NormalRecipe;
import recipe.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class Menu {
    private List<Recipe> recipes;

    public Menu(){
        recipes = new ArrayList<>();
    }

    public List<Recipe> getRecipes(){
        return this.recipes;
    }


    public void addRecipe(Recipe aRecipe) {
        recipes.add(aRecipe);
    }

    public void removeRecipe(Recipe aRecipe) {
        recipes.remove(aRecipe);
    }

    public Optional<Recipe> getRecipeByName(String aRecipeName){
        Recipe returnRecipe = null;
        List<Recipe> recipes = this.getRecipes();
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