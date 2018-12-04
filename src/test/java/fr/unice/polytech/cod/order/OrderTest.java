package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.order.states.OnCreation;
import fr.unice.polytech.cod.order.states.OrderState;
import fr.unice.polytech.cod.recipe.CookingType;
import fr.unice.polytech.cod.recipe.MixType;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.recipe.ingredients.Ingredient;
import fr.unice.polytech.cod.recipe.ingredients.IngredientType;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.workinghours.WorkingHours;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderTest {
    Order order;
    Recipe recipe;
    Stock stock = new Stock();
    Date date = new Date();
    String phoneNumber = "062578";
    WorkingHours wo = new WorkingHours();
    @Before
    public void setup(){
        order = new Order();

        OrderState mock = Mockito.mock(OrderState.class);
        Mockito.when(mock.addInfos(date,phoneNumber,wo)).thenReturn(true);
        Mockito.when(mock.nextState()).thenReturn(true);

        Ingredient ingredient = new Ingredient("ing", IngredientType.Topping,1,1);
        recipe = new NormalRecipe("name",1, CookingType.Crunchy, MixType.Mixed,new ArrayList<Ingredient>(Collections.singleton(ingredient)));
        Mockito.when(mock.addItem(recipe,5,stock)).thenReturn(true);


        order.setCurrentState(mock);
    }

    @Test(expected = NullPointerException.class)
    public void constructorNull(){
        OrderState os = new OnCreation(null);
    }
    //Test that it appeal the orderStateEachTime
    @Test
    public void addItem() {
        assertTrue(order.addItem(recipe,5,stock));
    }
    @Test
    public void addInfos() {
        assertTrue(order.addInfos(date,phoneNumber,wo));
    }
    @Test
    public void changeState(){
        assertTrue(order.changeState());
    }
}