package fr.unice.polytech.cod.ComportmentTesting;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.recipe.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManageRecipesStepDefs implements En {
    private Franchise franchise;
    private Store store;


    public ManageRecipesStepDefs() throws Throwable {

        //Creation of a recipe by the store
        When("^\"([^\"]*)\" creates a recipeOfTheMonth named \"([^\"]*)\" with Dough : \"([^\"]*)\", " +
                        "Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
                (String storeName, String monthlyRecipeName, String dough, String flavour, String topping, String cooking, String mix, String price) -> {
                    Optional<Store> store = franchise.getStoreByName(storeName);
                    if (store.isPresent()) {
                        store.get().setRecipeOfTheMonth(monthlyRecipeName, Dough.valueOf(dough), Flavour.valueOf(flavour), Topping.valueOf(topping), Cooking.valueOf(cooking), Mix.valueOf(mix), Double.parseDouble(price));
                    } else {
                        throw new RuntimeException("Store don't exists");
                    }
                });

        Then("^The recipeOfTheMonth is now \"([^\"]*)\"$", (String monthlyRecipeName) -> {
            assertEquals(store.getRecipeOfTheMonth().getName(), monthlyRecipeName);
        });

        Then("^The menu of the store \"([^\"]*)\" is now empty$", (String storeName) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                assertEquals(0, store.get().getMenu().getListOfAvailableRecipes().size());
            } else {
                throw new RuntimeException("Store don't exists");
            }
        });

        // ok ------------------------

        //Removal of a recipe from the franchiseMenu
        When("^\"([^\"]*)\" wants to remove the recipe \"([^\"]*)\" from their franchiseMenu$", (String arg0, String arg1) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });


        And("^The manager of \"([^\"]*)\" want to change the price of the \"([^\"]*)\" to (\\d+)$", (String storeName, String recipeName, Integer newPrice) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                Optional<Recipe> recipe = store.get().getMenu().getRecipeByName(recipeName);
                if (recipe.isPresent()) {
                    recipe.get().setPrice(Double.valueOf(newPrice));
                } else {
                    throw new RuntimeException("The recipe do not exists");
                }
            } else {
                throw new RuntimeException("Store don't exists");
            }
        });

        Then("^The price of the recipe \"([^\"]*)\" in the store \"([^\"]*)\" is (\\d+)$", (String recipeName, String storeName, Integer price) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                Optional<Recipe> recipe = store.get().getMenu().getRecipeByName(recipeName);
                if (recipe.isPresent()) {
                    assertTrue(price == recipe.get().getPrice());
                } else {
                    throw new RuntimeException("The recipe does'nt exists");
                }
            } else {
                throw new RuntimeException("Store doesn't exists");
            }
        });
        Then("^The store \"([^\"]*)\" menu has (\\d+) recipe(s)$", (String storeName, Integer nbRecipes) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                assertTrue(nbRecipes == store.get().getMenu().getListOfAvailableRecipes().size());
            } else {
                throw new RuntimeException("Store don't exists");
            }
        });

    }
}
