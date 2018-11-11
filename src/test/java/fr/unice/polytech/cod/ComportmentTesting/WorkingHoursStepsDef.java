package fr.unice.polytech.cod.ComportmentTesting;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

public class WorkingHoursStepsDef {
    private Franchise franchise;
    private Store store;


    public class MyExceptions {
        private boolean expectException;
        private List<RuntimeException> exceptions = new ArrayList<>();

        void expectException() {
            expectException = true;
        }

        void add(RuntimeException e) {
            if (!expectException) {
                throw e;
            }
            exceptions.add(e);
        }
    }

    private MyExceptions myExceptions = new MyExceptions();

    @Given("^\"([^\"]*)\" is a franchise$")
    public void isAFranchise(String name) {
        System.out.println("wh : isAFranchise");
        this.franchise = new Franchise(name);
    }


    @When("^we add a new opening fragment to the store \"([^\"]*)\" on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\" to \"([^\"]*)\":\"([^\"]*)\"$")
    public void weAddANewOpeningFragmentToTheStoreOnFromTo(String storeName, String openingDay, String openingHour, String openingMinutes, String closingHour, String closingMinutes) {
        System.out.println("wh : we add new opening fragement to the store on from to");
        this.store = getStoreByName(storeName);
        DayOfWeek day = fromStringToDayOfWeek(openingDay);
        LocalTime opening = LocalTime.of(parseInt(openingHour), parseInt(openingMinutes));
        LocalTime closing = LocalTime.of(parseInt(closingHour), parseInt(closingMinutes));
        try {
            store.getWorkingHours().addOpeningFragement(day, opening, closing);
        } catch (RuntimeException e) {
            myExceptions.add(e);
        }
    }


    private DayOfWeek fromStringToDayOfWeek(String day) {
        System.out.println("wh : fromStringToDayOfWeek");

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

    private Store getStoreByName(String storeName) {
        System.out.println("wh : getStoreByName");

        Optional<Store> optionalStore = franchise.getStoreByName(storeName);
        if (optionalStore.isPresent()) {
            return optionalStore.get();
        } else {
            throw new RuntimeException("Store not found");
        }
    }


    @Then("^the store \"([^\"]*)\" is( not?)* open on \"([^\"]*)\" at \"([^\"]*)\":\"([^\"]*)\"$")
    public void theStoreIsOpenOnAt(String storeName, String not, String openingDay, String candidatHour, String candidatMinutes) {
        System.out.println("wh : the store is open on at");

        this.store = getStoreByName(storeName);

        DayOfWeek day = fromStringToDayOfWeek(openingDay);
        LocalTime opening = LocalTime.of(parseInt(candidatHour), parseInt(candidatMinutes));

        if (not == null) {
            assertTrue(this.store.getWorkingHours().isOpenOn(day, opening));
        } else {
            assertFalse(this.store.getWorkingHours().isOpenOn(day, opening));
        }

    }


    @Then("a failure is expected")
    public void a_failure_is_expected() {
        System.out.println("wh : failure is expected");

        myExceptions.expectException();
    }

    @Then("it fails")
    public void it_fails() {
        System.out.println("wh : it fails");
        assertTrue(myExceptions.exceptions.size() != 0);
    }
}
