# new feature
# Tags: optional

Feature: order creation
  as a client of cod, I want to order cookies

  Background:
    Given The store stocks are 15 of each
    And A general recipe "hardChocolate" with Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
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

  Scenario: I order something that can not be done (stock too short)
    Given The store stocks are 3 of each
    When I order 4 "hardChocolate" for 17h00 tomorrow with the phone number "0623862099"
    Then The order contains 0 items

  Scenario: I order personalized cookies along with normal ones
    Given I order 1 "hardChocolate" for 17h00 tomorrow with the phone number "0623862099"
    And I compose a "Personalized" recipe called "OurPersonalizedRecipe" with mix type "Mixed", cooking type "Chewy"
    And I add the ingredient called "Oatmeal"
    And I add the ingredient called "MandMs"
    And I add the ingredient called "Vanilla"
    And I make my recipe
    And I add 3 cookies of my recipe to the order
    When I validate my order
    Then The order is in the state toDo
    And The order contains 2 items
    And The order contains 4 cookies

  Scenario: I order correctly with discount
    Given I order 1 "hardChocolate" for 17h00 tomorrow with the phone number "0623862099"
    And I add a discount because I have already ordered 30 cookies or more
    When I validate my order
    Then The order is in the state toDo
    And The price is 3.06

