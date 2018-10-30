# new feature
# Tags: optional
    
Feature: Creation of an order by the client

Background:
    Given La franchise "CoD" avec 20 magasins
    And La franchise contient une recette "hardChocolate" avec Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    And La franchise contient une recette "whiteChocolate" avec Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.5

Scenario: On créer correctement une commande et on la paye ( taxe non déclaré donc taxeRate = 1 )
    When Le client commande dans la boutique 1
    And Le client commande 4 fois le cookie 1
    And Le client commande 3 fois le cookie 2
    And Le client valide la commande
    And Le client paye
    Then Le prix est de 24.1
    And Le statut de la commmande est "toDo"
    And On envoie au client "payment done, the command is treated"

Scenario: On créer correctement une commande ( taxe non déclaré donc taxeRate = 1 )
    When Le client commande dans la boutique 1
    And Le client commande 4 fois le cookie 1
    And Le client commande 3 fois le cookie 2
    And Le client valide la commande
    Then Le prix est de 24.1
    And Le statut de la commmande est "toPay"

Scenario: On créer correctement une commande puis il y a un probleme de payement( taxe non déclaré donc taxeRate = 1 )
    When Le client commande dans la boutique 1
        And Le client commande 4 fois le cookie 1
        And Le client commande 3 fois le cookie 2
        And Le client valide la commande
        And Le client a un probleme de payement
        Then Le prix est de 24.1
        And Le statut de la commmande est "paymentProblem"
        And On envoie au client "payment failure, the command is canceled"

Scenario: On créer correctement une commande avec une mauvaise date( et elle passe l'ihm )
    When Le client commande dans la boutique 1
        And Le client commande 4 fois le cookie 1
        And Le client commande 3 fois le cookie 2
        And Le client rentre une date passée
        And Le client valide la commande
        And Le client paye
        Then Le prix est de 24.1
        And Le statut de la commmande est "refused"
        And On envoie au client "payment done but command canceled"


