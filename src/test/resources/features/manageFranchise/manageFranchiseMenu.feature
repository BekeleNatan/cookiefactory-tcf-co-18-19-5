Feature: Managing the franchise general menu
  a franchise responsible can add new (normal) recipes to the general menu
  the recipes of the general menu will be available in the menu of every store

  Background:
    Given "COD", a franchise
    And a store "COD Antibes" of the franchise "COD"
    And a store "COD Nice" of the franchise "COD"
    And a franchise responsible adds a new normal recipe called "choocaloco" to the general menu

  Scenario: a responsible of the franchise wants to add a new recipe to the general menu
    When a franchise responsible adds a new normal recipe called "SlimChap" to the general menu
    Then "SlimChap" is available in the menu of the store "COD Antibes"
    And "SlimChap" is available in the menu of the store "COD Nice"

  Scenario: a responsible of the franchise wants to delete a recipe from the general menu
    When a franchise responsible delete a recipe called "choocaloco" from the general menu
    Then "choocaloco" is not available in the menu of the store "COD Antibes"
    And "choocaloco" is not available in the menu of the store "COD Antibes"
