package recipe;

import recipe.ingredients.Ingredient;

import java.util.List;

public class CookieFactory {
    private int minPossibleToppings;
    private int maxPossibleToppings;
    private int minPossibleFlavour;
    private int maxPossibleFlavour;
    private int minPossibleDough;
    private int maxPossibleDough;
    private double specialMargin;

    public CookieFactory(int minPossibleDough, int maxPossibleDough, int minPossibleFlavour, int maxPossibleFlavour, int minPossibleToppings, int maxPossibleToppings, double specialMargin) {
        this.specialMargin = specialMargin;
        this.minPossibleDough = minPossibleDough;
        this.maxPossibleDough = maxPossibleDough;
        this.minPossibleFlavour = minPossibleFlavour;
        this.maxPossibleFlavour = maxPossibleFlavour;
        this.minPossibleToppings = minPossibleToppings;
        this.maxPossibleToppings = maxPossibleToppings;
    }

    public NormalRecipe createNormalRecipe(String name, double aPrice, CookingType aCookingType, MixType aMixType, List<Ingredient> aListIngredient) throws IllegalArgumentException {
        verifyIngredients(aListIngredient);
        return new NormalRecipe(name, aPrice, aCookingType, aMixType, aListIngredient);
    }

    public PersonnalizedRecipe createPersonnalizedRecipe(CookingType aCookingType, MixType aMixType, List<Ingredient> aListIngredient) throws IllegalArgumentException {
        verifyIngredients(aListIngredient);

        // processing the price
        double personnalizedRecipePrice = 0;
        for (Ingredient ingredient : aListIngredient) {
            personnalizedRecipePrice += ingredient.getPricePerUnit();
        }
        personnalizedRecipePrice += specialMargin;


        return new PersonnalizedRecipe(personnalizedRecipePrice, aCookingType, aMixType, aListIngredient);
    }

    // procedure to check if the ingredients respect the rules
    private void verifyIngredients(List<Ingredient> aListIngredient) {
        int nbOfToppings = 0;
        int nbOfFlavour = 0;
        int nbOfDough = 0;
        for (Ingredient ingredient : aListIngredient) {
            switch (ingredient.getType()) {
                case Dough:
                    nbOfDough++;
                    if (nbOfDough > maxPossibleDough) {
                        throw new IllegalArgumentException("You surpass the maximum of possible Doughs");
                    }
                    break;
                case Flavour:
                    nbOfFlavour++;
                    if (nbOfFlavour > maxPossibleFlavour) {
                        throw new IllegalArgumentException("You surpass the maximum of possible Flavours");
                    }
                    break;
                case Topping:
                    nbOfToppings++;
                    if (nbOfToppings > maxPossibleToppings) {
                        throw new IllegalArgumentException("You surpass the maximum of possible Toppings");
                    }
                    break;
            }
        }

        if (nbOfDough < minPossibleDough) {
            throw new IllegalArgumentException("You have lower quantity than possible Doughs");
        } else if (nbOfFlavour < minPossibleFlavour) {
            throw new IllegalArgumentException("You have lower quantity than possible Flavours");
        } else if (nbOfToppings < minPossibleToppings) {
            throw new IllegalArgumentException("You have lower quantity than possible Toppings");
        }
    }


    // procedures to edit the rules
    public void setNumberPossibleToppings(int aMin, int aMax) {
        this.minPossibleToppings = aMin;
        this.maxPossibleToppings = aMax;
    }

    public void setNumberPossibleFlavours(int aMin, int aMax) {
        this.minPossibleFlavour = aMin;
        this.maxPossibleFlavour = aMax;
    }

    public void setNumberPossibleDoughs(int aMin, int aMax) {
        this.minPossibleDough = aMin;
        this.maxPossibleDough = aMax;
    }

    public void setSpecialMargin(double specialMargin) {
        this.specialMargin = specialMargin;
    }
}