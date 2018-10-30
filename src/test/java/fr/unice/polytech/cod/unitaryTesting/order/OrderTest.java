package fr.unice.polytech.cod.unitaryTesting.order;

import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.State;
import fr.unice.polytech.cod.recipe.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {
    Franchise franchise = new Franchise("Cod");
    Store store;
    Recipe recipe1;
    Recipe recipe2;
    Recipe recipe3;
    Recipe recipe4;
    Item item1;
    Item item2;
    Item item3;
    Item item4;

    @Before
    public void initialisation(){
        franchise.addStore("robertCookies");
        store = franchise.chooseStore(0);
        store.setTaxeRate(1.2);
        recipe1 = new Recipe("cookie1", Dough.Chocolate, Flavour.Chili, Topping.MandMs, Cooking.Chewy,Mix.Mixed,9.5);
        recipe2 = new Recipe("cookie2", Dough.Oatmeal, Flavour.Chili, Topping.ReesesButtercup, Cooking.Chewy,Mix.Topped,3.4);
        recipe3 = new Recipe("cookie3", Dough.Plain, Flavour.Cinnamon, Topping.MilkChocolate, Cooking.Crunchy,Mix.Mixed,2.4);
        recipe4 = new Recipe("cookie4", Dough.Chocolate, Flavour.Vanilla, Topping.MandMs, Cooking.Crunchy,Mix.Topped,2.1);
        item1 = new Item(recipe1,1);
        item2 = new Item(recipe2,4);
        item3 = new Item(recipe3,2);
        item4 = new Item(recipe4,8);
    }

    @Test
    public void testOrderCreationBasic() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        Date date = new Date();
        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order = orders.get(0);
        assertEquals(0,(int)order.getID());
        assertEquals(53.64,order.getPrice(),0);
        assertEquals(State.toPay,order.getState());
    }

    @Test
    public void testOrderCreationDateProblem() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 02, 12, 12, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order = orders.get(0);
        assertEquals(0,(int)order.getID());
        assertEquals(53.64,order.getPrice(),0);
        assertEquals(State.refused,order.getState());
    }
}