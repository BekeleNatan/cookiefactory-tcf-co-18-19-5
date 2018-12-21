package features.stepdefs.manageFranchise;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import store.Store;

import static org.junit.Assert.assertTrue;

public class ManageFranchiseStoresStepdefs implements En {
    Franchise franchise;
    FranchiseMenu franchiseMenu = new FranchiseMenu();

    public ManageFranchiseStoresStepdefs() {
        Given("^a franchise named \"([^\"]*)\"$", (String franchiseName) -> {
            franchise = new Franchise(franchiseName);
        });
        When("^the manager adds a store named \"([^\"]*)\" to the franchise \"([^\"]*)\"$", (String storeName, String franchiseName) -> {
            franchise.addStore(storeName, franchiseMenu);
        });
        Then("^The store exists in the system and we can get it by searching \"([^\"]*)\"$", (String storeName) -> {
            assertTrue(franchise.getStoreByName(storeName).isPresent());
        });
        Given("^the franchise store named \"([^\"]*)\"$", (String storeName) -> {
            franchise.addStore(storeName, franchiseMenu);
        });
        When("^the manager deletes the store named \"([^\"]*)\" from the system$", (String storeName) -> {
            if (franchise.getStoreByName(storeName).isPresent()) {
                franchise.removeStore(storeName);
            }
        });
        Then("^the store \"([^\"]*)\" do not exist on the system$", (String storeName) -> {
            assertTrue(!franchise.getStoreByName(storeName).isPresent());
        });
    }
}
