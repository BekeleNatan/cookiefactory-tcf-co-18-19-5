package recipe.ingredients;

import java.util.Objects;

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
        return this.name;
    }

    public IngredientType getType() {
        return this.type;
    }

    public double getPricePerUnit() {
        return (this.ingredientPrice + this.priceMargin);
    }

    public double getPriceMargin() {

         return this.priceMargin;
    }

    public double getTotalPrice(){
        return this.priceMargin + this.ingredientPrice;
    }

    public void setIngredientPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public void setPriceMargin(double priceMargin) {
        this.priceMargin = priceMargin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.ingredientPrice, ingredientPrice) == 0 &&
                Double.compare(that.priceMargin, priceMargin) == 0 &&
                Objects.equals(name, that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, ingredientPrice, priceMargin);
    }
}