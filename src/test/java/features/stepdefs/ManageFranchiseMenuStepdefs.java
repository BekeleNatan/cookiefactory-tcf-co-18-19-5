package features.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManageFranchiseMenuStepdefs implements En {

    Franchise franchise;
    FranchiseMenu franchiseMenu = new FranchiseMenu();
    CookieFactory cookieFactory = new CookieFactory(0,3,0,1,0,3, 2);
    Stock stock = new Stock();

    public ManageFranchiseMenuStepdefs() {

        // background
        Given("^\"([^\"]*)\", a franchise$", (String franchiseName) -> {
            franchise = new Franchise(franchiseName);
        });
        And("^a store \"([^\"]*)\" of the franchise \"([^\"]*)\"$", (String storeName, String franchiseName) -> {
            franchise.addStore(storeName,franchiseMenu);
        });


        When("^a franchise responsible adds a new normal recipe called \"([^\"]*)\" to the general menu$", (String recipeName) -> {
            Recipe newRecipe = cookieFactory.createNormalRecipe(recipeName, 2, CookingType.Crunchy, MixType.Mixed, new ArrayList<>());
            franchiseMenu.getMenu().add(newRecipe);
        });
        Then("^\"([^\"]*)\" is( not?)* available in the menu of the store \"([^\"]*)\"$", (String not, String recipeName, String storeName) -> {
            Store store = franchise.getStoreByName(storeName).get();
            Optional<Recipe> recipe = store.getStoreMenu().getRecipeByName(recipeName);
            if(not == null){
                assertTrue(recipe.isPresent());
            }
            else {
                assertFalse(recipe.isPresent());
            }
        });
        When("^a franchise responsible delete a recipe called \"([^\"]*)\" from the general menu$", (String recipeName) -> {
            Recipe recipe = franchiseMenu.getRecipeByName(recipeName).get();
            franchiseMenu.removeRecipe(recipe);
        });

    }
}
