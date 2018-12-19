# new feature
# Tags: optional

Feature: recipe management
    as a franchise manager or a store I want to manage my recipes present in the menu


  Background:
    Given some ingredients exist :
     | name | type | price | priceMargin |
     | White chocolate | Topping | 2 | 1 |
     | Plain           | Dough   | 3 | 1 |
     | Vanilla         | Flavour | 2 | 2 |


  Scenario Outline: creating a recipe
  Given all the ingredients are available
  When he add "<nbrTooping>" tooping "<nbrDough>" dough "<nbrFlavour>"
  And choose "<mixType>" like Mix type , "<cookingType>" like cooking type
  Then the cookie is create
  Examples: ingredients

        | nbrTooping | nbrDough | nbrFlavour | mixType | cookingType |
        | 1          | 1        | 1          | Mixed   | Crunchy     |
        | 2          | 2        | 2          | Topped  | Chewy       |
        |            |          | 3          |         |             |

