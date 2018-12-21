Feature: Every store can manage its own menu
  - The manager can set a monthly recipe
  - The menu don't display recipes containing missing ingredient (out of stock)

  Background:
    Given "COD Antibes" a store of the franchise
    And "COD Montpellier" a store of the franchise

  Scenario: Setting the store's monthly recipe
    When the manager of the store "COD Antibes" set a new monthly recipe called "Bananana"
    Then "Bananana" is contained in the menu of "COD Antibes"
    And "Bananana" is not contained in the menu of "COD Montpellier"

  Scenario: Deleting the store's monthly recipe
    Given the manager of the store "COD Antibes" set a new monthly recipe called "Bananana"
    When the manager of the store "COD Antibes" deletes the monthly recipe called "Bananana"
    And "Bananana" is not contained in the menu of "COD Antibes"

  Scenario: The menu don't display recipes that contains ingredients out of stock
    Given "COD Antibes" have an empty stock
    When We add a recipe called "Kokoloco" to the menu of the store "COD Antibes"
    Then "Kokoloco" do not appear in the menu of "COD Antibes"