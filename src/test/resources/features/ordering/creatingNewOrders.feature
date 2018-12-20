# new feature
# Tags: optional

Feature: order creation
  as a client of cod, I want to order cookies

  Background:
    Given Une recette generale "hardChocolate" avec Dough : "Plain", Flavour : "Vanilla", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.4
    And Une recette generale "whiteChocolate" avec Dough : "Oatmeal", Flavour : "Cinnamon", Topping : "MandMs", Cooking : "Crunchy", Mix : "Mixed", Price : 3.5
    And Un magasin ouvert tous les jours de 8h00 à 18h00

  Scenario: Je ne peux pas commander en dehors des heures d'ouvertures
    Given Je commande 1 "hardChocolate" pour 19h00 avec le numéro de téléphone "0623862099"
    When Je valide ma commande
    Then La commande est dans l'état onCreation
