package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Franchise {
    private String name;
    private List<Store> stores;

    Franchise(String name) {
        stores = new ArrayList<>();
        this.name = name;
    }

    public void addStore(String name){
        stores.add(new Store(name));
    }

    public void removeStore(String name) {
        stores.removeIf(store -> store.getName().equals(name));
    }
    public Optional<Store> getStoreByName(String name){
        return getStores().stream().filter(store -> store.getName().equals(name)).findFirst();
    }


    public List<Store> getStores() {
        return stores;
    }
}