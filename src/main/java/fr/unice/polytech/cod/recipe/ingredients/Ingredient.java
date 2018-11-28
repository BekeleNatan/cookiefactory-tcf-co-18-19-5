package fr.unice.polytech.cod.recipe.ingredients;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Stock;

import java.util.ArrayList;

public class Ingredient {
    private String name;
    private IngredientType type;
    private double ingredientPrice;
    private double priceMargin;

    public Ingredient(String name, IngredientType ingredientType, double ingredientPrice, double priceMargin) {
        this.name = name;
        this.type = ingredientType;
        this.ingredientPrice = ingredientPrice;
        this.priceMargin = priceMargin;
    }

        public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

    public double getPricePerUnit() {
        return (ingredientPrice + priceMargin);
    }

    public void setIngredientPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public void setPriceMargin(double priceMargin) {
        this.priceMargin = priceMargin;
    }
}