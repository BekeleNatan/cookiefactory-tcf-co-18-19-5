package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.Recipe;
import java.util.*;


public class Franchise {

    Collection<Recipe> generalRecipe;
    HashMap<Integer,Store> stores;
    private String name;

    /**
     * @param storeId
     */
    public Store chooseStore(int storeId) {
        return stores.get(storeId);
    }

    public List<Recipe> getMenu() {
        // TODO - implement Franchise.getMenu
        throw new UnsupportedOperationException();
    }

    public List<Store> getStores() {
        // TODO - implement Franchise.getStores
        throw new UnsupportedOperationException();
    }

}