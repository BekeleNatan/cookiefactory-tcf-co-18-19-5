package features.stepdefs.manageRecipes;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;

import static org.junit.Assert.assertEquals;

public class ManageIngredientsStepdefs implements En {
    Ingredient ingredient;
    public ManageIngredientsStepdefs() {
        Given("^an ingredient called \"([^\"]*)\" of type \"([^\"]*)\", the price of the ingredient is (\\d+.\\d+) and the price margin (\\d+.\\d+)$", (String ingredientName, String ingredientType, String price, String margin) -> {
            this.ingredient = new Ingredient(ingredientName, IngredientType.valueOf(ingredientType), Double.parseDouble(price), Double.parseDouble(margin));
        });
        Then("^the total price of the ingredient is (\\d+.\\d+)$", (String totalPrice) -> {
            assertEquals(Double.parseDouble(totalPrice), ingredient.getTotalPrice(), 0.000001);
        });
        When("^the manager changes the price exclusive of taxes of the ingredient to (\\d+.\\d+)$", (String priceExclusiveOfTaxes) -> {
            ingredient.setIngredientPrice(Double.parseDouble(priceExclusiveOfTaxes));
        });

        When("^the manager changes the price margin of the ingredient to (\\d+.\\d+)$", (String marginPrice) -> {
            ingredient.setPriceMargin(Double.parseDouble(marginPrice));
        });
    }
}
