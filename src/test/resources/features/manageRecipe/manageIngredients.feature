Feature: Managing ingredients
  for every ingredient, we can modifiy its price, and its margin.

  Background:
    Given an ingredient called "chocolatDough" of type "Dough", the price of the ingredient is 0.2 and the price margin 0.2

  Scenario: changing the price exclusive of taxes
    When the manager changes the price exclusive of taxes of the ingredient to 0.3
    Then the total price of the ingredient is 0.5

  Scenario: changing the margin price of an ingredient
    When the manager changes the price margin of the ingredient to 0.1
    Then the total price of the ingredient is 0.3
