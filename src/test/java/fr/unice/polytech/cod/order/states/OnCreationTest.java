package fr.unice.polytech.cod.order.states;

import fr.unice.polytech.cod.order.Order;
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

    @Before
    public void setup(){
        onCreation = new OnCreation(order);
        stock = Mockito.mock(Stock.class);
        recipe = Mockito.mock(Recipe.class);
        wo = Mockito.mock(WorkingHours.class);

        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ing1 = new Ingredient("ing1", IngredientType.Dough, 1, 0.2);
        Ingredient ing2 = new Ingredient("ing2", IngredientType.Flavour, 2, 0.3);
        Ingredient ing3 = new Ingredient("ing3", IngredientType.Topping, 1.5, 1);
        Ingredient ing4 = new Ingredient("ing4", IngredientType.Topping, 1.5, 0.8);
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

    @Test
    public void addItem() {
    }

    @Test
    public void addInfos() {
    }
}