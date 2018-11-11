package fr.unice.polytech.cod.ComportmentTesting;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.recipe.*;

import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManageRecipesStepDefs implements En {
    private Franchise franchise;
    private Store store;


    public ManageRecipesStepDefs() throws Throwable {

        //Background
        Given("^A franchise with the name \"([^\"]*)\"$", (String arg0) -> {
            franchise = new Franchise("The cookie Factory");
        });

        And("^The franchise creates a store named \"([^\"]*)\" and its taxe rate is : \"([^\"]*)\"$", (String arg0, String arg1) -> {
            franchise.addStore(arg0,Double.parseDouble(arg1));
        });


        //Creation of a recipe by the franchise
        When("^The Franchise wants to add the recipe \"([^\"]*)\" with ingredients : Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", and the price is fixed to \"([^\"]*)\"to the stores menu$",
                (String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) -> {
            Recipe recipe = new Recipe(arg0, Dough.valueOf(arg1), Flavour.valueOf(arg2), Topping.valueOf(arg3), Cooking.valueOf(arg4), Mix.valueOf(arg5), Double.parseDouble(arg6));
            franchise.addRecipeToStore(recipe);
        });

        Then("^The store \"([^\"]*)\" menu has (\\d+) recipe$", (String arg0, Integer arg1) -> {
           Optional<Store> store = franchise.getStoreByName(arg0);
           store.ifPresent(store1 -> assertTrue(store1.getMenu().getListOfAvailableRecipes().size()== arg1));
        });




        //Creation of a recipe by the store
        When("^\"([^\"]*)\" creates a recipeOfTheMonth named \"([^\"]*)\" with Dough : \"([^\"]*)\", " +
                        "Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
                (String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String  arg7) -> {
                    store = franchise.getStores().get(0);
                    store.setRecipeOfTheMonth(arg1, Dough.valueOf(arg2), Flavour.valueOf(arg3), Topping.valueOf(arg4), Cooking.valueOf(arg5), Mix.valueOf(arg6), Double.parseDouble(arg7));

                });
        Then("^The recipeOfTheMonth is now \"([^\"]*)\"$", (String arg0) -> {
            assertEquals(store.getRecipeOfTheMonth().getName(),arg0);
        });



        //Modification of price of a recipe
 /*       When("^\"([^\"]*)\" changes the price of the recipe \"([^\"]*)\" from (.+) to (.+)$", (String arg0, String arg1, String arg2, String arg3) -> {
            store = franchise.getStores().get(0);
            store.setRecipeOfTheMonth(arg1, Dough.Chocolate, Flavour.Chili, Topping.MandMs, Cooking.Chewy, Mix.Topped, Double.parseDouble(arg2));
            store.getRecipeOfTheMonth().setPrice(Double.parseDouble(arg3));
        });
        Then("^The recipeOfTheMonth's price is now (.+)$", (String arg0) -> {
            assertEquals(Double.parseDouble(arg0), store.getRecipeOfTheMonth().getPrice(),001);
        });*/


        //Removal of a recipe from the franchiseMenu
        When("^\"([^\"]*)\" wants to remove the recipe \"([^\"]*)\" from their franchiseMenu$", (String arg0, String arg1) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^The franchiseMenu is now empty$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });



    }
}
