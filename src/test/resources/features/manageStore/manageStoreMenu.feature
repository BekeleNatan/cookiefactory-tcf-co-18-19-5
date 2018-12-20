Feature: Every store can manage its own menu
  - The manager can set a monthly recipe
  - The menu don't display recipes containing missing ingredient (out of stock)

  Background:
    Given a store "COD Antibes" of the franchise

  Scenario: Setting the store's monthly recipe
    When the manager of the store "COD Antibes" set a new monthly recipe called "Bananana"
    Then "Bananana" is the monthly recipe
    And "Bananana" do appear in the store's menu

  Scenario: Deleting the store's monthly recipe
    When the manager of the store "COD Antibes" deletes the monthly recipe called "Bananana"
    Then There's no monthly recipe
    And "Bananana" do not appear in the store's menu

  Scenario: The menu don't display recipes that contains ingredients out of stock
    Given the store "COD Antibes" has the following list of ingredients in its stock

    Then There's no monthly recipe
    And "Bananana" do not appear in the store's menu
