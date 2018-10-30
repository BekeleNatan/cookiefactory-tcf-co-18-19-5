
Feature: Manage recipes
  This features concerns the  creation, modifications or deletion of a recipe.

  Background:
    Given A franchise with the name "The Cookie Factory"
    And The franchiseMenu is empty
    And The franchise owns a store named "The Cookie Factory Antibes"

  Scenario: Creation of a recipe by the franchise
    When "The Cookie Factory" creates a recipe named "Chocolalala" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    Then The franchiseMenu has one recipe
    And The recipe name is "Chocolalala"


  Scenario: Creation of a recipe by the store
    When "The Cookie Factory Antibes" creates a recipeOfTheMonth named "hardChocolate" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.1
    Then The recipeOfTheMonth is now "hardChocolate"

  Scenario: Modification of price of a recipe
    When "The Cookie Factory Antibes" changes the price of the recipe "hardChocolate" from 3.1 to 4
    Then The recipeOfTheMonth's price is now 4

  Scenario: Removal of a recipe from the franchiseMenu
    When "The Cookie Factory" wants to remove the recipe "Chocolalala" from their franchiseMenu
    Then The franchiseMenu is now empty





