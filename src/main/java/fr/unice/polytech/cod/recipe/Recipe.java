package fr.unice.polytech.cod.recipe;

public class Recipe {

    private String name;
    private Dough dough;
    private Flavour flavours;
    private Topping topping;
    private Cooking cooking;
    private Mix mix;
    private double price;

    public Recipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price) {
        name = recipeName;
        this.dough = dough;
        this.flavours = flavours;
        this.topping = topping;
        this.cooking = cooking;
        this.mix = mix;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
}