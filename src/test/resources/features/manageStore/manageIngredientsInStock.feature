Feature: Every store can manage the ingredients in their stock
  - We can add ingredients and their quantity
    - if the ingredient is already in the stock, we sum the quantities
    - if the ingredient is not in the stock, we add it with the quantity entered
  - We can remove ingredients from the stock (if we use them to prepare cookie or else..)
    - if the remain quantity is 0, we remove the ingredient from our list
    - if the remain quantity is more than 0, we change the actual quantity by the remaining quantity

  Background:
    Given an empty stock of a store
    And we add 50 ingredient called "banana" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2 to the stock

  Scenario: getting the quantiy of an existing ingredient in the stock
    Then the quantity of "banana" is 50

  Scenario: adding the quantiy of an existing ingredient in the stock
    When we add 50 ingredient called "banana" of type "Flavour", the price of the ingredient is 0.2 and the price margin 0.2 to the stock
    Then the ingredient "banana" do exist in the stock
    And the quantity of "banana" is 100

  Scenario: removing a quantiy of an existing ingredient in the stock
    When we remove 1 ingredient called "banana"
    Then the quantity of "banana" is 49

  Scenario: removing all the quantity of an ingredient (so he do not exist no more on the stock)
    When we remove 50 ingredient called "banana"
    Then the ingredient "banana" do not exist in the stock