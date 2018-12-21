package features.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.FranchiseMenu;
import order.Order;
import order.states.Done;
import payment.PaymentMethodFactory;
import payment.information.PaymentLocation;
import payment.information.PaymentType;
import recipe.CookingType;
import recipe.MixType;
import recipe.NormalRecipe;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class PayOrderSteps implements En {
    Store store;
    private FranchiseMenu franchiseMenu;
    private List<Ingredient> ingredients = new ArrayList<>();
    Order order;
    PaymentMethodFactory paymentMethodFactory;


    public PayOrderSteps() {
        franchiseMenu= new FranchiseMenu();
        Stock stock =new Stock();
        Ingredient mnms_topping = new Ingredient("MandMs", IngredientType.Topping, 1.5, 1);
        ingredients.add(mnms_topping);
        Ingredient plain_dough = new Ingredient("Plain", IngredientType.Dough, 1, 0.2);
        ingredients.add(plain_dough);
        stock.addIngredient(mnms_topping,10);
        stock.addIngredient(plain_dough,15);
        paymentMethodFactory = new PaymentMethodFactory(1.0, 1.0);



        Given("^A Store \"([^\"]*)\" with a recipe \"([^\"]*)\" with a price of (\\d+)$", (String StoreName, String recipeName, Integer price) -> {
            franchiseMenu.addRecipe(new NormalRecipe(recipeName, price, CookingType.Chewy, MixType.Mixed, ingredients));
        });
        Given("^I order one \"([^\"]*)\" from store \"([^\"]*)\"$", (String arg0, String storeName) -> {
             store = new Store(storeName,franchiseMenu);
             order = store.createOrder();
             order.addItem(franchiseMenu.getRecipes().get(0),1,stock);

        });

        And("^I choose to pay at the counter$", () -> {
            order.setPaymentLocation(PaymentLocation.COUNTER);
            order.changePaymentType(PaymentType.CASH);
            order.changeState();
            order.setCurrentState(new Done(order));
        });

        Then("^I come to collect my order I should pay by cash", () -> {
            order = store.collectOrder(order.getOrderId());
            store.getCashRegister().pay(order,paymentMethodFactory.createCash(),order.getPrice());
            assertTrue(order.isPayed());
        });


    }
}
