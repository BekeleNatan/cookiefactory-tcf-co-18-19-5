package franchise;

import fr.unice.polytech.cod.Menu;
import recipe.Recipe;

import java.util.List;

public class FranchiseMenu extends Menu {
    public FranchiseMenu() {
        super();
    }

    public List<Recipe> getMenu() {
        return super.getRecipes();
    }

}