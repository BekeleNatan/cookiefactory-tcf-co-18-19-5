package features.stepdefs.manageStore;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import franchise.Franchise;
import franchise.FranchiseMenu;
import store.Store;
import static org.junit.Assert.assertEquals;

public class StoreTaxesStepdefs implements En {
    private Franchise franchise;
    private Store store;

    public StoreTaxesStepdefs() {
        Given("^\"([^\"]*)\" store of the franchise$", (String storeName) -> {
            franchise = new Franchise("COD");
            FranchiseMenu franchiseMenu = new FranchiseMenu();
            franchise.addStore(storeName, franchiseMenu);
            store = franchise.getStoreByName(storeName).get();
        });

        When("^the manager of the store \"([^\"]*)\" set the taxe rate to (\\d+.\\d+)$", (String storeName, String taxeRate) -> {
            this.store.setTaxeRate(Double.parseDouble(taxeRate));
        });
        Then("^the taxe rate of the store \"([^\"]*)\" is set to (\\d+.\\d+)$", (String storeName, String taxeRate) -> {
            assertEquals(Double.parseDouble(taxeRate), store.getTaxeRate(), 0.0001);
        });

    }
}
