package order;

import recipe.Recipe;

public class Item {
	private int quantity;
	private Recipe recipe;
	private int price;

	public Item(Recipe aRecipe, int aQuantity) {
		this.recipe = aRecipe;
		this.quantity = aQuantity;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

//This method allow us to remove or add to the quantity of existing items the quantity
// passed in parameter .We also have a boolean that allows us to know whether to add or remove quantity.
//True to say that we must add and false to say that we must remove
	public void addQuantity(int aQuantity,boolean b) throws IllegalArgumentException  {

	  if(b){

	  	 if(aQuantity >= 0){

	  	 	 this.quantity = this.quantity + aQuantity;
		 }
	  }else {

	  	  if(aQuantity >=0 & this.quantity > aQuantity){

	  	  	 this.quantity = this.quantity - aQuantity;
		  }

		  if(this.quantity < aQuantity){

			  throw new IllegalArgumentException("the quantity you need to enter must be less than the existing quantity");
		  }
	  }

	  if(aQuantity < 0){

		  throw new IllegalArgumentException("the minimum quantity of recipes to remove or  to add is 0 or more");
	  }

	}

	public void setQuantity(int aQuantite){

		 if(aQuantite >= 0){

		 	 this.quantity = aQuantite;
		 }else{

			 throw new IllegalArgumentException("the minimum quantity of recipes to remove or to add is 0 or more");
		 }
	}

	public Recipe getRecipe() {
		return this.recipe;
	}

	public double getPrice() {
		return recipe.getPrice()*quantity;
	}
}