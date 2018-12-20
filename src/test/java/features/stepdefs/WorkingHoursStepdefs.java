package features.stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import helpers.TestingFailureException;
import store.Store;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
                store.getWorkingHours().deleteOpeningFragement(day, opening, closing);
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
        And("^we have an order to be collected on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\"$", (String arg0, String arg1, String arg2) -> {
            throw new PendingException();
        });
    }
}
