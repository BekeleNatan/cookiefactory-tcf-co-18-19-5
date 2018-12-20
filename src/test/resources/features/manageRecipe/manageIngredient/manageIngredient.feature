Feature: Ingredient management
  as  a store I want to manage my ingredients


  Scenario Outline: add an unknown ingredient
    Given the type "<type>" of ingredient is known
    When the store "<store>" of franchise "<franchise>" has ingredient and he doesn't know the name and price and margin price
    Then the ingredient is had to store like an unknown ingredient
    Examples:
              | type    | store | franchise |
              | Flavour | ATK   | COD       |
              | Dough   | LeRoix| Coin      |
              | Topping | LaMer | bonChaud  |