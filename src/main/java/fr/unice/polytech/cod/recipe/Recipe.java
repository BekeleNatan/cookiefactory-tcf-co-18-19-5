package fr.unice.polytech.cod.recipe;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;

import java.util.List;

/**
 * last edit : 27/11/2018 par Islem
 */
public abstract class Recipe {
    private double price;
    private CookingType cookingType;
    private MixType mixType;
    private CookieType cookieType;
    private List<Ingredient> ingredients;

    // Define a constructor in the abstract class which sets the field so that the concrete implementations
    // are per the specification required to call/override the constructor. When you extend Recipe,
    // the class won't compile until you add a constructor which calls super(args..).
    // todo Recipe : we can add null checks in the constructor of the abstract class to make it more robust.
    Recipe(double price, CookingType cookingType, MixType mixType, CookieType cookieType, List<Ingredient> ingredients){
        this.price = price;
        this.cookingType = cookingType;
        this.mixType = mixType;
        this.cookieType = cookieType;
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return this.price;
    }

    public CookingType getCookingType() {
        return this.cookingType;
    }

    public MixType getMixType() {
        return this.mixType;
    }

    public CookieType getCookieType() {
        return this.cookieType;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public boolean setPrice(double price) {
        this.price = price;
        return true;
    }

}