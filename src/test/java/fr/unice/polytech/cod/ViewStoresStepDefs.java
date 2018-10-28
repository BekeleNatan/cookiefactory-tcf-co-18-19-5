package fr.unice.polytech.cod;
import cucumber.api.java8.En;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ViewStoresStepDefs implements En {

    Franchise franchise = new Franchise();
    List<Store> stores = new ArrayList<>();

    public ViewStoresStepDefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)

        When("Un client veut voir les magasins ou il peut commander$", () -> {
            for (int i =0 ; i < 5 ; i++){
                franchise.addStore();
            }
            stores = franchise.getStores();
        });

        Then("^Il les trouve$", () -> {

            assertEquals(stores.size(),5);
        });
    }

}
