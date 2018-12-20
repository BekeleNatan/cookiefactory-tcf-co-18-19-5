package store;

import fr.unice.polytech.cod.Menu;
import franchise.FranchiseMenu;
import recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class StoreMenu extends Menu {
    private FranchiseMenu franchiseMenu;

    StoreMenu(FranchiseMenu franchiseMenu){
        super();
        this.franchiseMenu = franchiseMenu;
    }

    @Override
    public List<Recipe> getRecipes(){
        List<Recipe> toReturn = super.getRecipes();
        toReturn.addAll(franchiseMenu.getMenu());
        return toReturn;
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