package fr.unice.polytech.cod.ComportmentTesting;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import fr.unice.polytech.cod.WorkingHours.OpeningFragment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        this.franchise = new Franchise(name);
    }

    @And("^\"([^\"]*)\" is a one store of the franchise$")
    public void isAOneStoreOfTheFranchise(String name) {
       this.franchise.addStore(name);
    }


    @When("^we add a new opening fragment to the store \"([^\"]*)\" on \"([^\"]*)\" from \"([^\"]*)\":\"([^\"]*)\" to \"([^\"]*)\":\"([^\"]*)\"$")
    public void weAddANewOpeningFragmentToTheStoreOnFromTo(String storeName, String openingDay, String openingHour, String openingMinutes, String closingHour, String closingMinutes)  {
        this.store = getStoreByName(this.franchise, storeName.toLowerCase());

        if (this.store == null){
            throw new PendingException("store not found");
        }
        DayOfWeek day = fromStringToDayOfWeek(openingDay);
        LocalTime opening = LocalTime.of(parseInt(openingHour), parseInt(openingMinutes));
        LocalTime closing = LocalTime.of(parseInt(closingHour), parseInt(closingMinutes));
        try {
            OpeningFragment of = new OpeningFragment(day, opening, closing);
            store.getWorkingHours().addOpeningFragement(of);
        } catch (RuntimeException e) {
            myExceptions.add(e);
        }
    }



    private DayOfWeek fromStringToDayOfWeek(String day){
        switch (day.toLowerCase()){
            case "monday" : return DayOfWeek.MONDAY;
            case "tuesday" : return DayOfWeek.TUESDAY;
            case "wednesday" : return DayOfWeek.WEDNESDAY;
            case "thursday" : return DayOfWeek.THURSDAY;
            case "friday" : return DayOfWeek.FRIDAY;
            case "saturday" : return DayOfWeek.SATURDAY;
            case "sunday" : return DayOfWeek.SUNDAY;
            default: return null;
        }
    }
    private Store getStoreByName(Franchise franchise, String name){
        for(Store store : franchise.getStores()){
            if (store.getName().toLowerCase().equals(name.toLowerCase())) {
                return store;
            }
        }
        return null;
    }


    @Then("^the store \"([^\"]*)\" is( not?)* open on \"([^\"]*)\" at \"([^\"]*)\":\"([^\"]*)\"$")
    public void theStoreIsOpenOnAt(String storeName, String not, String openingDay, String candidatHour, String candidatMinutes)  {
        this.store = getStoreByName(this.franchise, storeName);
        if (this.store == null){
            throw new PendingException("store not found");
        }
        DayOfWeek day = fromStringToDayOfWeek(openingDay);
        LocalTime opening = LocalTime.of(parseInt(candidatHour), parseInt(candidatMinutes));
        if(not == null){
            assertTrue(this.store.getWorkingHours().isOpenOn(day, opening));
        }else{
            assertFalse(this.store.getWorkingHours().isOpenOn(day, opening));
        }

    }


    @Then("a failure is expected")
    public void a_failure_is_expected() {
        myExceptions.expectException();
    }

    @Then("it fails")
    public void it_fails() {
        assertTrue(myExceptions.exceptions.size() != 0);
    }
}
