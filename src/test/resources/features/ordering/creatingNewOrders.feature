# new feature
# Tags: optional

Feature: order creation
  as a client of cod, I want to order cookies

  Background:
    Given A general recipe "hardChocolate" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    And A general recipe "whiteChocolate" with Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.5
    And A store opening every day from 8h00 to 18h00

  Scenario: I can't order outside the opening hours
    Given I order 1 "hardChocolate" for 19h00 tomorrow with the phone number "0623862099"
    When I validate my order
    Then The order is in the state onCreation

  Scenario: I come to get my cookies
    Given I have a done order
    And I have paid the order
    When The staff gives me my order
    Then The order is in the state collected

  Scenario: I know an order is paid
    Given I order 1 "hardChocolate" for 19h00 tomorrow with the phone number "0623862099"
    When I pay my order
    Then The order is paid

  Scenario: I order correctly without account
    Given I order 1 "hardChocolate" for 17h00 tomorrow with the phone number "0623862099"
    When I validate my order
    Then The order is in the state toDo
