Feature: Ingredient management
  as  a store I want to manage my ingredients

  Background:
     Given the franchise "COD" have a store "LaMer"

  Scenario Outline: add an unknown ingredient
    Given the type "<type>" of ingredient is known
    When the store of franchise  has ingredient and he doesn't know the name and price and margin price
    Then the ingredient is had to store like an unknown ingredient
    Examples:
              | type     |
              | Flavour  |
              | Dough    |
              | Topping  |

   Scenario Outline: ingredients margin management
     Given the ingredient "<ingredient>" exists and it is a "<type>"
     When he sets the new margin price of ingredient "<ingredient>" to "<marginPrice>"
     Then the ingredient's margin price is updated and the new margin price is "<marginPrice>"
     Examples:
           | ingredient       | type          | marginPrice |
           | Vanilla          | Flavour       | 0.5         |
           | Plain            | Dough         | 1.5         |
           | white chocolate  | Topping       | 2.0         |