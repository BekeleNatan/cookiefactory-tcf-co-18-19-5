Feature: Pay for an order
  An order can be paid online or at the counter, the customer can
  use a credit card, cash, his UnFaithPass ormix payment types to pay for an order.
  The customer can also collect points on his Unfaith pass after purchasing an item.

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


  Scenario: Pay for an order using UnfaithPass Money and cash at the counter
    Given I have an order with 5 "hardChoclolate" from store "A"
    And I choose to pay at the counter using my money on my UnfaithPass
    And I pay the rest by cash
    When I come to collect my order I should pay with money on my UnFaithPass of qrcode "QrCode" "0.7" of the price of the order
    And "0.3" of the price of the order by cash
    Then My order should be paid and collected

  Scenario: Pay for an order using my credit card
    Given I have an order with 4 "hardChoclolate" from store "A"
    And I choose to pay online using my credit card
    When I finish my order I should pay by credit card
    Then when i come to collect my order should be paid

  Scenario: Collect points on my Unfaithpass from my order
    Given I have an order with 7 "hardChoclolate" from store "A"
    When I pay for my order I have an Unfaithpass with qrcode "qrcode"
    Then I should be credited with some points on my Unfaitpass
