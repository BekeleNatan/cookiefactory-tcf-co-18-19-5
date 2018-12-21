package features.stepdefs.manageStore;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import org.json.Cookie;
import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ManageStoreMenuStepdefs implements En {

    Franchise franchise = new Franchise("COD");
    FranchiseMenu franchiseMenu = new FranchiseMenu();
    CookieFactory cookieFactory = new CookieFactory(0, 1, 0, 1, 0, 1, 2);
    Store store;


    public ManageStoreMenuStepdefs() {
        Given("^\"([^\"]*)\" a store of the franchise$", (String storeName) -> {
            franchise.addStore(storeName, franchiseMenu);
        });

        When("^the manager of the store \"([^\"]*)\" set a new monthly recipe called \"([^\"]*)\"$", (String storeName, String recipeName) -> {
            store = franchise.getStoreByName(storeName).get();
            NormalRecipe recipe = cookieFactory.createNormalRecipe(recipeName, 2, CookingType.Crunchy, MixType.Mixed, new ArrayList<>());
            store.getStoreMenu().addRecipe(recipe);
        });

        Then("^\"([^\"]*)\" is( not?)* contained in the menu of \"([^\"]*)\"$", (String doNot, String recipeName, String storeName) -> {
            store = franchise.getStoreByName(storeName).get();
            if (doNot == null) {
                assertTrue(store.getStoreMenu().getRecipeByName(recipeName).isPresent());
            } else {
                assertTrue(!store.getStoreMenu().getRecipeByName(recipeName).isPresent());
            }

        });
        When("^the manager of the store \"([^\"]*)\" deletes the monthly recipe called \"([^\"]*)\"$", (String storeName, String recipeName) -> {
            store = franchise.getStoreByName(storeName).get();
            if (store.getStoreMenu().getRecipeByName(recipeName).isPresent()) {
                Recipe recipe = store.getStoreMenu().getRecipeByName(recipeName).get();
                store.getStoreMenu().removeRecipe(recipe);
            } else {
                fail();
            }
        });
        Given("^\"([^\"]*)\" have an empty stock$", (String storeName) -> {
            store = franchise.getStoreByName(storeName).get();
            store.setStock(new Stock());
        });
        When("^We add a recipe called \"([^\"]*)\" to the menu of the store \"([^\"]*)\"$", (String recipeName, String storeName) -> {
            store = franchise.getStoreByName(storeName).get();
            // creating a recipe that contains ingredients out of stock
            List<Ingredient> listIngredientsOutOfStock = new ArrayList<>();
            listIngredientsOutOfStock.add(new Ingredient("ChocoBlanco", IngredientType.Topping, 2, 2));
            Recipe recipe = cookieFactory.createNormalRecipe(recipeName, 2, CookingType.Crunchy, MixType.Mixed, listIngredientsOutOfStock);

            store.getStoreMenu().addRecipe(recipe);
        });
        And("^\"([^\"]*)\" do( not?)* appear in the menu of \"([^\"]*)\"$", (String recipeName, String not, String storeName) -> {
            store = franchise.getStoreByName(storeName).get();
            Optional<Recipe> optionalRecipe = store.getStoreMenu().getRecipeByName(recipeName);
            if (not == null) {
                if (!optionalRecipe.isPresent()) {
                    fail();
                } else {
                    assertTrue(store.getStoreMenu().displayMenu(new Stock()).contains(optionalRecipe.get()));
                }
            }
            if (not != null) {
                if (!optionalRecipe.isPresent()) {
                    fail();
                } else {
                    assertTrue(!store.getStoreMenu().displayMenu(new Stock()).contains(optionalRecipe.get()));
                }
            }
        });
    }
}
