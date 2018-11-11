package fr.unice.polytech.cod.ComportmentTesting;

import cucumber.api.java8.En;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.unitaryTesting.WorkingHours.WorkingHours;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.State;
import fr.unice.polytech.cod.recipe.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.assertEquals;


public class OrderCreationStepDefs implements En {

    private Order order;
    private Franchise franchise;
    private Store store;
    private String phoneNumber;
    private List<Store> stores = new ArrayList<>();
    private List<Recipe> menu = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Date date = null;
    private Boolean loyaltyDiscount = false;

    public OrderCreationStepDefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)
        //Background
        Given("^A franchise with the name \"([^\"]*)\"$", (String franchiseName) -> {
            franchise = new Franchise(franchiseName);
        });


        //Creation of a recipe by the franchise
        When("^The manager of the store \"([^\"]*)\" was told by the franchise to add a recipe named " +
                "\"([^\"]*)\" to his store, this are the ingredients : Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", " +
                "Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", and the price is " +
                "fixed to \"([^\"]*)\"$", (String storeName, String recipeName, String arg2, String dough,
                                           String flavour, String topping, String cooking, String mix, String price) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                store.get().getMenu().addRecipe(recipeName, Dough.valueOf(dough), Flavour.valueOf(flavour), Topping.valueOf(topping), Cooking.valueOf(cooking), Mix.valueOf(mix), Double.parseDouble(price));
            } else {
                throw new RuntimeException();
            }
        });

        // commande
        When("^Le client commande dans la boutique \"([^\"]*)\"$", (String storeName) -> {
            Optional<Store> store = franchise.getStoreByName(storeName);
            if (store.isPresent()) {
                this.store = store.get();
            } else {
                throw new RuntimeException("Store not found");
            }
        });


        And("^Le client commande (\\d+) fois le cookie \"([^\"]*)\"$", (Integer quantity, String recipeName) -> {
            Optional<Recipe> recipe = this.store.getMenu().getRecipeByName(recipeName);
            if (recipe.isPresent()) {
                Item item = new Item(recipe.get(), quantity);
                items.add(item);
            } else {
                throw new RuntimeException("Recipe do not exist");
            }
        });

        Then("Le prix est de (.+)", (String price) -> {
            assertEquals(Double.parseDouble(price), order.getPrice(), 0);
        });

        And("^Le client met son numéro de téléphone : \"([^\"]*)\"$", (String phoneNumber) -> {
            this.phoneNumber = phoneNumber;
        });

        And("^Le client valide la commande$", () -> {
            if (date == null) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(2020, 02, 12, 12, 15, 01);
                date = cal.getTime(); // get back a Date object
            }
            order = store.takeOrder(items, date, phoneNumber, loyaltyDiscount);
        });

        And("^Le client paye$", () -> {
            order.makePayement(true, 489638445);
        });

        And("^Le statut de la commmande est \"([^\"]*)\"$", (String status) -> {
            State state = State.valueOf(status);
            assertEquals(state, order.getState());
        });

        And("^La commande est stockee$", () -> {
            assertEquals(1, store.getOrders().size());
        });

        And("Le client rentre une date passée", () -> {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(2014, 02, 12, 12, 15, 01);
            date = cal.getTime(); // get back a Date object
        });

        And("^Le client a un probleme de payement$", () -> {
            order.makePayement(false, 0);
        });

        And("^Le magasin a mis ses taxes a (.+)$", (String taxe) -> {
            store.setTaxeRate(Double.parseDouble(taxe));
            System.out.print(store.getTaxeRate());
        });

        And("^Le client a le droit a une remise fidelite$", () -> {
            loyaltyDiscount = true;
        });


        And("On envoie au client \"([^\"]*)\"$", (String message) -> {
            assertEquals(message, order.getCustomer().getMessage());
        });

        And("Le magasin (\\d+) est tout le temps ouvert$", (Integer storeId) -> {
            WorkingHours workingHours = franchise.chooseStore(storeId).getWorkingHours();
            workingHours.addOpeningFragement(DayOfWeek.MONDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.TUESDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.WEDNESDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.THURSDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.FRIDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.SATURDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
            workingHours.addOpeningFragement(DayOfWeek.SUNDAY, LocalTime.of(0, 00), LocalTime.of(23, 59));
        });

        When("^Un client veut voir le menu des cookies proposes dans la store (\\d+)$", (Integer idStore) -> {
            Store store = franchise.chooseStore(idStore);
            menu = store.getMenu().getListOfAvailableRecipes();
        });

        Then("^Il trouve (\\d+) cookies$", (Integer nbrCookies) -> {
            assertEquals((int) nbrCookies, menu.size());
        });


    }
}
