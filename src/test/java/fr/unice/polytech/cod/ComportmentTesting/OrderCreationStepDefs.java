package fr.unice.polytech.cod.ComportmentTesting;
import cucumber.api.java8.En;
import fr.unice.polytech.cod.FeaturesTestRunner;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.State;
import fr.unice.polytech.cod.recipe.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class OrderCreationStepDefs implements En {

    private Order order;
    private Store storeToCommand;
    Franchise franchise;
    List<Store> stores = new ArrayList<>();
    HashMap<Integer,Recipe> menu = new HashMap<>();
    List<Item> items = new ArrayList<>();

    public OrderCreationStepDefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)
        Given("^La franchise \"([^\"]*)\" avec (\\d+) magasins$",
                (String name, Integer nbrStore) -> {// besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                        franchise = new Franchise(name);
                        FeaturesTestRunner.initialiseStores(franchise,nbrStore);
        });

        When("Un client veut voir les magasins ou il peut commander$", () -> {
            stores = franchise.getStores();
        });

        Then("^Il trouve (\\d+) magasins$", (Integer nbrStore) -> {
            assertEquals((int)nbrStore ,stores.size());
        });

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

        When("^Le client commande dans la boutique (\\d+)$", (Integer idStore) -> {
            items = new ArrayList<>();
            storeToCommand = franchise.chooseStore(idStore);
        });
        And("^Le client commande (\\d+) fois le cookie (\\d+)$", (Integer nbrCookies, Integer cookieID) -> {
            addItem(nbrCookies,cookieID);
        });

        And("^Le client valide la commande$", () -> {
            order = storeToCommand.takeOrder(items,new Date(),"0623862099",false);
        });

        And("^Le client paye$", () -> {
            order.makePayement(true);
        });

        And("^Le client a un probleme de payement$", () -> {
            order.makePayement(false);
        });


        Then("Le prix est de (.+)", (String price) -> {
            assertEquals(Double.parseDouble(price),order.getPrice(),0);
        });

        And("^Le statut de la commmande est \"([^\"]*)\"$", (String status) -> {
            State state = State.valueOf(status);
            assertEquals(state,order.getState());
        });

        And("On envoie au client \"([^\"]*)\"$", (String message) -> {
            assertEquals(message,order.getCustomer().getMessage());
        });

        When("^Un client veut voir le menu des cookies proposes dans la store (\\d+)$", (Integer idStore) -> {
            Store store = franchise.chooseStore(idStore);
            menu = store.getMenu();
        });

        Then("^Il trouve (\\d+) cookies$", (Integer nbrCookies) -> {
            assertEquals((int)nbrCookies ,menu.size());
        });


    }

    private void addItem(Integer nbrCookies, Integer cookieID) {
        Recipe recipe = storeToCommand.getMenu().get(cookieID);
        Item item = new Item(recipe, nbrCookies);
        items.add(item);
    }

}
