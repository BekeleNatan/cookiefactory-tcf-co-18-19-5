package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.recipe.CookingType;
import fr.unice.polytech.cod.recipe.MixType;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OnCreationTest {

    OrderState onCreation;          // OrderState type because manipulate as OrderState in the code
    Order order = new Order();
    Stock stock;
    Recipe recipe;
    WorkingHours wo;
    Date date = new Date();
    Ingredient ing1;
    Ingredient ing2;
    Ingredient ing3;
    Ingredient ing4;

    @Before
    public void setup(){
        onCreation = new OnCreation(order);
        stock = Mockito.mock(Stock.class);
        recipe = Mockito.mock(Recipe.class);
        wo = Mockito.mock(WorkingHours.class);

        List<Ingredient> ingredients = new ArrayList<>();
        ing1 = new Ingredient("ing1", IngredientType.Dough, 1, 0.2);
        ing2 = new Ingredient("ing2", IngredientType.Flavour, 2, 0.3);
        ing3 = new Ingredient("ing3", IngredientType.Topping, 1.5, 1);
        ing4 = new Ingredient("ing4", IngredientType.Topping, 1.5, 0.8);
        ingredients.add(ing1);ingredients.add(ing2);ingredients.add(ing3);ingredients.add(ing4);
        Mockito.when(recipe.getIngredients()).thenReturn(ingredients);

        Mockito.when(stock.isEnough(ing1,1)).thenReturn(true);
        Mockito.when(stock.isEnough(ing2,1)).thenReturn(true);
        Mockito.when(stock.isEnough(ing3,1)).thenReturn(true);
        Mockito.when(stock.isEnough(ing4,1)).thenReturn(true);

        date.setHours(date.getHours()+3);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Instant instant = Instant.ofEpochMilli(calendar.getTimeInMillis());
        LocalTime localTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
        Mockito.when(wo.isOpenOn(DayOfWeek.of(dayOfTheWeek),localTime)).thenReturn(true);
    }

    //  *************************************
    //              NEXT STATE              *
    //  *************************************

    @Test
    public void nextStateTooEarly() {
        assertFalse(onCreation.nextState());
        assertTrue(order.getCurrentState() instanceof OnCreation);
    }

    @Test
    public void nextStateWithoutInfo(){
        onCreation.addItem(recipe,1, stock);
        assertFalse(onCreation.nextState());
        assertTrue(order.getCurrentState() instanceof OnCreation);
    }

    @Test
    public void nextStateWithoutItem(){
        onCreation.addInfos(date, "0623862099",wo);
        assertFalse(onCreation.nextState());
        assertTrue(order.getCurrentState() instanceof OnCreation);
    }

    @Test
    public void nextStateCorrect(){
        onCreation.addItem(recipe,1, stock);
        onCreation.addInfos(date, "0623862099",wo);
        assertTrue(onCreation.nextState());
        assertFalse(order.getCurrentState() instanceof OnCreation);
        assertTrue(order.getCurrentState() instanceof ToDo);
    }

    //  *************************************
    //              REFUSE STATE              *
    //  *************************************
    @Test
    public void refuseStateDefault(){
        onCreation.refuseState();
        assertFalse(order.getCurrentState() instanceof OnCreation);
        assertTrue(order.getCurrentState() instanceof Refused);
    }

    //  *************************************
    //              ADD ITEM                *
    //  *************************************

    @Test
    public void addItemCorrect() {
        Stock realStock = new Stock();
        realStock.addIngredient(ing1, 10);
        realStock.addIngredient(ing2, 1);
        realStock.addIngredient(ing3, 10);
        realStock.addIngredient(ing4, 10);
        assertTrue(onCreation.addItem(recipe,1, realStock));
        assertTrue(realStock.getIngredientByName("ing1").isPresent());
        assertFalse(realStock.getIngredientByName("ing2").isPresent());
    }

    @Test
    public void addSeveralItemsCorrect() {
        Stock realStock = new Stock();
        realStock.addIngredient(ing1, 10);
        realStock.addIngredient(ing2, 4);
        realStock.addIngredient(ing3, 10);
        realStock.addIngredient(ing4, 10);
        assertTrue(onCreation.addItem(recipe,4, realStock));
        assertTrue(realStock.getIngredientByName("ing1").isPresent());
        assertFalse(realStock.getIngredientByName("ing2").isPresent());
    }

    @Test
    public void addItemNotInStock() {
        Stock realStock = new Stock();
        realStock.addIngredient(ing1, 10);
        realStock.addIngredient(ing2, 4);
        realStock.addIngredient(ing3, 1);
        realStock.addIngredient(ing4, 10);
        assertFalse(onCreation.addItem(recipe,2, realStock));
        assertTrue(realStock.getIngredientByName("ing2").isPresent());
        assertTrue(realStock.getIngredientByName("ing3").isPresent());
    }

    @Test
    public void addItemRecipeSameToppingTwice() {
        Stock realStock = new Stock();
        realStock.addIngredient(ing1, 10);
        realStock.addIngredient(ing2, 4);
        realStock.addIngredient(ing3, 2);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ing1);ingredients.add(ing2);ingredients.add(ing3);ingredients.add(ing3);
        Recipe recipe = new NormalRecipe("recipe",12.04, CookingType.Crunchy, MixType.Mixed,ingredients);
        assertTrue(onCreation.addItem(recipe,1, realStock));
        assertTrue(realStock.getIngredientByName("ing2").isPresent());
        assertFalse(realStock.getIngredientByName("ing3").isPresent());
    }

    @Test
    public void addItemRecipeSameToppingTwiceNotInStock() {
        Stock realStock = new Stock();
        realStock.addIngredient(ing1, 10);
        realStock.addIngredient(ing2, 4);
        realStock.addIngredient(ing3, 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ing1);ingredients.add(ing2);ingredients.add(ing3);ingredients.add(ing3);
        Recipe recipe = new NormalRecipe("recipe",12.04, CookingType.Crunchy, MixType.Mixed,ingredients);
        assertFalse(onCreation.addItem(recipe,1, realStock));
        assertTrue(realStock.getIngredientByName("ing2").isPresent());
        assertTrue(realStock.getIngredientByName("ing3").isPresent());
    }

    @Test
    public void addItemBadArgs() {
        Stock realStock = new Stock();
        assertFalse(onCreation.addItem(null,1, stock));
        assertFalse(onCreation.addItem(recipe,0, stock));
        assertFalse(onCreation.addItem(recipe,-1, stock));
        assertFalse(onCreation.addItem(recipe,1, null));
    }


    @Test
    public void addInfos() {
    }
}