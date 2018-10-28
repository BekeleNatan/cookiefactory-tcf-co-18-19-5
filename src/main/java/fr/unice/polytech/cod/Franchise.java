package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.Recipe;
import java.util.*;


public class Franchise {

    private Collection<Recipe> generalRecipe;
    private HashMap<Integer, Store> stores = new HashMap<>();
    private String name;
    private Integer lastID = 0;

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
        if (stores.isEmpty()) return new ArrayList<>();
        List<Store> toReturn = new ArrayList<Store>();
        Collection<Store> values = stores.values();
        for (Store store : values) {
            toReturn.add(store);
        }
        return toReturn;
    }

    public void addStore() {
        Store store = new Store();
        stores.put(lastID, store);
        lastID++;
    }
}