package fr.unice.polytech.cod.franchise;

import fr.unice.polytech.cod.Menu;
import fr.unice.polytech.cod.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FranchiseMenu extends Menu {
    public FranchiseMenu() {
        super();
    }

    public List<Recipe> getMenu() {
        return super.getRecipes();
    }

}