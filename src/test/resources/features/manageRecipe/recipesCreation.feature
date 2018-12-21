Feature: Creation of recipes
  - creation of the perfect recipe have to respect certain rules defined by the big boss.. the Chef
  and the prices of every recipe exclusive of taxes have to respect the accountable rules and the ingredient prices
  plus the margin(s) depending if it's a simple or personalized cookie.
  - The user can compose his personalized recipe if he respects the rules
  - We can have as many as same toppings as we want. (we are not limited)

  Background:
    Given a Chef that decides that we can have minimum 1 and maximum 3 toppings, minimum 0 and maximum 1 flavour, minimum 1 and maximum 1 dough
    And the accountable decides that we have to make a special margin of 2 for personalized cookies
    And now we have our rules

  Scenario: We want to create a normal recipe that respect the rules
    When we compose a "Normal" recipe called "OurNormalRecipe" with mix type "Mixed", cooking type "Chewy"
    And the price of the recipe is set to 2.0
    And we add the ingredient called "chocolatDough" of type "Dough", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "mnms" of type "Topping", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "vanilla" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And we make our recipe
    Then our recipe costs 2.0

  Scenario: We want to create a personalized recipe that respect the rules
    When we compose a "Personalized" recipe called "OurPersonalizedRecipe" with mix type "Mixed", cooking type "Chewy"
    And we add the ingredient called "chocolatDough" of type "Dough", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "mnms" of type "Topping", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "vanilla" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And we make our recipe
    Then our recipe costs 3.2

  Scenario: We want to create a normal recipe that DONT respect the rules (two flavours instead of one authorized)
    When we compose a "Normal" recipe called "OurNormalRecipe" with mix type "Mixed", cooking type "Chewy"
    And the price of the recipe is set to 2.0
    And we add the ingredient called "chocolatDough" of type "Dough", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "mnms" of type "Topping", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "vanilla" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "banana" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And a failure is beign expected
    When we make our recipe
    Then we fail making our recipe

  Scenario: We want to create a personalized recipe DONT respect the rules (two flavours instead of one authorized)
    When we compose a "Personalized" recipe called "OurPersonalizedRecipe" with mix type "Mixed", cooking type "Chewy"
    And we add the ingredient called "chocolatDough" of type "Dough", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "mnms" of type "Topping", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "vanilla" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And we add the ingredient called "banana" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2
    And a failure is beign expected
    When we make our recipe
    Then we fail making our recipe