package fr.unice.polytech.cod.recipe;

public class Recipe {

    private String name;
    private Dough dough;
    private Flavour flavours;
    private Topping topping;
    private Cooking cooking;
    private Mix mix;
    private int price;

    public Recipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix) {
        name = recipeName;
        this.dough = dough;
        this.flavours = flavours;
        this.topping = topping;
        this.cooking = cooking;
        this.mix = mix;
    }

    public int getPrice() {
        return this.price;
    }

    /**
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

}