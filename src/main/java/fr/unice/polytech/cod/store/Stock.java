package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stock {
	public HashMap<Ingredient,Integer> ingredients = new HashMap<>();

	public void addIngredient(Ingredient aIngredient, int quantity) {

		if(aIngredient != null & quantity > 0){

			 this.ingredients.put(aIngredient,quantity);
		}
	}

	public  boolean removeIngredient(Ingredient aIngredient, int quantity) {

		if(isInStock(aIngredient,quantity)) {

			suppressIngredient(aIngredient, quantity);

			alertStockLow(aIngredient);

			return true;

		}
		return false;
	}

	private void alertStockLow(Ingredient aIngredient) {

		int quantity = this.ingredients.get(aIngredient);

		if(quantity == 0 ){

			System.out.println("Vous venez d'épuiser cet ingredient.Penser à vous approvisionner");
		}

		if(quantity < 25) {

			System.out.println("Cet ingrédient tand à s'épuiser.Penser à vous approvisionner");
		}
	}

	public boolean removeIngredient(Recipe recipe, int quantity){
		List<Ingredient> ingredients = recipe.getIngredients();

		if(canDoRecipe(recipe,quantity)){

			for (Ingredient ingredient : ingredients) {

				suppressIngredient(ingredient, quantity);

				alertStockLow(ingredient);
			}

			return true;
		}
		return false;
	}

	private void suppressIngredient(Ingredient ingredient, int quantity) {

		int oldQuantity = this.ingredients.get(ingredient);

		int newQuantite =  oldQuantity - quantity ;

		if(newQuantite<0){throw new UnsupportedOperationException();}

		this.ingredients.replace(ingredient,newQuantite);
	}

	private boolean canDoRecipe(Recipe recipe, int quantity){

		List<Ingredient> ingredients = recipe.getIngredients();

		boolean canDoRecipe = true;

		for (Ingredient ingredient : ingredients) {

			if(!isInStock(ingredient,quantity)){

				canDoRecipe = false;
			}
		}
		return canDoRecipe;
	}

	private boolean isInStock(Ingredient ingredient, int quantity) {
		boolean isPossible = false;

		if(ingredient!= null & quantity > 0) {

			int oldQuantity = this.ingredients.get(ingredient);

			int newQuantite = oldQuantity - quantity;

			if (newQuantite >= 0) {

				isPossible = true;
			} else {

				System.out.println("Le stock est insuffisant pour satisfaire cette commande");
				isPossible = false;
			}
		}
		return isPossible;
	}

	public Ingredient getIngredientByName(String aNomIngredient) {

		Ingredient ingredientObject = null;

		for (Ingredient ingredient  : this.ingredients.keySet()){

			 if(ingredient.getName().equals(aNomIngredient)){

			 	 ingredientObject = ingredient;
			 }
		}

		return ingredientObject;
	}

	public List<Ingredient> getIngredientsByType(IngredientType aIngredientType) {

		List<Ingredient> ingredientByType = new ArrayList<>();
		for (Ingredient ingredient  : this.ingredients.keySet()){

			if(ingredient.getType().equals(aIngredientType)){

				ingredientByType.add(ingredient);
			}
		}

		return ingredientByType;
	}
}