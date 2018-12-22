package features.stepdefs;

import cucumber.api.java8.En;
import franchise.FranchiseMenu;
import helpers.TestingFailureException;
import order.Item;
import order.Order;
import order.states.Collected;
import order.states.Done;
import order.states.OnCreation;
import order.states.ToDo;
import recipe.*;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;
import store.workinghours.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatingNewOrdersStepDefs implements En {
    private CookieFactory cookieFactory;
    private Recipe recipe;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private CookingType cookingType;
    private MixType mixType;
    private String recipeType;
    private String recipeName;
    FranchiseMenu fm = new FranchiseMenu();
    List<Ingredient> ingredients =new ArrayList<>();
    Stock stock = new Stock();
    Store store;
    Order order;
    public CreatingNewOrdersStepDefs() {
        cookieFactory = new CookieFactory(1, 1, 0, 1, 0, 3, 0.3);
        store = new Store("store", fm);
        Ingredient oatmeal_dough = new Ingredient("Oatmeal", IngredientType.Dough, 1, 0.2);
        Ingredient plain_dough = new Ingredient("Plain", IngredientType.Dough, 1, 0.2);
        Ingredient cinnamon_flavour = new Ingredient("Cinnamon", IngredientType.Flavour, 2, 0.3);
        Ingredient vanilla_flavour = new Ingredient("Vanilla", IngredientType.Flavour, 2, 0.3);
        Ingredient mnms_topping = new Ingredient("MandMs", IngredientType.Topping, 1.5, 1);
        Ingredient caramel_topping = new Ingredient("Caramel", IngredientType.Topping, 1.5, 0.8);

        And("^A general recipe \"([^\"]*)\" with Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
                (String recipeName, String dough, String flavour, String topping, String cooking, String mix, String price) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    List<Ingredient> ingredients = new ArrayList<>();
                    String toppings[] = topping.split(",");
                    for (String top : toppings) {
                        ingredients.add(stock.getIngredientByName(top).get());
                    }
                    ingredients.add(stock.getIngredientByName(flavour).get());
                    ingredients.add(stock.getIngredientByName(dough).get());
                    NormalRecipe nr = new NormalRecipe(recipeName, Double.parseDouble(price), CookingType.valueOf(cooking), MixType.valueOf(mix), ingredients);
                    fm.addRecipe(nr);
                });
        Given("I have a done order",()->{
            order = new Order(store);
            order.setCurrentState(new Done(order));
        });

        And("The store tax rate is (.+)",(String taxRate)->{
            store.setTaxeRate(Double.parseDouble(taxRate));
        });

        And("I have paid the order", ()->{
            order.setPayed(true);
        });

        Given("The store stocks are (.+) of each",(String stockToInt)->{
            int stockSize = Integer.parseInt(stockToInt);
            stock = new Stock();
            stock.addIngredient(oatmeal_dough, stockSize);
            stock.addIngredient(plain_dough, stockSize);
            stock.addIngredient(cinnamon_flavour, stockSize);
            stock.addIngredient(vanilla_flavour, stockSize);
            stock.addIngredient(mnms_topping, stockSize);
            stock.addIngredient(caramel_topping, stockSize);
        });

        And("^I compose a \"([^\"]*)\" recipe called \"([^\"]*)\" with mix type \"([^\"]*)\", cooking type \"([^\"]*)\"$", (String recipeType, String recipeName, String mixType, String cookingType) -> {
            this.recipeName = recipeName;
            this.recipeType = recipeType;
            this.mixType = MixType.valueOf(mixType);
            this.cookingType = CookingType.valueOf(cookingType);
        });
        And("^I add the ingredient called \"([^\"]*)\"$", (String ingredientName) -> {
            Ingredient ingredient = stock.getIngredientByName(ingredientName).get();
            this.ingredientList.add(ingredient);
        });
        And("^I make my recipe$", () -> {
            try {
                this.recipe = cookieFactory.createPersonnalizedRecipe(cookingType, mixType, ingredientList);
            } catch (IllegalArgumentException e) {
                fail();
            }
        });

        And("^A store opening every day from (.+)h(.+) to (.+)h(.+)$", (String openingHour, String openingMinutes, String closingHour, String closingMinutes) -> {
            WorkingHours workingHours = store.getWorkingHours();
            workingHours.addOpeningFragement(DayOfWeek.MONDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.TUESDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.WEDNESDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.THURSDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.FRIDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.SATURDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
            workingHours.addOpeningFragement(DayOfWeek.SUNDAY, LocalTime.of(Integer.parseInt(openingHour), Integer.parseInt(openingMinutes)), LocalTime.of(Integer.parseInt(closingHour), Integer.parseInt(closingMinutes)));
        });

        And("I add (.+) cookies of my recipe to the order",(String nbrOfCookies)->{
            this.order.addItem(recipe,Integer.parseInt(nbrOfCookies),stock);
        });

        Given("^I order (.+) \"([^\"]*)\" for (.+)h(.+) tomorrow with the phone number \"([^\"]*)\"$",
                (String quantity, String recipeName, String hour, String minute, String telephoneNumber) -> {
                    System.out.println(store.getStoreMenu().getMenu().size());
                    for (Recipe recipe : store.getStoreMenu().getMenu()){
                        System.out.println(((NormalRecipe)recipe).getName());
                    }
                    Recipe recipe = store.getStoreMenu().getRecipeByName(recipeName).get();
                    order = new Order(store);
                    order.addItem(recipe, Integer.parseInt(quantity), stock);
                    Date date = new Date();
                    if(date.getMonth()+1==13)date.setYear(date.getYear()+1);
                    if(date.getDate()+1==28)date.setMonth((date.getMonth()+1)%12);
                    date.setDate((date.getDate()%28)+1); // c'est dans le but d'avoir une date toujours correct
                    date.setHours(Integer.parseInt(hour));
                    date.setMinutes(Integer.parseInt(minute));
                    order.addInfos(date,telephoneNumber,store.getWorkingHours());
                });

        And("I add a discount because I have already ordered 30 cookies or more", ()->{
            order.setRightToVoucher(true);
        });

        When("^I validate my order$",()-> {
            order.changeState();
        });

        When("^I pay my order$",()-> {
            order.setPayed(true); //todo make the right way to pay
        });

        When("The staff gives me my order", () -> {
            order.changeState();
        });

        Then("^The order is in the state onCreation$",()-> {
            assertTrue(order.getCurrentState() instanceof OnCreation);
        });

        Then("^The order is in the state toDo$",()-> {
            assertTrue(order.getCurrentState() instanceof ToDo);
        });

        Then("^The order is in the state collected$",()-> {
            assertTrue(order.getCurrentState() instanceof Collected);
        });

        Then("^The order is paid$",()-> {
            assertTrue(order.isPayed());
        });

        Then("The order contains (.+) items", (String nbrItemsString) ->{
            int nbrItems = Integer.parseInt(nbrItemsString);
            assertEquals(nbrItems,order.getItems().size());
        });

        Then("The order contains (.+) cookies", (String nbrCookiesString) ->{
            int nbrCookies = Integer.parseInt(nbrCookiesString);
            int nbrCounted = 0;
            for(Item item : order.getItems()){
                nbrCounted += item.getQuantity();
                System.out.println(item.getQuantity());
            }
            assertEquals(nbrCookies,nbrCounted);
        });

        And("^The price is (.+)$",(String priceString)->{
            assertEquals(Double.parseDouble(priceString),order.getPrice(),0.01);
        });
    }
}
