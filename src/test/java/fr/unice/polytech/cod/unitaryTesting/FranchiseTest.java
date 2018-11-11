package fr.unice.polytech.cod.unitaryTesting;

import fr.unice.polytech.cod.Franchise;
import fr.unice.polytech.cod.Store;
import org.junit.Test;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;

public class FranchiseTest {

    private Franchise franchise = new Franchise("COD");


    @Test
    public void chooseStore() {
        franchise.addStore("COD Antibes", 1.2);
        franchise.addStore("COD Nice", 1.3);
    }

    @Test
    public void getStoreByName() {
        Store theStoreAdded = franchise.addStore("COD Cannes", 1.5);
        Optional<Store> optionalStore = franchise.getStoreByName("COD Cannes");

        if(optionalStore.isPresent()){
            assertEquals(theStoreAdded, franchise.getStoreByName("COD Cannes").get());
        }else{
            throw new RuntimeException("Store do not exists");
        }
    }

    @Test
    public void addStore() {
        assertTrue(franchise.getStores().isEmpty());
        franchise.addStore("COD Antibes", 1.2);
        assertEquals(1, franchise.getStores().size());
        franchise.addStore("COD Nice", 1.3);
        assertEquals(2, franchise.getStores().size());
    }
}