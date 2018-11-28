package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.franchise.FranchiseMenu;
import fr.unice.polytech.cod.recipe.NormalRecipe;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class StoreMenu extends Menu {
    private NormalRecipe monthlyRecipe;
    private FranchiseMenu franchiseMenu;

    StoreMenu(FranchiseMenu franchiseMenu){
        super();
        this.monthlyRecipe = null;
        this.franchiseMenu = franchiseMenu;
    }

    public void setMonthlyRecipe(NormalRecipe aRecipe) {
        this.monthlyRecipe = aRecipe;
    }

    public void deleteMonthlyRecipe() {
        this.monthlyRecipe = null;
    }

    public List<Recipe> getMenu(Stock aStock) {
        // todo : STORE MENU : check availability with the stock
        List<Recipe> storeMenu = new ArrayList<>(franchiseMenu.getMenu());
        if(monthlyRecipe != null){
            storeMenu.add(monthlyRecipe);
        }
        return storeMenu;
    }

}