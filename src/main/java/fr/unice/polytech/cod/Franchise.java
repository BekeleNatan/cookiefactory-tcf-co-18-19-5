package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.*;

import java.util.*;


public class Franchise {

    private List<Store> stores ;
    private String name;
    private int storesID = 0;
    private int recipeId = 1;

    public Franchise(String name){
        stores = new ArrayList<>();
        this.name = name;
    }

    // ok, stays public
    public Store chooseStore(int storeId) {
        return stores.get(storeId);
    }

    // ok, stays public
    public List<Store> getStores() {
       return stores;
    }

    // ok, stays public (but why Optional)
    public Optional<Store> getStoreByName(String name){
        return getStores().stream().filter(store -> store.getName().equals(name)).findFirst();
    }

    // ok, stays public.
    public Store addStore(String name, double taxeRate) {
        Store theNewStore = new Store(name, taxeRate);
        stores.add(theNewStore);
        return theNewStore;
    }

    public void addRecipeToStore(Recipe recipe){
        stores.forEach(store -> store.addRecipteToMenu(recipe));
    }
}