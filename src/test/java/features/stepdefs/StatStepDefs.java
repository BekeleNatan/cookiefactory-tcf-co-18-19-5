package features.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import order.Order;
import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import stat.CookiesByDay;
import stat.Stat;

import stat.SumOfCookieType;
import store.Stock;
import store.Store;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StatStepDefs implements En {

    Store store;
    Store store2;
    Franchise franchise;
    private FranchiseMenu franchiseMenu;
    CookieFactory cookieFactory;
    private List<Ingredient> ingredients = new ArrayList<>();
    Order order;
    Stat stat ;

    public StatStepDefs() {
        franchiseMenu = new FranchiseMenu();
        cookieFactory = new CookieFactory(0, 10, 0, 10, 0, 10, 1);
        Stock stock = new Stock();
        Ingredient mnms_topping = new Ingredient("MandMs", IngredientType.Topping, 1.5, 1);
        ingredients.add(mnms_topping);
        Ingredient plain_dough = new Ingredient("Plain", IngredientType.Dough, 1, 0.2);
        ingredients.add(plain_dough);
        stock.addIngredient(mnms_topping, 1000);
        stock.addIngredient(plain_dough, 1500);
        franchiseMenu.addRecipe(new NormalRecipe("cookie", 1, CookingType.Chewy, MixType.Mixed, ingredients));
        franchiseMenu.addRecipe(cookieFactory.createPersonnalizedRecipe(CookingType.Chewy, MixType.Mixed, ingredients));

        Given("^A store with orders on his order register$", () -> {
            store = new Store("A", franchiseMenu);


        });

        Given("^I have a order register with (\\d+) personalised cookies$", (Integer sum) -> {
            Order order1 = store.getOrderRegister().createNewOrder();
            Order order2 = store.getOrderRegister().createNewOrder();
            order1.addItem(franchiseMenu.getRecipes().get(0), 5, stock);
            order1.addItem(franchiseMenu.getRecipes().get(1), 2, stock); // order 1 has 2 personalized cookies
            order2.addItem(franchiseMenu.getRecipes().get(1), 3, stock); // order 2 has 3 personalized cookies

        });

        When("^I choose the stat type as number of personalised cookies$", () -> {
            stat = new SumOfCookieType();
        });

        Then("^The sum (\\d+) of personalized cookies must be returned$", (Integer sum) -> {
            int sumActual = stat.compute(store.getOrderRegister().getOrders());
            assertEquals((int) sum, sumActual);
        });

        Given("^I have a order register with (\\d+) cookies$", (Integer arg0) -> {
            Order order1 = store.getOrderRegister().createNewOrder();
            Order order2 = store.getOrderRegister().createNewOrder();
            order1.addItem(franchiseMenu.getRecipes().get(0), 5, stock);
            order1.setCollectTime(new Date());
            order2.setCollectTime(new Date());
            order2.addItem(franchiseMenu.getRecipes().get(1), 3, stock);
        });
        When("^I choose the stat type as cookies sold by day$", () -> {
            // Write code here that turns the phrase above into concrete actions
            stat = new CookiesByDay();
        });
        Then("^The sum (\\d+) of  cookies must be returned$", (Integer sum) -> {
            int sumActual = stat.compute(store.getOrderRegister().getOrders());
            assertEquals((int) sum, sumActual);
        });
        Given("^I have two stores$", () -> {
             franchise = new Franchise("COD");
            franchise.addStore("one", franchiseMenu);
            franchise.addStore("two", franchiseMenu);
            store=franchise.getStores().get(0);
            store2= franchise.getStores().get(1);

        });
        And("^Store one has sold (\\d+) personalized cookies$", (Integer arg0) -> {
            Order order1 = store.getOrderRegister().createNewOrder();

            order1.addItem(franchiseMenu.getRecipes().get(0), 5, stock);
            order1.addItem(franchiseMenu.getRecipes().get(1), 10, stock);

        });
        And("^Store two has sold (\\d+) personalized cookies$", (Integer arg0) -> {
            Order order2 = store2.getOrderRegister().createNewOrder();
            order2.addItem(franchiseMenu.getRecipes().get(1), 5, stock);
        });
        And("^I want to see the number of cookies sold for every store$", () -> {
            // Write code here that turns the phrase above into concrete actions
            stat = new SumOfCookieType();
        });
        Then("^the sum (\\d+) cookies should be returned$", (Integer arg0) -> {
            List<Order> orders = new ArrayList<>();
            for (Store store: franchise.getStores()){
                orders.addAll(store.getOrderRegister().getOrders());
            }
            stat.compute(orders);
        });


    }
}
