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

        Given("^Une franchise contenant (\\d+) magasins$",
                (Integer nbrStore) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    for (int i =0 ; i < nbrStore ; i++){
                        franchise.addStore();
                    }
                });

        When("Un client veut voir les magasins ou il peut commander$", () -> {
            stores = franchise.getStores();
        });

        Then("^Il en trouve (\\d+)$", (Integer nbrStore) -> {
            assertEquals((int)nbrStore ,stores.size());
        });
    }

}
