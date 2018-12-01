package fr.unice.polytech.cod.recipe;


import fr.unice.polytech.cod.recipe.ingredients.Ingredient;

import java.util.List;

/**
 * last edit : 27/11/2018 par Islem
 */
public class NormalRecipe extends Recipe {
    private String name;

    public NormalRecipe(String name, double price, CookingType cookingType, MixType mixType, List<Ingredient> ingredients) {
        super(price, cookingType, mixType, CookieType.NormalRecipe, ingredients);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}