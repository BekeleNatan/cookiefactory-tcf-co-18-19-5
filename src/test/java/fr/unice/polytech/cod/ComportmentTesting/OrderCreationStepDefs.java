package fr.unice.polytech.cod.ComportmentTesting;
import com.sun.org.apache.xpath.internal.operations.Bool;
import cucumber.api.java8.En;
import fr.unice.polytech.cod.FeaturesTestRunner;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.WorkingHours.OpeningFragment;
import fr.unice.polytech.cod.WorkingHours.WorkingHours;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.State;
import fr.unice.polytech.cod.recipe.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class OrderCreationStepDefs implements En {

    private Order order;
    private Store storeToCommand;
    Franchise franchise;
    List<Store> stores = new ArrayList<>();
    List<Recipe> menu = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    Date date = null;
    Boolean loyaltyDiscount = false;

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
            addItem(nbrCookies,cookieID-1);
        });

        And("^Le client valide la commande$", () -> {
            if(date==null) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(2020, 02, 12, 12, 15, 01);
                date = cal.getTime(); // get back a Date object
            }
            order = storeToCommand.takeOrder(items,date,"0623862099",loyaltyDiscount);
        });

        And("^Le client paye$", () -> {
            order.makePayement(true);
        });
        And("Le client rentre une date passée", () -> {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(2014, 02, 12, 12, 15, 01);
            date = cal.getTime(); // get back a Date object
        });

        And("^Le client a un probleme de payement$", () -> {
            order.makePayement(false);
        });

        And("^Le magasin a mis ses taxes a (.+)$", (String taxe) -> {
            storeToCommand.setTaxeRate(Double.parseDouble(taxe));
            System.out.print(storeToCommand.getTaxeRate());
        });

        And("^Le client a le droit a une remise fidelite$", () -> {
            loyaltyDiscount = true;
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

        And("Le magasin (\\d+) est tout le temps ouvert$", (Integer storeId) -> {
            WorkingHours workingHours = franchise.chooseStore(storeId).getWorkingHours();
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
            workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
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
