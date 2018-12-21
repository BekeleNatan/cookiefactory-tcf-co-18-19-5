package store;

import fr.unice.polytech.cod.Menu;
import franchise.FranchiseMenu;
import recipe.CookieType;
import recipe.NormalRecipe;
import recipe.Recipe;
import recipe.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreMenu extends Menu {
    private FranchiseMenu franchiseMenu;

    StoreMenu(FranchiseMenu franchiseMenu) {
        super();
        this.franchiseMenu = franchiseMenu;
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> toReturn = super.getRecipes();
        toReturn.addAll(franchiseMenu.getMenu());
        return toReturn;
    }

    @Override
    public Optional<Recipe> getRecipeByName(String aRecipeName) {
        List<Recipe> recipes = this.getRecipes();
        for (Recipe r : recipes) {
            if (r.getCookieType().equals(CookieType.NormalRecipe)) {
                if (((NormalRecipe) r).getName().equals(aRecipeName)) {
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();
    }

    public List<Recipe> getMenu() {
        // todo : STORE MENU : check availability with the stock
        // todo : STORE MENU : check if we don't have the same
        List<Recipe> storeMenu = new ArrayList<>(franchiseMenu.getMenu());
        if (!super.getRecipes().isEmpty()) {
            storeMenu.addAll(super.getRecipes());
        }
        return storeMenu;
    }

    public List<Recipe> displayMenu(Stock aStock){
        List<Recipe> recipesToDisplay = new ArrayList<>();

        for (Recipe recipe : this.getMenu()){
            boolean toAdd = true;
            for (Ingredient ingredient : recipe.getIngredients()){
                if(!aStock.isStockContains(ingredient)){
                    toAdd = false;
                }
            }
            if(toAdd == true){
                recipesToDisplay.add(recipe);
            }
        }
        return recipesToDisplay;
    }
}