package fr.unice.polytech.cod.recipe;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;

import java.util.List;

/**
 * last edit : 27/11/2018 par Islem
 */
public class PersonnalizedRecipe extends Recipe {
    PersonnalizedRecipe(double price, CookingType cookingType, MixType mixType, List<Ingredient> ingredients) {
        super(price, cookingType, mixType, CookieType.personnalizedRecipe, ingredients);
    }
}