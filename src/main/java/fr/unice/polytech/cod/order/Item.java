package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.recipe.Recipe;

public class Item {

    private int quantity;
    private double price;
    private Recipe recipe;

    /**
     * @param recipe
     * @param quantity
     */
    public Item(Recipe recipe, int quantity) {
        this.recipe = recipe;
        this.quantity = quantity;
        computePrice();
    }

    private void computePrice() {
        price = recipe.getPrice() * quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        computePrice();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Recipe getRecipe() {
        // TODO - implement Item.getRecipe
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        return this.price;
    }

}