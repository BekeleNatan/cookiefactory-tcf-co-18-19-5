# new feature
# Tags: optional

Feature: recipe management
  as a franchise manager or a store I want to manage my recipes present in the menu


  Background:
    Given there is the franchise "COD" that have a "ATK"
    And this store have some ingredients:
      | name            | type | price | priceMargin |
      | White chocolate | Topping | 2.0 | 1.0 |
      | Plain           | Dough   | 3.0 | 1.0 |
      | Vanilla         | Flavour | 2.0 | 2.0 |
      | Milk chocolate  | Topping | 3.0 | 0.5 |
      | Reese's buttercup| Topping | 2.5 | 1.5 |
      | M&M's TM         | Topping | 2.0 | 0.5 |



  Scenario Outline: creating a recipe
    Given the rules of creation are defined
    When he add "<topping>", a "<dough>" and "<flavour>"
    And choose "<mixType>" like Mix type , "<cookingType>" like cooking type
    And name the recipe "<name>" and set its price to "<price>"
    Then the cookie is create
    Examples: ingredients

      | name | price | topping                           | dough | flavour | mixType | cookingType |
      | bonSaveur | 4.5 | White chocolate,Reese's buttercup | Plain | Vanilla    | Mixed   | Crunchy     |
      | chaudCookie | 5.5 | M&M's TM,Milk chocolate,buttercup | Plain | Vanilla    | Topped    | Chewy       |

   Scenario: define the month's recipe
     Given the store menu as create
     When the store create the recipe that was added to the menu
     Then the month's recipe is define

