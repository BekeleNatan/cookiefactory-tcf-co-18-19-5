package order;

import order.states.OnCreation;
import order.states.OrderState;
import recipe.CookingType;
import recipe.MixType;
import recipe.NormalRecipe;
import recipe.Recipe;
import recipe.ingredients.Ingredient;
import recipe.ingredients.IngredientType;
import store.Stock;
import store.Store;
import store.workinghours.WorkingHours;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderTest {
    Order order;
    Store store = new Store("store",null);
    Recipe recipe;
    Stock stock = new Stock();
    Date date = new Date();
    String phoneNumber = "062578";
    WorkingHours wo = new WorkingHours();
    @Before
    public void setup(){
        order = new Order(store);

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

      @Test
    public  void correctDeductionFromPayment(){
        order.setPrice(10.0);
        Double expected = order.getRemainToPay() -  5;

        order.deductPayedAmount(5);
        Double actual = order.getRemainToPay();
        assertEquals(expected,actual,0.001);
    }


}