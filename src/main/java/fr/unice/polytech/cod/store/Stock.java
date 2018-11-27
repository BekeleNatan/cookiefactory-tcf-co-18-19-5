package fr.unice.polytech.cod.store;

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

		boolean isPossible = false;

		if(aIngredient!= null & quantity > 0){

			 int oldQuantity = this.ingredients.get(aIngredient);

			 int newQuantite =  oldQuantity - quantity ;

			 if(newQuantite >= 0 ) {

			 	  this.ingredients.replace(aIngredient,newQuantite);

			 	  isPossible = true;
			 }else{

			 	System.out.println("Le stock est insuffisant pour satisfaire cette commande");
			 	 isPossible = false;
			 }


		     if(newQuantite == 0 ){

		     	System.out.println("Vous venez d'épuiser cet ingredient.Penser à vous approvisionner");
			 }

			 if(newQuantite < 25) {

			 	System.out.println("Cet ingrédient tand à s'épuiser.Penser à vous approvisionner");
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