Feature: Pay for an order
  An order can be paid online or at the counter, the customer can
  use a credit card, cash or his UnFaithPass to pay for an order.

  Background:
    Given A Store "A" with a recipe "hardChocolate" with a price of 5

  Scenario: Pay for an order using cash at the counter
    Given I have an order with 1 "hardChoclolate" from store "A"
    And I choose to pay at the counter by cash
    When I come to collect my order I should pay by cash
    Then My order should be paid and collected

  Scenario: Pay for an order using UnfaithPassPoints
    Given I have an order with 3 "hardChoclolate" from store "A"
    And I choose to pay at the counter using my UnFaithpass Points
    When I come to collect my order I should pay with points on my UnFaithPass of qrcode "QrCode"
    Then My order should be paid and collected
