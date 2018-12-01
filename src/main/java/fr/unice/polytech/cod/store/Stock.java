package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;

import java.util.*;

public class Stock {

    Map<Ingredient, Integer> ingredients;

    public Stock() {
        ingredients = new HashMap<>();
    }

    Stock(HashMap<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient aIngredient, int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("the minimum quantity of ingredients to add is 0 or more");
        } else {
            if (this.ingredients.entrySet().isEmpty() || this.ingredients.containsKey(aIngredient)) {
                ingredients.put(aIngredient, quantity);
            } else {
                this.ingredients.put(aIngredient, this.ingredients.get(aIngredient) + quantity);
            }
        }
    }

    public void removeIngredient(Ingredient aIngredient, int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("the minimum quantity of ingredients to remove is 0 or more");
        } else {
            List<Ingredient> ingredientsToRemove = new ArrayList<>();
            for (Map.Entry<Ingredient, Integer> entry : this.ingredients.entrySet()) {
                if (entry.getKey().equals(aIngredient)) {
                    Integer newValue = entry.getValue() - quantity;
                    if (newValue <= 0) {
                        ingredientsToRemove.add(entry.getKey());
                    } else {
                        entry.setValue(newValue);
                    }
                }
            }
            for (Ingredient ingredient : ingredientsToRemove) {
                this.ingredients.remove(ingredient);
            }
        }
    }

    public Optional<Ingredient> getIngredientByName(String aNomIngredient) {

        Ingredient ingredientObject = null;

        for (Ingredient ingredient : this.ingredients.keySet()) {

            if (ingredient.getName().equals(aNomIngredient)) {
                return Optional.of(ingredient);
            }
        }

        return Optional.empty();
    }


    public List<Ingredient> getIngredientsByType(IngredientType aIngredientType) {
        List<Ingredient> ingredientByType = new ArrayList<>();
        for (Ingredient ingredient : this.ingredients.keySet()) {
            if (ingredient.getType().equals(aIngredientType)) {
                ingredientByType.add(ingredient);
            }
        }
        return ingredientByType;
    }

    Integer getQuantity(Ingredient ingredient) {
        Optional<Ingredient> optionalIngredient = getIngredientByName(ingredient.getName());
        if (optionalIngredient.isPresent()) {
            return ingredients.get(optionalIngredient.get());
        } else {
            return 0;
        }
    }
}