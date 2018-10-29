package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.*;

import java.util.*;


public class Franchise {

    private HashMap<Integer,Recipe> generalRecipe = new HashMap<>();
    private HashMap<Integer, Store> stores = new HashMap<>();
    private String name;
    private Integer storesID = 0;
    private Integer recipeId = 1;

    /**
     * @param storeId
     */
    public Store chooseStore(int storeId) {
        return stores.get(storeId);
    }

    public void addRecipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price){
        Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix, price);
        generalRecipe.put(recipeId,recipe);
        recipeId++;
    }

    public HashMap<Integer,Recipe> getMenu() {
        return generalRecipe;
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
        stores.put(storesID, store);
        storesID++;
    }
}