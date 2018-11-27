package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Franchise {
    private String name;
    private ArrayList<Store> stores = new ArrayList<>();

    Franchise(String name) {
        this.name = name;
    }

    Store findStoreByName(String aStoreName) {
        // todo FRANCHISE : choose store by name
        throw new UnsupportedOperationException();
    }

    Store findStoreById(int aStoreId) {
        // todo FRANCHISE : choose store by id
        throw new UnsupportedOperationException();
    }

    public List<Store> getStores() {
        return stores;
    }
}