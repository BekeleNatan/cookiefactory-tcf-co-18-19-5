package fr.unice.polytech.cod;

import fr.unice.polytech.cod.recipe.*;

import java.util.*;


public class Franchise {

    private List<Recipe> franchiseMenu ;
    private List<Store> stores ;
    private String name;
    private Integer storesID = 0;
    private Integer recipeId = 1;

    public Franchise(String name){
        stores = new ArrayList<>();
        franchiseMenu = new ArrayList<>();
        this.name = name;
    }
    /**
     * @param storeId
     */
    public Store chooseStore(int storeId) {
        return stores.get(storeId);
    }

    public void addRecipe(String recipeName, Dough dough, Flavour flavours, Topping topping, Cooking cooking, Mix mix, double price){
        Recipe recipe = new Recipe(recipeName, dough, flavours, topping, cooking, mix, price);
        franchiseMenu.add(recipe);

    }

    public List<Recipe> getMenu() {
        return franchiseMenu;
    }

    public Optional<Recipe> getRecipeByName(String name){
        return getMenu().stream().filter(recipe -> recipe.getName().equals(name)).findFirst();
    }

    public List<Store> getStores() {
       return stores;
    }

    public Optional<Store> getStoreByName(String name){
        return getStores().stream().filter(store -> store.getName().equals(name)).findFirst();
    }

    public void addStore(String name) {
        stores.add(new Store(this,name));

    }
}