package features.stepdefs.manageStore;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import store.Store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class manageUnfaithPassConversionTableStepDefs implements En {
    private Store store;
    private Franchise franchise;

    public manageUnfaithPassConversionTableStepDefs() {


        When("^the manager of the store \"([^\"]*)\" set the points to money rate at (\\d+.\\d+)$", (String storeName, String newPointsToMoneyRate) -> {
            this.store = franchise.getStoreByName(storeName).get();
            this.store.setPointsToMoneyRate(Double.parseDouble(newPointsToMoneyRate));
        });
        Then("^the points to money rate of the store \"([^\"]*)\" is set to (\\d+.\\d+)$", (String storeName, String newPointsToMoneyRate) -> {
            this.store = franchise.getStoreByName(storeName).get();
            assertEquals(Double.parseDouble(newPointsToMoneyRate), store.getPointsToMoneyRate(), 0.00001);
        });

        When("^the manager of the store \"([^\"]*)\" set the money to points rate at (\\d+.\\d+)$", (String storeName, String newMoneyToPointsRate) -> {
            this.store = franchise.getStoreByName(storeName).get();
            this.store.setMoneyToPointsRate(Double.parseDouble(newMoneyToPointsRate));
        });
        Then("^the money to points rate of the store \"([^\"]*)\" is set to (\\d+.\\d+)$", (String storeName, String newMoneyToPointsRate) -> {
            this.store = franchise.getStoreByName(storeName).get();
            assertEquals(Double.parseDouble(newMoneyToPointsRate), store.getMoneyToPointsRate(), 0.00001);
        });
        Given("^a store \"([^\"]*)\" of the franchise$", (String storeName) -> {
            franchise = new Franchise("COD");
            FranchiseMenu franchiseMenu = new FranchiseMenu();
            franchise.addStore(storeName, franchiseMenu);
            store = franchise.getStoreByName(storeName).get();
        });
    }
}

