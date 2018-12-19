# new feature
# Tags: optional
    
Feature: recipe management
    as a franchise manager or a store I want to create my recipes in a consistent way

Scenario Outline: creating a normal cookie
    Given all the ingredients are available
    When he add "<nbrTooping>" tooping "<nbrDough>" dough "<nbrFlavour">
    And choose "<mixType>" like Mix type , "<cookingType>" like cooking type
    Then the cookie is create
Examples: ingredients

        | nbrTooping | nbrDough | nbrFlavour | mixType | cookingType |
        | 1          | 1        | 1          | Mixed   | Crunchy     |
        | 2          | 2        | 2          | Topped  | Chewy       |

