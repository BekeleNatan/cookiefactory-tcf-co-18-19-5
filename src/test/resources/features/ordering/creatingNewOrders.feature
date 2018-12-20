# new feature
# Tags: optional

Feature: order creation
  as a client of cod, I want to order cookies

  Background:
    Given A general recipe "hardChocolate" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    And A general recipe "whiteChocolate" with Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.5
    And A store opening every day from 8h00 to 18h00

  Scenario: I can't order outside the opening hours
    Given I order 1 "hardChocolate" for 19h00 with the phone number "0623862099"
    When I validate my order
    Then The order is in the state onCreation
