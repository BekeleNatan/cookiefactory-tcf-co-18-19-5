Feature: Managing the franchise general menu
  a franchise responsible can add new (normal) recipes to the general menu
  the recipes of the general menu will be available in the menu of every store

  Background:
    Given a store "COD Antibes" of the franchise "COD"
    And a store "COD Nice" of the franchise "COD"

  Scenario: a responsible of the franchise wants to add a new recipe to the general menu
    When a franchise responsible adds a new normal recipe called "SlimChap" to the general menu
    Then "SlimChap" is available in the menu of the store "COD Antibes"
    And "SlimChap" is available in the menu of the store "COD Nice"

  Scenario: a responsible of the franchise wants to delete a recipe from the general menu
    When a franchise responsible delete a recipe called "SlimChap" from the general menu
    Then "SlimChap" is not available in the menu of the store "COD Antibes"
    And "SlimChap" is not available in the menu of the store "COD Antibes"
