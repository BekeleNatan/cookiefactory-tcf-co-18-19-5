package fr.unice.polytech.cod.unitaryTesting.order;

import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.WorkingHours.OpeningFragment;
import fr.unice.polytech.cod.WorkingHours.WorkingHours;
import fr.unice.polytech.cod.order.Customer;
import fr.unice.polytech.cod.order.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.State;
import fr.unice.polytech.cod.recipe.*;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
    WorkingHours workingHours;

    @Before
    public void initialisation(){
        franchise.addStore("robertCookies");
        store = franchise.chooseStore(0);
        workingHours = store.getWorkingHours();

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
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(0,00), LocalTime.of(23,59)));

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
        assertEquals(State.toPay,order.getState());
    }

    @Test
    public void testOrderCreationDateProblem() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(0,00), LocalTime.of(23,59)));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2014, 02, 12, 12, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order = orders.get(0);
        assertEquals(0,(int)order.getID());
        assertEquals(53.64,order.getPrice(),0);
        assertEquals(State.refused,order.getState());
    }

    @Test
    public void testOrderCreationDate_NotOpenProblem() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        WorkingHours workingHours = store.getWorkingHours();
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(10,00), LocalTime.of(23,59)));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 02, 10, 7, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order1 = orders.get(0);

        assertEquals(0,(int)order1.getID());
        assertEquals(53.64,order1.getPrice(),0);
        assertEquals(State.refused,order1.getState());
    }

    @Test
    public void testOrderCreationDate_NotOpenProblem2() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        WorkingHours workingHours = store.getWorkingHours();
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(10,00), LocalTime.of(23,59)));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 02, 16, 9, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order1 = orders.get(0);

        assertEquals(0,(int)order1.getID());
        assertEquals(53.64,order1.getPrice(),0);
        assertEquals(State.refused,order1.getState());
    }

    @Test
    public void testOrderCreationDate_Open() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        WorkingHours workingHours = store.getWorkingHours();
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(10,00), LocalTime.of(23,59)));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 02, 16, 12, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "0623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order1 = orders.get(0);

        assertEquals(0,(int)order1.getID());
        assertEquals(53.64,order1.getPrice(),0);
        assertEquals(State.toPay,order1.getState());
    }

    @Test
    public void testOrderCreationDate_BadPhoneNumber() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        WorkingHours workingHours = store.getWorkingHours();
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(8,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(10,00), LocalTime.of(23,59)));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 02, 16, 12, 15, 01);
        Date date = cal.getTime(); // get back a Date object

        store.takeOrder(items, date, "a623862099",false);
        List<Order> orders = store.getOrders();         // no payement but store

        assertEquals(1,orders.size());
        Order order1 = orders.get(0);

        assertEquals(0,(int)order1.getID());
        assertEquals(53.64,order1.getPrice(),0);
        assertEquals(State.refused,order1.getState());
    }

    @Test
    public void testOrderCancelBeforePayment() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(0,00), LocalTime.of(23,59)));

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
        assertEquals(State.toPay,order.getState());


        assertEquals("La command a été annulé",order.cancelOrder());
        assertEquals(State.refused,order.getState());
    }

    @Test
    public void testOrderCancelBeforePaymentErrorSendingMessage() {
        List<Item> items = new ArrayList<>();
        items.add(item1);items.add(item2);items.add(item3);items.add(item4);
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.THURSDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(0,00), LocalTime.of(23,59)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(0,00), LocalTime.of(23,59)));

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
        assertEquals(State.toPay,order.getState());
        
        Customer customer = mock(Customer.class);
        when(customer.sendMessage("Votre command a été annulé")).thenReturn(false);
        when(customer.getPhoneNumber()).thenReturn("0623862099");
        order.setCustomer(customer);


        assertEquals("La command a été annulé, mais on a pas pu le prévenir au : 0623862099",order.cancelOrder());
        assertEquals(State.refused,order.getState());
    }

}