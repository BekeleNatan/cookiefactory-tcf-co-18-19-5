package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.franchise.FranchiseMenu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class StoreMenu extends Menu {
    private FranchiseMenu franchiseMenu;

    StoreMenu(FranchiseMenu franchiseMenu){
        super();
        this.franchiseMenu = franchiseMenu;
    }

    public List<Recipe> getMenu(Stock aStock) {
        // todo : STORE MENU : check availability with the stock
        // todo : STORE MENU : check if we don't have the same
        List<Recipe> storeMenu = new ArrayList<>(franchiseMenu.getMenu());
        if(!super.getRecipes().isEmpty()){
            storeMenu.addAll(super.getRecipes());
        }
        return storeMenu;
    }
}