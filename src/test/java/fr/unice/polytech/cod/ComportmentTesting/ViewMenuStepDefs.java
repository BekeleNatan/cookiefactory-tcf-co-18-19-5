package fr.unice.polytech.cod.ComportmentTesting;
import cucumber.api.java8.En;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.recipe.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ViewMenuStepDefs implements En {

    Franchise franchise = new Franchise();
    List<Store> stores = new ArrayList<>();
    List<Recipe> menu = new ArrayList<>();

    public ViewMenuStepDefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)
        Given("^Une franchise contenant (\\d+) magasins$",
                (Integer nbrStore) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                        FeaturesTest.initialiseStores(franchise,nbrStore));

        Given("^Le store (\\d+) contient une recette \"([^\"]*)\" avec Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
                (Integer nbrStore, String recipeName, String dough, String flavour, String topping, String cooking, String mix, String price) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    Store store = franchise.chooseStore(nbrStore);
                    store.setRecipeOfTheMonth(recipeName,Dough.valueOf(dough),Flavour.valueOf(flavour),Topping.valueOf(topping),Cooking.valueOf(cooking),Mix.valueOf(mix), Double.parseDouble(price));
                });

        And("La franchise contient une recette \"([^\"]*)\" avec Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
                (String recipeName, String dough, String flavour, String topping, String cooking, String mix, String price) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    franchise.addRecipe(recipeName,Dough.valueOf(dough),Flavour.valueOf(flavour),Topping.valueOf(topping),Cooking.valueOf(cooking),Mix.valueOf(mix),Double.parseDouble(price));
                });

        When("^Un client veut voir le menu des cookies proposes dans la store (\\d+)$", (Integer idStore) -> {
            Store store = franchise.chooseStore(idStore);
            menu = store.getMenu();
        });

        Then("^Il trouve (\\d+) cookies$", (Integer nbrCookies) -> {
            assertEquals((int)nbrCookies ,menu.size());
        });
    }

}
