
Feature: Manage recipes
  This features concerns the  creation, modifications or deletion of a recipe.

  Background:
    Given A franchise with the name "The Cookie Factory"
    And The franchise creates a store named "The Cookie Factory Antibes" and its taxe rate is : "1.2"

  Scenario: Creation of a recipe by the franchise
    When The manager of the store "The Cookie Factory Antibes" was told by the franchise to add a recipe named "Chocolalala" to his store, this are the ingredients : Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", and the price is fixed to "3.4"
    Then The store "The Cookie Factory Antibes" menu has 1 recipe(s)

  Scenario: Creation of the monthly recipe by the store
    When "The Cookie Factory Antibes" creates a recipeOfTheMonth named "hardChocolate" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.1
    Then The recipeOfTheMonth is now "hardChocolate"

  Scenario: Modification of price of a recipe
    When The manager of "The Cookie Factory Antibes" want to change the price of the "RandomRecipe" to 4
    Then The price of the recipe "RandomRecipe" in the store "The Cookie Factory Antibes" is 4
    When The manager of "The Cookie Factory Antibes" want to change the price of the "RandomRecipe" to 5
    Then The price of the recipe "RandomRecipe" in the store "The Cookie Factory Antibes" is 5


  Scenario: Removal of a recipe from the franchiseMenu
    When "The Cookie Factory" wants to remove the recipe "Chocolalala" from their franchiseMenu
    Then The menu of the store "The Cookie Factory Antibes" is now empty





