package features.stepdefs.manageStore;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import helpers.TestingFailureException;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManageIngredientsInStockStepdefs implements En {
    private Stock stock;
    private TestingFailureException testingFailureException = new TestingFailureException();

    public ManageIngredientsInStockStepdefs() {
        Given("^an empty stock of a store$", () -> {
            stock = new Stock();
        });
        And("^we add (\\d+) ingredient called \"([^\"]*)\" of type \"([^\"]*)\", the price of the ingredient is (\\d+.\\d+) and the price margin (\\d+.\\d+) to the stock$", (Integer quantity, String ingredientName, String ingredientType, String ingredientPrice, String priceMargin) -> {
            Ingredient ingredient = new Ingredient(ingredientName, IngredientType.valueOf(ingredientType), Double.parseDouble(ingredientPrice), Double.parseDouble(priceMargin));
            this.stock.addIngredient(ingredient, quantity);
        });
        Then("^the quantity of \"([^\"]*)\" is (\\d+)$", (String ingredientName, Integer quantity) -> {
            Ingredient ingredient = stock.getIngredientByName(ingredientName).get();
            assertEquals(quantity.intValue() , stock.getQuantity(ingredient));

        });
        When("^we remove (\\d+) ingredient called \"([^\"]*)\"$", (Integer quantity, String ingredientName) -> {
            Ingredient ingredient = stock.getIngredientByName(ingredientName).get();
            stock.removeIngredient(ingredient, quantity);
        });

        When("^we expect a failure$", () -> {
            testingFailureException = new TestingFailureException();
            testingFailureException.expectException();
        });

        Then("^we have a failure$", () -> {
            assertTrue(testingFailureException.aFailureIsDetected());
        });
        Then("^the ingredient \"([^\"]*)\" do( not?)* exist in the stock$", (String ingredientName, String not) -> {
            if(not == null){
                assertTrue(stock.getIngredientByName(ingredientName).isPresent());
            } else {
                assertTrue(!stock.getIngredientByName(ingredientName).isPresent());
            }
        });


    }
}
