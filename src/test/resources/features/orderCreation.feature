# new feature
# Tags: optional

Feature: Creation of an order by the client

  Background:
    Given A franchise with the name "The Cookie Factory"
    And The franchise creates a store named "The Cookie Factory Antibes" and its taxe rate is : "1.2"
    And The manager of the store "The Cookie Factory Antibes" was told by the franchise to add a recipe named "HardChocolate" to his store, this are the ingredients : Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", and the price is fixed to "3.4"
    And The manager of the store "The Cookie Factory Antibes" was told by the franchise to add a recipe named "WhiteChocolate" to his store, this are the ingredients : Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", and the price is fixed to "3.5"

  Scenario: On créer correctement une commande et on la paye
    When Le client commande dans la boutique "The Cookie Factory Antibes"
    And Le client commande 4 fois le cookie "HardChocolate"
    And Le client commande 6 fois le cookie "WhiteChocolate"
    Then Le prix est de 24.1
    And Le client met son numéro de téléphone : "0623862099"
    And Le client valide la commande
    And Le client paye
    And Le statut de la commmande est "toDo"
    And On envoie au client "payment done, the command is treated"
    And La commande est stockee

  Scenario: On créer correctement une commande ( taxe non déclaré donc taxeRate = 1 )
    When Le client commande dans la boutique "The Cookie Factory Antibes"
    And Le client commande 3 fois le cookie "HardChocolate"
    And Le client commande 4 fois le cookie "WhiteChocolate"
    And Le client met son numéro de téléphone : "0623862099"
    Then Le prix est de 24.1
    And Le client valide la commande
    And Le statut de la commmande est "toPay"

  Scenario: On créer correctement une commande avec loyalty programme( taxeRate = 1.2 )
    When Le client commande dans la boutique "The Cookie Factory Antibes"
    And Le client commande 3 fois le cookie "HardChocolate"
    And Le client commande 4 fois le cookie "WhiteChocolate"
    And Le magasin a mis ses taxes a 1.5
    And Le client a le droit a une remise fidelite
    And Le client valide la commande
    Then Le prix est de 26.02
    And Le statut de la commmande est "toPay"

  Scenario: On créer correctement une commande puis il y a un probleme de payement( taxe non déclaré donc taxeRate = 1 )
    When Le client commande dans la boutique "The Cookie Factory Antibes"
    And Le client commande 3 fois le cookie "HardChocolate"
    And Le client commande 4 fois le cookie "WhiteChocolate"
    And Le client valide la commande
    And Le client a un probleme de payement
    Then Le prix est de 24.1
    And Le statut de la commmande est "paymentProblem"
    And On envoie au client "payment failure, the command is canceled"
    And La commande est stockee

  Scenario: On créer correctement une commande avec une mauvaise date( et elle passe l'ihm )
    When Le client commande dans la boutique "The Cookie Factory Antibes"
    And Le client commande 3 fois le cookie "HardChocolate"
    And Le client commande 4 fois le cookie "WhiteChocolate"
    And Le client rentre une date passée
    And Le client valide la commande
    And Le client paye
    Then Le prix est de 24.1
    And Le statut de la commmande est "refused"
    And On envoie au client "payment done but command canceled"
    And La commande est stockee


