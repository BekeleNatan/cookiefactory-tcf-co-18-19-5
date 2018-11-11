package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu {
    private List<Recipe> listOfAvailableRecipes ;

    public Menu(){
        listOfAvailableRecipes = new ArrayList<>();
    }

    public Recipe addRecipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, Double price) {
        Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix, price);
        listOfAvailableRecipes.add(recipe);
        return recipe;
    }


    public List<Recipe> getListOfAvailableRecipes() {
        return listOfAvailableRecipes;
    }

    public Optional<Recipe> getRecipeByName(String name) {
        return getListOfAvailableRecipes().stream().filter(recipe -> recipe.getName().equals(name)).findFirst();
    }

}


