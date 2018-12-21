import cucumber.api.PendingException;
import cucumber.api.java8.En;
import gherkin.ast.DataTable;
import gherkin.ast.TableRow;
import helpers.TestingFailureException;
import recipe.CookieFactory;
import recipe.CookingType;
import recipe.MixType;
import recipe.Recipe;
import recipe.ingredients.Ingredient;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import recipe.ingredients.IngredientType;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CookiesCreationStepdefs implements En {
    int minTopping;
    int maxTopping;
    int minFlavour;
    int maxFlavour;
    int minDough;
    int maxDough;
    int specialMargin;
    CookieFactory cookieFactory;

    List<Ingredient> ingredientList = new ArrayList<>();

    String recipeType;
    MixType mixType;
    CookingType cookingType;
    String recipeName;

    Double recipePrice;

    Recipe recipe;

    TestingFailureException testingFailureException = new TestingFailureException();
    public CookiesCreationStepdefs() {
        Given("^a Chef that decides that we can have minimum (\\d+) and maximum (\\d+) toppings, minimum (\\d+) and maximum (\\d+) flavour, minimum (\\d+) and maximum (\\d+) dough$", (Integer minTopping, Integer maxTopping, Integer minFlavour, Integer maxFlavour, Integer minDough, Integer maxDough) -> {
            this.minTopping = minTopping;
            this.maxTopping = maxTopping;
            this.minFlavour = minFlavour;
            this.maxFlavour = maxFlavour;
            this.minDough = minDough;
            this.maxDough = maxDough;
        });
        And("^the accountable decides that we have to make a special margin of (\\d+) for personalized cookies$", (Integer specialMargin) -> {
            this.specialMargin = specialMargin;
        });
        And("^now we have our rules$", () -> {
            cookieFactory = new CookieFactory(minDough, maxDough, minFlavour, maxFlavour, minTopping, maxTopping, specialMargin);
        });

        When("^we compose a \"([^\"]*)\" recipe called \"([^\"]*)\" with mix type \"([^\"]*)\", cooking type \"([^\"]*)\"$", (String recipeType, String recipeName, String mixType, String cookingType) -> {
            this.recipeName = recipeName;
            this.recipeType = recipeType;
            this.mixType = MixType.valueOf(mixType);
            this.cookingType = CookingType.valueOf(cookingType);
        });
        And("^the price of the recipe is set to (\\d+.\\d+)$", (String recipePrice) -> {
            this.recipePrice = Double.parseDouble(recipePrice);
        });
        And("^we add the ingredient called \"([^\"]*)\" of type \"([^\"]*)\", the price of the ingredient is (\\d+.\\d+) and the price margin (\\d+.\\d+)$", (String ingredientName, String ingredientType, String price, String margin) -> {
            Ingredient ingredient = new Ingredient(ingredientName, IngredientType.valueOf(ingredientType), Double.parseDouble(price), Double.parseDouble(margin));
            this.ingredientList.add(ingredient);
        });
        And("^we make our recipe$", () -> {
            try {
                if (this.recipeType.equals("Normal")) {
                    this.recipe = cookieFactory.createNormalRecipe(this.recipeName, this.recipePrice, cookingType, mixType, ingredientList);
                } else if (this.recipeType.equals("Personalized")) {
                    this.recipe = cookieFactory.createPersonnalizedRecipe(cookingType, mixType, ingredientList);
                }
            } catch (IllegalArgumentException e) {
                testingFailureException.addIllegalArgumentException(e);
            }
        });
        Then("^our recipe costs (\\d+.\\d+)$", (String price) -> {
            assertEquals(Double.parseDouble(price), recipe.getPrice(), 0.0000001);
        });
        And("^a failure is beign expected$", () -> {
            testingFailureException = new TestingFailureException();
            testingFailureException.expectException();
        });
        Then("^we fail making our recipe$", () -> {
            assertTrue(testingFailureException.aFailureIsDetected());
        });

    }
}
