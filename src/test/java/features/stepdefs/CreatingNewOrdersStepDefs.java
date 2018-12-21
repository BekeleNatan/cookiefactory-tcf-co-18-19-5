package features.stepdefs;

import cucumber.api.java8.En;
import franchise.FranchiseMenu;
import order.Order;
import order.states.Collected;
import order.states.Done;
import order.states.OnCreation;
import order.states.ToDo;
import recipe.CookingType;
import recipe.MixType;
import recipe.NormalRecipe;
import recipe.Recipe;
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

import static org.junit.Assert.assertTrue;

public class CreatingNewOrdersStepDefs implements En {
    FranchiseMenu fm = new FranchiseMenu();
    List<Ingredient> ingredients =new ArrayList<>();
    Stock stock = new Stock();
    Store store;
    Order order;
    public CreatingNewOrdersStepDefs() {
        store = new Store("store", fm);
        Ingredient oatmeal_dough = new Ingredient("Oatmeal", IngredientType.Dough, 1, 0.2);
        stock.addIngredient(oatmeal_dough, 15);
        Ingredient plain_dough = new Ingredient("Plain", IngredientType.Dough, 1, 0.2);
        stock.addIngredient(plain_dough, 15);
        Ingredient cinnamon_flavour = new Ingredient("Cinnamon", IngredientType.Flavour, 2, 0.3);
        stock.addIngredient(cinnamon_flavour, 15);
        Ingredient vanilla_flavour = new Ingredient("Vanilla", IngredientType.Flavour, 2, 0.3);
        stock.addIngredient(vanilla_flavour, 15);
        Ingredient mnms_topping = new Ingredient("MandMs", IngredientType.Topping, 1.5, 1);
        stock.addIngredient(mnms_topping, 15);
        Ingredient caramel_topping = new Ingredient("Caramel", IngredientType.Topping, 1.5, 0.8);
        stock.addIngredient(caramel_topping, 15);

        Given("^A general recipe \"([^\"]*)\" with Dough : \"([^\"]*)\", Flavour : \"([^\"]*)\", Topping : \"([^\"]*)\", Cooking : \"([^\"]*)\", Mix : \"([^\"]*)\", Price : (.+)$",
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
            order = new Order();
            order.setCurrentState(new Done(order));
        });

        And("I have paid the order", ()->{
            order.setPayed(true);
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

        Given("^I order (.+) \"([^\"]*)\" for (.+)h(.+) tomorrow with the phone number \"([^\"]*)\"$",
                (String quantity, String recipeName, String hour, String minute, String telephoneNumber) -> {
                    System.out.println(store.getStoreMenu().getMenu().size());
                    for (Recipe recipe : store.getStoreMenu().getMenu()){
                        System.out.println(((NormalRecipe)recipe).getName());
                    }
                    Recipe recipe = store.getStoreMenu().getRecipeByName(recipeName).get();
                    order = new Order();
                    order.addItem(recipe, Integer.parseInt(quantity), stock);
                    Date date = new Date();
                    if(date.getMonth()+1==13)date.setYear(date.getYear()+1);
                    if(date.getDate()+1==28)date.setMonth((date.getMonth()+1)%12);
                    date.setDate((date.getDate()%28)+1); // c'est dans le but d'avoir une date toujours correct
                    date.setHours(Integer.parseInt(hour));
                    date.setMinutes(Integer.parseInt(minute));
                    order.addInfos(date,telephoneNumber,store.getWorkingHours());
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
    }
}
