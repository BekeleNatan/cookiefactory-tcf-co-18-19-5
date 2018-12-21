Feature: Pay for an order
  An order can be paid online or at the counter, the customer can
  use a credit card, cash or his UnFaithPass to pay for an order.

  Background:
    Given A Store "A" with a recipe "hardChocolate" with a price of 5


  Scenario: Pay for an order using cash at the counter
    Given I order one "hardChocolate" from store "A"
    And I choose to pay at the counter
    Then I come to collect my order I should pay by cash
