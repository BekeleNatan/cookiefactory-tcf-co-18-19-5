package features.stepdefs.manageStore;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import helpers.TestingFailureException;
import order.Order;
import org.mockito.Mockito;
import store.Store;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorkingHoursStepdefs implements En {

    private Store store = null;
    private Franchise franchise;
    private TestingFailureException testingFailureException;

    private DayOfWeek fromStringToDayOfWeek(String day) {
        switch (day.toLowerCase()) {
            case "monday":
                return DayOfWeek.MONDAY;
            case "tuesday":
                return DayOfWeek.TUESDAY;
            case "wednesday":
                return DayOfWeek.WEDNESDAY;
            case "thursday":
                return DayOfWeek.THURSDAY;
            case "friday":
                return DayOfWeek.FRIDAY;
            case "saturday":
                return DayOfWeek.SATURDAY;
            case "sunday":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }

    private Store getStoreByName(Franchise franchise, String name) {
        for (Store store : franchise.getStores()) {
            if (store.getName().toLowerCase().equals(name.toLowerCase())) {
                return store;
            }
        }
        return null;
    }

    public WorkingHoursStepdefs() {
        // ok
        Given("^a store named \"([^\"]*)\" of the franchise$", (String storeName) -> {
            franchise = new Franchise("COD");
            FranchiseMenu franchiseMenu = new FranchiseMenu();
            franchise.addStore(storeName, franchiseMenu);
            store = franchise.getStoreByName(storeName).get();
        });

        // ok
        Then("^the store \"([^\"]*)\" is( not?)* open on \"([^\"]*)\" at \"([^\"]*)\":\"([^\"]*)\"$", (String storeName, String not, String openingDay, String candidatHour, String candidatMinutes) -> {
            DayOfWeek day = fromStringToDayOfWeek(openingDay);
            LocalTime opening = LocalTime.of(parseInt(candidatHour), parseInt(candidatMinutes));
            if (not == null) {
                assertTrue(this.store.getWorkingHours().isOpenOn(day, opening));
            } else {
                assertFalse(this.store.getWorkingHours().isOpenOn(day, opening));
            }
        });

        // ok
        Then("^a failure is expected$", () -> {
            testingFailureException = new TestingFailureException();
            testingFailureException.expectException();
        });

        // ok
        And("^no failure is expected$", () -> {
            testingFailureException = new TestingFailureException();
        });

        // ok
        And("^it fails$", () -> {
            assertTrue(testingFailureException.aFailureIsDetected());
        });

        // ok
        When("^the manager of the store \"([^\"]*)\" deletes an opening hours on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\" to \"([^\"]*)\":\"([^\"]*)\"$", (String storeName, String openingDay, String fromHH, String fromMM, String toHH, String toMM) -> {
            this.store = franchise.getStoreByName(storeName).get();
            DayOfWeek day = fromStringToDayOfWeek(openingDay);
            LocalTime opening = LocalTime.of(parseInt(fromHH), parseInt(fromMM));
            LocalTime closing = LocalTime.of(parseInt(toHH), parseInt(toMM));
            try {
                store.getWorkingHours().deleteOpeningFragement(day, opening, closing,store.getOrderRegister());
            } catch (RuntimeException e) {
                testingFailureException.add(e);
            }
        });

        // ok
        And("^the manager of the store \"([^\"]*)\" adds a new opening hours to the store on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\" to \"([^\"]*)\":\"([^\"]*)\"$", (String storeName, String openingDay, String fromHH, String fromMM, String toHH, String toMM) -> {
            this.store = franchise.getStoreByName(storeName).get();
            DayOfWeek day = fromStringToDayOfWeek(openingDay);
            LocalTime opening = LocalTime.of(parseInt(fromHH), parseInt(fromMM));
            LocalTime closing = LocalTime.of(parseInt(toHH), parseInt(toMM));
            try {
                store.getWorkingHours().addOpeningFragement(day, opening, closing);
            } catch (RuntimeException e) {
                testingFailureException.add(e);
            }
        });

        // ok
        Given("^we have an order to be collected on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\"$", (String day_of_the_week, String hours, String minutes) -> {
            Order o = store.getOrderRegister().createNewOrder(store);
            DayOfWeek day = fromStringToDayOfWeek(day_of_the_week);
            LocalTime collect_time = LocalTime.of(parseInt(hours), parseInt(minutes));
            Date date = Mockito.mock(Date.class);
            Mockito.when(date.getHours()).thenReturn(parseInt(hours));
            Mockito.when(date.getMinutes()).thenReturn(parseInt(minutes));

            int dayOfTheWeek = fromStringToDayOfWeek(day_of_the_week).getValue();
            if (dayOfTheWeek==0)dayOfTheWeek=7;
            Mockito.when(date.getDay()).thenReturn(dayOfTheWeek);

            o.setCollectTime(date);
        });
    }
}
