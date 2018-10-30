package fr.unice.polytech.cod.ComportmentTesting;

import cucumber.api.PendingException;
import cucumber.api.java8.En;

public class ManageRecipesStepDefs implements En {
    public ManageRecipesStepDefs() {
        Given("^A franchise with the name \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^The franchiseMenu is empty$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^The franchise owns a store named \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^\"([^\"]*)\" creates a recipe named \"([^\"]*)\" with Dough :" +
                " \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (\\d+)\\.(\\d+)$",
                (String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, Integer arg7, Integer arg8) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^The franchiseMenu has one recipe$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^The recipe name is \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^\"([^\"]*)\" creates a recipeOfTheMonth named \"([^\"]*)\" with Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\"," +
                " Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (\\d+)\\.(\\d+)$",
                (String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, Integer arg7, Integer arg8) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^The recipeOfTheMonth is now \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^\"([^\"]*)\" changes the price of the recipe \"([^\"]*)\" from (\\d+)\\.(\\d+) to (\\d+)$",
                (String arg0, String arg1, Integer arg2, Integer arg3, Integer arg4) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^The recipeOfTheMonth's price is now (\\d+)$", (Integer arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
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
