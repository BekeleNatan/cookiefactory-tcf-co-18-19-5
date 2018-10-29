package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.*;

import java.util.*;


public class Franchise {

    private Collection<Recipe> generalRecipe = new HashSet<>();
    private HashMap<Integer, Store> stores = new HashMap<>();
    private String name;
    private Integer lastID = 0;

    /**
     * @param storeId
     */
    public Store chooseStore(int storeId) {
        return stores.get(storeId);
    }

    public void addRecipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price){
        Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix, price);
        generalRecipe.add(recipe);
    }

    public List<Recipe> getMenu() {
        List<Recipe> toReturn = new ArrayList<>();
        toReturn.addAll(generalRecipe);
        if (toReturn==null){
            return new ArrayList<>();
        }
        return toReturn;
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

    public void addStore(String name) {
        Store store = new Store(this,name);
        stores.put(lastID, store);
        lastID++;
    }
}