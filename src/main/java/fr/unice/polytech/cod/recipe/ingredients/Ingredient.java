package fr.unice.polytech.cod.recipe.ingredients;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;

import java.util.ArrayList;

public class Ingredient {
    private String name;
    private IngredientType type;
    private double pricePerUnit;

    public Ingredient(String name, IngredientType ingredientType, double pricePerUnit) {
        this.name = name;
        this.type = ingredientType;
        this.pricePerUnit = pricePerUnit;
    }


    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}