package features.stepdefs;

import cucumber.api.java8.En;
import franchise.FranchiseMenu;
import order.Order;
import order.states.Collected;
import order.states.Done;
import org.mockito.Mockito;
import payment.CreditCard;
import payment.PaymentMethod;
import payment.PaymentMethodFactory;
import payment.creditcard.CreditCardPayment;
import payment.information.CreditCardType;
import payment.information.PaymentLocation;
import payment.information.PaymentType;
import payment.services.BankPaymentService;
import payment.services.UnfaithPassService;
import payment.unfaithpass.UnfaithPassMoney;
import payment.unfaithpass.UnfaithPassPoints;
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
        franchiseMenu = new FranchiseMenu();
        Stock stock = new Stock();
        Ingredient mnms_topping = new Ingredient("MandMs", IngredientType.Topping, 1.5, 1);
        ingredients.add(mnms_topping);
        Ingredient plain_dough = new Ingredient("Plain", IngredientType.Dough, 1, 0.2);
        ingredients.add(plain_dough);
        stock.addIngredient(mnms_topping, 10);
        stock.addIngredient(plain_dough, 15);
        Double moneyToPoint = 1.0;
        Double pointToMoney = 1.0;
        paymentMethodFactory = new PaymentMethodFactory(pointToMoney, moneyToPoint);


        Given("^A Store \"([^\"]*)\" with a recipe \"([^\"]*)\" with a price of (\\d+)$", (String StoreName, String recipeName, Integer price) -> {
            franchiseMenu.addRecipe(new NormalRecipe(recipeName, price, CookingType.Chewy, MixType.Mixed, ingredients));
        });


        Given("^I have an order with (\\d+) \"([^\"]*)\" from store \"([^\"]*)\"$", (Integer qty, String recipe, String storeName) -> {
            store = new Store(storeName, franchiseMenu);
            order = store.createOrder();
            order.addItem(franchiseMenu.getRecipes().get(0), qty, stock);
        });

        And("^I choose to pay at the counter by cash$", () -> {
            order.setPaymentLocation(PaymentLocation.COUNTER);
            order.addPaymentType(PaymentType.CASH);
            order.changeState();
            order.setCurrentState(new Done(order));
        });

        When("^I come to collect my order I should pay by cash", () -> {
            order = store.collectOrder(order.getOrderId());
            store.getCashRegister().pay(order, paymentMethodFactory.createCash(), order.getPrice());

        });

        Then("^My order should be paid and collected$", () -> {
            assertTrue(order.isPayed());
            assertTrue(order.getCurrentState() instanceof Collected);
        });


        Given("^I choose to pay at the counter using my UnFaithpass Points$", () -> {
            order.setPaymentLocation(PaymentLocation.COUNTER);
            order.addPaymentType(PaymentType.UNFAITHPASS_POINTS);
            order.changeState();
            order.setCurrentState(new Done(order));
        });

        When("^I come to collect my order I should pay with points on my UnFaithPass of qrcode \"([^\"]*)\"$", (String qrcode) -> {
            order = store.collectOrder(order.getOrderId());
            PaymentMethod paymentMethod = paymentMethodFactory.createUnfaithPassPoints(qrcode);
            UnfaithPassService unfaithPassService;
            unfaithPassService = Mockito.mock(UnfaithPassService.class);
            Mockito.when(unfaithPassService.getPointsLeft(qrcode)).thenReturn(10000.0);
            Double pointsToRemove = order.getPrice() * pointToMoney;
            Mockito.when(unfaithPassService.removePoints(qrcode,pointsToRemove)).thenReturn(Boolean.TRUE);
            ((UnfaithPassPoints) paymentMethod).setUnfaithPassService(unfaithPassService);
            store.getCashRegister().pay(order,paymentMethod , order.getRemainToPay());
        });
        And("^I choose to pay at the counter using my money on my UnfaithPass$", () -> {
            order.setPaymentLocation(PaymentLocation.COUNTER);
            order.addPaymentType(PaymentType.UNFAITHPASS_MONEY);
        });

        And("^I pay the rest by cash$", () -> {
            order.addPaymentType(PaymentType.CASH);
            order.changeState();
            order.setCurrentState(new Done(order));
        });

        When("^I come to collect my order I should pay with money on my UnFaithPass of qrcode \"([^\"]*)\" \"([^\"]*)\" of the price of the order$", (String qrcode, String proportion) -> {
            order = store.collectOrder(order.getOrderId());
            PaymentMethod paymentMethod = paymentMethodFactory.createUnfaithPassMoney(qrcode);
            UnfaithPassService unfaithPassService;
            unfaithPassService = Mockito.mock(UnfaithPassService.class);
            Mockito.when(unfaithPassService.getCashLeft(qrcode)).thenReturn(10000.0);
            Double cashToRemove = order.getPrice() * Double.parseDouble(proportion);
            Mockito.when(unfaithPassService.removeCash(qrcode,cashToRemove)).thenReturn(Boolean.TRUE);
            ((UnfaithPassMoney) paymentMethod).setUnfaithPassService(unfaithPassService);
            store.getCashRegister().pay(order,paymentMethod , cashToRemove);
        });
        And("^\"([^\"]*)\" of the price of the order by cash$", (String proportion) -> {
            PaymentMethod paymentMethod = paymentMethodFactory.createCash();
            store.getCashRegister().pay(order,paymentMethod , order.getRemainToPay());
        });
        And("^I choose to pay online using my credit card$", () -> {
            order.setPaymentLocation(PaymentLocation.ONLINE);
            order.addPaymentType(PaymentType.CREDIT_CARD);
            order.changeState();
        });
        When("^I finish my order I should pay by credit card$", () -> {
            order = store.collectOrder(order.getOrderId());
            CreditCardPayment paymentMethod = paymentMethodFactory.createCreditCard("John", "5468994925020925", "123", "12/22","");
            BankPaymentService bankPaymentService;
            bankPaymentService = Mockito.mock(BankPaymentService.class);
            paymentMethod.setBankPaymentService(bankPaymentService);
            Mockito.when(bankPaymentService.makePayment(paymentMethod.getCreditCard(),order.getRemainToPay())).thenReturn(true);

            store.getCashRegister().pay(order,paymentMethod , order.getRemainToPay());
        });
        Then("^when i come to collect my order should be paid$", () -> {
            order.setCurrentState(new Done(order));
            assertTrue(order.isPayed());
        });


    }
}
